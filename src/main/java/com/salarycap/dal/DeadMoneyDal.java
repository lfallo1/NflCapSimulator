package com.salarycap.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.DeadMoneyDao;
import com.salarycap.resources.DeadMoney;
import com.salarycap.resources.Player;
@Scope("session")
@Service
public class DeadMoneyDal {
	private List<DeadMoney> deadMoneyList;
	private DeadMoneyDao deadMoneyDao;

	@Autowired
	public DeadMoneyDal(DeadMoneyDao deadMoneyDao) {
		this.deadMoneyDao = deadMoneyDao;
	}

	public void reset() {
		deadMoneyList = deadMoneyDao.getAll();
	}

	public void populatePlayers(PlayerDal playerDal) {
		for (DeadMoney d : deadMoneyList) {
			Player p = playerDal.getByPlayerId(d.getPlayer().getId());
			d.setPlayer(p);
		}
	}

	public Double getTotalDeadMoneyByYearAndTeam(Integer year, Integer teamId) {

		Double sum = 0.0;
		for (DeadMoney d : deadMoneyList) {
			try {
				if (d.getYear().equals(year) && d.getTeam().equals(teamId)) {
					sum += d.getDeadMoney();
				}
			} catch (Exception e) {
				System.out.println("getTotalDeadMoneyByYearAndTeam error: " + e.getMessage());
				System.out.println(d);
			}
		}
		return sum;

	}

	public Integer getLastId() {
		Collections.sort(deadMoneyList, new Comparator<DeadMoney>() {
			@Override
			public int compare(DeadMoney d1, DeadMoney d2) {
				return -d1.getId().compareTo(d2.getId());
			}
		});
		return deadMoneyList.get(0).getId() + 1;
	}

	public List<DeadMoney> getDeadMoneyList() {
		return this.deadMoneyList;
	}

	public List<DeadMoney> getByYearAndTeam(Integer year, Integer teamId) {
		List<DeadMoney> list = new ArrayList<>();
		for (DeadMoney d : deadMoneyList) {
			if (d.getYear().equals(year) && d.getTeam().equals(teamId)) {
				list.add(d);
			}
		}
		return list;
	}

	public List<DeadMoney> getByPlayer(Integer playerId) {
		List<DeadMoney> list = new ArrayList<>();
		for (DeadMoney d : deadMoneyList) {
			if (d.getPlayer().getId().equals(playerId)) {
				list.add(d);
			}
		}
		return list;
	}

	public void remove(Integer id) {
		for (DeadMoney d : deadMoneyList) {
			if (d.getId().equals(id)) {
				deadMoneyList.remove(d);
				break;
			}
		}
	}

	public void addAll(List<DeadMoney> oldDeadMoney) {
		for (DeadMoney d : oldDeadMoney) {
			d.setId(getLastId());
			deadMoneyList.add(d);
		}
	}
}
