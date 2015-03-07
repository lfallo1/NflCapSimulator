package com.salarycap.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.MinimumSalaryDao;
import com.salarycap.resources.MinimumSalary;
@Scope("session")
@Service
public class MinimumSalaryDal {
	private List<MinimumSalary> minimumSalaries = new ArrayList<>();
	
	@Autowired
	public MinimumSalaryDal(MinimumSalaryDao minimumSalaryDao){
		this.minimumSalaries = minimumSalaryDao.getAll();
	}	
	
	public List<MinimumSalary> getMinimumSalaries(){
		return this.minimumSalaries;
	}

	public MinimumSalary getByYearAndCreditedSeasons(Integer year, Integer cs) {
		Integer creditedSeasons = cs > 10 ? 10 : cs;
		for (MinimumSalary minimumSalary : minimumSalaries) {
			if(minimumSalary.getYear().equals(year) && minimumSalary.getCreditedSeasons().equals(creditedSeasons)){
				return minimumSalary;
			}
		}
		return null;
	}
}
