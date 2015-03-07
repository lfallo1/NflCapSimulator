/**
 * 
 */
(function(){
	var validationServices = angular.module('validationServices', []);
	
	validationServices.factory ('SalaryValidator', function(){
		var factory = {};
		factory.validate = function(minimumSalary, contract, year){
			var errorObj = {'valid':true, 'msg':''};
			if(isNaN(contract.baseSalary) || isNaN(contract.guaranteedBaseSalary)){
				errorObj.valid = false;
				errorObj.msg = year + " contract is invalid. Base Salary and Guaranteed Base Salary must be valid numbers.";
			}
			else if(Number(contract.baseSalary) < Number(contract.guaranteedBaseSalary) || Number(contract.baseSalary) < Number(minimumSalary)){
				errorObj.valid = false;
				errorObj.msg = year + " contract is invalid. Ensure that min base salary is met, and that guaranteed amount is not greater than base salary.";
			}
			return errorObj;
		}
		return factory;
	});
	
	validationServices.factory ('GenericValidator', function(){
		var factory = {};
		
		//MaxLength
		function MaxLength(){
			this.name = 'MaxLength';
			this.value = 25;
		};
		MaxLength.prototype.validate = function(val){
			return val.trim().length < this.value
		};
		MaxLength.prototype.getErrorMessage = function(){return "field must be les than " + this.value + " characters";}
		factory.MaxLengthAnnotation = function(value){
			var maxLength = new MaxLength();
			maxLength.value = value !== undefined ? value : this.value;
			return maxLength;
		}
		
		//NotEmpty
		function NotEmpty(){
			this.name = 'NotEmpty';
		}
		NotEmpty.prototype.validate = function(val){
			if(val===undefined)
				return false;
			return val.toString().trim().length > 0;
			
		};
		NotEmpty.prototype.getErrorMessage = function(){return "Field cannot be empty";}
		factory.NotEmptyAnnotation = function(){return new NotEmpty();}
		
		//LessThan
		function LessThan(){
			this.name = 'LessThan';
			this.value = 25;
		};
		LessThan.prototype.validate = function(val){
			return val < this.value
		};
		LessThan.prototype.getErrorMessage = function(){return "field must less than " + thi.value;}
		factory.LessThanAnnotation = function(v){
			var lessThan = new LessThan();
			if(v!==undefined){lessThan.value = v;}
			return lessThan;
		}
		
		//Number
		function Number(){
			this.name = 'LessThan';
		};
		Number.prototype.validate = function(val){return !isNaN(val)};	
		Number.prototype.getErrorMessage = function(){return "field must be a numeric value";}
		factory.NumberAnnotation = function(){return new Number();}
		
		//Between
		function Between(){
			this.name = 'Between';
			this.min = 0;
			this.max = 200000000;
		}
		Between.prototype.validate = function(val){return val >= this.min && val <= this.max};
		Between.prototype.getErrorMessage = function(){return "Value must be between " + this.min + " and " + this.max;}
		factory.BetweenAnnotation = function(min, max){
			var between = new Between();
			if(min !== undefined){between.min = min;}
			if(max !== undefined){between.max = max;}
			return between;
		}
		
		factory.validate = function(property, fieldName){
			var errorObj = {'valid':true, 'msg':''};
			
			for(i = 0; i < property.annotations.length; i++){
				errorObj.valid = property.annotations[i].validate(property.value);
				if(!errorObj.valid){
					errorObj.msg = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1) + " " + property.annotations[i].getErrorMessage();
					return errorObj;
				}			
			}
			return errorObj;
		}
		
		return factory;
	});
	
})();