/**
 * 
 */

(function(){
	angular.module('salaryCapApp').directive('scTransactionItem', function(){
		return {
			scope : {
				transactionParam : "=",
				team : "="
			},
			templateUrl: _contextPath + 'app/js/directives/templates/transactionItem.html'
		}
	})
})();