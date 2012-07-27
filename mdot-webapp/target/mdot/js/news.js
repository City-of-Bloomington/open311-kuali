$(function() {
	$("li.extra").hide();
	$("li.expander").show();
});

// added the exp and col arguments which are i18n words meaning "expand" and "collapse" respectively. 
// kludgey sure, working with what I got.
function toggleVisibility(feedClass, exp, col) {
	var expander = $('li.expander.' + feedClass);
	var classes = 'li.extra';
	if (feedClass){
		classes += '.' + feedClass;
	}
	var elements = $(classes);
			
	if (expander.hasClass('collapsed')){
		elements.stop(true, true).slideDown();
		expander.removeClass('collapsed');
		var icon = $('li.expander.' + feedClass + ' span.ui-icon');
		icon.removeClass('ui-icon-arrow-d');
		icon.addClass('ui-icon-arrow-u');
		$('li.expander.' + feedClass + ' p.ui-li-desc strong').html(col);
	} else {
		elements.stop(true, true).slideUp();
		expander.addClass('collapsed');
		var icon = $('li.expander.' + feedClass + ' span.ui-icon');
		icon.removeClass('ui-icon-arrow-u');
		icon.addClass('ui-icon-arrow-d');
		$('li.expander.' + feedClass + ' p.ui-li-desc strong').html(exp);
	}

}