function onBodyLoad(){		
    document.addEventListener("deviceready", onDeviceReady, false);
    //alert("onBodyLoad");
}


/* When this function is called, PhoneGap has been initialized and is ready to roll */
/* If you are supporting your own protocol, the var invokeString will contain any arguments to the app launch.
see http://iphonedevelopertips.com/cocoa/launching-your-own-application-via-a-custom-url-scheme.html
for more details -jm */
function onDeviceReady(){

    otherDeviceReadyFunctions();
    setupEnvironment();
}

function setupEnvironment() {
    checkConnection();
}    	

function checkConnection() {
    var networkState = navigator.network.connection.type;		        
    if (networkState == 'unknown' || networkState == 'none') {
        navigator.notification.alert('Network connection unavailable.  Internet connectivity is required for Kuali Mobile.  Please connect to WiFi or 3G and try again.', setupEnvironment, 'Kuali Mobile', 'Try Again');
    } else {
        document.addEventListener("offline", looksLikeWeAreOffline, false);
    }
}

function looksLikeWeAreOffline() {
    navigator.notification.alert('Network connection unavailable.  Internet connectivity is required for IU Mobile.', offlineAction, 'IU Mobile', 'OK');
}	

function offlineAction() {
    // Do Nothing
}	