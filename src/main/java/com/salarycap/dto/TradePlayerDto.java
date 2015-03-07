package com.salarycap.dto;

public class TradePlayerDto {
	private Integer contractId;
	private Integer newTeamId;
	private Integer playerId;

	public TradePlayerDto() {
	}

	public TradePlayerDto(Integer contractId, Integer newTeamId,
			Integer playerId) {
		this.contractId = contractId;
		this.newTeamId = newTeamId;
		this.playerId = playerId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getNewTeamId() {
		return newTeamId;
	}

	public void setNewTeamId(Integer newTeamId) {
		this.newTeamId = newTeamId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

}
