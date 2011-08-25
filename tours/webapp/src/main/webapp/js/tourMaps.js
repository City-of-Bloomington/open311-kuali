var map;
var dynaMap;
var mapService;
var markers = [];
var searchResults = [];
var startMarker, stopMarker;
var selectedPlace;
var places;
var venues;
var poly;
var isEditing = false;
var isFineTuning = false;
var isSelectingPoint = false;
var isSelectingVenue = false;
var showMarkers = true;
var buildingMarkerImage = new google.maps.MarkerImage('http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=home|88BBFF');

var tempMarker;
var tempMarkerImage = new google.maps.MarkerImage('http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=home|FF7777');

var arcGisServerUrl = 'http://maps.iu.edu/ArcGIS/rest/services/Bloomington/MapServer';
var validationDistanceThresholdMeters = 100;
var foursquareId = 'L323STPYRKMYU2RZAQ1RHF4FCGDYWEHZZB4XHDLM5B51JGVQ';
var foursquareSecret = 'CZK5HSH4ZZBXMZ5LCZHM0SO320IBXW4REBDNVHM20D1S3FZY';

function initializeMap() {
    var latlng;
    var zoom = 15;
    latlng = new google.maps.LatLng(39.168486,-86.523455);
	
	if(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
			map.setCenter(initialLocation);
			}, 
			function() {
				//handleNoGeolocation(browserSupportFlag);
		});
	}
    
    var myOptions = {
		zoom: zoom,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		streetViewControl: true,
		zoomControl: true,
		scaleControl: false,
		mapTypeControl: true,
		disableDoubleClickZoom: true
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	//var agsType = new  gmaps.ags.MapType(arcGisServerUrl,{name:'ArcGIS', opacity:0.5});
    //map.overlayMapTypes.insertAt(0, agsType);
	
	var polyOptions = {
		strokeColor: '#000000',
		strokeOpacity: 0.5,
		strokeWeight: 3
	}
	poly = new google.maps.Polyline(polyOptions);
	poly.setMap(map);

	google.maps.event.addListener(map, 'click', addPoint);
	
	//var selectbox = document.getElementById("mapselect");
	//selectbox.options.length = 0;
	//addOption(selectbox,"select:",0);
	//places = new Array();
	//downloadMarkerData(null);
	
	dynamap = new gmaps.ags.MapOverlay(arcGisServerUrl);
	mapService = dynamap.getMapService();
	google.maps.event.addListenerOnce(mapService, 'load', function() {
		dynamap.setMap(map);
		$('#findButton').removeAttr('disabled');
	}); 
}
   
function addToRoute(){
	if (tempMarker){
		if (isSelectingPoint){
			var pointName = $('#pointName').val();
			if (!pointName.length){
				alert('Please name this location.');
				return;
			}
			tempMarker.building.name = pointName;
		}
		addPOI(tempMarker.building);
		tempMarker.setMap(null);
		tempMarker = null;
	}
}

function addPoint(event) {
	if (!isFineTuning && isEditing){
		addWaypoint(event.latLng);
	}
	
	if (isSelectingPoint){
		updateSelectedPlace(event.latLng);
	}
	
	if (isSelectingVenue){
		selectedPlace = null;
		if (tempMarker){
			tempMarker.setMap(null);
		}
		findFoursquareVenues(event.latLng);
	}
}

function updateSelectedPlace(latLng){
	selectedPlace = new Object();
	selectedPlace.location = latLng;
	$('#selectedLocation').text(selectedPlace.location.lat() + ', ' + selectedPlace.location.lng());
	setTempMarker(selectedPlace);
}

function selectAVenue(index){
	var venue = venues[index];
	setTempMarker(venue);
	map.panTo(venue.location);
	selectedPlace = venue;
}

function selectBuilding(number){
	if (number > 0) {
		var place = places[number-1]; // 'select:' is index 0, so number 1 = places[0]
		setTempMarker(place)
		selectedPlace = place;
		map.panTo(place.location);
	}
}

function setTempMarker(place){
	if (!tempMarker){
		tempMarker = new google.maps.Marker({
			position: place.location,
			map: map,
			draggable: false,
			icon: tempMarkerImage
		});
	} else {
		tempMarker.setPosition(place.location);
		tempMarker.setMap(map);
	}
	tempMarker.setTitle(place.name);
	tempMarker.building = place;
}

function addBuilding(number){
	if (number > 0) {
		var place = places[number-1]; // 'select:' is index 0, so number 1 = places[0]
		addWaypoint(place.location, place);
	}
}

