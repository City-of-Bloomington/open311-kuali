
function refreshTemplate(ajaxUrl, htmlElement, templateName, emptyContentMessage, successCallback, failCallback) {
	$.mobile.showPageLoadingMsg();
	$(htmlElement).text('');
	var dynamicDataResp = $.ajax({
		url: ajaxUrl,
		dataType: 'json',
		async: false,
		cache: false
	});
	dynamicDataResp.done(function(){
		var dynamicDataObj = jQuery.parseJSON(dynamicDataResp.responseText);
		$.tmpl(templateName, dynamicDataObj).appendTo(htmlElement);
		if (!$(htmlElement).children().length) {
			$(htmlElement).html(emptyContentMessage);
		}
		if (successCallback) {
			successCallback();
		}
	});
	dynamicDataResp.fail(function(){
		$.mobile.hidePageLoadingMsg();
		if (failCallback) {
			failCallback();
		}
		alert("A network error has occurred. An Internet connection is required for IU Mobile.");
	});
	dynamicDataResp.always(function(){
		$.mobile.hidePageLoadingMsg();
	});
}

function setPageTitle(title) {
	$('div[data-role=page] div[data-role=header] h1').text(title);
	//$('title').text(title); // not compatible with IE7, done with normal JS below
	document.title = title;
	return "";
}
