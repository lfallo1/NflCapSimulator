(function(){
    var extensionServices = angular.module('extensionservices', []);


    extensionServices.factory('ExtensionValidationService', function(){
       var factory = {};
        
        factory.validate = function(year, player, contract, numContracts, minimumSalaries, index, isNew){
            var errorObj = {
            	"hasError" : false,
            	"msg" : ""
            };

            var currentYear = isNew ? numContracts + index + year : index + year;
            var creditedSeasons = currentYear - player.accrued;
            var cs = creditedSeasons > 10 ? 10 : creditedSeasons;
            var minimumBaseSalary = minimumSalaries.filter(function(d){if(d.year === currentYear && d.creditedSeasons === cs) return d;})[0].baseSalary;
            
        	if(isNaN(contract.baseSalary) || isNaN(contract.guaranteedBaseSalary)){
        		errorObj.hasError = true;
        		errorObj.msg = "Both fields must be numeric values";
        		return errorObj;
        	}
        	if(Number(contract.baseSalary) < minimumBaseSalary){
        		errorObj.hasError = true;
        		errorObj.msg = "Min base salary for player with " + creditedSeasons + " credited seasons is " + minimumBaseSalary;
        		return errorObj;
        	}
			if(Number(contract.baseSalary) < Number(contract.guaranteedBaseSalary)){
        		errorObj.hasError = true;
        		errorObj.msg = "Guaranteed base salary must be less than base salary";
        		return errorObj;
			}
			if(Number(contract.baseSalary) < 0 || Number(contract.guaranteedBaseSalary)<0){
        		errorObj.hasError = true;
        		errorObj.msg = "Both fields must be greater than 0";
        		return errorObj;
			}			
			
            return errorObj;
        };
        
        return factory;
    });
    
})();