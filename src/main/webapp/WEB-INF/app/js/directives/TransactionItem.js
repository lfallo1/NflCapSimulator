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
			templateUrl: 'app/js/directives/templates/transactionItem.html'
		}
	})
})();