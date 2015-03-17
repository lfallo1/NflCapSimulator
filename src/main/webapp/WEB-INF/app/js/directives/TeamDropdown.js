(function(){
	angular.module('salaryCapApp').directive('scTeamDropdown', function(){
		return{
			templateUrl : 'app/js/directives/templates/teamDropdown.html',
			controller : function($scope, TeamResource, CalculationResource){
				TeamResource.query(function(data){
					$scope.teams = data.filter(function(d){if(d.id!==$scope.contract.team){return d;}});
					$scope.selectedTeam = $scope.teams[0].id;
					$scope.getTotalSalaries();
				});

				$scope.getTotalSalaries = function(){
					CalculationResource.getAllSalariesByTeam({
		            	team : $scope.selectedTeam
		            }, function(data){
		            	$scope.totalSalaries = data;
		            });
				}				
			}
		}
	});
})();