package com.salarycap.dal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.constants.TransactionType;
import com.salarycap.dal.ContractOverviewDal;
import com.salarycap.dal.DeadMoneyDal;
import com.salarycap.dal.PlayerDal;
import com.salarycap.dal.TransactionDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.service.TransactionService;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.DeadMoney;
import com.salarycap.resources.Transaction;
import com.salarycap.resources.YearlyContract;

@Scope("session")
@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionDal transactionDal;
	private YearlyContractDal contractDal;
	private DeadMoneyDal deadMoneyDal;
	private ContractOverviewDal contractOverviewDal;
	private PlayerDal playerDal;

	@Autowired
	public TransactionServiceImpl(TransactionDal transactionDal, YearlyContractDal contractDal, 
			DeadMoneyDal deadMoneyDal, ContractOverviewDal contractOverviewDal, PlayerDal playerDal) {
		this.transactionDal = transactionDal;
		this.contractDal = contractDal;
		this.deadMoneyDal = deadMoneyDal;
		this.contractOverviewDal = contractOverviewDal;
		this.playerDal = playerDal;
	}

	@Override
	public void undoTransaction(Transaction transaction) {
		List<YearlyContract> currentContracts = contractDal.getByPlayer(transaction.getPlayer().getId());
		List<DeadMoney> currentDeadMoney = deadMoneyDal.getByPlayer(transaction.getPlayer().getId());
		ContractOverview currentContractOverview = contractOverviewDal.getByPlayer(transaction.getPlayer().getId());
		for (YearlyContract contract : currentContracts) {
			contractDal.remove(contract.getId());
		}
		
		for (DeadMoney d : currentDeadMoney) {
			deadMoneyDal.remove(d.getId());
		}		
		contractOverviewDal.remove(currentContractOverview.getId());
		switch (transaction.getTransactionType()) {
			case TransactionType.ADD_PLAYER:
				playerDal.remove(transaction.getPlayer());
				//Remove all transaction objects for player, because undoing the addition of a
				//player will remove all player instances (including any roster actions that took
				//place after removing player)
				for (Transaction t : transactionDal.getByPlayer(transaction.getPlayer().getId())) {
					transactionDal.remove(t);
				}	
				
				break;
			case TransactionType.OTHER:
				List<YearlyContract> oldContracts = transaction.getOriginalContracts();
				List<DeadMoney> oldDeadMoney = transaction.getOriginalDeadMoney();				
				deadMoneyDal.addAll(oldDeadMoney);
				contractDal.addAll(oldContracts);
				contractOverviewDal.add(transaction.getOriginalContractOverview());
				this.transactionDal.remove(transaction);			
				break;
		}
	}

	@Override
	public void removeAllTransactions() {
		this.transactionDal.removeAll();

	}

	@Override
	public List<Transaction> getAllTransactions() {
		return this.transactionDal.getTransactions();
	}

	@Override
	public void push(Transaction transaction) {
		this.transactionDal.add(transaction);
	}

	@Override
	public Transaction peek() {
		return transactionDal.getTransactions().get(transactionDal.getTransactions().size()-1);
	}

	@Override
	public Transaction getTransactionById(Integer transactionId) {
		return this.transactionDal.getById(transactionId);
	}

	@Override
	public List<Transaction> getByPlayer(Integer playerId) {
		return this.transactionDal.getByPlayer(playerId);
	}

	@Override
	public Transaction tryGetLastTransaction() {
		return this.transactionDal.popLastTransaction();
	}

	@Override
	public void pushIntoLastTransaction(Transaction transaction) {
		transactionDal.pushLastTransaction(transaction);
	}

	@Override
	public List<Transaction> getAllTransactionsByTeam(Integer teamId) {
		return this.transactionDal.getByTeam(teamId);
	}

}
