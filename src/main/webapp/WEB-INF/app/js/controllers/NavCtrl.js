(function(){
	angular.module('app.controllers').controller('NavCtrl',['$scope', '$location', 'ApplicationState', 'MessageServices', function($scope, $location, ApplicationState, MessageServices) {
		
		$scope.reset = function(){
			MessageServices.setApplicationMessage("Resetting roster...");
			ApplicationState.reset();
		}
		
		$scope.navigate = function(location){
			if(!ApplicationState.isLoading()){
				$location.path(location);
			}
		}

	}]);
})();