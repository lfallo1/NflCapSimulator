(function(){
	angular.module('app.controllers').controller('CalculationsCtrl', ['$rootScope', '$scope', '$location', 'TransactionResource', 'CalculationResource',  function($rootScope, $scope, $location, 
			TransactionResource, CalculationResource){
		$scope.hasResults = false;
		$scope.reverse = true;
		$scope.yearDropdown = new Date().getFullYear();
		
		$scope.init = function(){

		$scope.calculate = function(type){
			$scope.active = type;
			$scope.hasResults = true;
			$scope.type = type;
			switch (type) {
				case "getHighestPaid":
					CalculationResource.getHighestPaid({
						year : $scope.yearDropdown
					}).$promise.then(function(data){
						$scope.highestPaidResults = data;
						$scope.predicate = 'capCharge';
					});
					break;			
				case "getHighestAPY":
					console.log(type);
					break;			
				case "getTeamSalaries":
					CalculationResource.getTeamSalaries({
						year : $scope.yearDropdown
					}).$promise.then(function(data){
						$scope.teamSalariesResults = data.sort(function(a,b){return a.totalSalary < b.totalSalary ? 1 : a.totalSalary > b.totalSalary ? -1 : 0});
						$scope.predicate = '(adjustedCap - totalSalary)';
					});
					break;
				default:
					break;
			}
		};
		$scope.calculate('getHighestPaid');
	}
		
		
		$scope.filterChanged = function(){
			$scope.calculate($scope.type);
		}
		
		$scope.isActive = function(type){
			return $scope.active === type;
		}	
		
		$scope.init();
	}]);
})();