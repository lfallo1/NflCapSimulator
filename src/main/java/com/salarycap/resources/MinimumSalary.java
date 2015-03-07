package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName(value="minimum_salary")
public class MinimumSalary {
	@Column(value="id")
	private Integer id;
	@Column(value="year")
	private Integer year;
	@Column(value="credited_seasons")
	private Integer creditedSeasons;
	@Column(value="base_salary")
	private Double baseSalary;
	
	public MinimumSalary(){}
	
	@TemplateConstructor
	public MinimumSalary(@PropertyName("id") Integer id, @PropertyName("year") Integer year, @PropertyName("creditedSeasons") Integer creditedSeasons,
			@PropertyName("baseSalary") Double baseSalary) {
		this.id = id;
		this.year = year;
		this.creditedSeasons = creditedSeasons;
		this.baseSalary = baseSalary;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getCreditedSeasons() {
		return creditedSeasons;
	}
	public void setCreditedSeasons(Integer creditedSeasons) {
		this.creditedSeasons = creditedSeasons;
	}
	public Double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
}
