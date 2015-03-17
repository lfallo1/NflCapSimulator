(function(){
    angular.module('app.controllers')
        .controller('TeamCtrl', function ($rootScope, $scope, $log, $timeout, $modal, $location, $routeParams, ContractResource, ContractStatusResource, SalaryCapConstantsResource, TransactionResource, RosterActionResource, PositionResource, DeadMoneyResource, TeamResource, ListServices, ContractManagementService, ArrayServices) {

        	//Init values
            $scope.notification = false;
            $scope.deadMoney = [];
            $scope.salaryCapConstants = [];
            $scope.loading = false;
            $scope.filtersVisible = false;
            $scope.actionDropdown = [];
            $scope.yearDropdown = new Date().getFullYear();          
            $scope.predicate = '-capCharge';

            //Filter objects
            $scope.actions = ListServices.getRosterActionsList();            
            
            //Init
    		if($rootScope.readyState === false){
    			$location.path("/");
    		}
            else if($routeParams.id != null && !isNaN($routeParams.id) && $routeParams.id != 0){
                TeamResource.get({id:$routeParams.id}, function(data){
                	$scope.team = data;
                	if($scope.team.name){
                		$scope.init();
                	}
                	else{
                		$location.path("/");
                	}
                });               
            }
            else{
            	$location.path("/");
            }
            
            //Get all contracts by team
            $scope.init = function(){
	            $scope.fullContractList = ContractResource.getByTeam({
	            	'teamId' : $routeParams.id
	            },function(){
	            	$scope.loadContracts();
	            	$scope.generateCalculations();
	            });
            }            
            
            $scope.generateCalculations = function(){
                ContractResource.calculateTotalSalary({
                	yearType : 'Offseason',
                	year : $scope.yearDropdown,
                	teamId : $routeParams.id
                }, function(data){
                	$scope.totalSalary = data.obj;
                	SalaryCapConstantsResource.getByYearAndTeam({
                    	year : $scope.yearDropdown,
                    	teamId : $routeParams.id
                    },function(data){
                    	$scope.salaryCapConstants = data;
                        $scope.salaryCap = $scope.salaryCapConstants.salaryCap;
                        $scope.adjustedCap = $scope.salaryCapConstants.adjustedCap;
                        $scope.capRoom = $scope.adjustedCap - $scope.totalSalary;
                        $rootScope.readyState = true;
                	});
                });
            };

            $scope.filterObj = {
                "year" : new Date().getFullYear(),
                "team" : $routeParams.id,
                "positions" : $scope.selectedPositions,
                "contractStatuses" : $scope.selectedContractStatuses
            };

            $scope.filterChanged = function(){
                $scope.loadContracts();
            };

            $scope.loadContracts = function(){
                $scope.filterObj.year = $scope.yearDropdown;
                $scope.filterObj.positions = $scope.selectedPositions.map(function(d){return d.positionName;});
                $scope.filterObj.contractStatuses = $scope.selectedContractStatuses.map(function(d){return d.contractStatusName;});
                $scope.contracts = [];
                $scope.loading = "Loading...";
                ContractResource.contractFilter({}, JSON.stringify($scope.filterObj), function(data){        	
                    $scope.contracts = data;
                    $scope.loading = false;
                    $scope.generateCalculations();
                    $scope.deadMoney = DeadMoneyResource.getByYearAndTeam({
                    	year : $scope.yearDropdown,
                    	teamId : $routeParams.id
                    });              
                    TransactionResource.tryGetLastTransaction(function(data){
                    	if(data.message!=undefined){
                    		$scope.showNotification(data);
                    	}
                    });
                });
            };

            $scope.inTop51or53 = function(contract){
                if($scope.fullContractList.length > 0 && $scope.team) {
                    return ContractManagementService.inTop51or53(contract, $scope.fullContractList, $scope.yearDropdown, 'Offseason', $scope.team);
                }
            };

            $scope.details = false;
            $scope.generateDetails = function(playerId){
                $scope.details = $scope.fullContractList.filter(function(d){if(d.player.id===playerId){return d;}}).sort(function (a, b) {
                    return a.year - b.year;
                });
                for(var i = 0; i < $scope.details.length; i++){
                    $scope.playersBySamePosition = $scope.fullContractList.filter(function (d) {
                            if (d.position === $scope.details[0].position && d.year === $scope.details[i].year && d.status === 'Contract') {
                                return d;
                            }
                        }
                    ).sort(function (a, b) {
                            return b.capCharge - a.capCharge;
                        });
                    $scope.details[i].pctRank = $scope.details[i].capCharge / ArrayServices.sum($scope.playersBySamePosition, 'capCharge');
                    for (var j = 0; j < $scope.playersBySamePosition.length; j++) {
                        if ($scope.playersBySamePosition[j].id === $scope.details[i].id) {
                            $scope.details[i].positionRank = Number(j + 1) + " out of " + $scope.playersBySamePosition.length;
                        }
                    }
                }
            };

            $scope.performAction = function(action, contract){
                switch(action) {
                    case 'cut':
                        RosterActionResource.cut({}, contract.id, function(data){
                            $scope.init();
                        });
                        break;
                    case 'june1cut':
                        RosterActionResource.june1cut({}, contract.id, function(data){
                        	$scope.init();
                        });
                        break;
                    case 'restructure':
                    	$location.path("/restructure/" + contract.id);
                        break;
                    case 'extend':
                        $location.path("/extension/" + contract.id);
                        break;
                    case 'trade':
                    	$location.path("/trade/" + contract.id);
                    	break;
                    default:
                        break;
                }
            };

            $scope.showNotification = function(transactionInfo) {
                $scope.notification = transactionInfo.message;
                $timeout(function () {
                    $scope.notification = false;
                }, 3000);
            };
            
            $scope.addPlayer = function(){
            	$location.path("/createPlayer/" + $scope.team.id);
            }

            /**
             * BOOTSTRAP MODALS
             */
            $scope.openTransactionModal = function () {
                TransactionResource.getByTeam({teamId : $scope.team.id}, function(data){
                	$rootScope.transactionsModifiedFromModal = false;
                    var modalInstance = $modal.open({
                        templateUrl: 'app/views/modals/transactionTemplate.html',
                        controller: 'TransactionModalInstanceCtrl',
                        resolve: {
                            transactions: function () {
                                return data;
                            }
                        }
                    });
                    
                    modalInstance.result.then(function (selectedItem) {
                        $scope.selected = selectedItem;
                      }, function () {
                    	  if($rootScope.transactionsModifiedFromModal===true){
                    		  $scope.init();
                    	  }
                      });                    
                });
            };

            $scope.openPlayerModal = function (playerId) {
                $scope.generateDetails(playerId);
                var modalInstance = $modal.open({
                    templateUrl: 'app/views/modals/playerTemplate.html',
                    controller: 'PlayerModalInstanceCtrl',
                    size : 'lg',
                    resolve: {
                        details : function(){
                            return $scope.details
                        }
                    }
                });
            };
            
            /**
             * DYNAMIC CSS STYLING
             */
            $scope.getPrimaryTeamColorBg = function(){
            	if($scope.team){
                	return {'background': '#'+$scope.team.primaryColor, 'color' : 'white'};
            	}            	
            }
            
            $scope.getSecondaryTeamColorBg = function(){
            	if($scope.team){
            		if($scope.team.secondaryColor === '000000'){
            			return {
            				'background' : '#' + 'white'
            			}
            		}
            		else{
            			return {'background': '#'+$scope.team.secondaryColor, 'color' : 'black',};
            		}
            	}            	
            }               
            
            $scope.getRowHeaderStyle = function(){
            	if($scope.team){
            		if($scope.team.secondaryColor === '000000'){
            			return {'background-color': '#'+$scope.team.secondaryColor, 'border' : '1px solid white'};
            		}
            		else{
            			return {'background-color': '#'+$scope.team.secondaryColor};
            		}
            	}
            }
            
            $scope.getPlayerLinkStyle = function(){
            	if($scope.team){
                	return {'color': '#'+$scope.team.secondaryColor, 'font-weight': 'bold', 'text-decoration' : 'underline'};
            	}
            }            
            
        });
})();