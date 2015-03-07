package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName("qualifying_contract_max_bonus")
public class QualifyingContractMaxBonus {
	@Column("id")
	private Integer id;
	@Column("year")
	private Integer year;
	@Column("max_bonus")
	private Double maxBonus;
	
	public QualifyingContractMaxBonus(){}

	@TemplateConstructor
	public QualifyingContractMaxBonus(@PropertyName("id") Integer id,
			@PropertyName("year") Integer year, @PropertyName("maxBonus") Double maxBonus) {
		this.id = id;
		this.year = year;
		this.maxBonus = maxBonus;
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

	public Double getMaxBonus() {
		return maxBonus;
	}

	public void setMaxBonus(Double maxBonus) {
		this.maxBonus = maxBonus;
	}
	
}
