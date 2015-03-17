(function(){
	angular.module('salaryCapApp').directive('scEnterSupport', function(){
		return{
			restrict : 'A',
			link : function(scope, el, attr){
				$('body').on('keypress', function(evt){
					if(evt.keyCode === 13){
						el[0].click();
					}
				})
			}
		}
	});
})();