function addWaypoint(latLng) {
	var path = poly.getPath();
	// Because path is an MVCArray, we can simply append a new coordinate
	// and it will automatically appear
	path.push(latLng);
	
	if (path.getLength() == 1){
		if (!startMarker){
			var markerImage = new google.maps.MarkerImage('http://www.google.com/intl/en_us/mapfiles/ms/micons/green-dot.png');
			startMarker = new google.maps.Marker({
				position: latLng,
				title: '#' + path.getLength(),
				map: map,
				draggable: false,
				icon: markerImage
			});
		} else {
			var point = path.getAt(0);
			startMarker.setPosition(point);
			startMarker.setMap(map);
		}
	}
	
	if (path.getLength() > 1){
		if (!stopMarker){
			var markerImage = new google.maps.MarkerImage('http://www.google.com/intl/en_us/mapfiles/ms/micons/red-dot.png');
			stopMarker = new google.maps.Marker({
				position: latLng,
				title: '#' + path.getLength(),
				map: map,
				draggable: false,
				icon: markerImage
			});
		} else {
			var point = path.getAt(path.getLength()-1);
			stopMarker.setPosition(point);
			stopMarker.setMap(map);
		}
	}
}

function addPOI(place) {
	// Add a new marker at the new plotted point on the polyline.
	var markerMap = null;
	if (showMarkers){
		markerMap = map;
	}

	var marker = new google.maps.Marker({
		position: place.location,
		title: place.name,
		map: markerMap,
		draggable: false,
		icon: buildingMarkerImage
	});
 
	marker.building = place;
	markers.push(marker);
	
	google.maps.event.addListener(marker, 'rightclick', function() {
		removeBuilding(marker);
	});
}

function startEditingRoute(){
	changeMode('edit');
	$('#editStatus').text('Editing Route');
}

function stopEditingRoute(){
	changeMode();
	$('#editStatus').text('Not Editing');
}

function fineTuneRoute(){
	changeMode('tune');
	$('#editStatus').text('Fine-Tuning');
	poly.runEdit(true);
	if (startMarker){
		startMarker.setMap(null);
	}
	if (stopMarker){
		stopMarker.setMap(null);
	}
}

function stopFineTuning(){
	poly.stopEdit();
	if (startMarker){
		var path = poly.getPath();
		if (path.getLength() > 0){
			var point = path.getAt(0);
			startMarker.setPosition(point);
			startMarker.setMap(map);
		}
	}
	if (stopMarker){
		var path = poly.getPath();
		if (path.getLength() > 1){
			var point = path.getAt(path.getLength()-1);
			stopMarker.setPosition(point);
			stopMarker.setMap(map);
		}
	}
	changeMode();
	$('#editStatus').text('Not Editing');
}

function removeBuilding(marker){
	var index = markers.indexOf(marker);
	if (index != null){
		markers.splice(index,1); 
		//poly.getPath().removeAt(index);
	}
	marker.setMap(null);
	marker = null;
	
	updateSelectedPOIs();
}

function showMarkers2() {
	if (markers) {
		for (i in markers) {
			markers[i].setMap(map);
		}
	}
	showMarkers = true;
}

function hideMarkers() {
	if (markers) {
		for (i in markers) {
			markers[i].setMap(null);
		}
	}
	showMarkers = false;
}


function generateRouteKML(){
	var kmlData = '<?xml version="1.0" encoding="UTF-8"?>\n<kml xmlns="http://www.opengis.net/kml/2.2"><Document>';
	
	kmlData += '<Style id="Path"><LineStyle><color>FF0000FF</color><width>3</width></LineStyle></Style>';
	//Get building markers
	for each (var marker in markers){
		if (marker.building){
			kmlData += '<Placemark>';
			kmlData += '<name>' + marker.building.name + '</name>';
    		kmlData += '<description>' + marker.building.name + '</description>';
    		kmlData += '<Point><coordinates>' + marker.building.location.lng() + ',' + marker.building.location.lat() + ',0</coordinates></Point>';
  			kmlData += '</Placemark>';
		}
	}
	
	//Get route path
	var path = poly.getPath();
	kmlData += '<Placemark>';
	kmlData += '<name>' + $('#routeName').val() + '</name>';
	kmlData += '<description>' + $('#routeDesc').val() + '</description>';
	kmlData += '<styleUrl>#Path</styleUrl>';
	kmlData += '<LineString><tessellate>1</tessellate><altitudeMode>clampToGround</altitudeMode><coordinates>\n';
	for each (var point in path.getArray()){
    		kmlData += point.lng() + ',' + point.lat() + ',0\n';
	}
	kmlData += '</coordinates></LineString></Placemark>';
	
	kmlData += '</Document></kml>';
	
	alert(kmlData);
	return kmlData;
}

