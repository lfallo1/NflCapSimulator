package com.salarycap.dto;

public class RestructureDto {
	private Integer contractId;
	private Double amount;
	
	public RestructureDto() {}	
	
	public RestructureDto(Integer contractId, Double amount) {
		this.contractId = contractId;
		this.amount = amount;
	}
	
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
