/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('RestructureCtrl', function($rootScope, $scope, $routeParams, $location, ContractResource, PlayerResource, MinimumSalaryResource, RosterActionResource, TeamResource){
		$scope.yearsRemaining = 0;
		$scope.amount = 25000;
		$scope.title = "Restructure";
		$scope.restructurePossible = true;
		
		if($rootScope.readyState ===false){
			$location.path("/");
		}		
		else if($routeParams.contractId != null){
			ContractResource.get({
				id : $routeParams.contractId
			}).$promise.then(function(data){
				$scope.contract = data;
				ContractResource.getByPlayer({
					id : $scope.contract.player.id
				}).$promise.then(function(data){
					$scope.playerContracts = data;
					$scope.yearsRemaining = data.filter(function(d){if(d.year >= $scope.contract.year && d.status === 'Contract'){return d;}}).length;
					$rootScope.readyState = true;
				});
				
				TeamResource.get({id : $scope.contract.team}).$promise.then(function(data){
					$scope.team = data;
				});
				
				PlayerResource.get({
					id : $scope.contract.player.id
				}).$promise.then(function(data){
					$scope.player = data;
					MinimumSalaryResource.getByYearAndCreditedSeasons({
						year : $scope.contract.year,
						cs : $scope.contract.year - $scope.player.accrued
					}).$promise.then(function(data){
						$scope.minimumSalary = data;
						$scope.maxRestructureAmount = $scope.contract.baseSalary - $scope.minimumSalary.baseSalary;
						if($scope.maxRestructureAmount <= 0){
							$scope.restructurePossible = false;
						}
					});					
				});					
			});
		}
		else{
			$location.path("/");
		}
		
		$scope.doResctructure = function(){
			if($scope.isValid()){
				RosterActionResource.restructure({}, {
					contractId : $routeParams.contractId,
					amount : $scope.amount
				}).$promise.then(function(data){
					$location.path("/team/" + $scope.team.id);
				}, function(err){
					console.log(err.statusText);
				});
			}
			else{
				console.log('error');
			}
		};
		
		$scope.cancel = function(){
			$location.path("/team/" + $scope.contract.team);
		}
		
		$scope.getClass = function(){
			return isNaN($scope.amount) || $scope.amount > $scope.maxRestructureAmount ? "calculations-bad" : "";
		}
		
		$scope.isValid = function(){
			return $scope.isNumber($scope.amount) && $scope.amount <= $scope.maxRestructureAmount && $scope.amount > 0;
		}
		
		$scope.isNumber = function(n){
			return !isNaN(n);
		}
		
		/**
		 * CUSTOM CSS STYLING
		 */
		$scope.getYearsOnContractStyle = function(){
				return $scope.team ? {
					'background': '#' + $scope.team.primaryColor		
				} : null;
		}
		
		$scope.getNoteStyle = function(){
			if($scope.team){
				var hex = $scope.team.secondaryColor === '000000' ? 'white' : $scope.team.secondaryColor;
				return {'color': '#' + hex};
			} 			
			return null;
		};
		
		$scope.getBadgeStyle = function(){
			return $scope.team ? {
				'background': '#' + $scope.team.primaryColor,
			} : null
		}
				
	});
})();