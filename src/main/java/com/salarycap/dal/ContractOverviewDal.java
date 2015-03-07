package com.salarycap.dal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.ContractOverviewDao;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.Player;

@Scope("session")
@Service
public class ContractOverviewDal {
	
	private List<ContractOverview> contractOverviewList;
	private ContractOverviewDao contractOverviewDao;
	
	@Autowired
	public ContractOverviewDal(ContractOverviewDao contractOverviewDao){
		this.contractOverviewDao = contractOverviewDao;
	}	
	
	public void reset(){
		contractOverviewList = contractOverviewDao.getAll();
	}
	
	public void populatePlayers(PlayerDal playerDal){
		for (ContractOverview c : contractOverviewList) {
			Player player = playerDal.getByPlayerId(c.getPlayer().getId());
			c.setPlayer(player);
		}		
	}
	
	public List<ContractOverview> getContractOverviewList(){
		return this.contractOverviewList;
	}

	public ContractOverview getByPlayer(Integer playerId) {
		for (ContractOverview c : contractOverviewList) {
			if(c.getPlayer().getId().equals(playerId)){
				return c;
			}
		}
		return null;
	}

	public ContractOverview update(String freeAgentYear, Player player, Integer team, Integer length) {
		for (ContractOverview c : contractOverviewList) {
			if(c.getPlayer().getId().equals(player.getId())){
				c.setFreeAgentYear(freeAgentYear);
				c.setYears(length);
				return c;
			}
		}
		ContractOverview c = new ContractOverview();
		c.setPlayer(player);
		c.setFreeAgentYear(freeAgentYear);
		c.setTeam(team);
		c.setYears(length);
		add(c);
		return c;
	}

	public Integer getLastId() {
		Collections.sort(contractOverviewList, new Comparator<ContractOverview>(){
			@Override
			public int compare(ContractOverview c1, ContractOverview c2) {
				return -c1.getId().compareTo(c2.getId());
			}
		});
		return contractOverviewList.get(0).getId()+1;		
	}

	public Integer add(ContractOverview contractOverview) {
		contractOverview.setId(getLastId());
		contractOverviewList.add(contractOverview);
		return contractOverview.getId(); 
	}

	public void remove(Integer id) {
		for (ContractOverview c : contractOverviewList) {
			if(c.getId().equals(id)){
				contractOverviewList.remove(c);
				break;
			}
		}
	}
	

}
