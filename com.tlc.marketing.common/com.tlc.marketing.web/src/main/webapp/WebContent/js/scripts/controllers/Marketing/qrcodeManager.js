/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service'], function (app) {
    app.controller('qrCodeManager', function (service,$scope,$location,$state,$stateParams) {
        alert("sss");
        service.post2SRV("getQrcodeImg", null,function(data,status) {
            console.log(data);
        },1000);
    });
});