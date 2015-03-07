package com.salarycap.dto;

import com.salarycap.resources.Team;

public class TeamSalaryDto {

	private Team team;
	private Double totalSalary;
	private Double adjustedCap;
	private Integer year;

	public TeamSalaryDto() {
	}

	public TeamSalaryDto(Team team, Double totalSalary, Double adjustedCap, Integer year) {
		this.team = team;
		this.totalSalary = totalSalary;
		this.year = year;
		this.adjustedCap = adjustedCap;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getAdjustedCap() {
		return adjustedCap;
	}

	public void setAdjustedCap(Double adjustedCap) {
		this.adjustedCap = adjustedCap;
	}

}
