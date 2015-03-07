package com.salarycap.dal.service;

import java.util.List;

import com.salarycap.dto.FilterObj;
import com.salarycap.resources.ContractOverview;
import com.salarycap.resources.YearlyContract;

public interface ContractManagementService {
	void reset();
	List<YearlyContract> getFullRosterFilter(FilterObj filterObj);
	List<YearlyContract> getByFilter(String fieldName, Object value, String searchType);
	Boolean isOnActiveRoster(String yearType, Integer contractId, Integer year, Integer teamId);
	Double calculateTotalSalary(String yearType, Integer year, Integer teamId);
	YearlyContract getLastOnActiveRoster(String yearType, Integer year, Integer teamId);
	YearlyContract getFirstOffActiveRoster(String yearType, Integer year, Integer teamId);
	ContractOverview generateContractOverview(List<YearlyContract> yearlyContracts);
	YearlyContract createFreeAgentContract(ContractOverview c);
	ContractOverview createContractOverview(ContractOverview byPlayer);
}
