package com.salarycap.resources;

import java.sql.Date;

import org.joda.time.LocalDate;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TableName;
import com.salarycap.annotations.TemplateConstructor;

@TableName(value="player")
public class Player {

	@Column("id")
	private Integer id;
	@Column("accrued")
	private Integer accrued;
	@Column("date_of_birth")
	private LocalDate dateOfBirth;
	@Column("name")
	private String name;
	@Column("notes")
	private String notes;
	@Column("college")
	private String college;
	@Column("draft_pick")
	private Integer draftPick;
	@Column("draft_round")
	private Integer draftRound;
	@Column("draft_year")
	private Integer draftYear;
	@Column("height")
	private String height;
	@Column("original_team_id")
	private Integer originalTeamId;
	@Column("weight")
	private Integer weight;
	@Column("code")
	private String code;	
	

	@SuppressWarnings("deprecation")
	@TemplateConstructor
	public Player(@PropertyName("id") Integer id, @PropertyName("accrued") Integer accrued, 
			@PropertyName("dateOfBirth") Date dateOfBirth, @PropertyName("name") String name,
			@PropertyName("notes") String notes, @PropertyName("college") String college,
			@PropertyName("draftPick") Integer draftPick, @PropertyName("draftRound") Integer draftRound,
			@PropertyName("draftYear") Integer draftYear, @PropertyName("height") String height,
			@PropertyName("originalTeamId") Integer originalTeamId, @PropertyName("weight") Integer weight,
			@PropertyName("code") String code) {
		this.id = id;
		this.accrued = accrued;
		this.dateOfBirth = new LocalDate(dateOfBirth.getYear(), dateOfBirth.getMonth()+1, dateOfBirth.getDate());;
		this.name = name;
		this.notes = notes;
		this.college = college;
		this.draftPick = draftPick;
		this.draftRound = draftRound;
		this.draftYear = draftYear;
		this.height = height;
		this.originalTeamId = originalTeamId;
		this.weight = weight;
		this.code = code;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccrued() {
		return accrued;
	}

	public void setAccrued(Integer accrued) {
		this.accrued = accrued;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Integer getDraftPick() {
		return draftPick;
	}

	public void setDraftPick(Integer draftPick) {
		this.draftPick = draftPick;
	}

	public Integer getDraftRound() {
		return draftRound;
	}

	public void setDraftRound(Integer draftRound) {
		this.draftRound = draftRound;
	}

	public Integer getDraftYear() {
		return draftYear;
	}

	public void setDraftYear(Integer draftYear) {
		this.draftYear = draftYear;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getOriginalTeamId() {
		return originalTeamId;
	}

	public void setOriginalTeamId(Integer originalTeamId) {
		this.originalTeamId = originalTeamId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	

}
