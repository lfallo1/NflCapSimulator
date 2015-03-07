package com.salarycap.dal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dal.TeamDal;
import com.salarycap.dal.service.TeamService;
import com.salarycap.resources.Team;

@Scope("session")
@Service
public class TeamServiceImpl implements TeamService {
	
	private TeamDal teamDal;

	@Autowired
	public TeamServiceImpl(TeamDal teamDal){
		this.teamDal = teamDal;
	}

	@Override
	public Team getById(Integer id) {
		return this.teamDal.getById(id);
	}

	@Override
	public List<Team> getAll() {
		return this.teamDal.getTeams();
	}

	@Override
	public List<Team> getAllSorted() {
		return this.teamDal.getAllSorted();
	}
}
