<div class="jumbotron">
	<h2>{{title}}</h2>
	<sc-player-head name="transaction.player.name" team="team" position="false"></sc-player-head>
    <sc-transaction-item transaction-param="transaction" team="team"></sc-transaction-item>
    <br>
    <button class="primary-button" ng-click="undo()">Undo Transaction</button>
    <button class="primary-button" ng-click="viewTeamPage()" ng-style="{'background' : '#' + team.primaryColor, 'color':'white'}">View Team Page</button>
</div>