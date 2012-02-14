function handleIncomingPush(msg, url, mid, emergency, badge){
    navigator.notification.alert(msg, pushCallback, "IU Mobile", "OK")
//    alert(msg + " " + url + " " + mid + " " + emergency + " " + badge );
}

function pushCallback(){

}