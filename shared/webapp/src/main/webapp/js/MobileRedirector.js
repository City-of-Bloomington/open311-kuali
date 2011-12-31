/*

Name: 
	MobileDetective.js

Description: 
	Detects if the user is accessing from a mobile borwser,
	If so, we offer to let them install the native app for 
	their specific browser. 
	If not, we save a cookie and don't bother the user for 
	365 days. 

Details:
	Straight forward function designed to run on when the Document/
	DOM is completely loaded and ready. 

Known Issues:
	None Currently. 

Version:
	1.0.0

Copyright (c) 2011, Indiana University
Copyright (c) 2011, IU Mobile
Author 2011, Mitchell T. Wagner

*/

$(document).ready(function(){

	//$.cookie('decline_mobile_install', "no", {expires: 0});
	//$.cookie('decline_mobile_install', null);

    //Check Cookie & Cookie Expiration Time
    var cookie 			= $.cookie('decline_mobile_install');
	var days 			= 365;

	//Native App URLs
	var AndroidURL 		= "https://market.android.com/details?id=iu.android";
	var iPhoneURL 		= "itms://itunes.apple.com/us/app/iu-mobile/id383456985?mt=8";
	var BlackberryURL	= "http://www.indiana.edu/~iumobile/blackberry/IUMobile.jad";


	console.log(cookie);

    //If there is no cookie or the cookie is not declining the native install
    if(cookie == null || cookie != "yes"){

    	// Is the browser on an iOS device?
    	if (/iPhone|iPad|iPod/i.test(navigator.userAgent)){
			console.log("Detected iOS device.");
    		// Ask the user if the want to install the native app.
    		if(confirm("Would you like to install the iOS App?")){

    			// Go to the App in the App Store
				console.log("Redirecting to iTunes app store.");
    			window.location = iPhoneURL;
    		}else{
    			// They don't want to install the native app. Remember that decision, for 365 days.
    			$.cookie('decline_mobile_install', "yes", {expires: days});
    		}

    	// Is  the browser on a Blackberry Device?
    	}else if (/Blackberry|RIM\sTablet/i.test(navigator.userAgent)){
			console.log("Detected Blackberry device.");

    		// Ask the user if the want to install the native app.					
    		if(confirm("Would you like to install the Blackberry App?")){
				console.log("Redirecting to Blackberry Download.");    			
    			window.location = BlackberryURL;
    		}else{
    			// They don't want to install the native app. Remember that decision, for 365 days.
    			$.cookie('decline_mobile_install', "yes", {expires: days});
    		}

    	// Is the broser on an Android Device?
    	}else if (/Android/i.test(navigator.userAgent)){
			console.log("Detected Android device.");

    		// Ask the user if the want to install the native app.
    		if(confirm("Would you like to install the Android App?")){
				console.log("Redirecting to Android Download.");
    			window.location = AndroidURL;
    		}else{
    			// They don't want to install the native app. Remember that decision, for 365 days.
    			$.cookie('decline_mobile_install', "yes", {expires:days});
    		}
    	}else{
			console.log("Looks like you're not on a Mobile Device");
		}
    }else{	
		console.log("Finished MobileRedirector");
    }

});