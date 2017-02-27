package com.salarycap.dal.serviceimpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.salarycap.dal.ContractOverviewDal;
import com.salarycap.dal.DeadMoneyDal;
import com.salarycap.dal.PlayerDal;
import com.salarycap.dal.YearlyContractDal;
import com.salarycap.dal.filters.ContractFilters;
import com.salarycap.dal.service.ContractManagementService;
import com.salarycap.dto.FilterObj;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.Player;
import com.salarycap.resources.YearlyContract;

@Scope("session")
@Service
public class ContractManagementServiceImpl implements ContractManagementService {
	private PlayerDal playerDal;
	private ContractOverviewDal contractOverviewDal;
	private YearlyContractDal yearlyContractDal;
	private DeadMoneyDal deadMoneyDal;

	@Autowired()
	public ContractManagementServiceImpl(PlayerDal playerDal,
			ContractOverviewDal contractOverviewDal,
			YearlyContractDal yearlyContractDal, DeadMoneyDal deadMoneyDal) {
		this.playerDal = playerDal;
		this.yearlyContractDal = yearlyContractDal;
		this.contractOverviewDal = contractOverviewDal;
		this.deadMoneyDal = deadMoneyDal;
		reset();
	}

	@Override
	public void reset() {
		this.playerDal.reset();
		yearlyContractDal.reset();
		yearlyContractDal.populatePlayers(playerDal);
		contractOverviewDal.reset();
		contractOverviewDal.populatePlayers(playerDal);
		deadMoneyDal.reset();
		deadMoneyDal.populatePlayers(playerDal);
		addFreeAgentContracts();
	}

	@Override
	public List<YearlyContract> getFullRosterFilter(FilterObj filterObj) {
		return ContractFilters.fullRosterFilter(
				yearlyContractDal.getYearlyContracts(), filterObj);
	}

	@Override
	public List<YearlyContract> getByFilter(String fieldName, Object value,
			String searchType) {
		return ContractFilters.filter(fieldName, value,
				yearlyContractDal.getYearlyContracts(), searchType);
	}

