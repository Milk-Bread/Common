manage.service('$service',function(){
	this.post2SRV = function(action, formData, callBack) {
		var url = action;
	    formData["actionName"] = action;
	    $.ajax({
	        async : false,
	        type : "POST",
	        data : formData,
	        url : url,
	        dataType : "json",
	        success : function(data, status, headers) {
	        	if(data == "failure"){
	        		new Pop("错误提示","系统繁忙，稍后再试");
	        		return false;
	        	} else if (data == "error") {
	        		new Pop("错误提示","系统内部错误！");
	        		return false;
	        	} else if(data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != null){
	        		new Pop("错误提示",data._exceptionMsg);
	        		return false;
	        	}
	        	if(JSON.parse(headers.response)._exceptionCode == undefined){
	        		status = true;
	        	}else{
	        		status = false;
	        	}
	        	callBack(data, status, headers);
	        },
	        error : function(data, status, headers, config) {
	        	var errorStr = "网络错误";
	        	if(data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != null){
	        		errorStr = data._exceptionMsg;
	        	}
	        	new Pop("错误提示",data._exceptionMsg);
	        }
	    });
	};
});

