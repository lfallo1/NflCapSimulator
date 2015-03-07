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
			templateUrl: _contextPath + 'directives/templates/transactionItem.html'
		}
	})
})();