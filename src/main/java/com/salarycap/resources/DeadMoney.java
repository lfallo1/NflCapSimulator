package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName("dead_money")
public class DeadMoney {
	@Column("id")
	private Integer id;
	@Column("dead_money")
	private Double deadMoney;	
	@Column("year")
	private Integer year;
	@Column("player")
	private Player player;
	@Column("team")
	private Integer team;
	
	public DeadMoney(){}
	
	@TemplateConstructor
	public DeadMoney(@PropertyName ("id") Integer id, @PropertyName ("deadMoney") Double deadMoney,
			@PropertyName ("year") Integer year, @PropertyName ("player") Integer playerId,
			@PropertyName ("team") Integer team) {
		Player player = new Player();
		player.setId(playerId);
		this.deadMoney = deadMoney;
		this.id = id;
		this.year = year;
		this.player = player;
		this.team = team;
	}
	public DeadMoney(Integer id, Double deadMoney, Integer year,
			Player player, Integer team) {
		this.id = id;
		this.deadMoney = deadMoney;
		this.year = year;
		this.player = player;
		this.team = team;
	}

	public Double getDeadMoney() {
		return deadMoney;
	}
	public void setDeadMoney(Double deadMoney) {
		this.deadMoney = deadMoney;
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
}
