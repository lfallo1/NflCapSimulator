package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.MinimumSalaryDal;
import com.salarycap.resources.MinimumSalary;
@Scope("session")
@RestController
@RequestMapping(value="/api/minimumsalary")
public class MinimumSalaryWebService {
	
	private MinimumSalaryDal minimumSalaryDal;
	
	@Autowired
	public MinimumSalaryWebService(MinimumSalaryDal minimumSalaryDal){
		this.minimumSalaryDal = minimumSalaryDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<MinimumSalary> getAll(){
		return minimumSalaryDal.getMinimumSalaries();
	}
	
	@RequestMapping(value="{year}/{cs}", method=RequestMethod.GET)
	public MinimumSalary getByYearAndCreditedSeasons(@PathVariable Integer year, @PathVariable Integer cs){
		return minimumSalaryDal.getByYearAndCreditedSeasons(year, cs);
	}	
}
