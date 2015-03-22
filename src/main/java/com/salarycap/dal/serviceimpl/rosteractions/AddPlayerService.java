package com.salarycap.dal.serviceimpl.rosteractions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.constants.TransactionType;
import com.salarycap.dal.PlayerDal;
import com.salarycap.dal.PositionDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dal.service.TeamService;
import com.salarycap.dal.service.TransactionService;
import com.salarycap.dto.ContractDto;
import com.salarycap.dto.NewPlayerWrapper;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.Player;
import com.salarycap.resources.Transaction;
import com.salarycap.resources.YearlyContract;
@Scope("session")
@Service
public class AddPlayerService {
	
	private TeamService teamService;
	private TransactionService transactionService;
	private PositionDal positionDal;
	private YearlyContractDal yearlyContractDal;
	private PlayerDal playerDal;
	private ContractManagementService contractManagementService;
	
	@Autowired
	public AddPlayerService(TeamService teamService, PlayerDal playerDal, 
			TransactionService transactionService, YearlyContractDal yearlyContractDal, 
			PositionDal positionDal, ContractManagementService contractManagementService){
		this.teamService = teamService;
		this.playerDal = playerDal;
		this.transactionService = transactionService;
		this.yearlyContractDal = yearlyContractDal;
		this.positionDal = positionDal;
		this.contractManagementService = contractManagementService;
	}		
	
	public void addPlayer(NewPlayerWrapper newPlayerWrapper){
		Player player = createPlayer(newPlayerWrapper);
		Double signingBonus = generateSigningBonus(newPlayerWrapper.getSigningBonus(), newPlayerWrapper.getContractDtoList().size());
		List<YearlyContract> contractList = new ArrayList<>();
		List<ContractDto> dtoList = newPlayerWrapper.getContractDtoList();
		for (int i = 0; i < dtoList.size(); i++) {
			Double sb = i < 5 ? signingBonus : 0.0; 
			Double capCharge = dtoList.get(i).getBaseSalary() + sb;
			contractList.add(createNewContract(newPlayerWrapper, newPlayerWrapper.getYear() + i, player, 
					dtoList.get(i).getBaseSalary(), dtoList.get(i).getGuaranteedBaseSalary(),
					sb, capCharge, newPlayerWrapper.getTeamId()));
		}
		calculateDeadMoney(contractList);
		yearlyContractDal.addAll(contractList);
		ContractOverview contractOverview = contractManagementService.generateContractOverview(contractList);
		updateFreeAgentContract(contractOverview);
		transactionService.push(new Transaction(TransactionType.ADD_PLAYER, "Added to Roster", 0.0, 0.0, newPlayerWrapper.getYear(),
				newPlayerWrapper.getTeamId(), player, null, null, contractOverview));
	}
	
	private void updateFreeAgentContract(ContractOverview contractOverview) {
		YearlyContract freeAgentContract = contractManagementService.createFreeAgentContract(contractOverview);
		yearlyContractDal.add(freeAgentContract);
	}	
	
	private Double generateSigningBonus(Double signingBonus, int size) {
		return size > 5 ? signingBonus / 5 : signingBonus / size;
	}

	@SuppressWarnings("deprecation")
	private Player createPlayer(NewPlayerWrapper newPlayerWrapper) {
		Player player = new Player(playerDal.getLastId(), new LocalDate().getYear() - newPlayerWrapper.getAccrued(), new Date(1, 1, 1900),
				newPlayerWrapper.getName(), "", "", 0,0,0,"", newPlayerWrapper.getTeamId(),
				0);
		playerDal.add(player);
		return player;
	}

	private void calculateDeadMoney(List<YearlyContract> contracts) {
		for (int i = 0; i < contracts.size(); i++) {
			YearlyContract c = contracts.get(i);
			Double deadMoney = 0.0;
			for (int j = i; j < contracts.size(); j++) {
				YearlyContract tmp = contracts.get(j);
				deadMoney += tmp.getGuaranteedBaseSalary() + tmp.getSigningBonus(); 
			}
			c.setDeadMoney(deadMoney);
			c.setCapSavings(c.getCapCharge() - c.getDeadMoney());
		}
	}	
	
	private YearlyContract createNewContract(NewPlayerWrapper newPlayerWrapper, Integer year, Player player, Double baseSalary, Double guaranteedBaseSalary, Double signingBonus, Double capCharge, Integer teamId) {
		return new YearlyContract(yearlyContractDal.getLastId(), player, teamId, year, baseSalary,
				capCharge, 0.0, 0.0, guaranteedBaseSalary, "", 0.0, signingBonus, 0.0, 0.0, 
				teamService.getById(teamId).getName(), positionDal.getByName(newPlayerWrapper.getPosition()).getRole(), 
				"Contract", newPlayerWrapper.getPosition());
	}		
}
