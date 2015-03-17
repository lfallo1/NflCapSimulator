(function(){
	angular.module('salaryCapApp').directive('scContractStatusDropdown', function(){
		return{
			restrict : 'E',
			templateUrl : _contextPath + 'app/js/directives/templates/contractStatusDropdown.html',
			controller : function($scope, ContractStatusResource){
				$scope.contractDropdownSettings = {displayProp: 'contractStatusName', dynamicTitle:false, externalIdProp: ''};
	            $scope.contractDropdownLabel = {buttonDefaultText: 'Contracts'};
	            ContractStatusResource.query(function(data){
	            	$scope.contractStatuses = data;
	            	$scope.selectedContractStatuses = [];
	            	$scope.selectedContractStatuses.push($scope.contractStatuses[0]);
	            });
	            $scope.contractDropdownEvents = {
	                onItemSelect : function(){$scope.filterChanged();},
	                onItemDeselect : function(){$scope.filterChanged();}
	            };
			}			
		}
	})
})();

