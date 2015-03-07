package com.salarycap.dto;

public class ContractDto {
	private Double baseSalary;
	private Double guaranteedBaseSalary;
	
	public ContractDto(){}
	
	public ContractDto(Double baseSalary, Double guaranteedBaseSalary) {
		this.baseSalary = baseSalary;
		this.guaranteedBaseSalary = guaranteedBaseSalary;
	}	

	public Double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getGuaranteedBaseSalary() {
		return guaranteedBaseSalary;
	}

	public void setGuaranteedBaseSalary(Double guaranteedBaseSalary) {
		this.guaranteedBaseSalary = guaranteedBaseSalary;
	}
}
