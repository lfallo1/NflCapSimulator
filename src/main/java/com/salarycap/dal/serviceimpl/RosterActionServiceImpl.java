package com.salarycap.dal.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dal.service.RosterActionService;
import com.salarycap.dal.service.TransactionService;
import com.salarycap.dal.serviceimpl.rosteractions.AddPlayerService;
import com.salarycap.dal.serviceimpl.rosteractions.CutService;
import com.salarycap.dal.serviceimpl.rosteractions.ExtensionService;
import com.salarycap.dal.serviceimpl.rosteractions.RestructureService;
import com.salarycap.dal.serviceimpl.rosteractions.TradePlayerService;
import com.salarycap.dto.ExtensionDtoWrapper;
import com.salarycap.dto.NewPlayerWrapper;
import com.salarycap.dto.TradePlayerDto;

@Scope("session")
@Service
public class RosterActionServiceImpl implements RosterActionService {

	private TransactionService transactionService;
	private ExtensionService extensionService;
	private RestructureService restructureService;
	private CutService cutService;
	private AddPlayerService addPlayerService;
	private ContractManagementService contractManagementService;
	private TradePlayerService tradePlayerService;

	@Autowired
	public RosterActionServiceImpl(ContractManagementService contractManagementService, 
			TransactionService transactionService, ExtensionService extensionService,
			RestructureService restructureService, CutService cutService,
			AddPlayerService addPlayerService, TradePlayerService tradePlayerService){
		this.contractManagementService = contractManagementService;
		this.transactionService = transactionService;
		this.extensionService = extensionService;
		this.restructureService = restructureService;
		this.cutService = cutService;
		this.addPlayerService = addPlayerService;
		this.tradePlayerService = tradePlayerService;
	}
	
	@Override
	public void cut(Integer contractId) {
		this.cutService.cut(contractId);
	}

	@Override
	public void june1cut(Integer contractId) {
		this.cutService.june1Cut(contractId);
	}

	@Override
	public void extension(ExtensionDtoWrapper dto) {
		this.extensionService.doExtension(dto);
	}
	
	@Override
	public void restructure(Integer contractId, Double amount) {
		this.restructureService.doRestructure(contractId, amount);
	}

	@Override
	public void resetRoster() {
		contractManagementService.reset();
		transactionService.removeAllTransactions();
	}

	@Override
	public void addPlayer(NewPlayerWrapper newPlayerWrapper) {
		this.addPlayerService.addPlayer(newPlayerWrapper);
	}

	@Override
	public void tradePlayer(TradePlayerDto tradePlayerDto) {
		this.tradePlayerService.doTrade(tradePlayerDto);
	}
}
