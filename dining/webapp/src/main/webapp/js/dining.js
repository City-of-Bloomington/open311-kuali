
// expanded form
var expanded = 0;

var menuHeight = 0;

$(document).ready(function() {
    $('li.contentItem').hide();
    //alert('Hid the stuff');
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
                $('span.ui-icon').removeClass('ui-icon-arrow-u');
                expanded = 1;
            } else {
                $('ul#results').transition({
                    y: '0px',
                    easing: 'in-out',
                    duration: '300ms'
                });
                expanded = 0;
            }
            return false;
        }
    );
});

/*function loadContents() {
    alert ("passedItem: " + passedItem);
    return false;
}*/

function loadContents(passedItem, itemName){
	//alert('Passed this:' + passedItem);
    $('.dropdownHeader a').text(itemName);
    $('li.contentItem').hide();
    $('li.category-' + passedItem).show();
    //alert(passedItem);
}


$(function() {
    $('li.dropdownItem').click(
        function () {
        	//alert('clicked dropdown!');
            viewportHeight = $(window).height();
            var str = $(this).text();
            //alert('this text is:' + str);
            //selectedItem = str.split(' ').join('');
            selectedItem = $(this).attr("value");
            //alert('select' + selectedItem);
            $('.dropdownHeader a').text('Loading.....');
            $('ul#results').transition({ y: viewportHeight, easing: 'in-out', duration: '500ms'}, function() { loadContents(selectedItem, str); });
            $('ul#results').transition({ y: '0px', easing: 'in-out', duration: '500ms'});
            expanded = 0;
            return false;
        }
    );
});
