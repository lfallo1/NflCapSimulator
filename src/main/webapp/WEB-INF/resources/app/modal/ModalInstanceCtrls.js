angular.module('app.controllers').controller('ErrorModalInstanceController', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

angular.module('app.controllers').controller('TransactionModalInstanceCtrl', function ($rootScope, $scope, $modalInstance, $location, transactions, TransactionResource) {

    $scope.transactions = transactions;

    $scope.ok = function () {
        $modalInstance.close('ok');
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    
	$scope.undoTransaction = function(transactionId){
		TransactionResource.undoTransaction({id : transactionId}, null, function(){
			$rootScope.transactionsModifiedFromModal = true;
			for(var i = 0; i < $scope.transactions.length; i++){
				if($scope.transactions[i].id === transactionId){
					$scope.transactions.splice(i,1);
				}
			}
		});
	}    
    
    $scope.showDetails = function(id){
    	$modalInstance.close('ok');
    	$location.path("/transaction/" + id);
    };    
});

angular.module('app.controllers').controller('PlayerModalInstanceCtrl', function ($scope, $modalInstance, $location, details) {

    $scope.details = details;
    $scope.thisYear = new Date().getFullYear();

    $scope.showPlayerPage = function(id){
    	$modalInstance.close('ok');
    	$location.path("/player/" + id);
    }
    
    $scope.ok = function () {
        $modalInstance.close('ok');
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
