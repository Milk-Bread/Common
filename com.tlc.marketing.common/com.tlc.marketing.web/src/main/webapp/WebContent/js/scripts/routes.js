define([], function () {
    return {
        defaultRoute: '/Login',
        routes: {
            'Login': {
            	url: '/Login',
                templateUrl: 'views/Login/login.html',
                dependencies: ['controllers/Login/login'],
                allowAnonymous: true
            },
            'Main': {
            	url: '/Main/:userName/:password',
                templateUrl: 'views/Main/main.html',
                dependencies: ['controllers/Main/main'],
                allowAnonymous: true
            },'Main.UserMng': {
            	url: '/UserMng',
                templateUrl: 'views/SysMng/userMng.html',
                dependencies: ['controllers/SysMng/userMng'],
                allowAnonymous: true
            },'Main.RoleMng': {
            	url: '/RoleMng',
                templateUrl: 'views/SysMng/roleMng.html',
                dependencies: ['controllers/SysMng/roleMng'],
                allowAnonymous: true
            }
        }
    };
});