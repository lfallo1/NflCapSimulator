package com.salarycap.dal.serviceimpl.rosteractions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.constants.RosterHelper;
import com.salarycap.constants.TransactionType;
import com.salarycap.dal.ContractOverviewDal;
import com.salarycap.dal.DeadMoneyDal;
import com.salarycap.dal.PlayerDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dal.service.TransactionService;
import com.salarycap.dto.ContractDto;
import com.salarycap.dto.ExtensionDtoWrapper;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.DeadMoney;
import com.salarycap.resources.Transaction;
import com.salarycap.resources.YearlyContract;
@Scope("session")
@Service
public class ExtensionService {
	private TransactionService transactionService;
	private YearlyContractDal yearlyContractDal;
	private PlayerDal playerDal;
	private DeadMoneyDal deadMoneyDal;
	private List<YearlyContract> originalContracts;
	private ContractManagementService contractManagementService;
	private ContractOverviewDal contractOverviewDal;
	
	@Autowired
	public ExtensionService(TransactionService transactionService, YearlyContractDal yearlyContractDal, 
			ContractOverviewDal contractOverviewDal, PlayerDal playerDal, DeadMoneyDal deadMoneyDal, 
			ContractManagementService contractManagementService){
		this.transactionService = transactionService;
		this.yearlyContractDal = yearlyContractDal;
		this.contractOverviewDal = contractOverviewDal;
		this.playerDal = playerDal;
		this.deadMoneyDal = deadMoneyDal;
		this.contractManagementService = contractManagementService;
	}
	
	public void doExtension(ExtensionDtoWrapper dto){
		originalContracts = new ArrayList<>();
		ContractOverview originalContractOverview = contractManagementService.createContractOverview(contractOverviewDal.getByPlayer(dto.getPlayerId()));
		List<DeadMoney> originalDeadMoney = deadMoneyDal.getByPlayer(dto.getPlayerId());
		List<YearlyContract> newContracts = new ArrayList<>();
		List<YearlyContract> contractsToUpdate = getUpdateYearsList(dto.getPlayerId(), dto.getYear());
		Double originalCapCharge = contractsToUpdate.get(0).getCapCharge();
		for(int i = 0; i < dto.getTotalYears(); i++){
			if(i<dto.getTotalYears()-dto.getAdditionalYears()){
				updateExistingContract(dto.getContractDtoList().get(i), contractsToUpdate.get(i), dto.getSigningBonus(), dto.getTotalYears());
			}
			else{
				newContracts.add(createNewContract(contractsToUpdate.get(0), dto.getYear() + i, dto.getPlayerId(), dto.getContractDtoList().get(i), ((Double)dto.getSigningBonus()/dto.getTotalYears())));
			}
		}
		calculateDeadMoney(newContracts);
		addNewContractsToDal(newContracts);
		ContractOverview contractOverview = contractManagementService.generateContractOverview(yearlyContractDal.getByPlayer(dto.getPlayerId()));
		updateFreeAgentContract(contractOverview);
		this.transactionService.push(new Transaction(TransactionType.OTHER, "Extension", 0.0, 0.0, dto.getYear(), newContracts.get(0).getTeam(), playerDal.getByPlayerId(dto.getPlayerId()), this.originalContracts, originalDeadMoney, originalContractOverview));
	}

	private void updateFreeAgentContract(ContractOverview contractOverview) {
		YearlyContract freeAgentContract = contractManagementService.createFreeAgentContract(contractOverview);
		yearlyContractDal.add(freeAgentContract);
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

	private YearlyContract updateExistingContract(ContractDto extensionDto,
			YearlyContract contractToUpdate, Double newSigningBonus, Integer totalYears) {
		Double newBaseSalary = extensionDto.getBaseSalary();
		Double newGuaranteedP5 = extensionDto.getGuaranteedBaseSalary();
		Double addlSigningBonus = newSigningBonus/totalYears;
		contractToUpdate.setBaseSalary(newBaseSalary);
		contractToUpdate.setGuaranteedBaseSalary(newGuaranteedP5);
		contractToUpdate.setSigningBonus(contractToUpdate.getSigningBonus() + addlSigningBonus);
		contractToUpdate.setCapCharge(newBaseSalary + contractToUpdate.getSigningBonus() + contractToUpdate.getOptionBonus());
		return contractToUpdate;
	}

	/**
	 * Generate list of contract representing contracts that exist for
	 * the given player, and need to be updated based on the restructure.
	 * @param dto
	 * @return
	 */
	private List<YearlyContract> getUpdateYearsList(Integer playerId, Integer currentYear) {
		List<YearlyContract> contracts = new ArrayList<>();
		for (YearlyContract contract : yearlyContractDal.getByPlayer(playerId)) {
			this.originalContracts.add(createNewContract(contract));
			if(contract.getStatus().contains("Contract") && contract.getYear()>=currentYear){
				contracts.add(contract);
			}
			else if(RosterHelper.FA_LIST.contains(contract.getStatus())){
				yearlyContractDal.remove(contract.getId());
			}
		}
		return contracts;
	}
	
	private YearlyContract createNewContract(YearlyContract c) {
		return new YearlyContract(c.getId(), c.getPlayer(), c.getTeam(), c.getYear(), c.getBaseSalary(),
				c.getCapCharge(), c.getCapSavings(), c.getDeadMoney(), c.getGuaranteedBaseSalary(),
				c.getNotes(), c.getOptionBonus(), c.getSigningBonus(), c.getRosterBonus(),
				c.getWorkoutBonus(), c.getTeamName(), c.getRole(), c.getStatus(), c.getPosition());
	}	
	
	/**
	 * Overload version to accept specific values
	 * of ContractDto object
	 * @param year
	 * @param playerId
	 * @param extensionDto
	 * @param signingBonus
	 * @return
	 */
	private YearlyContract createNewContract(YearlyContract templateContract, Integer year, Integer playerId, ContractDto extensionDto, Double signingBonus) {
		Double capCharge = extensionDto.getBaseSalary() + signingBonus;
		
		return new YearlyContract(yearlyContractDal.getLastId(), playerDal.getByPlayerId(playerId), templateContract.getTeam(),
				year, extensionDto.getBaseSalary(), capCharge, 0.0, 0.0, extensionDto.getGuaranteedBaseSalary(),
				"", 0.0, signingBonus, 0.0, 0.0, templateContract.getTeamName(), templateContract.getRole(),
				templateContract.getStatus(), templateContract.getPosition());
	}		
	
	private void addNewContractsToDal(List<YearlyContract> newContracts) {
		for (int i = 0; i < newContracts.size(); i++) {
			yearlyContractDal.add(newContracts.get(i));
		}
	}	
}
