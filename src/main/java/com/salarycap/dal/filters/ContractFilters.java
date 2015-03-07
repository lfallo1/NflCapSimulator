package com.salarycap.dal.filters;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.salarycap.constants.SearchType;
import com.salarycap.dto.FilterObj;
import com.salarycap.resources.YearlyContract;

public class ContractFilters {
	public static List<YearlyContract> filter(String fieldName, Object value, List<YearlyContract> contracts, String searchType){
		List<YearlyContract> results = new ArrayList<>();
		for (YearlyContract contract : contracts) {
			try {
				Field f = YearlyContract.class.getDeclaredField(fieldName);
				f.setAccessible(true);
				String fieldValue = f.get(contract).toString();
				switch (searchType) {
					case SearchType.EQUALS:
						if(fieldValue.equals(value)){
							results.add(contract);
						}					
						break;
					case SearchType.LIKE:
						if(fieldValue.contains((CharSequence) value)){
							results.add(contract);
						}
						break;
					case SearchType.LESSTHAN:
						if(Integer.parseInt(fieldValue) < Integer.parseInt(value.toString())){
							results.add(contract);
						}
						break;
					case SearchType.GREATERTHAN:
						if(Integer.parseInt(fieldValue) > Integer.parseInt(value.toString())){
							results.add(contract);
						}
						break;						
					default:
						break;
				}
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public static List<YearlyContract> fullRosterFilter(List<YearlyContract> yearlyContracts, FilterObj filterObj){
		List<YearlyContract> results = new ArrayList<>();
		for (YearlyContract c : yearlyContracts) {
			if(c.getYear().equals(filterObj.getYear()) && c.getTeam().equals(filterObj.getTeam()) 
				&& filterObj.getContractStatuses().contains(c.getStatus())
				&& (filterObj.getPositions().contains(c.getPosition()))){
				results.add(c);
			}
		}
		return results;
	}
}
