
// expanded form
var expanded = 0;

var menuHeight = 0;

$(document).ready(function() {
    $('li.contentItem').hide();
    $('ul#dropdownHeader span.ui-icon').hide();
    
    var thisCount = 0;
    $('li.contentItem').each(function(index) {
    	thisCount = 0;
    	$($(this).children('div.nutritionIcons')).each(function(index) {
    		$($(this).children('span.nutrition')).each(function(index) {
    			thisCount = thisCount + 1;
    		});
        });
    	$(this).css('padding-right',(thisCount*40)+"px");
	});
});

if (!$.support.transition)
    $.fn.transition = $.fn.animate;

$(function() {
    $('a#toggleDropdown').click(
        function () {
            if (expanded == 0) {
                $('ul#results').transition({
                    y: $('ul#dropdownMenu').height() + 'px',
                    easing: 'in-out',
                    duration: '300ms'
                });
                $('li.dropdownHeader span.ui-icon').removeClass('ui-icon-arrow-d');
                $('li.dropdownHeader span.ui-icon').addClass('ui-icon-arrow-u');
                expanded = 1;
            } else {
                $('ul#results').transition({
                    y: '0px',
                    easing: 'in-out',
                    duration: '300ms'
                });
                $('li.dropdownHeader span.ui-icon').removeClass('ui-icon-arrow-u');
                $('li.dropdownHeader span.ui-icon').addClass('ui-icon-arrow-d');
                expanded = 0;
            }
            return false;
        }
    );
});

function loadContents(passedItem, itemName){
    $('.dropdownHeader a').text(itemName);
    $('li.contentItem').hide();
    $('li.category-' + passedItem).show();
}

$(function() {
    $('li.dropdownItem').click(
        function () {
        	$('ul#dropdownHeader span.ui-icon').show();
            viewportHeight = $(window).height();
            var str = $(this).text();
            selectedItem = $(this).attr("value");
            $('.dropdownHeader a').text('Loading.....');
            $('ul#results').transition({ y: viewportHeight, easing: 'in-out', duration: '500ms'}, function() { loadContents(selectedItem, str); });
            // flip dropdown menu header arrow
            $('li.dropdownHeader span.ui-icon').removeClass('ui-icon-arrow-u');
            $('li.dropdownHeader span.ui-icon').addClass('ui-icon-arrow-d');
            $('li.dropdownHeader span.ui-icon').prop('data-icon','arrow-d');
            $('ul#results').transition({ y: '0px', easing: 'in-out', duration: '500ms'});
            expanded = 0;
            return false;
        }
    );
});
