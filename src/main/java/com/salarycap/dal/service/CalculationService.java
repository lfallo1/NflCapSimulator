package com.salarycap.dal.service;

import java.util.List;

import com.salarycap.dto.TeamSalaryDto;
import com.salarycap.resources.YearlyContract;

public interface CalculationService {
	List<YearlyContract> getHighestPaid(Integer year);
	List<Double> getHighestAPY();
	List<TeamSalaryDto> getAllSalariesByYear(Integer year);
	List<TeamSalaryDto> getAllSalariesByTeam(Integer teamId);
}
