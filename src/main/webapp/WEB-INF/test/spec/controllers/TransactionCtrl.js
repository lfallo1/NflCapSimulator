'use strict';

describe('Controller: HomeCtrl', function() {

	var success = {
		'status' : 200,
		'data' : {}
	};
	
	var TransactionResourceDeferred, TeamResourceDeferred;
	
	var $scope, $rootScope, $routeParams, $location, TransactionResource, TeamResource;
	
	beforeEach(module('app.controllers'));
	
	beforeEach(inject(function($q, $controller, _$rootScope_, _$location_, _TransactionResource_, _TeamResource_) {		

		var _$routeParams_ = {
			'id' : 1
		};
		
		$scope = _$rootScope_.$new();
		$rootScope = _$rootScope_;
		$routeParams = _$routeParams_;
		$location = _$location_;
		TransactionResource = _TransactionResource_;
		TeamResource = _TeamResource_;
		
		//setup promises
		TransactionResourceDeferred = $q.defer();
		TeamResourceDeferred = $q.defer();
		
		//setup spies
		spyOn(TransactionResource, 'get').and.returnValue({
			'$promise' : TransactionResourceDeferred.promise
		});	
		spyOn(TeamResource, 'get').and.returnValue({
			'$promise' : TeamResourceDeferred.promise
		});	
		spyOn(TransactionResource, 'undoTransaction').and.returnValue({
			'$promise' : TransactionResourceDeferred.promise
		});
		
		//create controller instance
		$controller('TransactionCtrl', {
			'$rootScope' : $rootScope,
			'$scope' : $scope,
			'$routeParams' : $routeParams,
			'$location' : $location,
			'TransactionResource' : TransactionResource,
			'TeamResource' : TeamResource
		});

	}));

	it('should redirect home due to undefined $routeParam', function(){
		$routeParams.id = undefined;
		$scope.init();
		expect($location.path()).toBe("/");		
	});
	
	it('should redirect home due to application not in ready state', function(){
		$rootScope.readyState = false;
		$scope.init();
		expect($location.path()).toBe("/");
	});
	
	it('should redirect home due to transaction not found', function(){
		$rootScope.readyState = false;
		$routeParams.id = -1;
		$scope.init();
		TransactionResourceDeferred.reject({'statusText' : 'transaction not found'});
		$scope.$digest();
		expect($location.path()).toBe("/");
	});	
	
	it('should successfully call TransactionResource & TeamResource on view load', function() {
		$rootScope.readyState = true;
		$routeParams.id = 1;
		$scope.init();
		TransactionResourceDeferred.resolve('transaction');
		TeamResourceDeferred.resolve('team');
		$scope.$digest();
		expect($scope.transaction).toBe('transaction');
		expect($scope.team).toBe('team');
	});
	
	it('should redirect user to team page', function(){
		$scope.transaction = {
			'teamId' : 1
		};
		$scope.viewTeamPage();
		expect($location.path()).toBe("/team/" + $scope.transaction.teamId);
	});

});