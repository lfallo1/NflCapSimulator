<div class="jumbotron">
	<h3>{{title}}</h3>
	<div class="row">
		<div class="player-div col-md-7">
			<sc-player-head name="contract.player.name" team="currentTeam" position="contract.position"></sc-player-head>
			<div single-year-contract-summary>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5>{{contract.year}} Contract Summary with {{contract.teamName}}</h5>
					</div>
					<div class="panel-body">
						<h6>Cap Charge {{contract.capCharge | currency:undefined:0}}</h6>
						<h6>Dead Money {{contract.deadMoney - contract.guaranteedBaseSalary | currency:undefined:0}}</h6>
						<h6>Cap Savings {{contract.capCharge - (contract.deadMoney - contract.guaranteedBaseSalary) | currency:undefined0}}</h6>
						<h6>Current Cap Space {{currentTeamSalary.adjustedCap - currentTeamSalary.totalSalary | currency:undefined:0}}</h6>
						<h6>Cap Space After Trade {{(currentTeamSalary.adjustedCap - currentTeamSalary.totalSalary) + (contract.capCharge - (contract.deadMoney - contract.guaranteedBaseSalary)) | currency:undefined:0}}</h6>
					</div>
				</div>
			</div>
		</div>
		<div class="new-team-div col-md-5">
			<h4 class="new-team-note">Select team to which player will be traded</h4>
			<sc-team-dropdown></sc-team-dropdown>
			<div>
				<table class="table">
					<thead>
						<tr>
							<th>Year</th>
							<th>Cap Space</th>
							<th>Projected space if player acquired</th>
						</tr>
					</thead>
					<tbody>	
						<tr ng-repeat="s in totalSalaries">
							<td>{{s.year}}</td>
							<td>{{s.adjustedCap - s.totalSalary | currency:undefined:0}}</td>
							<td ng-bind="getNewCapSpace(s) | currency:undefined:0"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>'
	</div>
	<div class="row">
		<div class="trade-page-btns col-md-12">
			<button class="success-background primary-button" ng-click="submitTrade()">Perform Trade</button>
			<button class="danger-background primary-button" ng-click="goToTeamPage()">Cancel</button>
		</div>
	</div>
</div>