
function refreshTemplate(ajaxUrl, htmlElement, templateName) {
	$.mobile.showPageLoadingMsg();
	$(htmlElement).text('');
	var dynamicDataResp = $.ajax({
		url: ajaxUrl,
		dataType: 'json',
		async: false,
		cache: false           
	});
	if (dynamicDataResp.status == 200) {
		var dynamicDataObj = jQuery.parseJSON(dynamicDataResp.responseText);
		$.tmpl(templateName, dynamicDataObj).appendTo(htmlElement);
		$(htmlElement).listview('refresh');
		$.mobile.hidePageLoadingMsg();
	} else {
		$.mobile.hidePageLoadingMsg();
		alert("An error has occurred. Make sure you have network connectivity.");
	}
}
