package com.salarycap.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.YearlyContractDao;
import com.salarycap.resources.Player;
import com.salarycap.resources.YearlyContract;
@Scope("session")
@Service
public class YearlyContractDal {
	private List<YearlyContract> yearlyContracts = new ArrayList<>();
	private YearlyContractDao yearlyContractDao;
	
	@Autowired
	public YearlyContractDal(YearlyContractDao yearlyContractDao){
		this.yearlyContractDao = yearlyContractDao;
	}
	
	public void reset(){
		yearlyContracts = yearlyContractDao.getAll();
	}
	
	public void populatePlayers(PlayerDal playerDal){
		for (YearlyContract c : yearlyContracts) {
			Player p = playerDal.getByPlayerId(c.getPlayer().getId());
			c.setPlayer(p);
		}
	}

	public List<YearlyContract> getYearlyContracts(){
		return this.yearlyContracts;
	}
	
	public List<YearlyContract> getByTeam(Integer teamId){
		List<YearlyContract> playerContracts = new ArrayList<>();
		for (YearlyContract contract : yearlyContracts) {
			if(contract.getTeam().equals(teamId)){
				playerContracts.add(contract);
			}
		}
		return playerContracts;		
	}
	
	public YearlyContract getByContractId(Integer contractId){
		for (YearlyContract contract : yearlyContracts) {
			if(contract.getId().equals(contractId)){
				return contract;
			}
		}
		return null;
	}
	
	public void remove(Integer contractId){
		for (YearlyContract contract : yearlyContracts) {
			if(contract.getId().equals(contractId)){
				yearlyContracts.remove(contract);
				break;
			}
		}
	}

	public void add(YearlyContract yc) {
		yearlyContracts.add(yc);
	}
	
	public List<YearlyContract> getByPlayer(Integer playerId) {
		List<YearlyContract> playerContracts = new ArrayList<>();
		for (YearlyContract contract : yearlyContracts) {
			try{
				if(contract.getPlayer().getId().equals(playerId)){
					playerContracts.add(contract);
				}
			}
			catch(NullPointerException e){
				System.out.println(e.getMessage());
			}
		}
		return playerContracts;
	}
	
	public Integer getLastId(){
		Collections.sort(yearlyContracts, new Comparator<YearlyContract>(){
			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				return -c1.getId().compareTo(c2.getId());
			}
		});
		return yearlyContracts.get(0).getId()+1;
	}

	public void addAll(List<YearlyContract> yearlyContractList) {
		for (YearlyContract yearlyContract : yearlyContractList) {
			yearlyContract.setId(getLastId());
			yearlyContracts.add(yearlyContract);
		}
	}	
	
}