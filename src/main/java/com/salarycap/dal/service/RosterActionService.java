package com.salarycap.dal.service;

import com.salarycap.dto.ExtensionDtoWrapper;
import com.salarycap.dto.NewPlayerWrapper;
import com.salarycap.dto.TradePlayerDto;


public interface RosterActionService {
	void cut(Integer contractId);
	void june1cut(Integer contractId);
	void extension(ExtensionDtoWrapper dto);
	void restructure(Integer contractId, Double amount);
	void resetRoster();
	void addPlayer(NewPlayerWrapper data);
	void tradePlayer(TradePlayerDto tradePlayerDto);
}
