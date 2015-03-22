<div ng-show="!resetRosterNotification" class="row">
	<div class="jumbotron">
		<h3><img id="nfl-logo" src="app/images/nfl-logo.png" /> NFL Salary Cap Simulator</h3>
		<p>Select a team below to get started.</p>
	</div>
	<div class="col-md-4 col-md-offset-4 select-team-wrapper">
		<ul class="list-group">
			<li class="team-list list-group-item" ng-repeat="t in teams" ng-click="goToTeamPage(t.id)">
				<span>{{t.name}}</span><img ng-src="app/images/teamlogos/{{t.logo}}" />
			</li>
		</ul>	
	</div>
</div>