function saveTour(){
	if (validateRoute()){
		//save here	
	}
}

function validateRoute(){
	if (!markers || markers.length == 0){
		alert('You have not added any points of interest to the tour.');
		return false;
	}
	if (poly.getPath().getLength() == 0){
		alert('You have not created a route for the tour.');
		return false;
	} else if (poly.getPath().getLength() < 2){
		alert('A tour route should have at least two waypoints.');
		return false;
	}
	var distance = 0;
	var buildings = '';
	for each (var marker in markers){
		distance = getDistanceToPolyMtrs(poly, marker.getPosition())
		if (distance > validationDistanceThresholdMeters){
			if (buildings != ''){
				buildings += ', ';
			}
			buildings += marker.building.name;
		}
	}
	if (buildings.length){
		if (confirm("The route does not pass near these points of interest:\n" + buildings + "\nDo you want to save anyway?")) { 
			return true;
		}
		return false;
	}
	return true;
}

function changeWizardTabs(event, ui){
	clearSearchResults();
	if (isEditing){
		stopEditingRoute();
	}
	if (isFineTuning){
		stopFineTuning();
	}
	
	updateSelectedPOIs();	
}

function changeSelectorTabs(event, ui){
	clearSearchResults();
	var $tabs = $('#selector').tabs();
	var selected = $tabs.tabs('option', 'selected');
	if (selected == 1){ //Select A Point is the second tab
		changeMode('select');	
	} else if (selected == 2){ //Select A Foursquare Venue is the third tab
		changeMode('venue');	
	} else {
		changeMode();
	}
	selectedPlace = null;
	if (tempMarker){
		tempMarker.setMap(null);
	}
}

function changeMode(mode){
	if (mode === 'edit'){
		isEditing = true;
		isFineTuning = false;
		isSelectingPoint = false;
		isSelectingVenue = false;
	} else if (mode === 'tune'){
		isEditing = false;
		isFineTuning = true;
		isSelectingPoint = false;
		isSelectingVenue = false;
	} else if (mode === 'select'){
		isEditing = false;
		isFineTuning = false;
		isSelectingPoint = true;
		isSelectingVenue = false;
	} else if (mode === 'venue') {
		isEditing = false;
		isFineTuning = false;
		isSelectingPoint = false;
		isSelectingVenue = true;
	} else {
		isEditing = false;
		isFineTuning = false;
		isSelectingPoint = false;
		isSelectingVenue = false;
	}
}

function updateSelectedPOIs(){
	var $tabs = $('#wizard').tabs();
	var selected = $tabs.tabs('option', 'selected');
	if (selected == 3){ //Save is the fourth tab
		var list = '';
		var i = 1;
		for each (var marker in markers){
			list += '<div>' + i + '. ' + marker.building.name + '</div>'
			i++;
		}
		$('#selectedPOIs').html(list);
	}
}

function searchBuildings(){
	var query = $('#buildingName').val();	
	findBuildings(query);
}

function findBuildings(query) {
      $('#busy').show();
	  clearSearchResults();
	  $('#results').html('');
      var params = {
        returnGeometry: true,
        searchText: query,
        contains: true,
        layerIds: [32, 36], // building names, frat names
        searchFields: ["MAP_NAME"],
        sr: 2966
      };
      mapService.find(params, processFindResults);
}
    
    
function processFindResults(rs) {
	var html = '';
	var x=0;
	for (var i = 0, c = rs.results.length; i < c; i++) {
		html += processResult(rs.results[i]);
	}
	$('#searchResults').html(html);
	$('#busy').hide();
}

function processResult(result){
	var feat = result.feature;
	var a = feat.attributes;
	var title = result.value;
	if (feat.geometry){
		var poly = feat.geometry[0];
		var b = new Object();
		b.name = title;
		b.location = new google.maps.LatLng(a['LATITUDE'],a['LONGITUDE']);
		b.id = a['IU BLDG NUMBER'];
		b.street = a['ADDRESS'];
		//b.state = venue.location.state;
		//b.city = venue.location.city;
		//b.zip = venue.location.postalCode;
		b.poly = poly;
		poly.setMap(map);
		searchResults.push(b);
		//poly.place = b;
		google.maps.event.addListener(poly, 'click', function() {
			setTempMarker(b);
		});
		return '<div onclick="selectSearchResult(' + (searchResults.length - 1) + ')" onmouseover="this.style.backgroundColor=\'#AAAAEE\'" onmouseout="this.style.backgroundColor=\'#FFFFFF\'">' + title + '</div>';
	}
	return '';
}

