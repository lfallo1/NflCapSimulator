package com.salarycap.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salarycap.dal.service.RosterActionService;
import com.salarycap.dto.ExtensionDtoWrapper;
import com.salarycap.dto.NewPlayerWrapper;
import com.salarycap.dto.RestructureDto;
import com.salarycap.dto.TradePlayerDto;
@Scope("session")
@RestController
@RequestMapping(value="/api/rosteractions")
public class RosterActionWebService {
	
	private RosterActionService rosterActionService;
	
	@Autowired
	public RosterActionWebService(RosterActionService rosterActionService){
		this.rosterActionService = rosterActionService;
	}
	
	@RequestMapping(value="/cut", method=RequestMethod.POST)
	public void cut(@RequestBody Integer contractId){
		rosterActionService.cut(contractId);
	}
	
	@RequestMapping(value="/june1cut", method=RequestMethod.POST)
	public void june1cut(@RequestBody Integer contractId){
		rosterActionService.june1cut(contractId);
	}
	
	@RequestMapping(value="/restructure", method=RequestMethod.POST)
	public void restructure(@RequestBody RestructureDto data){
		rosterActionService.restructure(data.getContractId(), data.getAmount());
	}
	
	@RequestMapping(value="/extension", method=RequestMethod.POST)
	public void extension(@RequestBody ExtensionDtoWrapper data){
		rosterActionService.extension(data);
	}		
	
	@RequestMapping(value="/addplayer", method=RequestMethod.POST)
	public void addPlayer(@RequestBody NewPlayerWrapper data){
		rosterActionService.addPlayer(data);
	}
	
	@RequestMapping(value="/trade", method=RequestMethod.POST)
	public void trade(@RequestBody TradePlayerDto tradePlayerDto){
		rosterActionService.tradePlayer(tradePlayerDto);
	}		
	
	@RequestMapping(value="/reset", method=RequestMethod.POST)
	public void reset(){
		rosterActionService.resetRoster();
	}
}
