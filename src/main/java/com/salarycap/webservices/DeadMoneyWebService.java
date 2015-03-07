package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.DeadMoneyDal;
import com.salarycap.resources.DeadMoney;
@Scope("session")
@RestController
@RequestMapping(value="/api/deadmoney")
public class DeadMoneyWebService {
	
	private DeadMoneyDal deadMoneyDal;
	
	@Autowired
	public DeadMoneyWebService(DeadMoneyDal deadMoneyDal){
		this.deadMoneyDal = deadMoneyDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<DeadMoney> getAll(){
		return deadMoneyDal.getDeadMoneyList();
	}
	
	@RequestMapping(value="/player/{playerId}", method=RequestMethod.GET)
	public List<DeadMoney> getByPlayer(@PathVariable Integer playerId){
		return deadMoneyDal.getByPlayer(playerId);
	}
	
	@RequestMapping(value="/year/{year}/{teamId}", method=RequestMethod.GET)
	public List<DeadMoney> getByYear(@PathVariable("year") Integer year, @PathVariable("teamId") Integer teamId){
		return deadMoneyDal.getByYearAndTeam(year, teamId);
	}	

}
