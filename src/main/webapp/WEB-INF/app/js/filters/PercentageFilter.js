(function(){
    angular.module('salaryCapApp').filter('Percentage', function(){
        return function(value) {
            return (value*100).toFixed(2) + '%';
        };
    });
})();