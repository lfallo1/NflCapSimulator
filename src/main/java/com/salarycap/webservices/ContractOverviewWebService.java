package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.ContractOverviewDal;
import com.salarycap.resources.ContractOverview;
@Scope("session")
@RestController
@RequestMapping(value="/api/contractoverview")
public class ContractOverviewWebService {
	
	private ContractOverviewDal contractOverviewDal;
	
	@Autowired
	public ContractOverviewWebService(ContractOverviewDal contractOverviewDal){
		this.contractOverviewDal = contractOverviewDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ContractOverview> getAll(){
		return this.contractOverviewDal.getContractOverviewList();
	}
	
	@RequestMapping(value="/player/{playerId}", method=RequestMethod.GET)
	public ContractOverview getByPlayer(@PathVariable Integer playerId){
		ContractOverview c =  this.contractOverviewDal.getByPlayer(playerId);
		return c;
	}	
}
