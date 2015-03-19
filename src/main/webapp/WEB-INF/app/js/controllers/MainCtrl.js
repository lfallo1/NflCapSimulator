(function(){
	angular.module('app.controllers').controller('MainCtrl', function($rootScope, $scope, $routeParams, $route, $location, $timeout, $window, RosterActionResource, TransactionResource) {
		$rootScope.readyState = false;
		$scope.resetRosterNotification = false;
		$scope.$watch(function() {
			return ($route.current && $route.current.css) ? $route.current.css
					: '';
		}, function(value) {
			$scope.css = value;
		});
		
        $scope.reset = function(){
        	$location.path('/');
        	$scope.resetRosterNotification = "Reset in progress, please wait...";
            RosterActionResource.reset({}).$promise.then(function(){
            	$scope.hideNotification();
            });
        };		
        
        $scope.goBack = function(){
        	$window.history.back();
        }
        
        $scope.hideNotification = function() {
        	$scope.resetRosterNotification = false;
        };          

	});
})();