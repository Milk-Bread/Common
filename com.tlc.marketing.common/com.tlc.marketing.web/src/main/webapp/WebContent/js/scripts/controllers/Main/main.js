define(['app', 'service'], function (app) {
	app.controller('mainCtrl', function (service,$scope,$location,$state,$stateParams) {
//	    if(_USER.userId == null || _USER.userId == ''){
//	    	service.skipPage('htmls/Login/views/login.html');
//	    	return false;
//	    }
		service.post2SRV("LodeMenu", null,function(data,status) {
			console.log(data);
			$scope.menuList = data;
    	},4000);
//	    $scope.menuList = [{"menuOne":"用户管理","menuTwo":[{"name":"角色管理","url":"UserMng"},{"name":"用户管理",'url':"UserMng"},{"name":"权限管理",'url':"UserMng"},{"name":"密码管理",'url':"UserMng"}]},
//	                       {"menuOne":"红包管理","menuTwo":[{"name":"角色管理","url":"RoleMng"},{"name":"用户管理",'url':"RoleMng"},{"name":"权限管理",'url':"RoleMng"},{"name":"密码管理",'url':"RoleMng"}]},
//	                       {"menuOne":"系统设置","menuTwo":[{"name":"角色管理","url":"UserMng"},{"name":"用户管理",'url':"UserMng"},{"name":"权限管理",'url':"UserMng"},{"name":"密码管理",'url':"UserMng"}]},
//	                       {"menuOne":"图片管理","menuTwo":[{"name":"角色管理","url":"UserMng"},{"name":"用户管理",'url':"UserMng"},{"name":"权限管理",'url':"UserMng"},{"name":"密码管理",'url':"UserMng"}]},
//	                       {"menuOne":"产品管理","menuTwo":[{"name":"角色管理","url":"UserMng"},{"name":"用户管理",'url':"UserMng"},{"name":"权限管理",'url':"UserMng"},{"name":"密码管理",'url':"UserMng"}]},
//	                       {"menuOne":"日志管理","menuTwo":[{"name":"角色管理","url":"UserMng"},{"name":"用户管理",'url':"UserMng"},{"name":"权限管理",'url':"UserMng"},{"name":"密码管理",'url':"UserMng"}]},];
	});
});