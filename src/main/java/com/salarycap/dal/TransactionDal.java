package com.salarycap.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.resources.Transaction;
@Scope("session")
@Service
public class TransactionDal {
	private List<Transaction> transactions = new ArrayList<>();
	private Transaction lastTransaction;
	
	public TransactionDal(){}
	
	public List<Transaction> getTransactions(){
		return this.transactions;
	}
	
	public void add(Transaction transaction){
		transactions.add(transaction);
		pushLastTransaction(transaction);
	}
	
	public void removeAll(){
		transactions.clear();
	}

	public void remove(Transaction transaction) {
		transactions.remove(transaction);
	}

	public Transaction getById(Integer transactionId) {
		for (Transaction transaction : transactions) {
			if(transaction.getId().equals(transactionId)){
				return transaction;
			}
		}
		return null;
	}

	public List<Transaction> getByPlayer(Integer playerId) {
		List<Transaction> transList = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if(transaction.getPlayer().getId().equals(playerId)){
				transList.add(transaction);
			}
		}
		return transList;
	}
	
	public void pushLastTransaction(Transaction transaction){
		this.lastTransaction = transaction;
	}
	
	public Transaction popLastTransaction(){
		Transaction newObj = null;
		if(this.lastTransaction!=null){
			newObj = new Transaction(this.lastTransaction);
			this.lastTransaction = null;
		}
		return newObj;
	}

	public List<Transaction> getByTeam(Integer teamId) {
		List<Transaction> transList = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if(transaction.getTeamId().equals(teamId)){
				transList.add(transaction);
			}
		}
		return transList;
	}
}
