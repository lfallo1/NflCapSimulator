package com.salarycap.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.PositionDal;
import com.salarycap.resources.Position;
@Scope("session")
@RestController
@RequestMapping(value="/api/position")
public class PositionWebService {
	
	private PositionDal positionDal;
	
	@Autowired
	public PositionWebService(PositionDal positionDal){
		this.positionDal = positionDal;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Position> getAll(){
		return positionDal.getPositionList();
	}
}
