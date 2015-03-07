package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.ContractStatusDal;
import com.salarycap.resources.ContractStatus;
@Scope("session")
@RestController
@RequestMapping(value="/api/contractstatus")
public class ContractStatusWebService {
	
	private ContractStatusDal contractStatusDal;
	
	@Autowired
	public ContractStatusWebService(ContractStatusDal contractStatusDal){
		this.contractStatusDal = contractStatusDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ContractStatus> getAll(){
		return this.contractStatusDal.getContractStatuses();
	}
}
