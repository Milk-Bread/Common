define([ 'app'], function(app) {
	app.service('service',function(){
		/** 统一ajax请求方法 **/
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
		        		showError("错误提示","系统繁忙，稍后再试");
		        		return false;
		        	} else if (data == "error") {
		        		showError("错误提示","系统内部错误！");
		        		return false;
		        	} else if(data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != null){
		        		showError("错误提示",data._exceptionMsg);
		        		return false;
		        	}
		        	callBack(data, status, headers);
		        },
		        error : function(data, status, headers, config) {
		        	var errorStr = "网络错误";
		        	if(data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != null){
		        		errorStr = data._exceptionMsg;
		        	}
		        	showError("错误提示",errorStr);
		        }
		    });
		};
		/** 统一页面跳转方法 **/
		this.skipPage = function (url){
			if(window.XMLHttpRequest){
		        XMLHttpR = new XMLHttpRequest();
		    }else if(window.ActiveXObject){
		        try{
		            XMLHttpR = new ActiveXObject("Msxml2.XMLHTTP");
		        }catch(e){
		            try{
		                XMLHttpR = new ActiveXObject("Microsoft.XMLHTTP");
		            }catch(e){
		            }
		        }
		    }
		    XMLHttpR.open("GET",url,true);
		    XMLHttpR.setRequestHeader("Content-Type","text/html;charset=utf-8");
		    XMLHttpR.onreadystatechange = function(){
			    if(XMLHttpR.readyState ==4 && XMLHttpR.status == 200){
			        document.body.innerHTML = XMLHttpR.responseText;
			    }
		    };
		    XMLHttpR.send(null);
		};
		this.showError = function(title,intro){
			$("#popTitle a").html(title);
			$("#popIntro").html(intro);
			$('#pop').slideDown(1000);
		};
	});
});
