$(document).ready(function() {
	if($.cookie("css")) {
		var json = $.cookie("css");
		var obj = jQuery.parseJSON(json);
		
		var style = 'div[data-role="page"], div.ui-body-b {\
			background-color:	' + obj.pageBackgroundColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.pageBackgroundColor + ', ' + obj.pageBackgroundColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.pageBackgroundColor + '), color-stop(1, ' + obj.pageBackgroundColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.pageBackgroundColor + '\', EndColorStr=\'' + obj.pageBackgroundColor + '\')";\
		}\
	\
		.ui-bar-a {\
			color:				' + obj.headerTextColor + ';\
			text-shadow: ' + obj.headerTextShadow + ';\
			background-color:	' + obj.headerGradientStartColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.headerGradientStartColor + ', ' + obj.headerGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.headerGradientStartColor + '), color-stop(1, ' + obj.headerGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.headerGradientStartColor + '\', EndColorStr=\'' + obj.headerGradientStopColor + '\')";\
		}\
	\
		.ui-btn-up-a {\
			background: 		' + obj.buttonAGradientStartColor + ';\
			color:				' + obj.buttonATextColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.buttonAGradientStartColor + ', ' + obj.buttonAGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.buttonAGradientStartColor + '), color-stop(1, ' + obj.buttonAGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.buttonAGradientStartColor + '\', EndColorStr=\'' + obj.buttonAGradientStopColor + '\')";\
		}\
	\
		.ui-btn-hover-a {\
			background: 		' + obj.buttonAHoverGradientStartColor + ';\
			color:				' + obj.buttonATextColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.buttonAHoverGradientStartColor + ', ' + obj.buttonAHoverGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.buttonAHoverGradientStartColor + '), color-stop(1, ' + obj.buttonAHoverGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.buttonAHoverGradientStartColor + '\', EndColorStr=\'' + obj.buttonAHoverGradientStopColor + '\')";\
		}\
	\
		.ui-btn-down-a {\
			background: 		' + obj.buttonADownGradientStartColor + ';\
			color:				' + obj.buttonATextColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.buttonADownGradientStartColor + ', ' + obj.buttonADownGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.buttonADownGradientStartColor + '), color-stop(1, ' + obj.buttonADownGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.buttonADownGradientStartColor + '\', EndColorStr=\'' + obj.buttonADownGradientStopColor + '\')";\
		}\
	\
		.ui-btn-up-c {\
			background: 		' + obj.buttonCGradientStartColor + ';\
			color:				' + obj.buttonCTextColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.buttonCGradientStartColor + ', ' + obj.buttonCGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.buttonCGradientStartColor + '), color-stop(1, ' + obj.buttonCGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.buttonCGradientStartColor + '\', EndColorStr=\'' + obj.buttonCGradientStopColor + '\')";\
		}\
	\
		.ui-btn-hover-c {\
			background: 		' + obj.buttonCHoverGradientStartColor + ';\
			color:				' + obj.buttonCTextColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.buttonCHoverGradientStartColor + ', ' + obj.buttonCHoverGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.buttonCHoverGradientStartColor + '), color-stop(1, ' + obj.buttonCHoverGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.buttonCHoverGradientStartColor + '\', EndColorStr=\'' + obj.buttonCHoverGradientStopColor + '\')";\
		}\
	\
		.ui-btn-down-c {\
			background: 		' + obj.buttonCDownGradientStartColor + ';\
			color:				' + obj.buttonCTextColor + ';\
			background-image: -moz-linear-gradient(top, ' + obj.buttonCDownGradientStartColor + ', ' + obj.buttonCDownGradientStopColor + ');\
			background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, ' + obj.buttonCDownGradientStartColor + '), color-stop(1, ' + obj.buttonCDownGradientStopColor + '));\
			-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr=\'' + obj.buttonCDownGradientStartColor + '\', EndColorStr=\'' + obj.buttonCDownGradientStopColor + '\')";\
		}';
		
		if (obj.headerLogoUrl){
		style += 	'div[data-role="header"] h1 {\
						background-image:url(\'' + obj.headerLogoUrl + '\');\
						background-repeat:no-repeat;\
					}';
		}
		
	    $("<style type='text/css'></style>").text(style).appendTo("head");
	}
});
