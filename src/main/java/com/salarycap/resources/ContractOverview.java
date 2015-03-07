package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName("contract_overview")
public class ContractOverview {
	@Column("id")
	private Integer id;
	@Column("player")
	private Player player;
	@Column("team")
	private Integer team;	
	@Column("average_per_year")
	private Double averagePerYear;
	@Column("free_agent_year")
	private String freeAgentYear;
	@Column("guarantee")
	private Double guarantee;
	@Column("position")
	private String position;
	@Column("role")
	private String role;
	@Column("status")
	private String status;
	@Column("total")
	private Double total;
	@Column("years")
	private Integer years;
	
	public ContractOverview(){}
	
	@TemplateConstructor
	public ContractOverview(@PropertyName("id") Integer id, @PropertyName("player") Integer playerId, 
			@PropertyName("team") Integer team, @PropertyName("averagePerYear") Double averagePerYear,
			@PropertyName("freeAgentYear") String freeAgentYear, @PropertyName("guarantee") Double guarantee, 
			@PropertyName("position") String position, 
			@PropertyName("role") String role, @PropertyName("status") String status, 
			@PropertyName("total") Double total, @PropertyName("years") Integer years) {
		Player player = new Player();
		player.setId(playerId);
		this.id = id;
		this.player = player;
		this.team = team;
		this.averagePerYear = averagePerYear;
		this.freeAgentYear = freeAgentYear;
		this.guarantee = guarantee;
		this.position = position;
		this.role = role;
		this.status = status;
		this.total = total;
		this.years = years;
	}

	public ContractOverview(ContractOverview contractOverview) {
		this.id = contractOverview.getId();
		this.player = contractOverview.getPlayer();
		this.team = contractOverview.getTeam();
		this.averagePerYear = contractOverview.getAveragePerYear();
		this.freeAgentYear = contractOverview.getFreeAgentYear();
		this.guarantee = contractOverview.getGuarantee();
		this.position = contractOverview.getPosition();
		this.role = contractOverview.getRole();
		this.status = contractOverview.getStatus();
		this.total = contractOverview.getTotal();
		this.years = contractOverview.getYears();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Integer getTeam() {
		return team;
	}
	public void setTeam(Integer team) {
		this.team = team;
	}
	public Double getAveragePerYear() {
		return averagePerYear;
	}
	public void setAveragePerYear(Double averagePerYear) {
		this.averagePerYear = averagePerYear;
	}
	public String getFreeAgentYear() {
		return freeAgentYear;
	}
	public void setFreeAgentYear(String freeAgentYear) {
		this.freeAgentYear = freeAgentYear;
	}
	public Double getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(Double guarantee) {
		this.guarantee = guarantee;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
}
