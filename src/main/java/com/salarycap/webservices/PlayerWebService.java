package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.PlayerDal;
import com.salarycap.resources.Player;
@Scope("session")
@RestController
@RequestMapping("/api/players")
public class PlayerWebService {
	
	private PlayerDal playerDal;
	
	@Autowired
	public PlayerWebService(PlayerDal playerDal){
		this.playerDal = playerDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Player> getAll(){
		return this.playerDal.getPlayers();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Player getById(@PathVariable("id") Integer id){
		return this.playerDal.getByPlayerId(id);
	}	
}
