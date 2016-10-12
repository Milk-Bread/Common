/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service'], function (app) {
    app.controller('userMngCtrl', function (service,$scope,$location,$state,$stateParams) {
        service.post2SRV("getQrcodeImg", null,function(data,status) {
            console.log(data);
        },1000);
    });
});