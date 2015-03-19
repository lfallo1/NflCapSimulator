/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('HomeCtrl', function($rootScope, $scope, $location, 
			TeamResource, CalculationResource){
		
		$scope.init = function(){
			$scope.teams = TeamResource.query();
			
			CalculationResource.getHighestPaid({
				year : new Date().getFullYear()
			}).$promise.then(function(data){
				$rootScope.readyState = true;
			}, function(err){});			
		}
		
		$scope.goToTeamPage = function(teamId){
			$location.path('/team/' + teamId);
		}
		
		$scope.getStyle = function(team){
			return {'background-color' : '#' + team.primaryColor, 'color' : 'white'};
		}
		
		$scope.init();
	});
})();