<div class="jumbotron">
	<h2>{{title}}</h2>
	<ul class="list-group">
		<li class="list-group-item" ng-repeat="t in transactions">
			<span>{{t.player.name}} ({{t.year}}) - {{t.description}} <span ng-show="t.capSavings > 0">Cap Savings: {{t.capSavings | currency:undefined:0}}</span></span>
		</li>
	</ul>
</div>