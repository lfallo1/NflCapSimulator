(function() {

    var restServices = angular.module('restservices', ['ngResource']);

    restServices.service('ContractResource', function($resource) {
        return $resource(_contextPath + "api/yearlycontracts/:id", {}, {
            contractFilter: {
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                url:_contextPath + "api/yearlycontracts/f", isArray: true
            },
            getByTeam: {
                method: "GET",
                url:_contextPath + "api/yearlycontracts/team/:teamId", isArray: true
            },            
            getByPlayer : {
            	method : "GET",
            	url : _contextPath + "api/yearlycontracts/player/:id",
            	isArray : true
            },
            isOnActiveRoster : {
            	method : "GET",
            	url : _contextPath + "api/yearlycontracts/isOnActiveRoster/:id/:teamId",
            	isArray : false
            },
            getFirstOffActiveRoster : {
            	method : "GET",
            	url : _contextPath + "api/yearlycontracts/getFirstOffActiveRoster/:id/:teamId",
            	isArray : false            	
            },
            calculateTotalSalary : {
            	method : "GET",
            	url : _contextPath + "api/yearlycontracts/calculateTotalSalary/:yearType/:year/:teamId"
            }
        });
    });
    
    restServices.service('ContractOverviewResource', function($resource){
    	return $resource(_contextPath + "api/contractoverview/:id", {}, {
    		getByPlayer : {
    			method : "GET",
    			url : _contextPath + "api/contractoverview/player/:playerId",
    			isArray : false
    		}
    	})
    });
    
    restServices.service('CalculationResource', function($resource){
    	return $resource(_contextPath + "api/calculation/:id", {},{
    		getHighestPaid : {
    			method : "GET",
    			url : _contextPath + "api/calculation/getHighestPaid/:year",
    			isArray : true
    		},
    		getTeamSalaries : {
    			method : "GET",
    			url : _contextPath + "api/calculation/getAllSalariesByYear/:year",
    			isArray : true
    		},    		
    		getAllSalariesByTeam : {
    			method : "GET",
    			url : _contextPath + "api/calculation/getAllSalariesByTeam/:team",
    			isArray : true
    		}
    	});
    });

    restServices.service('ContractStatusResource', function($resource){
        return $resource(_contextPath + "api/contractstatus/:id");
    });

    restServices.service('SalaryCapConstantsResource', function($resource){
        return $resource(_contextPath + "api/salarycapconstants/:id", {},{
        	getByYearAndTeam : {
        		method : "GET",
        		url : _contextPath + "api/salarycapconstants/byYear/:year/:teamId",
        		isArray : false
        	},
        	getByTeam : {
        		method : "GET",
        		url : _contextPath + "api/salarycapconstants/byTeam/:teamId",
        		isArray : true
        	}
        });
    });
    
    restServices.service('PositionResource', function($resource){
        return $resource(_contextPath + "api/position/:id");
    });    
    

    restServices.service('RosterActionResource', function($resource){
        return $resource(_contextPath + "api/rosteractions/:id", {}, {
            cut : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:_contextPath + "api/rosteractions/cut"
            },
            june1cut : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:_contextPath + "api/rosteractions/june1cut"
            },
            restructure : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:_contextPath + "api/rosteractions/restructure"            	
            },
            extension : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:_contextPath + "api/rosteractions/extension"            	
            },
            addPlayer : {
                method : "POST",
                headers: {'Content-Type': 'application/json'},
                url:_contextPath + "api/rosteractions/addplayer"            	
            },                 
            trade : {
            	method : "POST",
            	headers: {'Content-Type': 'application/json'},
            	url : _contextPath + "api/rosteractions/trade"
            },
            reset : {
            	method : "POST",
            	headers: {'Content-Type': 'application/json'},
            	url : _contextPath + "api/rosteractions/reset"
            }
        });
    });

    restServices.service('TransactionResource', function($resource){
        return $resource(_contextPath + "api/transactions/:id", {}, {
            getByPlayer : {
            	method : "GET",
            	url : _contextPath + "api/transactions/player/:id",
            	isArray : true
            },
            getByTeam : {
            	method : "GET",
            	url : _contextPath + "api/transactions/team/:teamId",
            	isArray : true
            },
            undoTransaction : {
            	method : "POST",
            	url : _contextPath + "api/transactions/undo/:id"
            },
            tryGetLastTransaction : {
            	method : "GET",
            	url : _contextPath + "api/transactions/lastTransaction",
            	isArray : false
            }
        });
    });
    
    restServices.service('PlayerResource', function($resource){
    	return $resource(_contextPath + "api/players/:id");
    });
    
    restServices.service('MinimumSalaryResource', function($resource){
    	return $resource(_contextPath + "api/minimumsalary/:id", {}, {
    		getByYearAndCreditedSeasons : {
    			method : "GET",
    			url : _contextPath + "api/minimumsalary/:year/:cs"
    		}
    	});
    });
    
    restServices.service('DeadMoneyResource', function($resource){
    	return $resource(_contextPath + "api/deadmoney/:id", {}, {
    		getByYearAndTeam : {
    			method : "GET",
    			url : _contextPath + "api/deadmoney/year/:year/:teamId",
    			isArray : true
    		},
    		getByPlayer : {
    			method : "GET",
    			url : _contextPath + "api/deadmoney/player/:playerId",
    			isArray : true
    		}
    	});
    });    
    
    restServices.service('TeamResource', function($resource){
    	return $resource(_contextPath + "api/team/:id");
    });

}());