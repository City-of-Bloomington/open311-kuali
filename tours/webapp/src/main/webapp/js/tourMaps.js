var arrows;
var markers = [];
var startMarker, stopMarker;
var preDefinedPois = [];

var poly;

var validationDistanceThresholdMeters = 100;

var editingPoiIndex;

$(function() {
	$('.poiCancel').hide();
});


function initializeMap(cntxtPath) {
	contextPath = cntxtPath;
	var latlng;
    var zoom = 15;
    latlng = new google.maps.LatLng(39.168486,-86.523455);
	
	//if(navigator.geolocation) {
	//	navigator.geolocation.getCurrentPosition(function(position) {
	//		initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
	//		map.setCenter(initialLocation);
	//		}, 
	//		function() {
				//handleNoGeolocation(browserSupportFlag);
	//	});
	//}
    
    var myOptions = {
		zoom: zoom,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.HYBRID,
		streetViewControl: true,
		zoomControl: true,
		scaleControl: false,
		mapTypeControl: false,
		disableDoubleClickZoom: false
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	var polyOptions = {
		strokeColor: '#0000FF',
		strokeOpacity: 0.5,
		strokeWeight: 4,
		clickable: false
	}
	poly = new google.maps.Polyline(polyOptions);
	poly.setMap(map);
	/*
	google.maps.event.addListener(poly, 'mouseover', function() {
		fineTuneRoute();
	});
	google.maps.event.addListener(poly, 'mouseout', function() {
	    stopFineTuning();
	});
	*/
	
	arrows = new ArrowHandler();

	google.maps.event.addListener(map, 'click', selectPoint);
	
	mapService = new gmaps.ags.MapService(arcGisServerUrl);
	var agsType = new  gmaps.ags.MapType(arcGisServerUrl,{name:'ArcGIS', opacity:1.0});
    map.overlayMapTypes.insertAt(0, agsType);
	google.maps.event.addListenerOnce(mapService, 'load', function() {
		$('#findButton').removeAttr('disabled');
	});
	
	
	google.maps.event.addListenerOnce(map, "tilesloaded", function() {
		initializeTour();
	}); 
	
	iw = new google.maps.InfoWindow();
}

function initializeTour() {
	var tourJson = $('#tourJson').val();
	if (tourJson.length){
		var obj = jQuery.parseJSON(tourJson);
		if (obj) {
			$('#tourId').val(obj.tourId);
			$('#tourName').val(obj.name);
			$('#tourDescription').val(obj.description);
			$('#tourVersion').val(obj.versionNumber);
			poly.setPath(google.maps.geometry.encoding.decodePath(obj.path));
			updateStartEndMarkers();
			updatePathDistance();
			
			for (var i=0; i<obj.pointsOfInterest.length; i++){
				var poi = obj.pointsOfInterest[i];
				place = new Object();
				place.name = poi.name;
				place.location = new google.maps.LatLng(poi.latitude,poi.longitude);
				place.iuBuildingCode = poi.locationId;
				place.venueId = poi.venueId;
				place.type = poi.type;
				place.media = poi.media;
				place.description = poi.description;
				place.url = poi.url;
				addPOI(place);
			}
			centerOverAllLocations();
			arrows.load(poly.getPath().getArray());
		}
	}
	var pois = $('#definedPoisJson').val();
	if (pois.length){
		var obj = jQuery.parseJSON(pois);
		if (obj) {
			for (var i=0; i<obj.length; i++){
				var poi = obj[i];
				place = new Object();
				place.name = poi.name;
				place.location = new google.maps.LatLng(poi.latitude,poi.longitude);
				place.iuBuildingCode = poi.locationId;
				place.venueId = poi.venueId;
				place.type = poi.type;
				place.media = poi.media;
				place.description = poi.description;
				place.url = poi.url;
				preDefinedPois[i] = place;
				$('#definedPOIs').append('<div onclick="selectPreDefinedPoi(' + i + ')" onmouseover="this.style.backgroundColor=\'#AAAAEE\'" onmouseout="this.style.backgroundColor=\'#FFFFFF\'">' + place.name + '</div>');
			}		
		}
	}
}

function centerOverAllLocations() {
	var bounds = new google.maps.LatLngBounds();
	if (markers) {
		for (i in markers) {
			bounds.extend(markers[i].getPosition());
		}
	}
	if (poly) {
		var array = poly.getPath().getArray();
		for (i in array) {
			bounds.extend(array[i]);
		}
	}
	map.fitBounds(bounds);
}
   
function addToRoute(){
	var place;
	if (editingPoiIndex) {
		var marker = markers[editingPoiIndex];
		if (tempMarker) {
			place = tempMarker.place;
		} else {
			place = marker.place;
		}
		marker.setPosition(place.location);
		marker.place = place;
		marker.title = place.name;
	} else {
		if (tempMarker) {
			place = tempMarker.place;
		}
	}
	if (place){
		var pointName = $('#poiName').val();
		if (!pointName.length){
			alert('Please name this location.');
			return;
		}
		var latitude = $('#latitude').val();
		var longitude = $('#longitude').val();
		if (!(latitude && longitude && !(isNaN(latitude) || isNaN(longitude)))){
			alert('Please ensure proper latitude and longitude coordinates are provided.');
			return;
		}
		place.name = pointName;
		place.location = new google.maps.LatLng(latitude,longitude);
		place.description = $('#description').val();
		place.url = $('#url').val();
		addSelectedMediaToPoi(place);
		clearSelectedMedia();
		if (!editingPoiIndex) {
			addPOI(place);
		}
		if (tempMarker){
			tempMarker.setMap(null);
			tempMarker = null;
		}
		stopEditingPoi();
	} else {
		alert('You must select a place to add/save.');
	}
}

function selectPoint(event) {
	if (!isFineTuning && isEditing){
		addWaypoint(event.latLng);
	}
	
	if (isSelectingPoint){
		updateSelectedPlace(event.latLng);
	}
	
	if (isSelectingVenue){
		if (tempMarker){
			tempMarker.setMap(null);
		}
		findFoursquareVenues(event.latLng);
	}
}

function selectPreDefinedPoi(index) {
	var place = preDefinedPois[index];
	setTempMarker(place);
	stopEditingMedia();
	clearSelectedMedia();
	for (var i=0; i< place.media.length; i++){
		var media = place.media[i];
		if (media.type == imageType){
			loadImageToList(media.url, media.title, media.caption);
		} else if (media.type == audioType){
			loadAudioToList(media.oggVorbisUrl, media.mp3Url, media.wavUrl, media.title, media.caption);
		} else if (media.type == videoType){
			loadVideoToList(media.oggUrl, media.mp4Url, media.webMUrl, media.youTubeUrl, media.title, media.caption);
		}
	}
	map.panTo(place.location);
}

function updateSelectedPlace(latLng) {
	geocodeLatLng(latLng, setAddress); 
}

function selectBuilding(number){
	if (number > 0) {
		var place = places[number-1]; // 'select:' is index 0, so number 1 = places[0]
		setTempMarker(place)
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
	tempMarker.place = place;
	$('#latitude').val(place.location.lat());
	$('#longitude').val(place.location.lng());
	$('#poiName').val(place.name);
	$('#url').val(place.url);
	$('#description').val(place.description);
}

function addBuilding(number){
	if (number > 0) {
		var place = places[number-1]; // 'select:' is index 0, so number 1 = places[0]
		addWaypoint(place.location, place);
	}
}

function addWaypoint(latLng) {
	var path = poly.getPath();
	var currentEnd = path.getAt(path.getLength()-1);
	// Because path is an MVCArray, we can simply append a new coordinate
	// and it will automatically appear
	path.push(latLng);
	
	arrows.create(currentEnd, latLng);
	
	updateStartEndMarkers();
	updatePathDistance();
}

function addPOI(place) {
	// Add a new marker at the new plotted point on the polyline.
	var markerMap = map;

	var marker = new google.maps.Marker({
		position: place.location,
		title: place.name,
		map: markerMap,
		draggable: false,
		icon: buildingMarkerImage
	});
 
	marker.place = place;
	markers.push(marker);
	
	google.maps.event.addListener(marker, 'rightclick', function() {
		removeBuilding(marker);
	});
	google.maps.event.addListener(marker, 'click', function() {
		if (!isEditing && !isFineTuning) {
			editPOI(marker);
		}
	});
}

function searchAddress() {
	var address = $('#address').val();
	if (address.length) {
		geocodeAddress(address, setAddress);
	} else {
		alert('Please enter a valid address.');
	}
}

function setAddress(place){
	setTempMarker(place);
	map.panTo(place.location);
}

function updatePathDistance() {
	var dist = google.maps.geometry.spherical.computeLength(poly.getPath().getArray());
	dist = dist * 0.000621371192; //convert meters to miles.
	$('#routeDistance').html(dist.toFixed(2) + ' Miles');
}

function startEditingRoute() {
	changeMode('edit');
	$('#editStatus').text('Editing Route');
}

function stopEditingRoute() {
	changeMode();
	$('#editStatus').text('Not Editing');
}

function fineTuneRoute(){
	if (!isFineTuning){
		changeMode('tune');
		$('#editStatus').text('Fine-Tuning');
		arrows.hideArrows();
		poly.runEdit(true);
		if (startMarker){
			startMarker.setMap(null);
		}
		if (stopMarker){
			stopMarker.setMap(null);
		}
	}
}

function stopFineTuning(){
	poly.stopEdit();
	updateStartEndMarkers();
	arrows.load(poly.getPath().getArray());
	changeMode();
	$('#editStatus').text('Not Editing');
	updatePathDistance();
}

function updateStartEndMarkers() {
	var path = poly.getPath();
	if (path.getLength() > 0){
		if (!startMarker){
			var markerImage = new google.maps.MarkerImage('/mdot/images/start.png');
			startMarker = new google.maps.Marker({
				position: path.getAt(0),
				title: 'Start',
				map: map,
				draggable: false,
				icon: markerImage
			});
		} else {
			startMarker.setPosition(path.getAt(0));
			startMarker.setMap(map);
		}
	}
	
	if (path.getLength() > 1){
		if (!stopMarker){
			var markerImage = new google.maps.MarkerImage('/mdot/images/end.png');
			stopMarker = new google.maps.Marker({
				position: path.getAt(path.getLength()-1),
				title: 'End',
				map: map,
				draggable: false,
				icon: markerImage
			});
		} else {
			stopMarker.setPosition(path.getAt(path.getLength()-1));
			stopMarker.setMap(map);
		}
	}
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

function saveTour(){
	if (validateRoute()){
		var places = [];
		for (var i=0; i<markers.length; i++){
			var place = markers[i].place;
			if (place.poly){
				delete place.poly;
			}
			places.push(place);
		}
		
		var tour = new Object();
		tour.id = $('#tourId').val();
		tour.name = $('#tourName').val();
		tour.description = $('#tourDescription').val();
		tour.path = google.maps.geometry.encoding.encodePath(poly.getPath());
		tour.version = $('#tourVersion').val();
		tour.POIs = places;
		
		$('#data').val(JSON.stringify(tour));
		$("#postForm").submit();
	}
}

function validateRoute(){
	if (!$('#tourName').val().length){
		alert('The tour must have a name.');
		return false;
	}
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
	for (var i = 0; i < markers.length; i++) {
		distance = getDistanceToPolyMtrs(poly, markers[i].getPosition())
		if (distance > validationDistanceThresholdMeters){
			if (buildings != ''){
				buildings += ', ';
			}
			buildings += markers[i].place.name;
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
	if (selected == 2){ //Select A Point is the second tab
		changeMode('select');	
	} else if (selected == 3){ //Select A Foursquare Venue is the third tab
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
	if (selected == 2){ //Save is the third tab
		var list = '';
		for (var i = 0; i < markers.length; i++) {
			list += '<div>' + (i+1) + '. ' + markers[i].place.name + '</div>'
		}
		$('#selectedPOIs').html(list);
	}
}

function editPOI(marker){
	$("#wizard").tabs('select', 0);
	stopEditingPoi();
	var place = marker.place;
	editingPoiIndex = markers.indexOf(marker);
	$('#editingPoi').text('Editing: ' + place.name);
	$('#editingPoi').show();
	$('.poiCancel').show();
	populatePoi(place);
}

function stopEditingPoi() {
	stopEditingMedia();
	$('#editingPoi').text('');
	$('#editingPoi').hide();
	$('.poiCancel').hide();
	clearPoiForms();
	editingPoiIndex = null;
}

function clearPoiForms() {
	$('#poiName').val('');
	$('#latitude').val('');
	$('#longitude').val('');
	$('#url').val('');
	$('#description').val('');
	clearMediaForms();
	clearSelectedMedia();
}

function populatePoi(place) {
	$('#poiName').val(place.name);
	$('#latitude').val(place.location.lat());
	$('#longitude').val(place.location.lng());
	$('#url').val(place.url);
	$('#description').val(place.description);
	populateMedia(place.media);
}

function populateMedia(mediaArray) {
	if (mediaArray) {
		for (var i=0; i< mediaArray.length; i++) {
			var media = mediaArray[i];
			if (media.type == imageType){
				loadImageToList(media.url, media.title, media.caption);
			} else if (media.type == audioType){
				loadAudioToList(media.oggVorbisUrl, media.mp3Url, media.wavUrl, media.title, media.caption);
			} else if (media.type == videoType){
				loadVideoToList(media.oggUrl, media.mp4Url, media.webMUrl, media.youTubeUrl, media.title, media.caption);
			}
		}
	}
}

function generateRouteKml(){
	generateKML(markers, poly.getPath());
}