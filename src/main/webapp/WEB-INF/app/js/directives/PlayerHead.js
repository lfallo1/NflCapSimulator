(function(){
	angular.module('salaryCapApp').directive('scPlayerHead', function(){
		return{
			scope : {
            	name : "=",
            	position : "=",
            	team : "=",
			},
			restrict : 'EA',
			templateUrl : 'app/js/directives/templates/playerHead.html'
		};
	});
})();