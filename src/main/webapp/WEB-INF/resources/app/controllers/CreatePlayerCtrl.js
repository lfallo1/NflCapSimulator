(function() {
	angular
			.module('app.controllers')
			.controller(
					'CreatePlayerCtrl',
					function($rootScope, $scope, $location, $routeParams, $modal, ContractResource,
							TransactionResource, RosterActionResource,
							PositionResource, TeamResource,
							MinimumSalaryResource, ArrayServices, GenericValidator, SalaryValidator) {
			    		if($rootScope.readyState ===false){
			    			$location.path("/");
			    		}
			    		else{
							TeamResource.get({
								id : $routeParams.teamId
							}, function(data) {
								$scope.team = data;
								$rootScope.readyState = true;
							});
	
							MinimumSalaryResource.query(function(data) {
								$scope.minimumSalaries = data;
								$scope.initExistingSalaries();
							});
							
							$scope.getMinimumSalary = function(year, creditedSeasons){
								var cs = creditedSeasons > 10 ? 10 : creditedSeasons;
								if($scope.minimumSalaries){
									return $scope.minimumSalaries.filter(function(d){if(d.year === year && d.creditedSeasons === cs) return d;})[0].baseSalary;	
								}
							}
			    		}

						/**
						 * Constants
						 */
						$scope.title = "Create Player";
						$scope.year = new Date().getFullYear();
					
						/**
						 * Properties
						 */
						$scope.player = {
							'name' : {
								value : 'John Doe',
								annotations : [GenericValidator.NotEmptyAnnotation(), GenericValidator.MaxLengthAnnotation(25)]
							},
							'selectedContractLength' : {
								value : 0,
								annotations : [GenericValidator.NotEmptyAnnotation(), GenericValidator.LessThanAnnotation(8), GenericValidator.NumberAnnotation()]
							},
							'selectedAccruedSeasons' : {
								value : 0,
								annotations : [GenericValidator.NotEmptyAnnotation(), GenericValidator.LessThanAnnotation(14), GenericValidator.NumberAnnotation()]
							},
							'selectedPosition' : {
								value : '',
								annotations : [GenericValidator.NotEmptyAnnotation()]
							},							
						};
						$scope.contractObj = {
							'signingBonus' : {
								value : 1000000,
								annotations : [GenericValidator.NotEmptyAnnotation(), GenericValidator.BetweenAnnotation(0,200000000), GenericValidator.NumberAnnotation()]
							},
							'contractList' : []
						};

						/**
						 * Contract Length Dropdown
						 */
						$scope.contractLengths = ArrayServices.generateArrayOfNumbers(7, true);
						$scope.player.selectedContractLength.value = $scope.contractLengths[0];
						
						/**
						 * Accrued Seasons Dropdown
						 */
						$scope.accruedSeasons = ArrayServices.generateArrayOfNumbers(14);
						$scope.player.selectedAccruedSeasons.value = $scope.accruedSeasons[0];

						/**
						 * Position Dropdown
						 */
						PositionResource.query(function(data) {
							$scope.positions = data.map(function(d){return d.positionName});
							$scope.player.selectedPosition.value = $scope.positions[0];
						});

						$scope.getNumber = function(number) {
							return new Array(parseInt(number));
						};
						
						$scope.initExistingSalaries = function(){
							for (var i = 0; i < $scope.player.selectedContractLength.value; i++) {
								var min = $scope.getMinimumSalary($scope.year + i, Number($scope.player.selectedAccruedSeasons.value) + i);
								$scope.contractObj.contractList[i].baseSalary = !$scope.contractObj.contractList[i].baseSalary || $scope.contractObj.contractList[i].baseSalary < min ? min : $scope.contractObj.contractList[i].baseSalary; 
							}							
						}			
						
						$scope.accruedChangedEvent = function(){
							for(var i = 0; i < $scope.contractObj.contractList.length; i++){
								var minSalary = $scope.getMinBaseSalary(i);
								var currentBaseSalary = $scope.contractObj.contractList[i].baseSalary;
								$scope.contractObj.contractList[i].baseSalary = currentBaseSalary < minSalary ? minSalary : currentBaseSalary;
							}
						}
						
						$scope.getMinBaseSalary = function(index){
							var baseSalary = $scope.getMinimumSalary($scope.year + index, Number($scope.player.selectedAccruedSeasons.value) + Number(index));
							return baseSalary;
						}

						/**
						 * Calculate/Generate text/numeric values
						 */
						$scope.getNameAndPosition = function() {
							var position = $scope.player.selectedPosition.value !== undefined ? ' - '
									+ $scope.player.selectedPosition.value
									: '';
							var name = $scope.player.name.value;
							return name + position;
						}

						$scope.getSigningBonusProration = function(yearNumber) {
							var prorationLen = $scope.player.selectedContractLength.value > 5 ? 5
									: $scope.player.selectedContractLength.value;
							return yearNumber > 5 ? 0
									: $scope.contractObj.signingBonus.value
											/ prorationLen;
						}

						$scope.getTotalValue = function() {
							var totalValue = 0;
							for (var i = 0; i < $scope.player.selectedContractLength.value; i++) {
								totalValue += $scope.contractObj.contractList[i] ? Number($scope.contractObj.contractList[i].baseSalary) : 0;
							}
							totalValue += Number($scope.contractObj.signingBonus.value);
							return totalValue;
						}

						$scope.getTotalGuaranteed = function() {
							var totalGuaranteed = 0;
							for (var i = 0; i < $scope.player.selectedContractLength.value; i++) {
								totalGuaranteed += $scope.contractObj.contractList[i] && $scope.contractObj.contractList[i].guaranteedBaseSalary ? Number($scope.contractObj.contractList[i].guaranteedBaseSalary) : 0;
							}
							totalGuaranteed += Number($scope.contractObj.signingBonus.value);
							return totalGuaranteed;
						}

						$scope.getPctGuaranteed = function() {
							return ((Number($scope.getTotalGuaranteed()) / Number($scope
									.getTotalValue())) * 100).toFixed(2)
									+ "%";
						};

						$scope.getAPY = function() {
							return Number($scope.getTotalValue())
									/ $scope.player.selectedContractLength.value;
						};
						
						$scope.filterContractListByTotalContractLength = function(){
							var contractList = [];
							for(var i = 0; i < $scope.player.selectedContractLength.value; i++){
								contractList.push($scope.contractObj.contractList[i]);
							}
							return contractList;
						}

						/**
						 * Validation
						 */
						$scope.isValid = function(objType, fieldName, index) {
							if(objType === 'player'){
								return GenericValidator.validate($scope.player[fieldName], fieldName);
							}
							else if(objType === 'contractObj'){
								return GenericValidator.validate($scope.contractObj[fieldName], fieldName);
							}
							else if(objType === 'salary'){
								var minimumSalary = $scope.getMinimumSalary(new Date().getFullYear() + index, Number($scope.player.selectedAccruedSeasons.value) + index);
								return SalaryValidator.validate(minimumSalary, $scope.contractObj.contractList[index], new Date().getFullYear() + index);
							}
						}
						
						$scope.validateAll = function(){
							for(var i = 0; i < $scope.player.selectedContractLength.value; i++){
								if(!$scope.isValid('salary', null, i).valid){
									return false;
								}
							}
							for(p in $scope.player){
								if(!$scope.isValid('player', p, null).valid){
									return false;
								}
							}
							if(!$scope.isValid('contractObj', 'signingBonus').valid){
								return false;
							}
							return true;
						}
						
						/**
						 * Try to submit player
						 */
						$scope.submit = function() {
							if($scope.validateAll()){
								var contractList = $scope.filterContractListByTotalContractLength();
								RosterActionResource
										.addPlayer(
												{},
												{
													'name' : $scope.player.name.value,
													'position' : $scope.player.selectedPosition.value,
													'accrued' : $scope.player.selectedAccruedSeasons.value,
													'teamId' : $scope.team.id,
													'year' : new Date()
															.getFullYear(),
													'signingBonus' : $scope.contractObj.signingBonus.value,
													'contractDtoList' : contractList
												}, function(data) {
													$location.path("/team/"
															+ $scope.team.id);
												});
							}
							else{
								$scope.openErrorModal();
							}
						};

						$scope.cancel = function() {
							$location.path("/team/" + $scope.team.id);
						}
						
			            /**
			             * BOOTSTRAP MODALS
			             */
			            $scope.openErrorModal = function () {
		                    var modalInstance = $modal.open({
		                        templateUrl: 'resources/app/modal/templates/errorSubmittingFormTemplate.html',
		                        controller: 'ErrorModalInstanceController'
			                });
		                    modalInstance.result.then(function (selectedItem) {
		                        $scope.selected = selectedItem;
		                    });
			            };						

						/**
						 * Dynamic Css Styling
						 */
						$scope.getBackground = function() {
							return $scope.team ? {
								'background' : '#' + $scope.team.primaryColor
							} : null;
						}

						$scope.getPrimaryTextColor = function() {
							return $scope.team ? {
								'color' : '#' + $scope.team.primaryColor
							} : null;
						}
					});
})();