function selectSearchResult(index){
	var place = searchResults[index];
	setTempMarker(place);
	map.panTo(place.location);
}

function clearSearchResults() {
	if (searchResults) {
		for (var i = 0; i < searchResults.length; i++) {
			searchResults[i].poly.setMap(null);
		}
		searchResults.length = 0;
	}
	$('#searchResults').html('');
}

function getPolyCenter(poly) {
	var paths, path, latlng;
	var lat = 0;
	var lng = 0;
	var c = 0;
	paths = poly.getPaths();
	for (var j = 0, jc = paths.getLength(); j < jc; j++) {
		path = paths.getAt(j);
		for (var k = 0, kc = path.getLength(); k < kc; k++) {
			latlng = path.getAt(k);
			lat += latlng.lat();
			lng += latlng.lng();
			c++;
		}
	}
	if (c > 0) {
		return new google.maps.LatLng(lat / c, lng / c);
	}
	return null;
}

function findFoursquareVenues(latlng){
	var url = "https://test.uisapp2.iu.edu/ccl-unt/maps/markers/foursquare?lat="+ latlng.lat() + "&lng=" + latlng.lng();
	url = "https://api.foursquare.com/v2/venues/search?v=20110627&ll="+ latlng.lat() + "," + latlng.lng() +"&limit=8&client_id=" + foursquareId + "&client_secret=" + foursquareSecret
	var jqxhr = $.ajax({
	  url: url,
	  dataType: 'json',
	  success: function (data) {parseVenues(data);},
	  crossDomain: true
	});
	
	//var jqxhr = jQuery.getJSON(url, parseVenues);
	jqxhr.error(function() { $("#venues").html("There was an error contacting the Foursquare service."); })
}

function parseVenues(data){
	var venuesHtml = "";
	venues = new Array();
	for (var i=0; i<data.response.venues.length; i++){
		var venue = data.response.venues[i];
		venuesHtml = venuesHtml + "<div onClick='javascript:selectAVenue(" + i + ")' class='venue " + (i%2==0? "even" : "odd") + (i+1==data.response.venues.length? " last" : "") + "'>";
		venuesHtml = venuesHtml + "<div class='image'>";
		if (venue.categories.length > 0){
			var category = null;
			for (var c=0; c<venue.categories.length; c++){
				var cat = venue.categories[c];
				if (cat.primary){
					category = cat;
					break;
				}
			}
			venuesHtml = venuesHtml + "<img src='" + category.icon + "'></img>";
		}
		venuesHtml = venuesHtml + "</div>"; //close image
		
		venuesHtml = venuesHtml + "<div class='info'>";
		venuesHtml = venuesHtml + "<div class='name'>" + venue.name + "</div>";
		
		venuesHtml = venuesHtml + "<div class='address'>";
		if (venue.location.address){
			venuesHtml = venuesHtml + "<span>" + venue.location.address + "</span><br/>";
		}
		if (venue.location.city){
			venuesHtml = venuesHtml + "<span>" + venue.location.city + "</span>";
		}
		if (venue.location.state && venue.location.city){
			venuesHtml = venuesHtml + ",&nbsp";
		}
		if (venue.location.state){
			venuesHtml = venuesHtml + "<span>" + venue.location.state + "</span>";
		}
		if ((venue.location.city || venue.location.state) && venue.location.postalCode){
			venuesHtml = venuesHtml + "&nbsp";
		}
		if (venue.location.postalCode){
			venuesHtml = venuesHtml + "<span>" + venue.location.postalCode + "</span>";
		}
		venuesHtml = venuesHtml + "</div>"; //close address
		
		venuesHtml = venuesHtml + "</div>"; //close info
		
		venuesHtml = venuesHtml + "</div>"; //close venue
		venues[i] = parseVenue(venue);
	}
	$("#venues").html(venuesHtml);
}

function parseVenue(venue){
	var v = new Object();
	v.name = venue.name;
	v.location = new google.maps.LatLng(venue.location.lat,venue.location.lng);
	v.id = venue.id;
	v.street = venue.location.address;
	v.state = venue.location.state;
	v.city = venue.location.city;
	v.zip = venue.location.postalCode;

	return v;
}