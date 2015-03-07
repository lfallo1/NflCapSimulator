<div class="calculations jumbotron">
	<div class="row">
		<div class="col-md-8">
			<h2>Salary Cap Calculations</h2>
		</div>
		<div class="col-md-4 year-dropdown-wrapper">
	        <select class="form-control" ng-model="yearDropdown" ng-change="filterChanged()">
				<option value="2015" selected="selected">2015</option>
	            <option value="2016">2016</option>
	            <option value="2017">2017</option>
	            <option value="2018">2018</option>
	        </select>
		</div>
	</div>
</div>
<div class="calculations" class="col-md-8 col-md-push-2">	
    <ul id="calculations-list">
        <li ng-class="{'active-tab' : isActive('getHighestPaid')}" ng-click="calculate('getHighestPaid')"><a>Highest Cap Charge</a></li>
        <li ng-class="{'active-tab' : isActive('getTeamSalaries')}" ng-click="calculate('getTeamSalaries')"><a>Team Salaries</a></li>
    </ul>
    <div ng-show="hasResults">
    	<table ng-show="isActive('getHighestPaid')" class="table table-hover table-responsive">
    		<thead>
    			<tr>
    				<th ng-click="predicate='player.name';reverse=!reverse">Name</th>
    				<th ng-click="predicate='position';reverse=!reverse">Position</th>
    				<th ng-click="predicate='baseSalary';reverse=!reverse">Base Salary</th>
    				<th ng-click="predicate='signingBonus';reverse=!reverse">Bonus</th>
    				<th ng-click="predicate='capCharge';reverse=!reverse">Cap Charge</th>
    			</tr>
    		</thead>
    		<tbody>
    			<tr ng-repeat="r in highestPaidResults | orderBy:predicate:reverse">
    				<td><a href="#/player/{{r.player.id}}">{{r.player.name}}</a></td>
    				<td>{{r.position}}</td>
    				<td>{{r.baseSalary | currency:undefined:0}}</td>
    				<td>{{r.signingBonus | currency:undefined:0}}</td>
    				<td>{{r.capCharge | currency:undefined:0}}</td>
    			</tr>
    		</tbody>
    	</table>
    	<table ng-show="isActive('getTeamSalaries')" class="table table-hover table-responsive">
    		<thead>
    			<tr>
    				<th ng-click="predicate='year';reverse=!reverse">Year</th>
    				<th ng-click="predicate='team.name';reverse=!reverse">Team</th>
    				<th ng-click="predicate='adjustedCap';reverse=!reverse">Adjusted Cap</th>
    				<th ng-click="predicate='totalSalary';reverse=!reverse">Total Salary</th>
    				<th ng-click="predicate='(adjustedCap - totalSalary)';reverse=!reverse">Cap Room</th>
    			</tr>
    		</thead>
    		<tbody>
    			<tr ng-repeat="r in teamSalariesResults | orderBy:predicate:reverse ">
    				<td>{{r.year}}</td>
    				<td><a href="#/team/{{r.team.id}}">{{r.team.name}}</a></td>
    				<td>{{r.adjustedCap | currency:undefined:0}}</td>
    				<td>{{r.totalSalary | currency:undefined:0}}</td>
    				<td>{{r.adjustedCap - r.totalSalary | currency:undefined:0}}</td>
    			</tr>
    		</tbody>
    	</table>
    </div>
</div>