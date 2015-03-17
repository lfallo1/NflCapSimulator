(function() {

    var restServices = angular.module('restservices', ['ngResource']);

    restServices.service('ContractResource', function($resource) {
        return $resource("api/yearlycontracts/:id", {}, {
            contractFilter: {
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                url:"api/yearlycontracts/f", isArray: true
            },
            getByTeam: {
                method: "GET",
                url:"api/yearlycontracts/team/:teamId", isArray: true
            },            
            getByPlayer : {
            	method : "GET",
            	url : "api/yearlycontracts/player/:id",
            	isArray : true
            },
            isOnActiveRoster : {
            	method : "GET",
            	url : "api/yearlycontracts/isOnActiveRoster/:id/:teamId",
            	isArray : false
            },
            getFirstOffActiveRoster : {
            	method : "GET",
            	url : "api/yearlycontracts/getFirstOffActiveRoster/:id/:teamId",
            	isArray : false            	
            },
            calculateTotalSalary : {
            	method : "GET",
            	url : "api/yearlycontracts/calculateTotalSalary/:yearType/:year/:teamId"
            }
        });
    });
    
    restServices.service('ContractOverviewResource', function($resource){
    	return $resource("api/contractoverview/:id", {}, {
    		getByPlayer : {
    			method : "GET",
    			url : "api/contractoverview/player/:playerId",
    			isArray : false
    		}
    	})
    });
    
    restServices.service('CalculationResource', function($resource){
    	return $resource("api/calculation/:id", {},{
    		getHighestPaid : {
    			method : "GET",
    			url : "api/calculation/getHighestPaid/:year",
    			isArray : true
    		},
    		getTeamSalaries : {
    			method : "GET",
    			url : "api/calculation/getAllSalariesByYear/:year",
    			isArray : true
    		},    		
    		getAllSalariesByTeam : {
    			method : "GET",
    			url : "api/calculation/getAllSalariesByTeam/:team",
    			isArray : true
    		}
    	});
    });

    restServices.service('ContractStatusResource', function($resource){
        return $resource("api/contractstatus/:id");
    });

    restServices.service('SalaryCapConstantsResource', function($resource){
        return $resource("api/salarycapconstants/:id", {},{
        	getByYearAndTeam : {
        		method : "GET",
        		url : "api/salarycapconstants/byYear/:year/:teamId",
        		isArray : false
        	},
        	getByTeam : {
        		method : "GET",
        		url : "api/salarycapconstants/byTeam/:teamId",
        		isArray : true
        	}
        });
    });
    
    restServices.service('PositionResource', function($resource){
        return $resource("api/position/:id");
    });    
    

    restServices.service('RosterActionResource', function($resource){
        return $resource("api/rosteractions/:id", {}, {
            cut : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:"api/rosteractions/cut"
            },
            june1cut : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:"api/rosteractions/june1cut"
            },
            restructure : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:"api/rosteractions/restructure"            	
            },
            extension : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:"api/rosteractions/extension"            	
            },
            addPlayer : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:"api/rosteractions/addplayer"            	
            },                 
            trade : {
            	method : "POST",
            	headers: {'Content-Type': 'application/json'},
            	url : "api/rosteractions/trade"
            },
            reset : {
            	method : "POST",
            	headers: {'Content-Type': 'application/json'},
            	url : "api/rosteractions/reset"
            }
        });
    });

    restServices.service('TransactionResource', function($resource){
        return $resource("api/transactions/:id", {}, {
            getByPlayer : {
            	method : "GET",
            	url : "api/transactions/player/:id",
            	isArray : true
            },
            getByTeam : {
            	method : "GET",
            	url : "api/transactions/team/:teamId",
            	isArray : true
            },
            undoTransaction : {
            	method : "POST",
            	url : "api/transactions/undo/:id"
            },
            tryGetLastTransaction : {
            	method : "GET",
            	url : "api/transactions/lastTransaction",
            	isArray : false
            }
        });
    });
    
    restServices.service('PlayerResource', function($resource){
    	return $resource("api/players/:id");
    });
    
    restServices.service('MinimumSalaryResource', function($resource){
    	return $resource("api/minimumsalary/:id", {}, {
    		getByYearAndCreditedSeasons : {
    			method : "GET",
    			url : "api/minimumsalary/:year/:cs"
    		}
    	});
    });
    
    restServices.service('DeadMoneyResource', function($resource){
    	return $resource("api/deadmoney/:id", {}, {
    		getByYearAndTeam : {
    			method : "GET",
    			url : "api/deadmoney/year/:year/:teamId",
    			isArray : true
    		},
    		getByPlayer : {
    			method : "GET",
    			url : "api/deadmoney/player/:playerId",
    			isArray : true
    		}
    	});
    });    
    
    restServices.service('TeamResource', function($resource){
    	return $resource("api/team/:id");
    });

}());