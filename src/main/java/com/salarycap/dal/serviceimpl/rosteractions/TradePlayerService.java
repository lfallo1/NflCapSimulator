package com.salarycap.dal.serviceimpl.rosteractions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.constants.TransactionType;
import com.salarycap.dal.ContractOverviewDal;
import com.salarycap.dal.DeadMoneyDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dal.service.TeamService;
import com.salarycap.dal.service.TransactionService;
import com.salarycap.dal.serviceimpl.rosteractions.utils.RosterActionUtilities;
import com.salarycap.dto.TradePlayerDto;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.DeadMoney;
import com.salarycap.resources.Player;
import com.salarycap.resources.Transaction;
import com.salarycap.resources.YearlyContract;

@Scope("session")
@Service
public class TradePlayerService {
	private TransactionService transactionService;
	private YearlyContractDal yearlyContractDal;
	private ContractOverviewDal contractOverviewDal;
	private DeadMoneyDal deadMoneyDal;
	private ContractManagementService contractManagementService;
	private TeamService teamService;
	
	private Double oldTeamDeadMoney = 0.0;
	private Double oldTeamCapSavings = 0.0;
	private Integer oldTeamId = null;
	private Player player = null;
	
	@Autowired
	public TradePlayerService(ContractManagementService contractManagementService,
			TransactionService transactionService, YearlyContractDal contractDal, 
			ContractOverviewDal contractOverviewDal, DeadMoneyDal deadMoneyDal,
			TeamService teamService){
		this.transactionService = transactionService;
		this.yearlyContractDal = contractDal;
		this.deadMoneyDal = deadMoneyDal;
		this.contractOverviewDal = contractOverviewDal;
		this.contractManagementService = contractManagementService;
		this.teamService = teamService;
	}		
	
	public void doTrade(TradePlayerDto tradePlayerDto){
		List<YearlyContract> allPlayerContracts = yearlyContractDal.getByPlayer(tradePlayerDto.getPlayerId());
		YearlyContract templateContract = yearlyContractDal.getByContractId(tradePlayerDto.getContractId());
		generateOldTeamValues(templateContract);
		ContractOverview overview = contractOverviewDal.getByPlayer(tradePlayerDto.getPlayerId());
		List<DeadMoney> originalDeadMoney = deadMoneyDal.getByPlayer(templateContract.getPlayer().getId());
		List<YearlyContract> originalContracts = new ArrayList<>();
		List<YearlyContract> newTeamContracts = new ArrayList<>();
		RosterActionUtilities.addDeadMoney(this.deadMoneyDal, templateContract, this.yearlyContractDal, templateContract.getDeadMoney(), templateContract.getYear(), templateContract.getPlayer());
		for (YearlyContract c : allPlayerContracts) {
			originalContracts.add(RosterActionUtilities.createNewContract(c));
			if(c.getYear() >= templateContract.getYear()){
				addContractToNewTeam(tradePlayerDto, newTeamContracts, c);
			}
		}
		calculateDeadMoney(newTeamContracts);
		transactionService.push(new Transaction(TransactionType.OTHER, "Trade", oldTeamCapSavings, oldTeamDeadMoney, templateContract.getYear(), oldTeamId, player, originalContracts, originalDeadMoney, overview));
	}

	private void generateOldTeamValues(YearlyContract templateContract) {
		oldTeamId = templateContract.getTeam();
		oldTeamDeadMoney = templateContract.getDeadMoney();
		oldTeamCapSavings = templateContract.getCapSavings();
		player = templateContract.getPlayer();
	}

	/**
	 * Add contracts to the new team, following rules of trade as in CBA.
	 * These rules state that only a players base salary & guaranteed bonus
	 * are "transferred" to the new team. NOTE: Roster/Workout bonuses may
	 * also be transferred depending when those bonuses were do & whether 
	 * the team trading the player had already paid out the money. But for
	 * the sake of this simulator, it will be assumed that ALL bonus money
	 * is the responsibility of the team trading the player
	 * @param tradePlayerDto
	 * @param newTeamContracts
	 * @param c
	 */
	private void addContractToNewTeam(TradePlayerDto tradePlayerDto,
			List<YearlyContract> newTeamContracts, YearlyContract c) {
		c.setTeam(tradePlayerDto.getNewTeamId());
		c.setTeamName(teamService.getById(tradePlayerDto.getNewTeamId()).getName());
		c.setSigningBonus(0.0);
		c.setOptionBonus(0.0);
		c.setRosterBonus(0.0);
		c.setWorkoutBonus(0.0);
		c.setCapCharge(c.getBaseSalary());
		newTeamContracts.add(c);
	}
	
	private void calculateDeadMoney(List<YearlyContract> contracts) {
		for (int i = 0; i < contracts.size(); i++) {
			YearlyContract c = contracts.get(i);
			Double deadMoney = 0.0;
			for (int j = i; j < contracts.size(); j++) {
				YearlyContract tmp = contracts.get(j);
				deadMoney += tmp.getGuaranteedBaseSalary() + tmp.getSigningBonus() + tmp.getOptionBonus(); 
			}
			c.setDeadMoney(deadMoney);
			c.setCapSavings(c.getCapCharge() - c.getDeadMoney());
		}
	}	
}
