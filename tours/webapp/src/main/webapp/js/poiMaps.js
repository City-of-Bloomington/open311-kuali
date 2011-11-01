function initializeMap(cntxtPath) {
	contextPath = cntxtPath;
	var latlng;
    var zoom = 15;
    latlng = new google.maps.LatLng(39.168486,-86.523455);
    
    var myOptions = {
		zoom: zoom,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		streetViewControl: true,
		zoomControl: true,
		scaleControl: false,
		mapTypeControl: false,
		disableDoubleClickZoom: false
    };
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    
	google.maps.event.addListener(map, 'click', selectPoint);
	
	mapService = new gmaps.ags.MapService(arcGisServerUrl);
	var agsType = new  gmaps.ags.MapType(arcGisServerUrl,{name:'ArcGIS', opacity:1.0});
    map.overlayMapTypes.insertAt(0, agsType);
	google.maps.event.addListenerOnce(mapService, 'load', function() {
		$('#findButton').removeAttr('disabled');
	});
	
	iw = new google.maps.InfoWindow();
	
	initializePoi();
}

function initializePoi() {
	var poiJson = $('#poiJson').val();
	if (poiJson.length){
		var obj = jQuery.parseJSON(poiJson);
		if (obj) {
			$('#poiId').val(obj.poiId);
			$('#poiName').val(obj.name);
			$('#description').val(obj.description);
			$('#poiVersion').val(obj.versionNumber);
			$('#latitude').val(obj.latitude);
			$('#longitude').val(obj.longitude);
			$('#url').val(obj.url);
			
			for (var i=0; i< obj.media.length; i++){
				var media = obj.media[i];
				if (media.type == imageType){
					loadImageToList(media.url, media.title, media.caption);
				} else if (media.type == audioType){
					loadAudioToList(media.oggVorbisUrl, media.mp3Url, media.wavUrl, media.title, media.caption);
				} else if (media.type == videoType){
					loadVideoToList(media.oggUrl, media.mp4Url, media.webMUrl, media.youTubeUrl, media.title, media.caption);
				}
			}
			
			place = new Object();
			place.name = obj.name;
			place.location = new google.maps.LatLng(obj.latitude,obj.longitude);
			place.id = obj.locationId;
			place.type = obj.type;
			place.media = obj.media;
			place.description = obj.description;
			place.url = obj.url;
			
			google.maps.event.addListenerOnce(map, "tilesloaded", function() {
				setTempMarker(place);
				map.panTo(place.location);
			});
		}
	}
}

function savePoi(){
	if (validatePOI()){	
		var poi = new Object();
		
		poi.id = $('#poiId').val();
		poi.locationId = tempMarker.place.id;
		poi.type = tempMarker.place.type;
		poi.name = $('#poiName').val();
		poi.description = $('#description').val();
		poi.version = $('#poiVersion').val();
		place.location = new google.maps.LatLng($('#latitude').val(), $('#longitude').val());
		poi.url = $('#url').val();	
		addSelectedMediaToPoi(poi);

		$('#data').val(JSON.stringify(poi));
		$("#postForm").submit();
	}
}

function validatePOI(place) {
	if (!tempMarker || !tempMarker.place) {
		alert('You must choose a location.');
		return false;
	}
	if (tempMarker.place.name && tempMarker.place.location) return true;
	else return false;
}

function selectPoint(event) {
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
	selectedPlace.type = customPointType;
	setTempMarker(selectedPlace);
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
		google.maps.event.addListener(tempMarker, 'click', function() {
			showIW();
		});
	} else {
		tempMarker.setPosition(place.location);
	}
	tempMarker.setTitle(place.name);
	tempMarker.place = place;
	$('#latitude').val(place.location.lat());
	$('#longitude').val(place.location.lng());
	$('#poiName').val(place.name);
	$('#url').val(place.url);
}

function showIW() {
	var content = '';
	if ($('#poiName').val()){
		content += '<h4>' + $('#poiName').val() + '</h4>';
	} else {
		
	}
	if ($('#description').val()){
		content += $('#description').val();
	}
	if ($('#url').val()){
		content += '<p><a href="' + $('#url').val() + '" target="_blank">' + $('#url').val() + '</a><p>';
	}
	if (content) {
		iw.setContent(content);
		iw.open(map, tempMarker);
	}
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
}

function changeMode(mode){
	if (mode === 'select'){
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
		for (var i = 0; i < markers.length; i++) {
			list += '<div>' + (i+1) + '. ' + markers[i].building.name + '</div>'
		}
		$('#selectedPOIs').html(list);
	}
}