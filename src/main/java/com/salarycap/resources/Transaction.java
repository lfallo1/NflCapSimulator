package com.salarycap.resources;

import java.text.DecimalFormat;
import java.util.List;

public class Transaction {
	private static Integer counter = 1;
	private Integer id;
	private String transactionType;
	private String description;
	private Double capSavings;
	private Double deadMoney;
	private Integer year;
	private Integer teamId;
	private Player player;
	private List<YearlyContract> originalContracts;
	private List<DeadMoney> originalDeadMoney;
	private ContractOverview originalContractOverview;
	
	public Transaction(){}
	
	public Transaction(String transactionType, String description, Double capSavings, Double deadMoney, Integer year,
			Integer teamId, Player player, List<YearlyContract> originalContracts, List<DeadMoney> originalDeadMoney,
			ContractOverview originalContractOverview) {
		this.id = counter++;
		this.transactionType = transactionType;
		this.description = description;
		this.capSavings = capSavings;
		this.deadMoney = deadMoney;
		this.year = year;
		this.teamId = teamId;
		this.player = player;
		this.originalContracts = originalContracts;
		this.originalDeadMoney = originalDeadMoney;
		this.originalContractOverview = originalContractOverview;
	}
	
	public Transaction(Transaction transaction){
		this.transactionType = transaction.getTransactionType();
		this.description = transaction.getDescription();
		this.capSavings = transaction.getCapSavings();
		this.deadMoney = transaction.getDeadMoney();
		this.year = transaction.getYear();
		this.player = transaction.getPlayer();
		this.originalContracts = transaction.getOriginalContracts();
		this.originalDeadMoney = transaction.getOriginalDeadMoney();
		this.originalContractOverview = transaction.getOriginalContractOverview();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getMessage() {
		String msg = "";
		switch (this.description) {
		case "Cut":
			msg = this.getPlayer().getName() + "(" + this.getYear() + ")" + this.getDescription() + ". Cap Savings: " + DecimalFormat.getCurrencyInstance().format(this.getCapSavings());
			break;
		case "June 1 Cut":
			msg = this.getPlayer().getName() + "(" + this.getYear() + ")" + this.getDescription() + ". Cap Savings: " + DecimalFormat.getCurrencyInstance().format(this.getCapSavings());
			break;
		case "Restructure":
			msg = this.getPlayer().getName() + "(" + this.getYear() + ")" + this.getDescription() + ". Cap Savings: " + DecimalFormat.getCurrencyInstance().format(this.getCapSavings());
			break;
		case "Extension":
			msg = this.getPlayer().getName() + "(" + this.getYear() + ")" + this.getDescription() + ". Cap Savings: " + DecimalFormat.getCurrencyInstance().format(this.getCapSavings());
			break;			
		case "Reset":
			msg = "All rosters have been reset";
			break;
		default:
			msg = this.getPlayer().getName() + " " + this.getTransactionType();
			break;
		}
		return msg;
	}
	public List<YearlyContract> getOriginalContracts() {
		return originalContracts;
	}
	public void setOriginalContracts(List<YearlyContract> originalContracts) {
		this.originalContracts = originalContracts;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Double getDeadMoney() {
		return deadMoney;
	}

	public void setDeadMoney(Double deadMoney) {
		this.deadMoney = deadMoney;
	}

	public Double getCapSavings() {
		return capSavings;
	}

	public void setCapSavings(Double capSavings) {
		this.capSavings = capSavings;
	}

	public List<DeadMoney> getOriginalDeadMoney() {
		return originalDeadMoney;
	}

	public void setOriginalDeadMoney(List<DeadMoney> originalDeadMoney) {
		this.originalDeadMoney = originalDeadMoney;
	}

	public ContractOverview getOriginalContractOverview() {
		return originalContractOverview;
	}

	public void setOriginalContractOverview(
			ContractOverview originalContractOverview) {
		this.originalContractOverview = originalContractOverview;
	}
	
	
}
