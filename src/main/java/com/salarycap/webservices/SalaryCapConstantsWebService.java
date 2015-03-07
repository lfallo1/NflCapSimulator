package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.SalaryCapConstantsDal;
import com.salarycap.resources.SalaryCapConstants;
@Scope("session")
@RestController
@RequestMapping(value="/api/salarycapconstants")
public class SalaryCapConstantsWebService {
	
	private SalaryCapConstantsDal salaryCapConstantsDal;
	
	@Autowired
	public SalaryCapConstantsWebService(SalaryCapConstantsDal salaryCapConstantsDal){
		this.salaryCapConstantsDal = salaryCapConstantsDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<SalaryCapConstants> getAll(){
		return salaryCapConstantsDal.getSalaryCapConstantsList();
	}
	
	@RequestMapping(value="/byYear/{year}/{teamId}", method=RequestMethod.GET)
	public SalaryCapConstants getByYearAndTeam(@PathVariable ("year") Integer year, @PathVariable("teamId") Integer teamId){
		return salaryCapConstantsDal.getByYearAndTeam(year, teamId);
	}	
	
	@RequestMapping(value="/byTeam/{teamId}", method=RequestMethod.GET)
	public List<SalaryCapConstants> getByTeam(@PathVariable("teamId") Integer teamId){
		return salaryCapConstantsDal.getByTeam(teamId);
	}		
}
