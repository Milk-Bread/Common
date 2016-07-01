var manage = angular.module("Menege", ['ngRoute']);
manage.config(['$routeProvider','$locationProvider','$httpProvider', function($routeProvider,$locationProvider,$httpProvider){
    $routeProvider.when('/',{
    	templateUrl: 'html/Login/views/login.html'
    }).when('/Main',{
    	templateUrl: 'html/Main/views/main.html'
    })
    .otherwise({redirectTo:'html/Login/views/login.html'});
}]);

