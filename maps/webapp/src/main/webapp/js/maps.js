var contextPath;
function setContextPath(path) {
	contextPath = path;
}

function initialize(id, lat, lng) {
	var latlng = new google.maps.LatLng(lat, lng);
	var myOptions = {
		zoom: 17,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
    };
	var newmap = new google.maps.Map(document.getElementById(id), myOptions);
    //map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    google.maps.event.trigger(newmap, 'resize');
    return newmap;
}

function addMarker(map, array, location, icon) {
	if (icon) {
		var myOptions = {
			position: location,
			map: map,
			icon: icon 
		};
		
	} else {
		var myOptions = {
			position: location,
			map: map
		};
	}
	marker = new google.maps.Marker(myOptions);
	array.push(marker);
}

// Removes the overlays from the map, but keeps them in the array
function clearOverlays(array) {
	if (array) {
		for (i in array) {
			array[i].setMap(null);
		}
	}
}

// Shows any overlays currently in the array
function showOverlays(map, array) {
	if (array) {
		for (i in array) {
			array[i].setMap(map);
		}
	}
}

// Deletes all markers in the array by removing references to them
function deleteOverlays(array) {
	if (array) {
		for (i in array) {
			array[i].setMap(null);
		}
		array.length = 0;
	}
}

function showLocationByCoordinates(map, markersArray, latitude, longitude) {
	if (map) {
		google.maps.event.trigger(map, 'resize');
		var location = new google.maps.LatLng(latitude, longitude);
		map.setZoom(17);
		map.setCenter(location);
		addMarker(map, markersArray, location);
		google.maps.event.trigger(map, 'resize');
	}
}

function showBuildingByCode(map, buildingCode) {
	if (buildingCode) {
		var url = contextPath + '/maps/building/' + buildingCode;
		$.getJSON(url, function(data) {
			var items = [];
			var latitude = data.latitude;
			var longitude = data.longitude;
			showLocationByCoordinates(map, markersArray, latitude, longitude);
		});
	}
}

function drawUserLocation(map, markersArray, userMarkersArray) {
	if(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position){
			//initialize(position.coords.latitude,position.coords.longitude);
			var location = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
			deleteOverlays(userMarkersArray);
			addMarker(map, userMarkersArray, location, "http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png");
			if (hasLoaded == 0) {
				hasLoaded = 1;
				centerOverAllLocations(map, markersArray, userMarkersArray);
			}
		});
	}
}

function centerOverAllLocations(map, markersArray, userMarkersArray) {
	var bounds = new google.maps.LatLngBounds();
	if (markersArray) {
		for (i in markersArray) {
			bounds.extend(markersArray[i].getPosition());
		}
	}
	if (userMarkersArray) {
		for (i in userMarkersArray) {
			bounds.extend(userMarkersArray[i].getPosition());
		}
	}
	map.fitBounds(bounds);
}

/* resize map to full height after page load */
$(window).load(function () {
	$('div#map_canvas').height($('div#mapslocation').height());
});
