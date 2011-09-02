var map;
var mapService;
var searchResults = [];
var iw; //infowindow

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