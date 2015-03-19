/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('TransactionsCtrl', function($rootScope, $scope, $location, 
			TransactionResource){
		$scope.title = "All Transactions";
		if($rootScope.readyState ===false){
			$location.path("/");
		}		
		else{
			TransactionResource.query().$promise.then(function(data){
				$scope.transactions = data;
			});
		}		
		
		$scope.viewTeamPage = function(teamid){
			$location.path("/team/" + teamId);
		}
	});
})();