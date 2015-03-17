'use strict';

describe('Controller: HomeCtrl', function(){
	
	var $scope, $rootScope, $location;
	
	beforeEach(module('app.controllers'));
	beforeEach(inject(function(_$rootScope_, $controller, _$location_){
		$scope = _$rootScope_.$new();
		$rootScope = _$rootScope_;
		$location = _$location_;
		$controller('HomeCtrl', {
			'$scope' : $scope,
			'$location' : $location
		});
	}));
	
	it('should change location path to \'team/5\'', function(){
		$scope.goToTeamPage(5);
		expect($location.path()).toBe('/team/5');
	})
	
});