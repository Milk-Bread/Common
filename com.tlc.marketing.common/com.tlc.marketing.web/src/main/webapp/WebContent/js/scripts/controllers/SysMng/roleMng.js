define(['app', 'service'], function (app) {
	app.controller('roleMngCtrl', function (service,$scope,$location,$state,$stateParams) {
		$scope.userName = '权限管理';
	    console.log("hello roleMngCtrl");
	    $scope.doIt = function(){
	    	$state.go("Main.UserMng");
	    };
	});
});