define(['app', 'service'], function (app) {
	app.controller('roleMngCtrl', function (service,$scope,$location,$state,$stateParams,$rootScope) {
		var user = $rootScope._USER;
		console.log(user);
		$scope.userName = '权限管理';
	    console.log("hello roleMngCtrl");
	    $scope.doIt = function(){
	    	$state.go("Main.UserManager");
	    };
	});
});