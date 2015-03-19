'use strict';

describe('Controller: HomeCtrl', function() {

	var success = {
		'status' : 200,
		'data' : {}
	};
	
	var CalculationResourceDeferred;
	
	var $scope, $rootScope, $location, TeamResource, CalculationResource;
	
	var TEAMS = ['team1', 'team2'];
	
	beforeEach(module('app.controllers'));
	
	beforeEach(inject(function($q, _$rootScope_, $controller, _$location_, _TeamResource_, _CalculationResource_) {		

		$scope = _$rootScope_.$new();
		$rootScope = _$rootScope_;
		$location = _$location_;
		TeamResource = _TeamResource_;
		CalculationResource = _CalculationResource_;
		
		//setup promises
		CalculationResourceDeferred = $q.defer();
		
		//setup spies
		spyOn(CalculationResource, "getHighestPaid").and.returnValue({
			'$promise' : CalculationResourceDeferred.promise
		});
		spyOn(TeamResource, 'query').and.callFake(function(){
			return TEAMS;
		});	
		
		//create controller instance
		$controller('HomeCtrl', {
			'$rootScope' : $rootScope,
			'$scope' : $scope,
			'$location' : $location,
			'TeamResource' : TeamResource,
			'CalculationResource' : CalculationResource
		});

	}));

	it('should set ready state to true after calling CalculationResource', function() {
		$scope.init();
		CalculationResourceDeferred.resolve(success);
		$scope.$digest();
		expect($scope.teams).toEqual(TEAMS);
		expect(CalculationResource.getHighestPaid).toHaveBeenCalled();
		expect($rootScope.readyState).toBeTruthy();
	});
	
	it('should NOT set ready state to true after calling CalculationResource due to getting REJECT', function() {
		$scope.init();
		CalculationResourceDeferred.reject('error');
		$scope.$digest();
		expect($scope.teams).toEqual(TEAMS);
		expect($rootScope.readyState).toBeFalsy();
	});
	

	it('should change location path to \'team/5\'', function() {
		$scope.goToTeamPage(5);
		expect($location.path()).toBe('/team/5');
	});

	it('should return primary color of team object as css style', function() {
		var team = {
			"primaryColor" : "FF0000"
		}
		var result = $scope.getStyle(team);
		expect(result['color']).toBe('white');
		expect(result['background-color']).toBe('#FF0000');
	});

});