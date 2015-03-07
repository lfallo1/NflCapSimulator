package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.service.TransactionService;
import com.salarycap.resources.Transaction;
@Scope("session")
@RestController
@RequestMapping(value="/api/transactions")
public class TransactionWebService {
	
	private TransactionService transactionService;
	
	@Autowired
	public TransactionWebService(TransactionService transactionService){
		this.transactionService = transactionService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Transaction> getAll(){
		return transactionService.getAllTransactions();
	}
	
	@RequestMapping(value="/team/{teamId}", method=RequestMethod.GET)
	public List<Transaction> getByTeam(@PathVariable Integer teamId){
		return transactionService.getAllTransactionsByTeam(teamId);
	}	
	
	@RequestMapping(value="{transactionId}", method=RequestMethod.GET)
	public Transaction getById(@PathVariable Integer transactionId){
		return transactionService.getTransactionById(transactionId);
	}
	
	@RequestMapping(value="/player/{playerId}", method=RequestMethod.GET)
	public List<Transaction> getByPlayer(@PathVariable Integer playerId){
		return transactionService.getByPlayer(playerId);
	}		
	
	@RequestMapping(value="/reset", method=RequestMethod.GET)
	public void resetAll(){
		transactionService.removeAllTransactions();
	}
	
	@RequestMapping(value="/undo/{id}", method=RequestMethod.POST)
	public void undoTransaction(@PathVariable Integer id){
		transactionService.undoTransaction(transactionService.getTransactionById(id));
	}
	
	@RequestMapping(value="/lastTransaction", method=RequestMethod.GET)
	public Transaction tryGetLastTransaction(){
		return transactionService.tryGetLastTransaction();
	}	
}
