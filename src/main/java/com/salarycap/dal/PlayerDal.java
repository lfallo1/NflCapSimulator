package com.salarycap.dal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.PlayerDao;
import com.salarycap.resources.Player;
@Scope("session")
@Service
public class PlayerDal {
	private List<Player> players;
	private PlayerDao playerDao;
	
	@Autowired
	public PlayerDal (PlayerDao playerDao){
		this.playerDao = playerDao;
	}
	
	public List<Player> getPlayers(){
		return this.players;
	}
	
	public Player getByPlayerId(Integer id){
		for (Player player : players) {
			if(player.getId().equals(id)){
				return player;
			}
		}
		return null;
	}

	public void reset() {
		this.players = playerDao.getAll();
	}

	public Integer getLastId() {
		Collections.sort(players, new Comparator<Player>(){
			@Override
			public int compare(Player p1, Player p2) {
				return -p1.getId().compareTo(p2.getId());
			}
		});
		return players.get(0).getId()+1;
	}

	public void add(Player player) {
		this.players.add(player);
	}
	
	public void remove(Player player){
		for (Player p : players) {
			if(p.getId().equals(player.getId())){
				players.remove(p);
				break;
			}
		}
	}
}
