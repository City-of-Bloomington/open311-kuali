function generateRouteKML(markers, path){
	var kmlData = '<?xml version="1.0" encoding="UTF-8"?>\n<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2"><Document>';
	
	kmlData += '<Style id="Path"><LineStyle><color>FF0000FF</color><width>3</width></LineStyle></Style>';
	//Get building markers
	kmlData += '<Folder><name>Points of Interest</name>';
	for (var i = 0; i < markers.length; i++) {
		var marker = markers[i];
		if (marker.building) {
			kmlData += '<Placemark>';
			kmlData += '<name>' + marker.building.name + '</name>';
    		kmlData += '<description>' + marker.building.name + '</description>';
    		kmlData += '<Point><coordinates>' + marker.building.location.lng() + ',' + marker.building.location.lat() + ',0</coordinates></Point>';
  			kmlData += '</Placemark>';
		}
	}
	kmlData += '</Folder>';
	
	kmlData += buildTour(path);
	
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

function buildTour(path){
	var tourString = '<gx:Tour><name>' + $('#tourName').val() + '</name><gx:Playlist>';
	
	var array = path.getArray();
	for (var i = 0; i < array.length; i++) {
		var point = array[i];
		
		var heading = 0;
		if (i > 0){
			point1 = array[i-1];
			heading = google.maps.geometry.spherical.computeHeading(point1, point).toFixed(1);
		}
		
    	tourString += buildFlyTo(point, heading, 'smooth', 3.0);
	}
	
	tourString += '</gx:Playlist></gx:Tour>'
	return tourString;
}

function buildFlyTo(latLng, heading, mode, duration){
	
	return '<gx:FlyTo><gx:duration>' + duration + '</gx:duration><gx:flyToMode>' + mode + '</gx:flyToMode>' + buildLookAt(latLng, heading) + '</gx:FlyTo>';
}

function buildLookAt(latLng, heading){
	return '<LookAt><longitude>' + latLng.lng() + '</longitude><latitude>' + latLng.lat() + '</latitude><heading>' + heading + '</heading><range>100</range><tilt>45</tilt><altitudeMode>clampToGround</altitudeMode></LookAt>'
}