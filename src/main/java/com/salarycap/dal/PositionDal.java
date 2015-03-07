package com.salarycap.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dao.PositionDao;
import com.salarycap.resources.Position;
@Scope("session")
@Service
public class PositionDal {
	private List<Position> positionList;
	
	@Autowired
	public PositionDal(PositionDao positionDao){
		positionList = positionDao.getAll();
	}	
	
	public List<Position> getPositionList(){
		return this.positionList;
	}

	public Position getByName(String position) {
		for (Position p : positionList) {
			if(p.getPositionName().equals(position)){
				return p;
			}
		}
		return null;
	}
}
