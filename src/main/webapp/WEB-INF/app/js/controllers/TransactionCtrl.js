/**
 * 
 */
(function(){
	angular.module('app.controllers')
			.controller(
					'TransactionCtrl',
					[
							'$rootScope',
							'$scope',
							'$routeParams',
							'$location',
							'TransactionResource',
							'TeamResource',
							function($rootScope, $scope, $routeParams,
									$location, TransactionResource,
									TeamResource) {
		$scope.title = "Transactions";
		
		$scope.init = function(){
			if($routeParams.id !== undefined){
				$scope.init = function(){
					TransactionResource.get({
						id : $routeParams.id
					}).$promise.then(function(data, err){
						$scope.transaction = data;
						TeamResource.get({
							id : data.teamId
						}).$promise.then(function(data){
							$scope.team = data;
						});
					}, function(err){
						$location.path("/")
					});
				};
			}
			else{
				$location.path("/");
			}
		}
		
		$scope.undo = function(){
			TransactionResource.undoTransaction({id : $routeParams.id}, null).$promise.then(function(){
				$location.path("/team/" + $scope.transaction.teamId);
			});
		}
		
		$scope.viewTeamPage = function(){
			$location.path("/team/" + $scope.transaction.teamId);
		}
		
		$scope.init();
	}]);
})();