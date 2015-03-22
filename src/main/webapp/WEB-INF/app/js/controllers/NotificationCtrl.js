(function(){
	angular.module('app.controllers').controller('NotificationCtrl', ['$scope', 'ApplicationState', 'MessageServices', 
			function($scope, ApplicationState, MessageServices){
		
		$scope.getCurrentApplicationMessage = function(){
			return MessageServices.getApplicationMessage();
		};
		
	}]);
})();