	@Override
	public Boolean isOnActiveRoster(String yearType, Integer contractId,
			Integer year, Integer teamId) {
		List<YearlyContract> yearlyContracts = yearlyContractDal
				.getByTeam(teamId);
		Integer limit = yearType == "Offseason" ? 51 : 53;
		Integer count = 0;
		Collections.sort(yearlyContracts, new Comparator<YearlyContract>() {
			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				return -c1.getCapCharge().compareTo(c2.getCapCharge());
			}
		});
		for (int i = 0; i < yearlyContracts.size(); i++) {
			if (yearlyContracts.get(i).getYear().equals(year)
					&& yearlyContracts.get(i).getStatus().contains("Active")) {
				count++;
			}
			if (yearlyContracts.get(i).getId().equals(contractId)) {
				return count <= limit;
			}
		}
		return false;
	}

	@Override
	public Double calculateTotalSalary(String yearType, Integer year,
			Integer teamId) {
		List<YearlyContract> yearlyContracts = yearlyContractDal
				.getByTeam(teamId);
		Integer limit = "Offseason".equals(yearType) ? 51 : 53;
		Integer count = 0;
		Double totalSalary = 0.0;
		Collections.sort(yearlyContracts, new Comparator<YearlyContract>() {
			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				return -c1.getCapCharge().compareTo(c2.getCapCharge());
			}
		});
		for (int i = 0; i < yearlyContracts.size(); i++) {
			try {
				if (yearlyContracts.get(i).getYear().equals(year)
						&& yearlyContracts.get(i).getStatus().contains("Contract")) {
					if (count < limit) {
						totalSalary += yearlyContracts.get(i).getCapCharge();
					} else {
						totalSalary += yearlyContracts.get(i).getSigningBonus()
								+ yearlyContracts.get(i).getOptionBonus();
					}
					count++;
				}
			} catch (Exception e) {
				System.out.println("calculateTotalSalary error: " + e.getMessage());
				System.out.println(yearlyContracts.get(i));
			}
		}
		totalSalary += deadMoneyDal
				.getTotalDeadMoneyByYearAndTeam(year, teamId);
		return totalSalary;
	}

	@Override
	public YearlyContract getLastOnActiveRoster(String yearType, Integer year,
			Integer teamId) {
		List<YearlyContract> yearlyContracts = yearlyContractDal
				.getByTeam(teamId);
		Integer limit = yearType == "Offseason" ? 51 : 53;
		Integer count = 0;
		Collections.sort(yearlyContracts, new Comparator<YearlyContract>() {
			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				return -c1.getCapCharge().compareTo(c2.getCapCharge());
			}
		});
		for (int i = 0; i < yearlyContracts.size(); i++) {
			if (yearlyContracts.get(i).getYear().equals(year)
					&& yearlyContracts.get(i).getStatus().contains("Active")) {
				count++;
			}
			if (count == limit) {
				return yearlyContracts.get(i);
			}
		}
		return null;
	}

	@Override
	public YearlyContract getFirstOffActiveRoster(String yearType,
			Integer year, Integer teamId) {
		List<YearlyContract> yearlyContracts = yearlyContractDal
				.getByTeam(teamId);
		Integer limit = yearType == "Offseason" ? 51 : 53;
		Integer count = 0;
		Collections.sort(yearlyContracts, new Comparator<YearlyContract>() {
			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				return -c1.getCapCharge().compareTo(c2.getCapCharge());
			}
		});
		for (int i = 0; i < yearlyContracts.size(); i++) {
			if (yearlyContracts.get(i).getYear().equals(year)
					&& yearlyContracts.get(i).getStatus().contains("Active")) {
				count++;
			}
			if (count == (limit + 1)) {
				return yearlyContracts.get(i);
			}
		}
		return null;
	}

	private void addFreeAgentContracts() {
		for (ContractOverview c : contractOverviewDal.getContractOverviewList()) {
			if (c.getFreeAgentYear() != null
					&& !c.getFreeAgentYear().equals("0")) {
				yearlyContractDal.add(createFreeAgentContract(c));
			}
		}
	}

	@Override
	public YearlyContract createFreeAgentContract(ContractOverview c) {
		YearlyContract yc = new YearlyContract(
				yearlyContractDal.getLastId(), c.getPlayer().getId(),
				c.getTeam(), getFreeAgentYear(c), 0.0, 0.0, 0.0,
				0.0, 0.0, "", 0.0, 0.0, 0.0, 0.0, "", c.getRole(),
				getFreeAgentType(c.getFreeAgentYear()), c.getPosition());
		yc.setPlayer(playerDal.getByPlayerId(c.getPlayer().getId()));
		return yc;
	}
	
	private Integer getFreeAgentYear(ContractOverview c){
		if(StringUtils.isEmpty(c.getFreeAgentYear()) || c.getFreeAgentYear().length() < 4){
			List<YearlyContract> contracts = this.yearlyContractDal.getByPlayer(c.getPlayer().getId());
			Collections.sort(contracts, new Comparator<YearlyContract>() {
				@Override
				public int compare(YearlyContract c1, YearlyContract c2) {
					return -c1.getYear().compareTo(c2.getYear());
				}
			});
			return contracts.size() > 0 ? contracts.get(0).getYear() : 0;
		}
		return Integer.parseInt(c.getFreeAgentYear().subSequence(0, 4).toString());
	}

	private String getFreeAgentType(String s) {
		if (s.contains("E")) {
			return "EFA";
		} else if (s.contains("R")) {
			return "RFA";
		} else {
			return "UFA";
		}
	}

	@Override
	public ContractOverview generateContractOverview(List<YearlyContract> yearlyContracts) {
		Collections.sort(yearlyContracts, new Comparator<YearlyContract>(){

			@Override
			public int compare(YearlyContract c1, YearlyContract c2) {
				// TODO Auto-generated method stub
				return -c1.getYear().compareTo(c2.getYear());
			}
		});
		
		YearlyContract template = yearlyContracts.get(0);
		Integer freeAgentYear = template.getYear() + 1;
		Player player = template.getPlayer();
		Integer team = template.getTeam();
		Integer length = yearlyContracts.size();
		return contractOverviewDal.update(freeAgentYear.toString(), player, team, length);
	}
	
	@Override
	public ContractOverview createContractOverview(ContractOverview contractOverview) {
		return new ContractOverview(contractOverview);
	}

}
