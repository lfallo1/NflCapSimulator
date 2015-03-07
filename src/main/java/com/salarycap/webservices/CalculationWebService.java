package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.service.CalculationService;
import com.salarycap.dto.TeamSalaryDto;
import com.salarycap.resources.YearlyContract;

@Scope("session")
@RestController
@RequestMapping(value="/api/calculation")
public class CalculationWebService {
	
	private CalculationService calculationService;
	
	@Autowired
	public CalculationWebService(CalculationService calculationService){
		this.calculationService = calculationService;
	}
	
	@RequestMapping(value="/getHighestPaid/{year}", method=RequestMethod.GET)
	public List<YearlyContract> getHighestPaid(@PathVariable Integer year){
		return this.calculationService.getHighestPaid(year);
	}
	
	@RequestMapping(value="/getAllSalariesByYear/{year}", method=RequestMethod.GET)
	public List<TeamSalaryDto> getAllSalariesByYear(@PathVariable Integer year){
		return this.calculationService.getAllSalariesByYear(year);
	}
	
	@RequestMapping(value="/getAllSalariesByTeam/{teamId}", method=RequestMethod.GET)
	public List<TeamSalaryDto> getAllSalariesByTeam(@PathVariable Integer teamId){
		return this.calculationService.getAllSalariesByTeam(teamId);
	}	
	
}
