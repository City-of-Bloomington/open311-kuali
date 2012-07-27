//alert( localStorage.site );
gApp = new Array();

gApp.deviceready = false;
gApp.c2dmregid = '';

window.onbeforeunload  =  function(e) {

		if ( gApp.c2dmregid.length > 0 )
		{
			// The same routines are called for success/fail on the unregister. You can make them unique if you like
			window.plugins.C2DM.unregister( C2DM_Success, C2DM_Fail );			// close the C2DM 
		
		}
};



//$(document).bind("mobileinit", function() {
//alert( 'Starting...');

	$("#app-status-ul").append( '<li>Mobileinit event received' );

	document.addEventListener('deviceready', function() {
		// This is the PhoneGap deviceready event. Once this is called PhoneGap is available to be used
		$("#app-status-ul").append( '<li>deviceready event received' );

		$("#app-status-ul").append( '<li>calling C2DM.register, register our email with Google' );

		
		gApp.DeviceReady = true;

		// Some Unique stuff here,
		// The first Parm is your Google email address that you were authorized to use C2DM with
		// the Event Processing rountine (2nd parm) we pass in the String name
		// not a pointer to the routine, under the covers a JavaScript call is made so the name is used
		// to generate the function name to call. I didn't know how to call a JavaScript routine from Java
		// The last two parms are used by PhoneGap, they are the callback routines if the call is successful or fails
		//
		// CHANGE: your_c2dm_account@gmail.com
		// TO: what ever your C2DM authorized email account name is
		//
		window.plugins.C2DM.register("mtwagner@gmail.com", "C2DM_Event", C2DM_Success, C2DM_Fail );
		
	}, false );
	
	
//});

function post_to_url(path, params, method) {
    method = method || "post"; // Set method to post by default, if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);

        form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form.submit();
}

function 
C2DM_Event(e)
{
	var li = '';
	li = '<li>EVENT -> RECEIVED:' + e.event;
	$("#app-status-ul").append( li )
	
	switch( e.event )
	{
	case 'registered':
		// the definition of the e variable is json return defined in C2DMReceiver.java
		// In my case on registered I have EVENT and REGID defined
		gApp.c2dmregid = e.regid;
		if ( gApp.c2dmregid.length > 0 )
		{
			li = '<li>JAVASCRIPT -> REMOTE';
			$("#app-status-ul").append( li );			
		
			li = '<li>REGISTERED -> REGID:' + e.regid;
			$("#app-status-ul").append( li );

			li = '<li>DEVICE -> UUID:' + device.uuid;
			$("#app-status-ul").append(li);			

			postquery = "https://www.indiana.edu/~iumobile/push/registerAndroid.php?regID=" + e.regid + "&deviceName=" + device.name + "&deviceUUID=" + device.uuid;
			
			$.get(postquery, function(data){
				$("#app-status-ul").append("<LI>REGID -> Get to AppServer: " + data);			
			});
			
			
			// ==============================================================================
			// ==============================================================================
			// ==============================================================================
			//
			// This is where you would code to send the REGID to your server for this device
			//
			// ==============================================================================
			// ==============================================================================
			// ==============================================================================
			
		}
			
		break
		
	case 'message':
		// the definition of the e variable is json return defined in C2DMReceiver.java
		// In my case on registered I have EVENT, MSG and MSGCNT defined
			
		// You will NOT receive any messages unless you build a HOST server application to send 
		// Messages to you, This is just here to show you how it might work
			
        alert(e.msg);
			
			
		li = '<li>MESSAGE -> MSG:' + e.msg;
		$("#app-status-ul").append( li )
//		li = '<li>MESSAGE -> MSGCNT:' + e.msgcnt;
//		$("#app-status-ul").append( li )

		break;

		
	case 'error':
			
		li = '<li>ERROR -> MSG:' + e.msg;
		$("#app-status-ul").append( li )
		break;
		
		
		
	default:
		li = '<li>EVENT -> Unknown, an event was received and we do not know what it is';
		$("#app-status-ul").append( li )
		break;
	}
}

function 
C2DM_Success(e)
{
	li = '<li>C2DM_Success -> We have successfully registered and called the C2DM plugin, waiting for C2DM_Event:reistered -> REGID back from Google';
	$("#app-status-ul").append( li )
}

function 
C2DM_Fail(e)
{
	li = '<li>C2DM_Fail -> C2DM plugin failed to register';
	$("#app-status-ul").append( li )
	li = '<li>C2DM_Fail -> ' + e.msg;
	$("#app-status-ul").append( li )
}

