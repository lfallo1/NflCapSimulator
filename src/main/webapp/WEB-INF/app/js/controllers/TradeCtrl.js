(function(){
	angular
			.module('app.controllers')
			.controller(
					'TradeCtrl',
					[
							'$rootScope',
							'$scope',
							'$routeParams',
							'$location',
							'RosterActionResource',
							'TeamResource',
							'CalculationResource',
							'PlayerResource',
							'ContractResource', 'TransactionResource', 'MessageServices',
							function($rootScope, $scope, $routeParams,
									$location, RosterActionResource,
									TeamResource, CalculationResource,
									PlayerResource, ContractResource,
									TransactionResource, MessageServices) {
		$scope.title = "Trade Player";
		ContractResource.get({
			id : $routeParams.id
		}).$promise.then(function(data){
			$scope.contract = data;
			$scope.player = $scope.contract.player;
			ContractResource.getByPlayer({
				id : $scope.contract.player.id 
			}).$promise.then(function(data){
				$scope.allPlayerContracts = data.filter(function(d){
					if(d.status === 'Contract' && d.year >= $scope.contract.year){
						return d;
					}
				});
			});
			
			CalculationResource.getAllSalariesByTeam({
	            	team : $scope.contract.team
	            }).$promise.then(function(data){
	            	$scope.currentTeamSalary = data.filter(function(d){if(d.year === $scope.contract.year){return d;}})[0];
            });
		
			TeamResource.get({
				id:$scope.contract.team
			}).$promise.then(function(data){
				$scope.currentTeam = data;
				$rootScope.readyState = true;
			});
		});
			
		$scope.getNewCapSpace = function(yearlySalaryDto){
			if(!!$scope.allPlayerContracts){
				var contract = $scope.allPlayerContracts.filter(function(d){if(d.year===yearlySalaryDto.year){return d;}})[0];
				var originalCapSpace = yearlySalaryDto.adjustedCap - yearlySalaryDto.totalSalary;
				return contract === undefined ? originalCapSpace : originalCapSpace - (contract.baseSalary + contract.guaranteedBaseSalary);	
			}
			return 0;
		}
		
		$scope.submitTrade = function(){
			RosterActionResource.trade({},{
				contractId : $scope.contract.id,
				newTeamId : $scope.selectedTeam,
				playerId : $scope.contract.player.id
			}).$promise.then(function(){
                TransactionResource.tryGetLastTransaction().$promise.then(function(data){
              	  MessageServices.setRosterActionMessage(data.message)        	  
              	  $location.path("/team/" + $scope.contract.team);
                });	
			});
		};
		
		$scope.cancel = function(){
			$location.path("/team/" + $scope.contract.team);
		}

	}]);
})();