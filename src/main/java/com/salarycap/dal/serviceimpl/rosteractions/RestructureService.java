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
public class RestructureService {
	
	private TransactionService transactionService;
	private YearlyContractDal contractDal;
	private DeadMoneyDal deadMoneyDal;
	private ContractManagementService contractManagementService;
	private ContractOverviewDal contractOverviewDal;
	
	@Autowired
	public RestructureService(TransactionService transactionService, YearlyContractDal contractDal,
			DeadMoneyDal deadMoneyDal, ContractManagementService contractManagementService,
			ContractOverviewDal contractOverviewDal){
		this.transactionService = transactionService;
		this.contractDal = contractDal;
		this.deadMoneyDal = deadMoneyDal;
		this.contractManagementService = contractManagementService;
		this.contractOverviewDal = contractOverviewDal;
	}	
	
	public void doRestructure(Integer contractId, Double amount){
		YearlyContract currentContract = contractDal.getByContractId(contractId);
		ContractOverview originalContractOverview = contractManagementService.createContractOverview(contractOverviewDal.getByPlayer(currentContract.getPlayer().getId()));
		Double oldTeamSalary = contractManagementService.calculateTotalSalary("Offseason", currentContract.getYear(), currentContract.getTeam());
		List<YearlyContract> originalContracts = new ArrayList<>();
		List<DeadMoney> originalDeadMoney = deadMoneyDal.getByPlayer(currentContract.getPlayer().getId());
		List<YearlyContract> playerContracts = contractDal.getByPlayer(currentContract.getPlayer().getId());
		Double addlSigningBonus = amount / RosterActionUtilities.getYearsRemaining(playerContracts, currentContract.getYear());
		for (YearlyContract c : playerContracts) {
			originalContracts.add(RosterActionUtilities.createNewContract(c));
			if(c.getStatus().contains("Contract")){
				Double p5 = c.getBaseSalary();
				Double signingBonus = c.getSigningBonus();
				Double capCharge = c.getCapCharge();
				if(c.getYear().equals(currentContract.getYear())){
					c.setBaseSalary(p5 - amount);
					c.setSigningBonus(signingBonus + addlSigningBonus);
					c.setCapCharge(capCharge - amount + addlSigningBonus);
				}
				else if(c.getYear()>currentContract.getYear()){
					c.setSigningBonus(signingBonus + addlSigningBonus);
					c.setCapCharge(capCharge + addlSigningBonus);
				}
			}
		}
		Double newTeamSalary = contractManagementService.calculateTotalSalary("Offseason", currentContract.getYear(), currentContract.getTeam());
		transactionService.push(new Transaction(TransactionType.OTHER, "Restructure", oldTeamSalary - newTeamSalary, 0.0, currentContract.getYear(), currentContract.getTeam(), currentContract.getPlayer(), originalContracts, originalDeadMoney, originalContractOverview));		
	}
}
