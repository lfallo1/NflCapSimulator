/**
 * 
 */
(function(){
	angular.module('app.controllers').controller('HomeCtrl', [
			'$rootScope', '$scope', '$location',
			'TeamResource', 'CalculationResource',
			function($rootScope, $scope, $location, 
			TeamResource, CalculationResource){

		$scope.init = function(){
			$scope.teams = TeamResource.query();
		}				
				
		$scope.goToTeamPage = function(teamId){
			$location.path('/team/' + teamId);
		}
		
		$scope.getStyle = function(team){
			return {'background-color' : '#' + team.primaryColor, 'color' : 'white'};
		}
		
		$scope.init();
	}]);
})();