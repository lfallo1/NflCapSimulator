package com.salarycap.resources;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName(value="yearly_contract")
public class YearlyContract {
	@Column("id")
	private Integer id;
	@Column("player")
	private Player player;
	@Column("team")
	private Integer team;
	@Column("year")
	private Integer year;
	@Column("base_salary")
	private Double baseSalary;
	@Column("cap_charge")
	private Double capCharge;
	@Column("cap_savings")
	private Double capSavings;
	@Column("dead_money")
	private Double deadMoney;
	@Column("guaranteed_base_salary")
	private Double guaranteedBaseSalary;
	@Column("notes")
	private String notes;
	@Column("option_bonus")
	private Double optionBonus;
	@Column("signing_bonus")
	private Double signingBonus;
	@Column("roster_bonus")
	private Double rosterBonus;
	@Column("workout_bonus")
	private Double workoutBonus;
	@Column("team_name")
	private String teamName;
	@Column("role")
	private String role;
	@Column("status")
	private String status;
	@Column("position")
	private String position;

	public YearlyContract() {
	}
	
	@TemplateConstructor
	public YearlyContract(@PropertyName("id") Integer id, @PropertyName("player")Integer player, 
			@PropertyName("team") Integer team, @PropertyName("year") Integer year,
			@PropertyName("baseSalary") Double baseSalary, @PropertyName("capCharge") Double capCharge,
			@PropertyName("capSavings") Double capSavings, @PropertyName("deadMoney") Double deadMoney,
			@PropertyName("guaranteedBaseSalary") Double guaranteedBaseSalary, @PropertyName("notes") String notes,
			@PropertyName("optionBonus") Double optionBonus, @PropertyName("signingBonus") Double signingBonus,
			@PropertyName("rosterBonus") Double rosterBonus, @PropertyName("workoutBonus") Double workoutBonus,
			@PropertyName("teamName") String teamName, @PropertyName("role") String role,
			@PropertyName("status") String status, @PropertyName("position") String position) {
		Player p = new Player();
		p.setId(player);
		this.id = id;
		this.player = p;
		this.team = team;
		this.year = year;
		this.baseSalary = baseSalary;
		this.capCharge = capCharge;
		this.capSavings = capSavings;
		this.deadMoney = deadMoney;
		this.guaranteedBaseSalary = guaranteedBaseSalary;
		this.notes = notes;
		this.optionBonus = optionBonus;
		this.signingBonus = signingBonus;
		this.rosterBonus = rosterBonus;
		this.workoutBonus = workoutBonus;
		this.teamName = teamName;
		this.role = role;
		setStatus(status);
		this.position = position;
	}

	public YearlyContract(Integer id, Player player, Integer team,
			Integer year, Double baseSalary, Double capCharge,
			Double capSavings, Double deadMoney,
			Double guaranteedBaseSalary, String notes, Double optionBonus,
			Double signingBonus, Double rosterBonus, Double workoutBonus,
			String teamName, String role, String status, String position) {
		this.id = id;
		this.player = player;
		this.team = team;
		this.year = year;
		this.baseSalary = baseSalary;
		this.capCharge = capCharge;
		this.capSavings = capSavings;
		this.deadMoney = deadMoney;
		this.guaranteedBaseSalary = guaranteedBaseSalary;
		this.notes = notes;
		this.optionBonus = optionBonus;
		this.signingBonus = signingBonus;
		this.rosterBonus = rosterBonus;
		this.workoutBonus = workoutBonus;
		this.teamName = teamName;
		this.role = role;
		this.status = status;
		this.position = position;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Double getCapCharge() {
		return capCharge;
	}

	public void setCapCharge(Double capCharge) {
		this.capCharge = capCharge;
	}

	public Double getCapSavings() {
		return capSavings;
	}

	public void setCapSavings(Double capSavings) {
		this.capSavings = capSavings;
	}

	public Double getDeadMoney() {
		return deadMoney;
	}

	public void setDeadMoney(Double deadMoney) {
		this.deadMoney = deadMoney;
	}

	public Double getGuaranteedBaseSalary() {
		return guaranteedBaseSalary;
	}

	public void setGuaranteedBaseSalary(Double guaranteedBaseSalary) {
		this.guaranteedBaseSalary = guaranteedBaseSalary;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getOptionBonus() {
		return optionBonus;
	}

	public void setOptionBonus(Double optionBonus) {
		this.optionBonus = optionBonus;
	}

	public Double getSigningBonus() {
		return signingBonus;
	}

	public void setSigningBonus(Double signingBonus) {
		this.signingBonus = signingBonus;
	}

	public Double getRosterBonus() {
		return rosterBonus;
	}

	public void setRosterBonus(Double rosterBonus) {
		this.rosterBonus = rosterBonus;
	}

	public Double getWorkoutBonus() {
		return workoutBonus;
	}

	public void setWorkoutBonus(Double workoutBonus) {
		this.workoutBonus = workoutBonus;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
		this.status = status.equals("Active") ? "Contract" : status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
