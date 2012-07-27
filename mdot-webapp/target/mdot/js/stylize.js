$(document).ready(function() {
	$('#saveButton').click(applyStyles);
	$('#clearButton').click(clearStyles);
	if($.cookie("css")) {
		var json = $.cookie("css");
		var obj = jQuery.parseJSON(json);
		
		$('input[name="pageBackgroundColor"]').val(obj.pageBackgroundColor);
		$('input[name="headerLogoUrl"]').val(obj.headerLogoUrl);
		$('input[name="headerTextColor"]').val(obj.headerTextColor);
		$('input[name="headerTextShadow"]').val(obj.headerTextShadow);
		$('input[name="headerGradientStartColor"]').val(obj.headerGradientStartColor);
		$('input[name="headerGradientStopColor"]').val(obj.headerGradientStopColor);
		
		$('input[name="buttonATextColor"]').val(obj.buttonATextColor);
		$('input[name="buttonAGradientStartColor"]').val(obj.buttonAGradientStartColor);
		$('input[name="buttonAGradientStopColor"]').val(obj.buttonAGradientStopColor);
		$('input[name="buttonAHoverGradientStartColor"]').val(obj.buttonAHoverGradientStartColor);
		$('input[name="buttonAHoverGradientStopColor"]').val(obj.buttonAHoverGradientStopColor);
		$('input[name="buttonADownGradientStartColor"]').val(obj.buttonADownGradientStartColor);
		$('input[name="buttonADownGradientStopColor"]').val(obj.buttonADownGradientStopColor);
		
		$('input[name="buttonCTextColor"]').val(obj.buttonCTextColor);
		$('input[name="buttonCGradientStartColor"]').val(obj.buttonCGradientStartColor);
		$('input[name="buttonCGradientStopColor"]').val(obj.buttonCGradientStopColor);
		$('input[name="buttonCHoverGradientStartColor"]').val(obj.buttonCHoverGradientStartColor);
		$('input[name="buttonCHoverGradientStopColor"]').val(obj.buttonCHoverGradientStopColor);
		$('input[name="buttonCDownGradientStartColor"]').val(obj.buttonCDownGradientStartColor);
		$('input[name="buttonCDownGradientStopColor"]').val(obj.buttonCDownGradientStopColor);
	}
});

function applyStyles() {
	var obj = new Object();
	
	obj.pageBackgroundColor = $('input[name="pageBackgroundColor"]').val();
	obj.headerLogoUrl = $('input[name="headerLogoUrl"]').val();
	obj.headerTextColor = $('input[name="headerTextColor"]').val();
	obj.headerTextShadow = $('input[name="headerTextShadow"]').val();
	obj.headerGradientStartColor = $('input[name="headerGradientStartColor"]').val();
	obj.headerGradientStopColor = $('input[name="headerGradientStopColor"]').val();
	
	obj.buttonATextColor = $('input[name="buttonATextColor"]').val();
	obj.buttonAGradientStartColor = $('input[name="buttonAGradientStartColor"]').val();
	obj.buttonAGradientStopColor = $('input[name="buttonAGradientStopColor"]').val();
	obj.buttonAHoverGradientStartColor = $('input[name="buttonAHoverGradientStartColor"]').val();
	obj.buttonAHoverGradientStopColor = $('input[name="buttonAHoverGradientStopColor"]').val();
	obj.buttonADownGradientStartColor = $('input[name="buttonADownGradientStartColor"]').val();
	obj.buttonADownGradientStopColor = $('input[name="buttonADownGradientStopColor"]').val();
	
	obj.buttonCTextColor = $('input[name="buttonCTextColor"]').val();
	obj.buttonCGradientStartColor = $('input[name="buttonCGradientStartColor"]').val();
	obj.buttonCGradientStopColor = $('input[name="buttonCGradientStopColor"]').val();
	obj.buttonCHoverGradientStartColor = $('input[name="buttonCHoverGradientStartColor"]').val();
	obj.buttonCHoverGradientStopColor = $('input[name="buttonCHoverGradientStopColor"]').val();
	obj.buttonCDownGradientStartColor = $('input[name="buttonCDownGradientStartColor"]').val();
	obj.buttonCDownGradientStopColor = $('input[name="buttonCDownGradientStopColor"]').val();

	if (!obj.headerGradientStopColor) {obj.headerGradientStopColor = obj.headerGradientStartColor;}
	if (!obj.buttonAGradientStopColor) {obj.buttonAGradientStopColor = obj.buttonAGradientStartColor;}
	if (!obj.buttonAHoverGradientStopColor) {obj.buttonAHoverGradientStopColor = obj.buttonAHoverGradientStartColor;}
	if (!obj.buttonADownGradientStopColor) {obj.buttonADownGradientStopColor = obj.buttonADownGradientStartColor;}
	if (!obj.buttonCGradientStopColor) {obj.buttonCGradientStopColor = obj.buttonCGradientStartColor;}
	if (!obj.buttonCHoverGradientStopColor) {obj.buttonCHoverGradientStopColor = obj.buttonCHoverGradientStartColor;}
	if (!obj.buttonCDownGradientStopColor) {obj.buttonCDownGradientStopColor = obj.buttonCDownGradientStartColor;}
	
	$.cookie("css", JSON.stringify(obj), {expires: 365, path: '/', raw: true});
}
	
function clearStyles() {
	$.cookie("css", '', {expires: 0, path: '/', raw: true});
	window.location.reload();
}