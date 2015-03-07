package com.salarycap.dto;

import java.util.List;

public class NewPlayerWrapper {
	private String name;
	private Integer accrued;
	private String position;
	private Integer year;
	private Integer teamId;
	private Double signingBonus;
	private List<ContractDto> contractDtoList;

	public NewPlayerWrapper() {
	}

	public NewPlayerWrapper(String name, Integer accrued, String position, Integer year,
			Integer teamId, Double signingBonus,
			List<ContractDto> contractDtoList) {
		this.name = name;
		this.accrued = accrued;
		this.position = position;
		this.year = year;
		this.teamId = teamId;
		this.signingBonus = signingBonus;
		this.contractDtoList = contractDtoList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAccrued() {
		return accrued;
	}

	public void setAccrued(Integer accrued) {
		this.accrued = accrued;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Double getSigningBonus() {
		return signingBonus;
	}

	public void setSigningBonus(Double signingBonus) {
		this.signingBonus = signingBonus;
	}

	public List<ContractDto> getContractDtoList() {
		return contractDtoList;
	}

	public void setContractDtoList(List<ContractDto> contractDtoList) {
		this.contractDtoList = contractDtoList;
	}

}
