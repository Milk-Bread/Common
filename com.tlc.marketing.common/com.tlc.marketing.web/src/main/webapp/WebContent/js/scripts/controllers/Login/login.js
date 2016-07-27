define(['app', 'service'], function (app) {
	app.controller('LoginCtrl', function (service,$scope,$location,$state) {
    	console.log('hello LoginCtrl');
    	$scope.doIt = function(){
    		var username = $scope.UserName;
    		if(username == undefined || username == ''){
    			service.showError("用户名错误","请输入你的用户名");
    			return;
    		}
    		var password = $scope.Password;
    		if(password == undefined || password == ''){
    			service.showError("密码错误","请输入你的密码");
    			return;
    		}
//    		angular.element('.form').fadeOut(500);
//    		angular.element('.wrapper').addClass('form-success');
    		console.log("ddd");
    		var formData = {
    			"userName" : username,
    			"password" : password
    		};
    		service.post2SRV("Login.do", formData,function(data,status) {
    			_USER.userId = data.userId;
    			_USER.userName = data.userName;
    			_USER.sex = 'M';
//    			$service.skipPage('html/Main/views/main.html');
    			$state.go("Main",{'userName':data.userName,'password':data.password})
//    			$location.path('/Main/'+data.userName+"/"+data.password);
//    			service.showError("返回数据","用户名："+data.userName+"密码："+data.password);
    			//angular.element('.form').slideToggle(500);
    			//angular.element('.wrapper').removeClass('form-success');
        	});
    	};
    });
});