<div class="row">
	<div class="col-md-12">
		<button class="primary-button home-btn" ng-click="backToTeamPage()">View team page</button>
		<sc-player-head name="player.name" team="team" position="contracts[contracts.length-1].position"></sc-player-head>	
		<!-- 	<h4 class="name-and-position" ng-bind="getNameAndPosition()"></h4> -->
		<!-- 	<img ng-src="/resources/images/teamlogos/{{team.logo}}" height="100px" /> -->
		   <div class="well" ng-style="getTeamPrimaryColorBg('0.5')">
		   	<div class="row player-bio">
		   		<div class="col-md-6">
		   			<div ng-show="player.weight!==0">
					    <h5>Date of Birth: <span ng-bind="formatDob(player.dateOfBirth)"></span></h5>
					    <h5 ng-show="player.weight!==0">{{player.height}} {{player.weight}}lbs</h5>
					    <h5>Accrued Seasons: <span ng-bind="getAccrued(player.accrued)"></span></h5>
					    <h5 ng-bind="getDraftInfo()"></h5>    		
					    <h5 ng-show="player.college !== 0">{{player.college}}</h5>
					</div>
					<div ng-show="player.weight===0">
						<h4>Player bio is unavailable</h4>
					</div>
		   		</div>
		   		<div class="col-md-6 contract-overview">
		   			<h5>Contract Length: <span ng-bind="getOverview().contractLength"></span></h5>
		   			<h5>Worth: <span ng-bind="getOverview().totalValue | currency:undefined:0"></span></h5>
		   			<h5>Guaranteed: <span ng-bind="getOverview().guaranteed | currency:undefined:0"></span></h5>
		   			<h5>APY: <span ng-bind="getOverview().apy | currency:undefined:0"></span></h5>
		   			<h5>Free Agent: <span ng-bind="getOverview().freeAgent"></span></h5>
		   		</div>   		
		   	</div>
		</div>
	 	<div ng-show="contracts.length > 0">
	      <table class="table table-hover">
	          <thead ng-style="getTeamPrimaryColorBg('1.0')">
	           <tr>
	               <th>Year</th>
	               <th>Status</th>
	               <th>P5</th>
	               <th>Guaranteed P5
	               <th>Signing Bonus</th>
	               <th>OB</th>
	               <th>OB</th>
	               <th>RB</th>
	               <th>Cap Charge</th>
	               <th>Dead Money</th>
	               <th>Cap Savings</th>
	           </tr>
	          </thead>
	          <tbody>
	           <tr ng-repeat="c in contracts | orderBy:'year'">
	               <td>{{c.year}}</td>
	               <td>{{c.status}}</td>
	               <td>{{c.baseSalary | currency:undefined:0}}</td>
	               <td>{{c.guaranteedBaseSalary | currency:undefined:0}}</td>
	               <td>{{c.signingBonus | currency:undefined:0}}</td>
	               <td>{{c.optionBonus | currency:undefined:0}}</td>
	               <td>{{c.workoutBonus | currency:undefined:0}}</td>
	               <td>{{c.rosterBonus | currency:undefined:0}}</td>
	               <td>{{c.capCharge | currency:undefined:0}}</td>
	               <td>{{c.deadMoney | currency:undefined:0}}</td>
	               <td>{{c.capSavings | currency:undefined:0}}</td>
	           </tr>
	          </tbody>
	      </table>
	    </div>
	    <div ng-show="!contracts.length > 0">
	    	<h4>Player has no contract information available</h4>
	    </div>	
	</div>
</div>