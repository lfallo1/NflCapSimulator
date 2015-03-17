(function(){
	angular.module('salaryCapApp').directive('scPlayerHead', function(){
		return{
			scope : {
            	name : "=",
            	position : "=",
            	team : "=",
			},
			restrict : 'EA',
			templateUrl : _contextPath + 'app/js/directives/templates/playerHead.html'
		};
	});
})();