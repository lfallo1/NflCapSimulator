<div class="jumbotron">
	<h1>{{title}}</h1>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-8">
			<ul class="list-group">
				<li class="list-group-item error-class" ng-show="!isValid('player', 'name').valid"><span class="glyphicon glyphicon-remove-circle">&nbsp;</span><span ng-bind="isValid('player', 'name').msg"></span></li>
				<li class="list-group-item error-class" ng-show="!isValid('contractObj', 'signingBonus').valid"><span class="glyphicon glyphicon-remove-circle">&nbsp;</span><span ng-bind="isValid('contractObj', 'signingBonus').msg"></span></li>
			</ul>
			<table class="table" id="contract-overview-table">
				<tbody>
					<tr ng-class="{'error-bg' : !isValid('player', 'name').valid}">
						<td class="custom-label">Name</td>
						<td><input ng-class="{'error-class' : !isValid('player', 'name').valid}" class="form-control" name="name" ng-model="player.name.value" placeholder="Enter player name..." /></td>  
				    </tr> 
					<tr ng-class="{'error-bg' : !isValid('contractObj', 'signingBonus').valid}">
						<td class="custom-label">Signing Bonus</td>
						<td><input ng-class="{'error-class' : !isValid('contractObj', 'signingBonus').valid}" class="form-control" name="signingBonus" ng-model="contractObj.signingBonus.value" /></td>
					</tr>
					<tr>
						<td class="custom-label">Accrued Seasons</td>
						<td>    					
							<div id="accrued-seasons-dropdown">
								<select class="form-control" ng-model="player.selectedAccruedSeasons.value" ng-change="accruedChangedEvent()">
						        	<option ng-repeat="a in accruedSeasons" value="{{a}}">{{a}}</option>
						    	</select>								
							</div>
						</td>
					</tr>					
					<tr>
						<td class="custom-label">Contract Length</td>
						<td>    					
							<div id="contract-length-dropdown">
								<select class="form-control" ng-model="player.selectedContractLength.value">
						        	<option ng-repeat="c in contractLengths" value="{{c}}">{{c}}</option>
						    	</select>
							</div>
						</td>
					</tr>
					<tr>
						<td class="custom-label">Position</td>
						<td>
						 	<select class="form-control" ng-model="player.selectedPosition.value">
					        	<option ng-repeat="p in positions" value="{{p}}">{{p}}</option>
					    	</select>
						</td>
					</tr>
				</tbody>
			</table>		
		</div>
		<div class="col-md-4 summary">
			<h4>Summary</h4>
			<div ng-bind="getNameAndPosition()"></div>
			<div>SB:{{contractObj.signingBonus.value | currency}}</div>
			<div>Contract Length: {{player.selectedContractLength.value}}</div>
			<div>Total Value: <span ng-bind="getTotalValue() | currency"></span></div>
			<div>APY: <span ng-bind="getAPY() | currency"></span></div>
			<div>Guaranteed: <span ng-bind="getTotalGuaranteed() | currency"></span></div>
			<div>Percent Guaranteed <span ng-bind="getPctGuaranteed()"></span></div>
			<hr>
			<button class="submit-button success-background " ng-click="submit()" sc-enter-support>Submit</button>
			<button class="submit-button error-bg" ng-click="cancel()">Cancel</button>
		</div>		
	</div>
	<hr>
	<h4>Yearly Conract Information</h4>
	<div id="contracts" class="row">
		<div class="col-md-12">
			<div ng-repeat="i in getNumber(player.selectedContractLength.value) track by $index">
				<li class="list-group-item error-class" ng-show="!isValid('salary', null, $index).valid"><span class="glyphicon glyphicon-remove-circle">&nbsp;</span><span ng-bind="isValid('salary', null, $index).msg"></span></li>
			</div>
			<br>
			<form class="form-inline">
				<table class="table table-hover" id="contracts-table">
					<thead>
						<tr>
							<th></th>
							<th>Signing Bonus</th>
							<th>Base Salary</th>
							<th>Guaranteed</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-class="{'error-bg' : !isValid('salary', null, $index).valid}" ng-repeat="i in getNumber(player.selectedContractLength.value) track by $index">
							<td class="text-align-middle"><span class="year-label">{{year + $index}}</span></td>
							<td class="text-align-middle"><span class="bonus-label" ng-bind="getSigningBonusProration($index+1) | currency"></span></td>
							<td>
						      	<input type="text" class="form-control" 
						      		ng-model="contractObj.contractList[$index].baseSalary"
						      		ng-init="contractObj.contractList[$index].baseSalary=getMinBaseSalary($index)"
						      		placeholder="Base Salary">
							</td>
							<td>			
								 <input type="text" class="form-control"
						      		ng-model="contractObj.contractList[$index].guaranteedBaseSalary" 
						      		ng-init="contractObj.contractList[$index].guaranteedBaseSalary=0">
							</td>		
							<td class="text-align-middle">
								<span class="min-salary-help-msg">*Min Base Salary <span ng-bind="getMinBaseSalary($index) | currency:undefined:0"></span></span>
							</td>	
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>