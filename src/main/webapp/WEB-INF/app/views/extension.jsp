<div class="jumbotron">
	<h1>{{title}}</h1>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-12">
				<sc-player-head name="contract.player.name" team="team" position="contract.position"></sc-player-head>
<!-- 				<h4 id="restructure-head">{{contract.player.name}} <span class="badge" ng-style="getBadgeStyle()">{{contract.position}} </span><small> {{contract.year}} </small></h4> -->
				<hr>
			</div>		
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
	        <div class="col-md-12">
	            <div class="multi-select-list" ng-dropdown-multiselect=""
	                 options="years"
	                 selected-model="selectedYears"
	                 extra-settings="yearDropdownSettings"
	                 translation-texts="yearDropdownLabel"
	                 events="yearDropdownEvents">
	             </div>
	        </div>
			<div class="col-md-12">
	            <input class="form-control" type="text" placeholder="Enter signing bonus" ng-model="signingBonus" />
	        </div>			
		</div>
		<div class="col-md-10">
    		<form name="restructuredContract" class="form-inline">
    			<div ng-repeat="c in contracts">
	    			<span class="year-label-old badge">{{c.year}}</span>
	    			<span class="signing-bonus" ng-style="signingBonusStyle()">{{c.signingBonus + c.optionBonus + signingBonus/(selectedYears.id + contracts.length) | currency}}</span>
	    			<input name="baseSalary[{{$index}}]" class="form-control" placeholder="Base Salary" ng-model="c.baseSalary"/>
	    			<input name="guaranteedBaseSalary[{{$index}}]" class="form-control" placeholder="Guaranteed Base Salary" ng-model="c.guaranteedBaseSalary"/>
	    			<span class="text-danger"
	    				ng-show="validateObj($index, false).hasError"
	    				ng-bind="validateObj($index, false).msg">
    				</span>
    			</div>
	    		<div ng-repeat="i in getNumber(selectedYears) track by $index">
	    			<span class="badge" ng-style="getNewYearLabelStyle()">{{contract.year + contracts.length + $index}}</span>
	    			<span class="signing-bonus" ng-style="signingBonusStyle()">{{signingBonus/(selectedYears.id + contracts.length) | currency}}</span>
	    			<input name="baseSalary[{{$index}}]" class="form-control" placeholder="Base Salary" ng-model="extensionObj[$index].baseSalary"/>
	    			<input name="guaranteedBaseSalary[{{$index}}]" class="form-control" placeholder="Guaranteed Base Salary" ng-model="extensionObj[$index].guaranteedBaseSalary"/>
	    			<span class="text-danger" 
	    				ng-init="extensionObj[$index].baseSalary = 1500000; extensionObj[$index].guaranteedBaseSalary = 0;"
	    				ng-show="validateObj($index, true).hasError"
	    				ng-bind="validateObj($index, true).msg">
    				</span>
	    		</div>
	    		<span id="submit-restructure" class="btn btn-info" ng-click="submitExtension()" sc-enter-support>Submit</span>
	    		<span class="home btn btn-default" ng-click="home()">Cancel</span>
    		</form>
    		<br>
    		<span class="legend badge" ng-style="legendStyle()">*Highlighted years denotes a year being added to existing contract</span>
    	</div> 
	</div>
</div>