/**
 * 
 */
(function(){
	angular.module('app.controllers')
			.controller(
					'TransactionsCtrl',
					[
							'$rootScope',
							'$scope',
							'$location',
							'TransactionResource',
							function($rootScope, $scope, $location,
									TransactionResource) {
		$scope.title = "All Transactions";
		
		TransactionResource.query().$promise.then(function(data){
			$scope.transactions = data;
		});
				
		$scope.viewTeamPage = function(teamid){
			$location.path("/team/" + teamId);
		}
	}]);
})();