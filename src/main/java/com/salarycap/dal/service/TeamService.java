package com.salarycap.dal.service;

import java.util.List;

import com.salarycap.resources.Team;

public interface TeamService {
	Team getById(Integer id);
	List<Team> getAll();
	List<Team> getAllSorted();
}
