<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="salaryCapApp" ng-controller="CssCtrl">
<head>
    <title>Salary Cap App</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="description" content="NFL Salary Cap Simulator">
	<meta name="keywords" content="NFL Salary Cap Football Franchise Sports Ravens Cowboys Packers Money">
	<meta name="author" content="Lance Fallon">    
    <script src="bower/jquery/dist/jquery.min.js" type="text/javascript"></script>
    <script src="bower/angular/angular.min.js" type="text/javascript"></script>
    <script src="bower/angular-route/angular-route.min.js" type="text/javascript"></script>
    <script src="bower/angular-resource/angular-resource.min.js" type="text/javascript"></script>
    <script src="bower/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="bower/angular-bootstrap/ui-bootstrap.min.js" type="text/javascript"></script>
    <script src="bower/angular-bootstrap/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
    <script src="bower/bootstrap-multiselect/dist/js/bootstrap-multiselect.js" type="text/javascript"></script>
    <script src="bower/lodash/lodash.js" type="text/javascript"></script>
    <script src="bower/angularjs-dropdown-multiselect/dist/angularjs-dropdown-multiselect.min.js" type="text/javascript"></script>
    <script src="app/js/app.js" type="text/javascript"></script>
    <script src="app/js/controllers.js" type="text/javascript"></script>
    <script src="app/js/services/application.js" type="text/javascript"></script>
    <script src="app/js/services/restservices.js" type="text/javascript"></script>
    <script src="app/js/services/utilityservices.js" type="text/javascript"></script>
    <script src="app/js/services/extensionservices.js" type="text/javascript"></script>
    <script src="app/js/services/rosterservices.js" type="text/javascript"></script>
    <script src="app/js/services/validation.js" type="text/javascript"></script>
    <script src="app/js/controllers/CssCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/NavCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/NotificationCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/HomeCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/TeamCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/TransactionCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/TransactionsCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/PlayerCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/ExtensionCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/RestructureCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/CreatePlayerCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/TradeCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/CalculationsCtrl.js" type="text/javascript"></script>
    <script src="app/js/controllers/modals/ModalInstanceCtrls.js" type="text/javascript"></script>
    <script src="app/js/filters/PercentageFilter.js" type="text/javascript"></script>
    <script src="app/js/directives/PlayerHead.js" type="text/javascript"></script>
    <script src="app/js/directives/TransactionItem.js" type="text/javascript"></script>
    <script src="app/js/directives/PositionDropdown.js" type="text/javascript"></script>
    <script src="app/js/directives/ContractStatusDropdown.js" type="text/javascript"></script>
    <script src="app/js/directives/EnterSupport.js" type="text/javascript"></script>
    <script src="app/js/directives/TeamDropdown.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="bower/bootstrap/dist/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="bower/bootstrap-multiselect/dist/css/bootstrap-multiselect.css" />
    <link type="text/css" rel="stylesheet" href="bower/animate-css/animate.min.css" />
    <link type="text/css" rel="stylesheet" href="app/css/site.css" />
    <link type="text/css" rel="stylesheet" ng-href="{{css}}">
</head>
<body>
<!-- Navigation Bar -->
<nav role="navigation" class="navbar navbar-default" ng-controller="NavCtrl">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#toggle-items">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#/"><span class="glyphicon glyphicon-home"></span></a>
    </div>
    <div id="toggle-items" class="navbar-collapse collapse navbar-responsive-collapse">
        <ul class="nav navbar-nav navbar-left">
            <li><a class="navbar-item" href="" ng-click="navigate('/')">Home</a></li>
            <li><a class="navbar-item" href="" ng-click="navigate('/calculations')">Calculations</a></li>
            <li><a class="navbar-item" href="" ng-click="navigate('/transactions')">All Transactions</a></li>
            <li><a class="navbar-item" href="" ng-click="reset()">Reset</a></li>
        </ul>
        <span class="right-align"><p>Contract data courtesy of Jason Fitzgerald at <a href="http://overthecap.com/"><img id="otclogo" src="app/images/otc-logo.png" /></a></span>
    </div>
</nav>
<div class="container">
	<div class="startup" ng-show="!readyState" ng-controller="NotificationCtrl">
		<div class="loading-wrapper">
			<h3><img ng-src="app/images/loading.gif" /><span ng-bind="getCurrentApplicationMessage()"></span></h3>
		</div>
	</div>	
	<!-- Views -->
    <div ng-view ng-show="readyState" class="animated fadeIn"></div>
</div>
<hr>
<div class="footer">
	<p>Contract data courtesy of Jason Fitzgerald at <a href="http://overthecap.com/"><img id="otclogo" src="app/images/otc-logo.png" /></a>
	<p>Lance Fallon | <a href="mailto:LFallon@NflCapSimulator.com">LFallon@NflCapSimulator.com</a> | 2015</p>
</div>
</body>
</html>