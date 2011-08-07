/**
 * Asynchronous get with data that has backup default success and error functions
 * which utilize Alert.js to return the status of the JSON request. These functions
 * can be overridden for custom actions
 * @author Paul Scarrone(NuRelm)
 * @param url The URL to process the request against
 * @param data JSON encoded object which contains the request data
 * @param successfcn a function which takes data as a parameter
 * @param errorfcn a function which takes XMLHttpRequest, textStatus, errorThrown as parameters
 */
function AjaxGet(url, data, successfcn, errorfcn){
	var AjaxInstance = new AjaxErrorHandler();
	var success;
	var error;
	if(typeof(data) == "function"){
        successfcn = data;
        if(typeof(successfcn) == "function"){
            errorfcn = successfcn;
        }
	}
	/*Alert.CreateOneTimeUseDialog(url +"<br>"+"<br>"+
     data +"<br>"+"<br>"+
     successfcn +"<br>"+"<br>"+
     errorfcn
     );*/
	if(typeof(errorfcn) != "function"){
        error = function(XMLHttpRequest, textStatus, errorThrown){
            //Alert.CreateOneTimeUseDialog("Status: " +textStatus +"<br> Error: " + errorThrown, "Request Failed");
            alert("Status: " +textStatus +"<br> Error: " + errorThrown, "Request Failed");
        };
	}else{
        error = errorfcn;
	}
	if(typeof(successfcn) != "function"){
        success = function(data){
            //Alert.CreateOneTimeUseDialog("Success" + data + "|", "Request Succeeded");
            alert("Success" + data + "|", "Request Succeeded");
        };
        
	}else{
        success = successfcn;
	}
	if(data == null){
        data = {};
	}
	AjaxInstance.setURL(url);
	AjaxInstance.settype("GET");
	AjaxInstance.setasync(true);
	AjaxInstance.setdataType("json");
	AjaxInstance.setdata(data); //data is set in setURL
	AjaxInstance.setsuccessCall(success);
	AjaxInstance.seterrorCall(error);
	AjaxInstance.ajaxJSONGet();
}
/*function TestAJAXData(url){
 AjaxErrorHandler.setURL(url);//this will set data
 AjaxErrorHandler.settype('post');
 AjaxErrorHandler.setasync(true);
 AjaxErrorHandler.setdataType('json');
 AjaxErrorHandler.setdata({}); //data is set in setURL
 //alert(data);
 var success = function(data, textStatus, XMLHttpRequest){alert("This is the setSuccessFcn"+ textStatus);};
 var error = function(XMLHttpRequest, textStatus, errorThrown){alert("This is the errFcn"+ errorThrown);};
 AjaxErrorHandler.setsuccessCall(success);
 AjaxErrorHandler.seterrorCall(error);
 AjaxErrorHandler.ajaxJSONGet();
 }*/
/**
 * AjaxWrapper is a wrapper for the jQuery .AJAX system with fully customizable parameters
 * the goal is to allow quick rendering of custom functions which more directly handle all
 * of the functionality of .AJAX and make them available for quick reuse.
 * TODO add complete function wrapper
 * Origionally this was a Public Static but since this operates asyncronously
 * it must be instanced or object will collide
 */
var AjaxErrorHandler = function AjaxWrapper(){
	var url;
	var errorCall = function(){};;
	var successCall = function(){};;
	var completeCall = function(){};
	var async = true;
	var type = "POST";
	var timeout = 10000;
	var requestdata;
	var dataType = 'json';
	var xhr;
	var dataresult;
	var errorresult;
	var doOvers = 0;
	this.setdoOvers = function(num){
		doOvers = num;
	};
	this.setURL = function(datain){
		url = datain;
	};
	this.seterrorCall = function(fcn){
		errorCall = fcn;
	};
	this.setsuccessCall = function(fcn){
		successCall = fcn;
	};
	this.setcompleteCall = function(fcn){
		completeCall = fcn;
	};
	this.setasync = function(sync){
		async = sync;
	};
	this.settype = function(requesttype){
		type = requesttype;
	};
	this.settimeout = function(timeoutMS){
		timeout = timeouMS;
	};
	this.setdata = function(datain){
		requestdata = datain;
	};
	this.setdataType = function(type){
		dataType = type;
	};
	this.getxhr = function(){
		return xhr;
	};
	this.setdataresult = function(data){
		dataresult = data;
	};
	this.seterrorresult = function(error){
		if(error == "doOver"){
			alert(doOvers);
			this.doOver();
			doOvers++;
		}else{
			errorresult = error;
		}
	};
	this.setAJAXResult = function(str){
        returnResult = str;
    };
	this.getAJAXResult = function(){
        this.ajaxJSONGet();
        return returnResult;
	};
	/**
	 * doOver is a special case advanced handler which will retry the instances request 3 times in syncronous mode
	 * if the request continues to fail it will stop after 3 tries.
	 * If a success occurs the retry counter goes to zero
	 */
	this.doOver = function(){
		if(doOvers < 3){
			this.setasync(false);
			this.ajaxJSONGet();
			doOvers++;
		}else{
			Alert.CreateOneTimeUseDialog("I tried to get the data 3 times with no success","Fatal Error");
		}
	};
	/**
	 * ajaxJSONGet take is a generic builder for creating an ajax call with an error handler
	 * @param url the url that is used in the request
	 * @param data ther params sent in the request
	 * @param errorfcn a function which will fire if the request errors
	 * @param successfcn a function which will fire if the returned dataType matches expected
	 */
	this.ajaxJSONGet = function(){
		var that = this;
		$.ajax({
            type: type,
            global : false,
            timeout : timeout,
            url: url,
            data: requestdata,
            async:async,
            dataType: dataType,
            complete: function(){completeCall();},
            success: function(data, textStatus, XMLHttpRequest){successCall(data, textStatus, XMLHttpRequest); that.setdoOvers(0);},
            error: function(XMLHttpRequest, textStatus, errorThrown){that.seterrorresult(errorCall(XMLHttpRequest, textStatus, errorThrown));}
		});
	};
};
