var map;
var mapService;
var searchResults = [];
var iw; //infowindow

var geocoder;

var selectedPlace;
var places;
var venues;

var isEditing = false;
var isFineTuning = false;
var isSelectingPoint = false;
var isSelectingVenue = false;

var contextPath;

var tempMarker;
var tempMarkerImage = new google.maps.MarkerImage('http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=home|FF7777');
var buildingMarkerImage = new google.maps.MarkerImage('http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=home|88BBFF');

var iuBuildingType = 'I';
var venueType = 'V';
var customPointType = 'C';
var predefinedPointType = 'P';

function geocodeAddress(address, callback) {
	if (!geocoder) {
		geocoder = new google.maps.Geocoder();
	}
	
	geocoder.geocode( { 'address': address}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var place = parseGeocodeResultToPlace(results[0]);
			callback(place);
		} else {
			alert("Address search was not successful for the following reason: " + status);
		}
	});
}

function geocodeLatLng(latlng, callback) {
	if (!geocoder) {
		geocoder = new google.maps.Geocoder();
	}
	
	geocoder.geocode({'latLng': latlng}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[0]) {
	        	var place = parseGeocodeResultToPlace(results[0]);
	        	place.location = latlng;
	        	callback(place);
			}
		} else {
			alert("Address search was not successful for the following reason: " + status);
		}
    });
}

function parseGeocodeResultToPlace(result){
	var place = new Object();
	place.location = result.geometry.location;
	place.name = result.formatted_address;
	place.id = null;
	place.url = null;
	place.description = null;
	place.media = [];
	place.type = customPointType;
	return place;
}


String.prototype.escapeHTML = function () {                                       
    return(                                                                 
        this.replace(/&/g,'&amp;').                                         
            replace(/>/g,'&gt;').                                           
            replace(/</g,'&lt;').                                           
            replace(/"/g,'&quot;')                                         
    );                                                                     
};