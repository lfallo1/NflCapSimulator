(function(){
	angular.module('app.controllers').controller('CssCtrl', ['$scope', '$route', function($scope, $route) {
		
		$scope.$watch(function() {
			return ($route.current && $route.current.css) ? $route.current.css
					: '';
		}, function(value) {
			$scope.css = value;
		});

	}]);
})();