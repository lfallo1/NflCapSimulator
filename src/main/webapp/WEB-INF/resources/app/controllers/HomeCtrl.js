/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('HomeCtrl', function($rootScope, $scope, $location, 
			TeamResource, CalculationResource){
		$scope.teams = TeamResource.query();
		
		CalculationResource.getHighestPaid({
			year : new Date().getFullYear()
		}, function(data){
			$rootScope.readyState = true;
		});
		
		$scope.goToTeamPage = function(teamId){
			$location.path('/team/' + teamId);
		}
		
		$scope.getStyle = function(team){
			return {'background-color' : '#' + team.primaryColor, 'color' : 'white'};
		}
	});
})();