package com.salarycap.dal.service;

import java.util.List;

import com.salarycap.resources.Transaction;

public interface TransactionService {
	void push(Transaction transaction);
	void undoTransaction(Transaction transaction);
	void removeAllTransactions();
	Transaction getTransactionById(Integer transactionId) throws Exception;
	List<Transaction> getAllTransactions();
	Transaction peek();
	List<Transaction> getByPlayer(Integer playerId);
	
	/**
	 * Return the last transaction, then set last transaction
	 * object to null
	 * @return
	 */
	Transaction tryGetLastTransaction();
	
	/**
	 * Method to push a transaction directly into the
	 * object that stores an object storing the last transaction, bypassing
	 * the process of persisting the transaction. This means,
	 * the value of this object can only be returned one time, 
	 * because once retrieved it is popped from the stac
	 * @param transaction
	 */
	void pushIntoLastTransaction(Transaction transaction);
	
	
	List<Transaction> getAllTransactionsByTeam(Integer teamId);
}
