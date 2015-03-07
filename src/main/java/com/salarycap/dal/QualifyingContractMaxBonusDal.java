package com.salarycap.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.QualifyingContractMaxBonusDao;
import com.salarycap.resources.QualifyingContractMaxBonus;
@Scope("session")
@Service
public class QualifyingContractMaxBonusDal {
	private List<QualifyingContractMaxBonus> qualifyingContractMaxBonusList;
	
	@Autowired
	public QualifyingContractMaxBonusDal(QualifyingContractMaxBonusDao qualifyingContractMaxBonusDao){
		qualifyingContractMaxBonusList = qualifyingContractMaxBonusDao.getAll();
	}	
	
	public List<QualifyingContractMaxBonus> getQualifyingContractMaxBonusList(){
		return this.qualifyingContractMaxBonusList;
	}
}
