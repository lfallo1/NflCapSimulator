/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('PlayerCtrl',[
							'$rootScope',
							'$scope',
							'$routeParams',
							'$location',
							'$window',
							'PlayerResource',
							'ContractResource',
							'ContractOverviewResource',
							'TransactionResource',
							'TeamResource',
							'DeadMoneyResource',
							'ColorServices',
							'ContractManagementService',	                                                           
			function($rootScope, $scope, $routeParams, $location, 
			$window, PlayerResource, ContractResource, ContractOverviewResource, TransactionResource, 
			TeamResource, DeadMoneyResource, ColorServices,
			ContractManagementService){
		$scope.title = "Player Profile";
		
		if($routeParams.id != null && !isNaN($routeParams.id)){
			PlayerResource.get({
				id : $routeParams.id
			}).$promise.then(function(data){
				$scope.player = data;
				if(!$scope.player.name){
					$location.path("/");
				}
				else{
					$scope.init();
				}
				
			});
		}
		else{
			$location.path("/");
		}
		
		$scope.init = function(){
			ContractOverviewResource.getByPlayer({
				playerId : $routeParams.id
			}).$promise.then(function(data){
				$scope.contractOverview = data;
			});
						
			ContractResource.getByPlayer({
				id : $routeParams.id
			}).$promise.then(function(data){
				$scope.contracts = data.filter(function(d){if(d.status=='Contract'){return d;}});
				/**
				 * Team Info
				 */
				TeamResource.get({
					id : $scope.contracts.sort(function(a,b){return a.year > b.year ? 1 : a.year < b.year ? -1 : 0;})[$scope.contracts.length-1].team
				}).$promise.then(function(data){
					$scope.team = data;
				});
			});
			
			TransactionResource.getByPlayer({
				id : $routeParams.id
			}).$promise.then(function(data){
				$scope.transactions = data.length > 0 ? data : false;
			});			
		}
		
		$scope.getNameAndPosition = function(){
			if($scope.contracts && $scope.player){
				var name = $scope.player.name;
				var i = 0;
				while($scope.contracts[i].position === null){
					i++;
				}
				return name + " - " + $scope.contracts[i].position;
			}
		}
		
		$scope.getOverview = function(){
			if($scope.contracts && $scope.contractOverview.freeAgentYear){
				return ContractManagementService.generateOverview($scope.contracts, $scope.contractOverview);
			}
		}
		
		$scope.formatDob = function(dob){
			return dob ? dob.monthOfYear + "-" + dob.dayOfMonth + '-' + Number(dob.year + 1900) : "";
		}
		
		$scope.getAccrued = function(){
			return $scope.player ? new Date().getFullYear() - $scope.player.accrued : 0;
		}
		
		$scope.getDraftInfo = function(){
			if($scope.player){
				var draftText = '';
				draftText += $scope.player.draftYear !== 0 ? '' + $scope.player.draftYear : '';
				draftText += $scope.player.draftRound !== 0 ? ' Round#' + $scope.player.draftRound : '';
				draftText += $scope.player.draftPick !== 0 ? ' Pick#' + $scope.player.draftPick : '';
				return draftText;
			}
		}
		
		$scope.backToTeamPage = function(){
			$location.path("/team/" + $scope.team.id);
		}
		
		/**
		 * DYNAMIC CSS STYLING
		 */
		$scope.getTeamPrimaryColorBg = function(opacity){
			return $scope.team ? {
				'background' : 'rgba(' + ColorServices.hexToRgb($scope.team.primaryColor).rgb + ',' + opacity
			} : null;
		}
		
	}]);
})();