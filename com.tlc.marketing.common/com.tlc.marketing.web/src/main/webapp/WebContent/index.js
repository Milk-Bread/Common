var manage = angular.module("Menege", ['ngRoute']);
manage.config(['$routeProvider', function($routeProvider){
    $routeProvider.when('/',{
    	templateUrl: 'html/Login/views/login.html'
    }).when('/Main/:userName/:password',{
        templateUrl: 'html/Main/views/main.html',
        controller:'mainCtrl'
    }).otherwise({redirectTo:'html/Login/views/login.html'});
}]);

