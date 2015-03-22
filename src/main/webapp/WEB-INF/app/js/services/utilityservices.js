(function(){
    var utilityServices = angular.module('utilityservices', []);

    utilityServices.factory('ArrayServices', function(){
       var factory = {};
        
        factory.sum = function(myArray, key){
            var total = 0;
            for(var i = 0; i < myArray.length; i++){
                total += myArray[i][key];
            }
            return total;
        };
        
        factory.arrayContains = function(myArray, value){
            for(var i = 0; i < myArray.length; i++){
               if(myArray[i]===value){
                   return true;
               }
            }
            return false;
        };

        factory.isArray = function(obj){
            return Object.prototype.toString.call(obj) === '[object Array]';
        }
        
        factory.generateArrayOfNumbers = function(length, startAtOne){
        	var obj = [];
        	var startingIndex = startAtOne ? 1 : 0;
        	var endingIndex = startAtOne ? length + 1 : length;
        	for(var i = startingIndex; i < endingIndex; i++){
        		obj.push(i);
    		}
        	return obj;
        }
        
        return factory;
    });

    utilityServices.factory('ListServices', function(){
        var rosterActions = ['Cut', 'June 1 Cut', 'Restructure', 'Extend'];
        var seasonList = ['Season', 'Offseason'];
        return {
            getSeasonList : function(){
                return seasonList;
            },
            getRosterActionsList : function(){
                return rosterActions;
            }
        };
    });
    
    utilityServices.factory('ColorServices', function(){
    	return{
    		hexToRgb : function(hex) {
    		    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    		    return result ? {
    		        rgb : parseInt(result[1], 16) + ',' + parseInt(result[2], 16) + ',' + parseInt(result[3], 16)
    		    } : null;
    		}
    	};
    });
    
    utilityServices.factory('MessageServices', function(){
    	var factory = {};
    	var applicationMessage = "Application is loading...";
    	var rosterActionMessage;
    	
    	factory.setApplicationMessage = function(message){
    		applicationMessage = message;
    	};
    	
    	factory.getApplicationMessage = function(){
    		return applicationMessage;
    	};
    	
    	factory.setRosterActionMessage = function(message){
    		rosterActionMessage = message;
    	};
    	
    	factory.getRosterActionMessage = function(){
    		return rosterActionMessage;
    	};    	
    	
    	return factory;
    });

})();
