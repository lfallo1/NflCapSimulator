(function(){
	angular.module('salaryCapApp').directive('scPositionDropdown', function(){
		return{
			restrict : 'E',
			templateUrl : _contextPath + 'app/js/directives/templates/positionDropdown.html',
			controller : function($scope, PositionResource){
				$scope.positionDropdownSettings = {displayProp: 'positionName', dynamicTitle:false, externalIdProp: ''};
	            $scope.positionDropdownLabel = {buttonDefaultText: 'Positions'};
	            PositionResource.query(function(data){
	            	$scope.positions = data;
	            	$scope.selectedPositions = [];
	            	for(var i = 0; i < $scope.positions.length; i++){
	            		$scope.selectedPositions.push($scope.positions[i]);
	            	}
	            });
	            $scope.positionDropdownEvents = {
	                onItemSelect : function(){$scope.filterChanged();},
	                onItemDeselect : function(){$scope.filterChanged();}
	            };				
			}
		}
	});
})();