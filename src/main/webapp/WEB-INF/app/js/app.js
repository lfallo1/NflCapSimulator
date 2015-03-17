(function() {
    var myApp = angular.module('salaryCapApp', [
        'ngRoute',
        'app.controllers',
        'ui.bootstrap',
        'angularjs-dropdown-multiselect'
    ]);

    myApp.config(['$routeProvider', function($routeProvider) {

    	$routeProvider.when('/', {
    		templateUrl : 'Home',
    		controller : 'HomeCtrl',
    		css : 'app/css/home.css'
    	});

    	$routeProvider.when('/calculations', {
    		templateUrl : 'Calculations',
    		controller : 'CalculationsCtrl',
    		css : 'app/css/calculations.css'
    	});    	
    	
        $routeProvider.when('/team/:id', {
            templateUrl: 'Team',
            controller: 'TeamCtrl'
        });
        
        //Route for the inventory index page
        $routeProvider.when('/transaction/:id', {
         templateUrl: 'Transaction',
         controller: 'TransactionCtrl'
       });     
        
        //Route for the inventory index page
        $routeProvider.when('/transactions', {
         templateUrl: 'Transactions',
         controller: 'TransactionsCtrl'
       });           
        
        $routeProvider.when("/player/:id", {
        	templateUrl: 'Player',
        	controller: 'PlayerCtrl',
        	css : 'app/css/player.css'
        });
        
        $routeProvider.when("/extension/:contractId", {
        	templateUrl: 'Extension',
        	controller: 'ExtensionCtrl',
        	css : "app/css/extension.css"
        });
        
        $routeProvider.when("/restructure/:contractId", {
        	templateUrl: 'Restructure',
        	controller: 'RestructureCtrl',
        	css : "app/css/restructure.css"
        });
        
        $routeProvider.when("/trade/:id", {
        	templateUrl: 'Trade',
        	controller: 'TradeCtrl',
        	css : "app/css/trade.css"
        });        
        
        $routeProvider.when("/createPlayer/:teamId", {
        	templateUrl: 'CreatePlayer',
        	controller: 'CreatePlayerCtrl',
        	css : "app/css/createPlayer.css"
        });               

        $routeProvider.otherwise({
            redirectTo: '/'
        });
    }]);
}());