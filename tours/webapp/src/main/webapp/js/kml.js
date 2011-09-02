var flySpeed = 40;//mph

function generateKML(markers, path){
	var kmlData = '<?xml version="1.0" encoding="UTF-8"?>\n<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2"><Document>';
	
	kmlData += '<Style id="Path"><LineStyle><color>FF0000FF</color><width>3</width></LineStyle></Style>';
	//Get building markers
	kmlData += '<Folder><name>Points of Interest</name>';
	for (var i = 0; i < markers.length; i++) {
		var marker = markers[i];
		if (marker.building) {
			kmlData += '<Placemark id="poi' + i + '">';
			kmlData += '<name>' + marker.building.name + '</name>';
    		kmlData += '<description>' + marker.building.description + '</description>';
    		kmlData += '<Point><coordinates>' + marker.building.location.lng() + ',' + marker.building.location.lat() + ',0</coordinates></Point>';
  			kmlData += '</Placemark>';
		}
	}
	kmlData += '</Folder>';
	
	kmlData += buildTour(path, markers);
	
	kmlData += '<Placemark>';
	kmlData += '<name>' + $('#tourName').val() + '</name>';
	kmlData += '<description>' + $('#tourDescription').val() + '</description>';
	kmlData += '<styleUrl>#Path</styleUrl>';
	kmlData += '<LineString><tessellate>1</tessellate><altitudeMode>clampToGround</altitudeMode><coordinates>\n';
	var array = path.getArray();
	for (var i = 0; i < array.length; i++) {
		var point = array[i];
    	kmlData += point.lng() + ',' + point.lat() + ',0\n';
	}
	kmlData += '</coordinates></LineString></Placemark>';
	
	kmlData += '</Document></kml>';
	
	newwindow=window.open();
	newdocument=newwindow.document;
	newdocument.write(kmlData);
	newdocument.close();
}

function buildTour(path, markers){
	var markerPathIndices = findPoiTourIndices(path, markers);
	var lastPoint;
	var flyTime = 3.0;
	var tourString = '<gx:Tour><name>' + $('#tourName').val() + '</name><gx:Playlist>';
	
	var array = path.getArray();
	for (var i = 0; i < array.length; i++) {
		var point = array[i];
		
		var heading = 0;
		if (lastPoint){
			heading = google.maps.geometry.spherical.computeHeading(lastPoint, point).toFixed(1);
			flyTime = computeFlyTime(lastPoint, point);
			tourString += buildFlyTo(point, heading, 'smooth', flyTime);
		} else {
			tourString += buildFlyTo(point, heading, 'bounce', flyTime);
		}
    	lastPoint = point;
    	
    	if (markerPathIndices[i]) {
    		var markerIndices = markerPathIndices[i];
    		for (var m = 0; m < markerIndices.length; m++) {
    			var markerIndex = markerIndices[m];
    			var marker = markers[markerIndex];
    			point = marker.getPosition();
    			heading = google.maps.geometry.spherical.computeHeading(lastPoint, point).toFixed(1);
    			flyTime = computeFlyTime(lastPoint, point);
        		tourString += buildFlyTo(point, heading, 'smooth', flyTime);
        		tourString += buildShowHideBalloon('poi' + markerIndex, 1);
        		tourString += buildPause();
        		tourString += buildShowHideBalloon('poi' + markerIndex, 0);
        		lastPoint = point;
    		}
    	}
	}
	
	tourString += '</gx:Playlist></gx:Tour>'
	return tourString;
}

function buildFlyTo(latLng, heading, mode, duration) {
	return '<gx:FlyTo><gx:duration>' + duration + '</gx:duration><gx:flyToMode>' + mode + '</gx:flyToMode>' + buildLookAt(latLng, heading) + '</gx:FlyTo>';
}

function buildLookAt(latLng, heading) {
	return '<LookAt><longitude>' + latLng.lng() + '</longitude><latitude>' + latLng.lat() + '</latitude><heading>' + heading + '</heading><range>100</range><tilt>45</tilt><altitudeMode>clampToGround</altitudeMode></LookAt>'
}

function buildShowHideBalloon(placemarkId, show) {
	return '<gx:AnimatedUpdate><Update><targetHref/><Change><Placemark targetId="' + placemarkId + '"><gx:balloonVisibility>' + show + '</gx:balloonVisibility></Placemark></Change></Update></gx:AnimatedUpdate>';
}

function buildPause() {
	return '<gx:TourControl><gx:playMode>pause</gx:playMode></gx:TourControl>';
}

function computeFlyTime(p1, p2){
	var distance = google.maps.geometry.spherical.computeDistanceBetween(p1, p2);
	return (distance * 2.2369363 / flySpeed).toFixed(1); //convert to meters/second
}

function findPoiTourIndices(path, markers) {
	var markerPathIndices = []; //markerPathIndices[indexOfPointInPath] = poiMarkerIndexArray;  //will be undefined if the point is not the closest to a POI
	var array = path.getArray();
	var shortestVal;
	var shortestIndex;
	var markerPos;
	var point;
	var distance;
	
	for (var m = 0; m < markers.length; m++) {
		markerPos = markers[m].getPosition();
		shortestVal = 999999;
		shortestIndex = 0;
		for (var i = 0; i < array.length; i++) {
			point = array[i];
			distance = google.maps.geometry.spherical.computeDistanceBetween(point, markerPos);
			if (distance < shortestVal) {
				shortestVal = distance;
				shortestIndex = i;
			}
		}
		if (!markerPathIndices[shortestIndex]) {
			markerPathIndices[shortestIndex] = new Array();
		}
		markerPathIndices[shortestIndex].push(m);
	}
	return markerPathIndices;
}