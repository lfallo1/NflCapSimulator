package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName("salary_cap_constants")
public class SalaryCapConstants {
	@Column("id")
	private Integer id;
	@Column("year")
	private Integer year;
	@Column("team")
	private Team team;
	@Column("carryover_incentive_adjustments")
	private Double carryoverIncentiveAdjustments;
	@Column("adjusted_cap")
	private Double adjustedCap;
	@Column("salary_cap")
	private Double salaryCap;	

	public SalaryCapConstants() {
	}

	@TemplateConstructor
	public SalaryCapConstants(
			@PropertyName("id") Integer id,
			@PropertyName("year") Integer year, @PropertyName("team") Integer teamId,
			@PropertyName("carryoverIncentiveAdjustments") Double carryoverIncentiveAdjustments,
			@PropertyName("adjustedCap") Double adjustedCap, @PropertyName("salaryCap") Double salaryCap) {
		Team team = new Team();
		team.setId(teamId);
		this.id = id;
		this.year = year;
		this.team = team;
		this.adjustedCap = adjustedCap;
		this.salaryCap = salaryCap;
	}
	
	public SalaryCapConstants(Integer id, Integer year, Team team,
			Double carryoverIncentiveAdjustments, Double adjustedCap,
			Double salaryCap) {
		this.id = id;
		this.year = year;
		this.team = team;
		this.carryoverIncentiveAdjustments = carryoverIncentiveAdjustments;
		this.adjustedCap = adjustedCap;
		this.salaryCap = salaryCap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Double getCarryoverIncentiveAdjustments() {
		return carryoverIncentiveAdjustments;
	}

	public void setCarryoverIncentiveAdjustments(
			Double carryoverIncentiveAdjustments) {
		this.carryoverIncentiveAdjustments = carryoverIncentiveAdjustments;
	}

	public Double getAdjustedCap() {
		return adjustedCap;
	}

	public void setAdjustedCap(Double adjustedCap) {
		this.adjustedCap = adjustedCap;
	}

	public Double getSalaryCap() {
		return salaryCap;
	}

	public void setSalaryCap(Double salaryCap) {
		this.salaryCap = salaryCap;
	}
	
	
}
