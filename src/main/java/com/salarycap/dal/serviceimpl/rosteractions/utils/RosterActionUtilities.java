package com.salarycap.dal.serviceimpl.rosteractions.utils;

import java.util.List;

import com.salarycap.dal.DeadMoneyDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.resources.DeadMoney;
import com.salarycap.resources.Player;
import com.salarycap.resources.YearlyContract;

public class RosterActionUtilities {
	public static YearlyContract createNewContract(YearlyContract c) {
		return new YearlyContract(c.getId(), c.getPlayer(), c.getTeam(), c.getYear(), c.getBaseSalary(),
				c.getCapCharge(), c.getCapSavings(), c.getDeadMoney(), c.getGuaranteedBaseSalary(), c.getNotes(),
				c.getOptionBonus(), c.getSigningBonus(), c.getRosterBonus(), c.getWorkoutBonus(), c.getTeamName(),
				c.getRole(), c.getStatus(), c.getPosition());
	}	
	
	public static void addDeadMoney(DeadMoneyDal deadMoneyDal, YearlyContract yearlyContract, YearlyContractDal yearlyContractDal, Double deadMoney, Integer year, Player player){
		if(deadMoney > 0){
			deadMoneyDal.getDeadMoneyList().add(new DeadMoney(deadMoneyDal.getLastId(), deadMoney, year,
					player, yearlyContract.getTeam()));
		}		
	}	

	/**
	 * Given a list of player contracts, return how many years remain
	 * where that player is under contract (note that this excludes any
	 * entries where the player is a FA or counting as dead money)
	 * @return
	 */
	public static Integer getYearsRemaining(List<YearlyContract> contracts, Integer year){
		Integer count = 0;
		for (YearlyContract contract : contracts) {
			if(contract.getYear()>=year && contract.getStatus().equals("Contract")){
				count++;
			}
		}
		return count;
	}	
}
