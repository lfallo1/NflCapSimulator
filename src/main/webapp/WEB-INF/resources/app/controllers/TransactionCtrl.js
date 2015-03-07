/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('TransactionCtrl', function($rootScope, $scope, $routeParams, $location, 
			TransactionResource, TeamResource){
		$scope.title = "Transactions";
		if($rootScope.readyState ===false){
			$location.path("/");
		}		
		else if($routeParams.id != null){
			TransactionResource.get({
				id : $routeParams.id
			}, function(data){
				$scope.transaction = data;
				TeamResource.get({
					id : data.teamId
				}, function(data){
					$scope.team = data;
					$rootScope.readyState = true;
				});
			});
		}		
		else{
			$location.path("/");
		}
		
		$scope.undo = function(){
			TransactionResource.undoTransaction({id : $routeParams.id}, null, function(){
				$scope.viewTeamPage();
			});
		}
		
		$scope.viewTeamPage = function(){
			$location.path("/team/" + $scope.transaction.teamId);
		}
	});
})();