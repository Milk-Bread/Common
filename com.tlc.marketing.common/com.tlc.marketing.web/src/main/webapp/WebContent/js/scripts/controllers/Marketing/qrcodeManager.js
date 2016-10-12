/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service'], function (app) {
    app.controller('userMngCtrl', function (service,$scope,$location,$state,$stateParams) {
        $scope.userName = '二维码管理';
        $scope.doIt = function(){
            $state.go("Main.QrCodeManager");
        };
    });
});