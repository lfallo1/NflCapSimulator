package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dto.FilterObj;
import com.salarycap.dto.ObjWrapper;
import com.salarycap.resources.YearlyContract;
@Scope("session")
@RestController
@RequestMapping("/api/yearlycontracts")
public class YearlyContractWebService {
	
	private YearlyContractDal yearlyContractDal;
	private ContractManagementService contractManagementService;
	
	@Autowired
	public YearlyContractWebService(ContractManagementService contractManagementService,
			 YearlyContractDal yearlyContractDal){
		this.contractManagementService = contractManagementService;
		this.yearlyContractDal = yearlyContractDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<YearlyContract> getAll(){
		return this.yearlyContractDal.getYearlyContracts();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public YearlyContract getById(@PathVariable("id") Integer id){
		return this.yearlyContractDal.getByContractId(id);
	}
	
	@RequestMapping(value="/team/{teamId}", method=RequestMethod.GET)
	public List<YearlyContract> getByTeam(@PathVariable("teamId") Integer teamId){
		return this.yearlyContractDal.getByTeam(teamId);
	}	
	
	@RequestMapping(value="/player/{playerId}", method=RequestMethod.GET)
	public List<YearlyContract> getByPlayer(@PathVariable Integer playerId){
		return this.yearlyContractDal.getByPlayer(playerId);
	}
	
	@RequestMapping(value="/reset", method=RequestMethod.GET)
	public void reset(){
		contractManagementService.reset();
	}
	
	@RequestMapping(value="/f", method=RequestMethod.POST)
	public List<YearlyContract> getByFilter(@RequestBody FilterObj data){
		return this.contractManagementService.getFullRosterFilter(data);
	}		
	
	@RequestMapping(value="isOnActiveRoster/{contractId}/{teamId}", method=RequestMethod.GET)
	public Boolean isOnActiveRoster(@PathVariable Integer contractId, @PathVariable Integer teamId){
		return contractManagementService.isOnActiveRoster("Offseason", contractId, yearlyContractDal.getByContractId(contractId).getYear(), teamId);
	}
	
	@RequestMapping(value="getFirstOffActiveRoster/{year}/{teamId}", method=RequestMethod.GET)
	public YearlyContract getFirstOffActiveRoster(@PathVariable Integer year, @PathVariable Integer teamId){
		return contractManagementService.getFirstOffActiveRoster("Offseason", year, teamId);
	}
	
	@RequestMapping(value="getLastOnActiveRoster/{year}/{teamId}", method=RequestMethod.GET)
	public YearlyContract getLastOnActiveRoster(@PathVariable Integer year, @PathVariable Integer teamId){
		return contractManagementService.getLastOnActiveRoster("Offseason", year, teamId);
	}
	
	@RequestMapping(value="calculateTotalSalary/{yearType}/{year}/{teamId}", method=RequestMethod.GET)
	public ObjWrapper<Double> calculateTotalSalary(@PathVariable String yearType, @PathVariable Integer year, @PathVariable Integer teamId){
		return new ObjWrapper<Double>(contractManagementService.calculateTotalSalary(yearType, year, teamId));
	}
}
