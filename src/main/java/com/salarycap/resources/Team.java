package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName("team")
public class Team {
	@Column("id")
	private Integer id;
	@Column("name")
	private String name;
	@Column("logo")
	private String logo;
	@Column("primary_color")
	private String primaryColor;
	@Column("secondary_color")
	private String secondaryColor;

	@TemplateConstructor
	public Team(@PropertyName("id") Integer id,
			@PropertyName("name") String name, @PropertyName("logo") String logo,
			@PropertyName("primaryColor") String primaryColor,
			@PropertyName("secondaryColor") String secondaryColor) {
		this.id = id;
		this.name = name;
		this.logo = logo;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}

	public Team() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}

	public String getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

}
