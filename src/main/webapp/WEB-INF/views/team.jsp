<div class="jumbotron" ng-cloak>
    <h1>{{team.name}} Salary Cap</h1>
    <img ng-src="${pageContext.request.contextPath}/resources/images/teamlogos/{{team.logo}}" alt="No Team Available" />
    <p><strong>Salary Cap: {{salaryCap | currency}}</strong></p>
    <p><strong>Adjusted Cap: {{adjustedCap | currency}}</strong></p>    
    <p><strong>Total Salary: {{totalSalary | currency}}</strong></p>
    <p><strong>Cap Room: {{capRoom | currency }}</strong></p>
    <button class="primary-button" ng-style="getPrimaryTeamColorBg()" ng-click="addPlayer()">Add Player</button>
    <button class="primary-button" ng-style="getSecondaryTeamColorBg()" ng-click="openTransactionModal()">Transactions</button>
</div>
<div class="alert alert-warning" ng-show="notification!=false"><span class="glyphicon glyphicon-info-sign"></span> {{notification}}</div>
<div class="row">
    <div class="col-md-10 col-md-push-2">
        <table class="table table-hover table-striped">
            <thead>
            <tr id="contract-table-headers" ng-style="getRowHeaderStyle()">
                <th></th>
                <th></th>
                <th ng-click="predicate='player.name'; reverse=!reverse">Name</th>
                <th ng-click="predicate='position'; reverse=!reverse">Position</th>
                <th ng-click="predicate='status'; reverse=!reverse">Status</th>
                <th ng-click="predicate='baseSalary'; reverse=!reverse">P5</th>
                <th ng-click="predicate='signingBonus'; reverse=!reverse">Bonus</th>
                <th ng-click="predicate='capCharge'; reverse=!reverse">Cap Charge</th>
                <th ng-click="predicate='deadMoney'; reverse=!reverse">Dead Money</th>
            </tr>
            </thead>
            <tbody>
            <tr class="contract-table-row" ng-repeat="c in contracts | orderBy:predicate:reverse" ng-style="inTop51or53(c)">
                <td>{{$index+1}}</td>
                <td>
                    <!-- Action Button -->
                    <div class="btn-group" dropdown>
                        <button type="button" class="btn btn-default dropdown-toggle" dropdown-toggle>
                        	<span>Actions</span>
                            <span class="caret"></span>
                            <span class="sr-only">Actions</span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="" ng-click="performAction('cut', c)">Cut</a></li>
                            <li><a href="" ng-click="performAction('june1cut', c)">June 1 Cut</a></li>
                            <li><a href="" ng-click="performAction('restructure', c)">Restructure</a></li>
                            <li><a href="" ng-click="performAction('extend', c)">Extend</a></li>
                            <li><a href="" ng-click="performAction('trade', c)">Trade</a></li>
                        </ul>
                    </div>
                </td>
                <td><span class="btn btn-link" ng-style="getPlayerLinkStyle()" ng-click="openPlayerModal(c.player.id)">{{c.player.name}}</span></td>
                <td>{{c.position}}</td>
                <td>{{c.status}}</td>
                <td>{{c.baseSalary | currency:undefined:0}}</td>
                <td>{{c.signingBonus + c.optionBonus | currency:undefined:0}}</td>
                <td>{{c.capCharge | currency:undefined:0}}</td>
                <td>{{c.deadMoney | currency:undefined:0}}</td>
            </tr>
            </tbody>
        </table>
        <div id="loading" ng-show="loading"><img ng-src="resources/images/loading.gif" /></div>
    </div>
    <div class="col-md-2 col-md-pull-10">
        <div id="toggle-filters-btn" class="primary-button" ng-click="filtersVisible=!filtersVisible">Toggle Filters</div>
        <div id="filters" ng-show="filtersVisible">
            <div class="row">
                <div id="year-dropdown" class="col-md-12">
                    <select class="form-control" ng-model="yearDropdown" ng-change="filterChanged()">
                        <option value="2014" selected="selected">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                        <option value="2018">2018</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                	<sc-position-dropdown></sc-position-dropdown>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
					<sc-contract-status-dropdown></sc-contract-status-dropdown>
                </div>
            </div>
        </div>
        <hr>
        <div id="dead-money-home" class="row">
        	<h4>Dead Money</h4>
          	<div class="col-md-12">
          		<div class="dead-money-cell" ng-repeat="d in deadMoney | orderBy:'deadMoney':-1">
          			<span class="dead-money-player">{{d.player.name}}</span>
          			<span class="dead-money-amount">{{d.deadMoney | currency}}</span>
          		</div>
          	</div>
        </div>        
    </div>
</div>