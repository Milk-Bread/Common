manage.service('$service',function(){
	this.post2SRV = function(action, formData, callBack) {
		var url = action;
	    formData["actionName"] = action;
	    $.ajax({
	        async : false,
	        type : "POST",
	        data : JSON.stringify(formData),
	        url : url,
	        dataType : "json",
	        success : function(data, status, headers, config) {
	        	console.log(data);
	        	if(data == "failure"){
	        		window.alert("系统繁忙，稍后再试！[WB]");
	        		return;
	        	} else if (data == "error") {
	        		window.alert("系统内部错误！[WB]");
	        		return;
	        	}
	        	console.log(data.respCode);
	        	console.log(status);
	        	console.log(headers);
	        	console.log(config);
	        	callBack(data, status, headers, config);
	        },
	        error : function(data, status, headers, config) {
	        	window.alert("网络错误[WB]");
	        }
	    });
	};
});

