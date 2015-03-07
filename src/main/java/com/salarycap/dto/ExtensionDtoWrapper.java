package com.salarycap.dto;

import java.util.List;

public class ExtensionDtoWrapper {
	private Double signingBonus;
	private Integer year;
	private Integer playerId;
	private Integer additionalYears;
	private Integer totalYears;
	private List<ContractDto> contractDtoList;

	public ExtensionDtoWrapper() {
	}

	public ExtensionDtoWrapper(Double signingBonus, Integer year,
			Integer playerId, Integer additionalYears, Integer totalYears,
			List<ContractDto> contractDtoList) {
		this.signingBonus = signingBonus;
		this.year = year;
		this.playerId = playerId;
		this.additionalYears = additionalYears;
		this.totalYears = totalYears;
		this.contractDtoList = contractDtoList;
	}

	public Double getSigningBonus() {
		return signingBonus;
	}

	public void setSigningBonus(Double signingBonus) {
		this.signingBonus = signingBonus;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public List<ContractDto> getContractDtoList() {
		return contractDtoList;
	}

	public void setContractDtoList(List<ContractDto> contractDtoList) {
		this.contractDtoList = contractDtoList;
	}

	public Integer getAdditionalYears() {
		return additionalYears;
	}

	public void setAdditionalYears(Integer additionalYears) {
		this.additionalYears = additionalYears;
	}

	public Integer getTotalYears() {
		return totalYears;
	}

	public void setTotalYears(Integer totalYears) {
		this.totalYears = totalYears;
	}

}
