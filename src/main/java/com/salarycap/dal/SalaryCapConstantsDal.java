package com.salarycap.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dal.service.TeamService;
import com.salarycap.dao.SalaryCapConstantsDao;
import com.salarycap.resources.SalaryCapConstants;
@Scope("session")
@Service
public class SalaryCapConstantsDal {
	private List<SalaryCapConstants> salaryCapConstantsList;
	private TeamService teamService;
	
	@Autowired
	public SalaryCapConstantsDal(SalaryCapConstantsDao salaryCapConstantsDao,
			TeamService teamService){
		salaryCapConstantsList = salaryCapConstantsDao.getAll();
		this.teamService = teamService;
	}	
	
	public List<SalaryCapConstants> getSalaryCapConstantsList(){
		return this.salaryCapConstantsList;
	}
	
	public SalaryCapConstants getByYearAndTeam(Integer year, Integer teamId){
		for (SalaryCapConstants c : salaryCapConstantsList) {
			if(c.getYear().equals(year) && c.getTeam().getId().equals(teamId)){
				return c;
			}
		}
		return null;
	}

	public List<SalaryCapConstants> getByYear(Integer year) {
		List<SalaryCapConstants> results = new ArrayList<>();
		for (SalaryCapConstants s : salaryCapConstantsList) {
			if(s.getYear().equals(year)){
				results .add(s);
			}
		}
		return results;
	}

	public List<SalaryCapConstants> getByTeam(Integer teamId) {
		List<SalaryCapConstants> results = new ArrayList<>();
		for (SalaryCapConstants s : salaryCapConstantsList) {
			if(s.getTeam().getId().equals(teamId)){
				results .add(s);
			}
		}
		return results;
	}
}
