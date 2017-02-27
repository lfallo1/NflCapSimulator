/**
 * 
 */
(function(){
	var rosterservices = angular.module('rosterservices', []);
	
	rosterservices.factory('ContractManagementService', function(){
    	var factory = {};
    	
    	factory.generateOverview = function(contracts, contractOverview){
			var overview = {
				'totalValue' : 0,
				'guaranteed': 0,
				'apy' : 0,
				'freeAgent' : "",
				'contractLength' : 0
			}
			
			//last year - length = start year
			//start index is start year - first year
			var filteredContracts = contracts.filter(function(d){if(d.status === 'Contract'){return d;}});
			var sortedContracts = filteredContracts.sort(function(a,b){return a.year > b.year ? 1 : a.year < b.year ? -1 : 0;});
			var startYearOfCurrentContract = sortedContracts[sortedContracts.length-1].year - contractOverview.years + 1;
			var length = sortedContracts[sortedContracts.length-1].year - startYearOfCurrentContract + 1;
			var startingIndex = startYearOfCurrentContract - sortedContracts[0].year;
			for(var i = startingIndex; i < startingIndex + length; i++){
				if(sortedContracts[i]){
					overview.totalValue += sortedContracts[i].capCharge;
					overview.guaranteed += sortedContracts[i].signingBonus + sortedContracts[i].optionBonus + sortedContracts[i].guaranteedBaseSalary;
				}
			}
			overview.apy = (overview.totalValue / contractOverview.years).toFixed(2);
			overview.contractLength = contractOverview.years;
			overview.freeAgent = factory.getFreeAgentText(contractOverview.freeAgentYear);
			return overview;
		};
		
        factory.calculateDeadMoney = function(roster, year){
            return ArrayServices.sum(roster.filter(function (d) {
                if (d.year == year && (d.contractStatus.id == 11)) {
                    return d;
                }
            }), 'capCharge');
        };
        
        factory.getFreeAgentText = function(freeAgentYear){
			var year = freeAgentYear.substring(0,4);
			var abbrev = freeAgentYear.length > 4 ? freeAgentYear.substring(4) : "U";
			var type = '';
			if(type!==null){
				switch (abbrev) {
					case "U":
						type = "UFA";
						break;
					case "E":
						type = "EFA";
						break;
					case "O":
						type = "Option";
						break;
					case "R":
						type = "RFA"
						break;
					case "V":
						type = "Contract Voids";
						break;
					default:
						break;
				}
			}
			return year + " ("+ type +")";
		};

        factory.inTop51or53 = function(contract, roster, year, type, team){
            var resultClass = {'background-color': 'white', 'color': 'black'};

            var filtered = roster.filter(function(d){
                if(d.year==year && d.status === 'Contract'){
                    return d;
                }
            }).sort(function(a,b){return b.capCharge - a.capCharge;});

            var limit = type === 'Season' ? 53 : 51;
            limit = limit > filtered.length ? filtered.length : limit;

            for(var i = 0; i < limit; i++){
                if(filtered[i].id === contract.id){
                    resultClass = {'background-color': '#'+team.primaryColor, 'color':'white'};
                    break;
                }
            }
            return resultClass;
        };

        factory.calculateTotalSalary = function(roster, year, type){
            var totalSalary = 0;
            if(!(roster===undefined)) {
                var filtered = roster.filter(function (d) {
                    if (d.year==year) {
                        return d;
                    }
                }).sort(function (a, b) {
                    return b.capCharge - a.capCharge;
                });

                if(type==='Offseason'){
                    for (var i = 0; i < filtered.length; i++) {
                        totalSalary += i < limit ? filtered[i].capCharge : filtered[i].signingBonus + filtered[i].optionBonus;
                    }
                }
                else{
                    totalSalary = ArrayServices.sum(filtered, 'capCharge');
                }

                totalSalary += factory.calculateDeadMoney(roster, year);
            }
            return totalSalary;
        };	
		
		return factory;
	});
	
})();