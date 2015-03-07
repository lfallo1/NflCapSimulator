package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName(value = "position")
public class Position {
	@Column("id")
	private Integer id;
	@Column("position_name")
	private String positionName;
	@Column("role")
	private String role;

	public Position() {
	}

	@TemplateConstructor
	public Position(@PropertyName("id") Integer id,
			@PropertyName("positionName") String positionName,
			@PropertyName("role") String role) {
		this.id = id;
		this.positionName = positionName;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
