<div class="jumbotron" ng-cloak>
    <h1>{{title}}</h1>
    <sc-player-head name="player.name" team="team" position="contract.position"></sc-player-head>
    <hr>
	<div class="row" ng-show="restructurePossible">
		<div class="col-md-6">
			<h3>Base Salary: {{contract.baseSalary | currency}}</h3>
			<h6 class="years-on-contract" ng-style="getYearsOnContractStyle()">[Years left on contract <strong>{{yearsRemaining}}</strong>]</h6>
			<h6 ng-style="getNoteStyle()">Max Restructure Amount: {{maxRestructureAmount | currency}}</h6>
			<input class="form-control" ng-model="amount" placeholder="Enter restructure amount" />
			<button class="primary-button" ng-click="doResctructure()" ng-disabled="!isValid()" sc-enter-support >Submit</button>
			<button class="danger-background primary-button" ng-click="cancel()">Cancel</button>
		</div>
		<div class="col-md-6">
			<h3>Salary Cap Calculation</h3>
			<div class="well" ng-class="getClass()">
				<h5>New Base Salary <strong>{{contract.baseSalary - amount | currency}}</strong></h5>
				<h5>New Cap Charge <strong>{{contract.capCharge - amount + (amount/yearsRemaining) | currency}}</strong></h5>
				<h5>Cap Relief <strong>{{contract.capCharge - (contract.capCharge - amount + (amount/yearsRemaining)) | currency}}</strong></h5>
			</div>
			<div ng-show="!isValid()">
				<h6 class="error" ng-show="!isNumber(amount)"><span class="glyphicon glyphicon-remove-circle"></span> Restructure amount must be a valid number</h6>
				<h6 class="error" ng-show="amount > maxRestructureAmount || amount <= 0"><span class="glyphicon glyphicon-remove-circle"></span> Restructure amount must be between $0.00 and {{maxRestructureAmount | currency}}</h6>
			</div>
		</div>
	</div>
	<div class="row" ng-show="!restructurePossible">
		<div class="col-md-8">
			<div class="alert alert-warning">
				<h4 id="restructure-not-possible-msg"><span class="glyphicon glyphicon-info-sign"></span> Restructure not available for the selected player.</h4>
				<a href="#/home" class="btn btn-lg btn-primary">Home</a>
			</div>
		</div>
	</div>
</div>