package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName(value="contract_status")
public class ContractStatus {
	@Column("id")
	private Integer id;
	@Column("name")
	private String contractStatusName;
	
	public ContractStatus(){}
	
	@TemplateConstructor
	public ContractStatus(@PropertyName("id") Integer id, @PropertyName("contractStatusName") String contractStatusName) {
		this.id = id;
		this.contractStatusName = contractStatusName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContractStatusName() {
		return contractStatusName;
	}
	public void setContractStatusName(String contractStatusName) {
		this.contractStatusName = contractStatusName;
	}
}
