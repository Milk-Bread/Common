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
	        	if(data == "failure"){
	        		new Pop("错误提示","系统繁忙，稍后再试");
	        		return;
	        	} else if (data == "error") {
	        		new Pop("错误提示","系统内部错误！");
	        		return;
	        	}
	        	callBack(data, status, headers, config);
	        },
	        error : function(data, status, headers, config) {
	        	new Pop("错误提示",data._exceptionMsg)
	        }
	    });
	};
});

