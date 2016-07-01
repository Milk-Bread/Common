LoginCtrl.$inject = [ "$rootScope", "$scope","$service" ];
function LoginCtrl($rootScope,$scope,$service) {
	$scope.doIt = function(){
		var username = $scope.UserName;
		if(username == undefined || username == ''){
			new Pop("用户名错误","请输入你的用户名");
			return;
		}
		var password = $scope.Password;
		if(password == undefined || password == ''){
			new Pop("密码错误","请输入你的密码");
			return;
		}
		angular.element('.form').fadeOut(500);
		angular.element('.wrapper').addClass('form-success');
		var formData = {
			"userName" : username,
			"password" : password
		};

		$service.post2SRV("Login.do", formData,function(data,status) {
			new Pop("返回数据","用户名："+data.userName+"密码："+data.password);
			angular.element('.form').slideToggle(500);
			angular.element('.wrapper').removeClass('form-success');
    	});
	};
}