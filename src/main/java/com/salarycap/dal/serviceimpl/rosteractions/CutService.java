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
import com.salarycap.dal.service.TransactionService;
import com.salarycap.dal.serviceimpl.rosteractions.utils.RosterActionUtilities;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.DeadMoney;
import com.salarycap.resources.Transaction;
import com.salarycap.resources.YearlyContract;
@Scope("session")
@Service
public class CutService {
	
	private TransactionService transactionService;
	private YearlyContractDal yearlyContractDal;
	private ContractOverviewDal contractOverviewDal;
	private DeadMoneyDal deadMoneyDal;
	private ContractManagementService contractManagementService;
	
	@Autowired
	public CutService(ContractManagementService contractManagementService,
			TransactionService transactionService, YearlyContractDal contractDal, 
			ContractOverviewDal contractOverviewDal, DeadMoneyDal deadMoneyDal){
		this.transactionService = transactionService;
		this.yearlyContractDal = contractDal;
		this.deadMoneyDal = deadMoneyDal;
		this.contractOverviewDal = contractOverviewDal;
		this.contractManagementService = contractManagementService;
	}		
	
	public void cut(Integer contractId){
		YearlyContract contract = yearlyContractDal.getByContractId(contractId);
		ContractOverview originalContractOverview = contractOverviewDal.getByPlayer(contract.getPlayer().getId());
		List<DeadMoney> originalDeadMoney = deadMoneyDal.getByPlayer(contract.getPlayer().getId());
		Double oldTeamSalary = contractManagementService.calculateTotalSalary("Offseason", contract.getYear(), contract.getTeam());
		Double deadMoney = 0.0;
		List<YearlyContract> originalContracts = new ArrayList<>();
		for(YearlyContract c : yearlyContractDal.getByPlayer(contract.getPlayer().getId())){
			originalContracts.add(RosterActionUtilities.createNewContract(c));
			if(c.getYear() >= contract.getYear()){
				deadMoney += c.getSigningBonus() + c.getOptionBonus() + c.getGuaranteedBaseSalary();
				yearlyContractDal.remove(c.getId());
			}
		}
		RosterActionUtilities.addDeadMoney(this.deadMoneyDal, contract, this.yearlyContractDal, deadMoney, contract.getYear(), contract.getPlayer());
		Double newTeamSalary = contractManagementService.calculateTotalSalary("Offseason", contract.getYear(), contract.getTeam());
		transactionService.push(new Transaction(TransactionType.OTHER, "Cut", oldTeamSalary - newTeamSalary, deadMoney, contract.getYear(), contract.getTeam(), contract.getPlayer(), originalContracts, originalDeadMoney, originalContractOverview));		
	}
	
	public void june1Cut(Integer contractId){
		YearlyContract contract = yearlyContractDal.getByContractId(contractId);
		ContractOverview originalContractOverview = contractOverviewDal.getByPlayer(contract.getPlayer().getId());
		List<DeadMoney> originalDeadMoney = deadMoneyDal.getByPlayer(contract.getPlayer().getId());
		Double oldTeamSalary = contractManagementService.calculateTotalSalary("Offseason", contract.getYear(), contract.getTeam());
		Double deadMoneyYearOne = 0.0;
		Double deadMoneyAfterYearOne = 0.0;
		List<YearlyContract> originalContracts = new ArrayList<>();
		for(YearlyContract c : yearlyContractDal.getByPlayer(contract.getPlayer().getId())){
			originalContracts.add(RosterActionUtilities.createNewContract(c));
			if(c.getId().equals(contractId)){
				deadMoneyYearOne += c.getSigningBonus() + c.getOptionBonus() + c.getGuaranteedBaseSalary();
				yearlyContractDal.remove(c.getId());
			}
			else if(c.getYear() > contract.getYear()){
				deadMoneyAfterYearOne += c.getSigningBonus() + c.getOptionBonus() + c.getGuaranteedBaseSalary();
				yearlyContractDal.remove(c.getId());				
			}
		}
		RosterActionUtilities.addDeadMoney(this.deadMoneyDal, contract, this.yearlyContractDal, deadMoneyYearOne, contract.getYear(), contract.getPlayer());
		RosterActionUtilities.addDeadMoney(this.deadMoneyDal, contract, this.yearlyContractDal, deadMoneyAfterYearOne, contract.getYear() + 1, contract.getPlayer());
		Double newTeamSalary = contractManagementService.calculateTotalSalary("Offseason", contract.getYear(), contract.getTeam());
		transactionService.push(new Transaction(TransactionType.OTHER, "June 1 Cut", oldTeamSalary - newTeamSalary, deadMoneyYearOne, contract.getYear(), contract.getTeam(), contract.getPlayer(), originalContracts, originalDeadMoney, originalContractOverview));		
	}
}
