(function(){
	
	var application = angular.module('application', []);
	
	application.factory( 'ApplicationState', [ '$rootScope', '$location', 'CalculationResource', 'RosterActionResource', function($rootScope, $location, CalculationResource, RosterActionResource){
		var loading = false;
		return {
			loadApplication : function(){
				loading = true;
				CalculationResource.getHighestPaid({
					year : new Date().getFullYear()
				}).$promise.then(function(data){
					loading = false;
					$rootScope.readyState = true;
				}, function(err){});				
			},
			isLoading : function(){
				return loading;
			},
			reset : function(){
				loading = true;
				$rootScope.readyState = false;
	            RosterActionResource.reset({}).$promise.then(function(){
	            	loading = false;
	            	$rootScope.readyState = true;
	            	$location.path('/');
	            });
			}
		};
	}])	
})();