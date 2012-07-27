/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
*	handleIncomingPush() gets called whenever a push notification comes in while the app is running
*	or in the background. If app is not running it will not fire, other signs of incoming push will
*	show, ie, Badge, Banner, Notifcation Center etc. 
*
*/

function handleIncomingPush(msg, url, mid, emergency, badge){
//	alert(msg + " " + url + " " + mid + " " + emergency + " " + badge );
	navigator.notification.alert(msg, alertCallback, 'IU Mobile', 'OK');
}

function alertCallback(){
	
	
}