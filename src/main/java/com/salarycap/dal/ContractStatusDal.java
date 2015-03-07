package com.salarycap.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.ContractStatusDao;
import com.salarycap.resources.ContractStatus;
@Scope("session")
@Service
public class ContractStatusDal {
	private List<ContractStatus> contractStatuses;
	
	@Autowired
	public ContractStatusDal(ContractStatusDao contractStatusDao){
		this.contractStatuses = contractStatusDao.getAll();
	}	
	
	public List<ContractStatus> getContractStatuses(){
		return this.contractStatuses;
	}
	
	public ContractStatus getById(Integer id){
		for (ContractStatus contractStatus : contractStatuses) {
			if(contractStatus.getId().equals(id)){
				return contractStatus;
			}
		}
		return null;
	}
}
