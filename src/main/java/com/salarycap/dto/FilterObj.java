package com.salarycap.dto;

import java.util.List;

public class FilterObj {
	private Integer year;
	private Integer team;
	private List<String> positions;
	private List<String> contractStatuses;

	public FilterObj() {
	}

	public FilterObj(Integer year, Integer team, List<String> positions,
			List<String> contractStatuses) {
		this.year = year;
		this.team = team;
		this.positions = positions;
		this.contractStatuses = contractStatuses;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTeam() {
		return team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}

	public List<String> getPositions() {
		return positions;
	}

	public void setPositions(List<String> positions) {
		this.positions = positions;
	}

	public List<String> getContractStatuses() {
		return contractStatuses;
	}

	public void setContractStatuses(List<String> contractStatuses) {
		this.contractStatuses = contractStatuses;
	}
}
