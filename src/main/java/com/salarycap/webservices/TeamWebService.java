package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.service.TeamService;
import com.salarycap.resources.Team;
@Scope("session")
@RestController
@RequestMapping(value="/api/team")
public class TeamWebService {
	
	private TeamService teamService;
	
	@Autowired
	public TeamWebService(TeamService teamService){
		this.teamService = teamService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Team> getAll(){
		return teamService.getAllSorted();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Team getByTeamId(@PathVariable Integer id){
		return teamService.getById(id);
	}	
}
