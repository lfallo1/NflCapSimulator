package com.salarycap.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.TeamDao;
import com.salarycap.resources.Team;
@Scope("session")
@Service
public class TeamDal {
	private List<Team> teams;
	
	@Autowired
	public TeamDal(TeamDao teamDao){
		teams = teamDao.getAll();
	}	
	
	public List<Team> getTeams(){
		return this.teams;
	}
	
	public Team getById(Integer id){
		for (Team t : teams) {
			if(t.getId().equals(id)){
				return t;
			}
		}
		return null;
	}

	public List<Team> getAllSorted() {
		List<Team> teamsExcludingNotAvailable = new ArrayList<>();
		for (Team t : teams) {
			if(!t.getName().contains("Not available")){
				teamsExcludingNotAvailable.add(t);
			}
		}
		return teamsExcludingNotAvailable;
	}
}
