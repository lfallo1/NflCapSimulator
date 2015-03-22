/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('ExtensionCtrl', ['$rootScope', '$scope', '$routeParams', '$location', 'ContractResource', 'PlayerResource', 'MinimumSalaryResource', 'ExtensionValidationService', 'RosterActionResource', 'TeamResource', 'ColorServices', 'TransactionResource', 'MessageService', function($rootScope, $scope, $routeParams, $location, ContractResource, PlayerResource, MinimumSalaryResource, ExtensionValidationService, RosterActionResource, TeamResource, ColorServices, TransactionResource, MessageService){
		$scope.title = "Extension";
		$scope.signingBonus = 1000000;
		$scope.extensionObj = [];
		if($routeParams.contractId != null && !isNaN($routeParams.contractId)){
			ContractResource.get({
				id : $routeParams.contractId
			}).$promise.then(function(data){
				$scope.contract = data;
				$scope.year = $scope.contract.year;
				
				TeamResource.get({id : $scope.contract.team}).$promise.then(function(data){
					$scope.team = data;
				});				
				
				ContractResource.getByPlayer({
					id : $scope.contract.player.id
				}).$promise.then(function(data){
					$scope.contracts = data.filter(function(d){
						if(d.year>=$scope.contract.year && d.status === 'Contract'){
							return d;
						}
					}).sort(function(a,b){return a.year - b.year;});
					$rootScope.readyState = true;
				});
			});
			
			MinimumSalaryResource.query().$promise.then(function(data){
				$scope.minimumSalaries = data;
			});
		}
		else{
			$location.path("/");
		}
		
        //Year Dropdown
        $scope.yearDropdownSettings = {showUncheckAll : false, scrollable : false, selectionLimit : 1, displayProp: 'id', dynamicTitle:false, idProp:'id'};
        $scope.yearDropdownLabel = {buttonDefaultText: 'Years'};
        $scope.years = [{"id":1}, {"id":2},{"id":3},{"id":4},{"id":5},{"id":6},{"id":7}];
        $scope.selectedYears = {id: 1};	
        
        $scope.getMinimumSalary = function(year, creditedSeasons){
    		MinimumSalaryResource.getByYearAndCreditedSeasons({
    			year : year,
    			cs : creditedSeasons
    		}).$promise.then(function(data){
    			return data.minimumSalary;
    		});
        };
        
        $scope.getNumber = function(years) {
        	if(years){
        		return new Array(parseInt(years.id));
        	}
        };
        
        $scope.validateObj = function(index, isNew){
        	if($scope.year && $scope.contracts && $scope.minimumSalaries && $scope.contract){
        		if(isNew){
        			return ExtensionValidationService.validate($scope.year, $scope.contract.player, $scope.extensionObj[index], $scope.contracts.length, $scope.minimumSalaries, index, isNew);
        		}
        		else{
        			return ExtensionValidationService.validate($scope.year, $scope.contract.player, $scope.contracts[index], $scope.contracts.length, $scope.minimumSalaries, index, isNew);
        		}
        	}
        	return { "hasError" : false, "msg" : ""};
        }
        
        $scope.createExtensionObjList = function(){
        	$scope.extensionObjList = [];
        	for(var i = 0; i < $scope.contracts.length; i++){
        		$scope.extensionObjList.push({
        			"baseSalary" : $scope.contracts[i].baseSalary,
        			"guaranteedBaseSalary" : $scope.contracts[i].guaranteedBaseSalary
        		});
        	}
        	for(var i = 0; i < $scope.extensionObj.length; i++){
        		$scope.extensionObjList.push({
        			"baseSalary" : $scope.extensionObj[i].baseSalary,
        			"guaranteedBaseSalary" : $scope.extensionObj[i].guaranteedBaseSalary
        		});
        	}        	
        }
        
        $scope.validateAll = function(){
        	var valid = true;
        	for(var i = 0; i < $scope.contracts.length; i++){
        		if($scope.validateObj(i, false).hasError){
        			valid = false;
        		}
        	}
        	for(var i = 0; i < $scope.extensionObj.length; i++){
        		if($scope.validateObj(i, true).hasError){
        			valid = false;
        		}
        	}
        	return valid;
        }
        
        $scope.submitExtension = function(){
        	if($scope.validateAll()){
        		$scope.createExtensionObjList();
        		RosterActionResource.extension({},{
            		"signingBonus" : Number($scope.signingBonus),
            		"year" : $scope.contract.year,
            		"playerId" : $scope.contract.player.id,
            		"additionalYears" : $scope.selectedYears.id,
            		"totalYears" : $scope.selectedYears.id + $scope.contracts.length,
            		"contractDtoList" : $scope.extensionObjList
        		}).$promise.then(function(data){
                    TransactionResource.tryGetLastTransaction().$promise.then(function(data){
                  	  MessageServices.setRosterActionMessage(data.message)        	  
                        $location.path("/team/" + $scope.team.id);
                    });	
        		});
        	}
        	else{
        		console.log("Has Errors")
        	}
        };
        
        $scope.home = function(){
        	$location.path("/team/" + $scope.contract.team);
        }
        
        
        /**
         * CUSTOM CSS STYLING
         */
        $scope.signingBonusStyle = function(){
			if($scope.team)
				return{
		        	'background': 'rgba('+ ColorServices.hexToRgb($scope.team.primaryColor).rgb +', 0.4)',
		        	'border': '1px solid rgba('+ ColorServices.hexToRgb($scope.team.secondaryColor).rgb +', 0.4)'
	        	};
        }
        
		$scope.getBadgeStyle = function(){
			if($scope.team)
				return{
					'background': '#' + $scope.team.primaryColor
				};			
		}        
		
		$scope.getNewYearLabelStyle = function(){
			if($scope.team)
				return {
					background: '#' + $scope.team.secondaryColor				
				};
		};
		
		$scope.legendStyle = function(){
			if($scope.team)
				return {
					'background': 'rgba('+ ColorServices.hexToRgb($scope.team.secondaryColor).rgb +', 0.5)'				
				};
		}
		
	}]);
})();