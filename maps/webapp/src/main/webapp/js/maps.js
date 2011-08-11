var contextPath;
var userLocationTimeout;
var hasLoaded = 0;

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

	// Create the DIV to hold the control and call the TrackerControl()
	// constructor passing in this DIV.
	var trackerControlDiv = document.createElement('DIV');
	var trackerControl = new TrackerControl(newmap, trackerControlDiv);
	trackerControlDiv.index = 1;
	newmap.controls[google.maps.ControlPosition.BOTTOM_RIGHT].push(trackerControlDiv);
	
	google.maps.event.trigger(newmap, 'resize');
    return newmap;
}

/**
 * The TrackerControl adds a control to the map that
 * returns the user to the control's defined home.
 */

// Define properties to hold the state
TrackerControl.prototype.active_ = null;
TrackerControl.prototype.controlDiv_ = null;

// Define setters and getters for this property
TrackerControl.prototype.getActive = function() {
  return this.active_;
}
TrackerControl.prototype.setActive = function(boolean) {
  this.active_ = boolean;
  var test = this.controlDiv_;
  if (boolean) {
	  test.firstChild.style.backgroundColor = 'black';  // Look into toggle class
	  //element.children(":first").toggleClass("redClass");
  } else {
	  test.firstChild.style.backgroundColor = 'white';
  }
  
  var test2 = 1;
}

function TrackerControl(map, div) {

  // Get the control DIV. We'll attach our control UI to this DIV.
  var controlDiv = div;

  // We set up a variable for the 'this' keyword since we're adding event
  // listeners later and 'this' will be out of scope.
  var control = this;

  // Set the property upon construction
  control.controlDiv_ = controlDiv;

  // Set CSS styles for the DIV containing the control. Setting padding to
  // 5 px will offset the control from the edge of the map
  controlDiv.style.padding = '5px';

  // Set CSS for the control border
  var trackerToggleUI = document.createElement('DIV');
  trackerToggleUI.style.backgroundColor = 'white';
  trackerToggleUI.style.borderStyle = 'solid';
  trackerToggleUI.style.borderWidth = '2px';
  trackerToggleUI.style.cursor = 'pointer';
  trackerToggleUI.style.textAlign = 'center';
  trackerToggleUI.title = 'Click to show where you are';
  controlDiv.appendChild(trackerToggleUI);

  // Set CSS for the control interior
  var trackerToggleText = document.createElement('DIV');
  trackerToggleText.style.fontFamily = 'Arial,sans-serif';
  trackerToggleText.style.fontSize = '12px';
  trackerToggleText.style.paddingLeft = '4px';
  trackerToggleText.style.paddingRight = '4px';
  trackerToggleText.innerHTML = 'My Location';
  trackerToggleUI.appendChild(trackerToggleText);

  // Setup the click event listener for the tracker toggle:
  google.maps.event.addDomListener(trackerToggleUI, 'click', function() {
	  if (control.getActive()) {
		  control.setActive(false);
		  stopTrackingUserLocation();
		  alert("Stop");
	  } else {
		  control.setActive(true);
		  startTrackingUserLocation(map, markersArray, userMarkersArray);
		  alert("Start");
	  }
  });
}

/*
 * Marker handling
 */

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
		}).error(function() { 
			alert("Could not retrieve data at this time."); 
		});
	} else {
		alert("No building code provided.");
	}
}

function retrieveBuildingsForGroup(groupCode) {
	// http://localhost:9999/mdot/maps/group/BL
	$('#searchBuilding').empty();
	$('#searchBuilding').append($("<option></option>").attr("value", "").text(""));
	$.getJSON('${pageContext.request.contextPath}/maps/group/' + groupCode, function(data) {
		$.each(data.mapsLocations, function(key, value) {
			$('#searchBuilding').append($("<option></option>").attr("value", value.buildingCode).text(value.name));
		});
		$('#searchBuilding').selectmenu('refresh', true);
	}).error(function() { 
		alert("Could not retrieve data at this time."); 
	});
}

function startTrackingUserLocation(map, markersArray, userMarkersArray) {
	if (userLocationTimeout) { 
		clearTimeout(userLocationTimeout);
	}
	drawUserLocation(map, markersArray, userMarkersArray);
	userLocationTimeout = setTimeout(function(){
		startTrackingUserLocation(map, markersArray, userMarkersArray);
	}, 5000);
}

function stopTrackingUserLocation() {
	clearTimeout(userLocationTimeout);
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


function getCampusBounds(campusCode) {
	var bounds = new google.maps.LatLngBounds();
	var latnw;
	var lngnw;
	var latse;
	var lngse;
	if ("BL" == campusCode) {
		latnw = 39.186364553452776;
		lngnw = -86.52849197387695;
		latse = 39.163741758811305;
		lngse = -86.49707794189453;
	} else if ("IN" == campusCode) {
		latnw = 39.779255060511474;
		lngnw = -86.18817329406738;
		latse = 39.76903016131954;
		lngse = -86.16877555847168;
	} else if ("CO" == campusCode) {
		latnw = 39.25628280051443;
		lngnw = -85.91246366500854;
		latse = 39.24834029722123;
		lngse = -85.89401006698608;
	} else if ("EA" == campusCode) {
		latnw = 39.86846872971864;
		lngnw = -84.89223718643188;
		latse = 39.865026590919314;
		lngse = -84.88304257392883;
	} else if ("KO" == campusCode) {
		latnw = 40.46334797900547;
		lngnw = -86.13686800003052;
		latse = 40.459446082380765;
		lngse = -86.12846732139587;
	} else if ("NW" == campusCode) {
		latnw = 41.55735157080083;
		lngnw = -87.34203815460205;
		latse = 41.55347378410055;
		lngse = -87.33360528945923;
	} else if ("SE" == campusCode) {
		latnw = 38.34752114090129;
		lngnw = -85.82106471061707;
		latse = 38.34407964355921;
		lngse =  -85.811687707901;
	} else if ("SB" == campusCode) {
		latnw = 41.665634758010086;
		lngnw = -86.22310638427734;
		latse = 41.65893400481601;
		lngse = -86.21585369110107;
	} else if ("UA" == campusCode) {
		latnw = 41.74069404739962;
		lngnw = -87.58524999999997;
		latse = 38.15762916783622;
		lngse = -84.69953642272947;
		
	} else {
		return null;
	}
	var locnw = new google.maps.LatLng(latnw, lngnw);
	var locse = new google.maps.LatLng(latse, lngse);
	bounds.extend(locnw);
	bounds.extend(locse);
	return bounds;
}

/* resize map to full height after page load */
$(window).load(function () {
	$('div#map_canvas').height($('div#mapslocation').height());
});

