package com.salarycap.dal.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dal.PlayerDal;
import com.salarycap.dal.SalaryCapConstantsDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.service.CalculationService;
import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dal.service.TeamService;
import com.salarycap.dto.TeamSalaryDto;
import com.salarycap.resources.Player;
import com.salarycap.resources.Team;
import com.salarycap.resources.YearlyContract;

@Scope("session")
@Service
public class CalculationServiceImpl implements CalculationService {
	private PlayerDal playerDal;
	private YearlyContractDal yearlyContractDal;
	private TeamService teamService;
	private SalaryCapConstantsDal salaryCapConstantsDal;
	private ContractManagementService contractManagementService;

	@Autowired
	public CalculationServiceImpl(PlayerDal playerDal,
			YearlyContractDal yearlyContractDal,
			TeamService teamService,
			SalaryCapConstantsDal salaryCapConstantsDal,
			ContractManagementService contractManagementService) {
		this.playerDal = playerDal;
		this.yearlyContractDal = yearlyContractDal;
		this.teamService = teamService;
		this.salaryCapConstantsDal = salaryCapConstantsDal;
		this.contractManagementService = contractManagementService;
	}

	@Override
	public List<YearlyContract> getHighestPaid(Integer year) {
		List<YearlyContract> results = new ArrayList<>();
		Collections.sort(yearlyContractDal.getYearlyContracts(), new Comparator<YearlyContract>(){
			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				return -c1.getCapCharge().compareTo(c2.getCapCharge());
			}
		});
		
		int counter = 0, i = 0;
		while(counter < 50){
			if(yearlyContractDal.getYearlyContracts().get(i).getYear().equals(year)){
				results.add(yearlyContractDal.getYearlyContracts().get(i));
				counter++;
			}
			i++;
		}
		return results;
	}
	
	@Override
	public List<TeamSalaryDto> getAllSalariesByYear(Integer year){
		List<TeamSalaryDto> teamSalaries = new ArrayList<>();
		for (Team t : teamService.getAll()) {
			if(!t.getName().toLowerCase().equals("Not Available".toLowerCase())){
				Double totalSalary = contractManagementService.calculateTotalSalary("Offseason", year, t.getId());
				Double adjustedCap = 0.0;
				try{
					adjustedCap = salaryCapConstantsDal.getByYearAndTeam(year, t.getId()).getAdjustedCap();
				}
				catch(Exception e){
					System.out.println(t.getName() + " " + t.getId() + ": " + e.getMessage());
				}
				teamSalaries.add(new TeamSalaryDto(t, totalSalary, adjustedCap, year));
			}
		}
		return teamSalaries;
	}
	
	@Override
	public List<Double> getHighestAPY() {		
		List<Double> sortedList = new ArrayList<>();
		List<Double> results = new ArrayList<>();
		for (Player p : playerDal.getPlayers()) {
			Double total = 0.0;
			Integer years = 0;
			for (YearlyContract c : yearlyContractDal.getByPlayer(p.getId())) {
				if(c.getStatus().equals("Contract")){
					total += c.getCapCharge();
					years ++;
				}
			}
			sortedList.add(total/years);
		}
		Collections.sort(sortedList, new Comparator<Double>(){
			@Override
			public int compare(Double a, Double b) {
				return a.compareTo(b);
			}
		});
		
		for (int i = 0; i < 50; i++) {
			results.add(sortedList.get(i));
		}
		return results;
	}

	@Override
	public List<TeamSalaryDto> getAllSalariesByTeam(Integer teamId) {
		List<TeamSalaryDto> teamSalaries = new ArrayList<>();
		for(int year = 2015; year <= 2018; year++){
			Double totalSalary = contractManagementService.calculateTotalSalary("Offseason", year, teamId);
			Double adjustedCap = 0.0;
			try{
				adjustedCap = salaryCapConstantsDal.getByYearAndTeam(year, teamId).getAdjustedCap();
			}
			catch(Exception e){
				System.out.println(teamId + ": " + e.getMessage());
			}
			teamSalaries.add(new TeamSalaryDto(new Team(teamId, null, null, null, null), totalSalary, adjustedCap, year));
		}
		return teamSalaries;
	}
}
