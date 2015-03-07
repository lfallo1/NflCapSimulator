<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="salaryCapApp" ng-controller="MainCtrl">
<head>
    <title>Salary Cap App</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="description" content="NFL Salary Cap Simulator">
	<meta name="keywords" content="NFL Salary Cap Football Franchise Sports Ravens Cowboys Packers Money">
	<meta name="author" content="Lance Fallon">    
	<script type="text/javascript">
	    var _contextPath = "${pageContext.request.contextPath}" + "/";
	</script>    
    <script src="libraries/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="libraries/js/angular.min.js" type="text/javascript"></script>
    <script src="libraries/js/angular-route.min.js" type="text/javascript"></script>
    <script src="libraries/js/angular-resource.min.js" type="text/javascript"></script>
    <script src="libraries/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="libraries/js/ui-bootstrap-tpls-0.12.0.min.js" type="text/javascript"></script>
    <script src="libraries/js/lodash.js" type="text/javascript"></script>
    <script src="libraries/js/angularjs-dropdown-multiselect.min.js" type="text/javascript"></script>
    <script src="resources/app/app.js" type="text/javascript"></script>
    <script src="resources/app/controllers.js" type="text/javascript"></script>
    <script src="resources/app/restservices.js" type="text/javascript"></script>
    <script src="resources/app/utilityservices.js" type="text/javascript"></script>
    <script src="resources/app/extensionservices.js" type="text/javascript"></script>
    <script src="resources/app/rosterservices.js" type="text/javascript"></script>
    <script src="resources/app/validation.js" type="text/javascript"></script>
    <script src="resources/app/controllers/MainCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/HomeCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/TeamCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/TransactionCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/TransactionsCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/PlayerCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/ExtensionCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/RestructureCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/CreatePlayerCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/TradeCtrl.js" type="text/javascript"></script>
    <script src="resources/app/controllers/CalculationsCtrl.js" type="text/javascript"></script>
    <script src="resources/app/modal/ModalInstanceCtrls.js" type="text/javascript"></script>
    <script src="resources/app/filters/PercentageFilter.js" type="text/javascript"></script>
    <script src="resources/app/directives/PlayerHead.js" type="text/javascript"></script>
    <script src="resources/app/directives/TransactionItem.js" type="text/javascript"></script>
    <script src="resources/app/directives/PositionDropdown.js" type="text/javascript"></script>
    <script src="resources/app/directives/ContractStatusDropdown.js" type="text/javascript"></script>
    <script src="resources/app/directives/EnterSupport.js" type="text/javascript"></script>
    <script src="resources/app/directives/TeamDropdown.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="libraries/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="libraries/css/bootstrap-multiselect.css" />
    <link type="text/css" rel="stylesheet" href="resources/css/site.css" />
    <link type="text/css" rel="stylesheet" ng-href="{{css}}">
</head>
<body>
<!-- Navigation Bar -->
<nav role="navigation" class="navbar navbar-default">
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
            <li><a class="navbar-item" href="#/">Home</a></li>
            <li><a class="navbar-item" href="#/calculations">Calculations</a></li>
            <li><a class="navbar-item" href="#/transactions">All Transactions</a></li>
            <li><a class="navbar-item" href="" ng-click="reset()">Reset</a></li>
        </ul>
        <span class="right-align"><p>Contract data courtesy of Jason Fitzgerald at <a href="http://overthecap.com/"><img id="otclogo" src="resources/images/otc-logo.png" /></a></span>
    </div>
</nav>
<div class="container">
	<!-- Reset Notificaion Dialogue -->
	<div class="row" id="roster-reset-notification-div" ng-show="resetRosterNotification">
		<div class="col-md-8 col-md-offset-2">
			<h3><img ng-src="${pageContext.request.contextPath}/resources/images/loading.gif" /> {{resetRosterNotification}}</h3>
		</div>
	</div>
	<div class="startup" ng-show="readyState===false">
		<div class="loading-wrapper">
			<h3><img ng-src="${pageContext.request.contextPath}/resources/images/loading.gif" /> Application is Starting...</h3>
		</div>
	</div>	
	<!-- Views -->
    <div ng-view></div>
</div>
<hr>
<div class="footer">
	<p>Contract data courtesy of Jason Fitzgerald at <a href="http://overthecap.com/"><img id="otclogo" src="resources/images/otc-logo.png" /></a>
	<p>Lance Fallon | <a href="mailto:LFallon@NflCapSimulator.com">LFallon@NflCapSimulator.com</a> | 2015</p>
</div>
</body>
</html>