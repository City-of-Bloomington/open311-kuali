<%--
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="${tour.name}" id="tours" backButton="true" homeButton="true" cssFilename="tours" usesGoogleMaps="true" usesGoogleMapsGeometry="true">
    <script src="${pageContext.request.contextPath}/js/mapArrows.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/arcgislink.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/arcgis.js" type="text/javascript"></script>
    <script type="text/javascript">
	    var userLocationMarker;
	    var map;
	    var buildingMarkerImage = new google.maps.MarkerImage('http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=home|88BBFF');
	    var userMarkerImage = new google.maps.MarkerImage('http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png');
	    var arrows;
	    var markers = [];
	    var startMarker, stopMarker;
	    var poly;
	    var userLocationCircle;
    
    	jQuery(window).load(function() {
			initializeMap();
		});
	    
	    function initializeMap() {
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
	    		disableDoubleClickZoom: true
	        };
	        map = new google.maps.Map(document.getElementById("map"), myOptions);

	        if(navigator.geolocation) {
	    		navigator.geolocation.getCurrentPosition(function(initialPosition) {
	    			userLocationMarker = new google.maps.Marker({
						title: "Your Location",
						map: map,
						draggable: false,
						icon: userMarkerImage
					});
	    			var success = function(position) {
	    				setUsersPosition(position);	
	    			};
	    			var fail = function(error) {
	    				alert("Could not retrieve your position.");
	    			};
	    			updateLocation = navigator.geolocation.watchPosition(success, fail);			
	    		});
	    	}
	        
	    	var polyOptions = {
	    		strokeColor: '#0000FF',
	    		strokeOpacity: 0.5,
	    		strokeWeight: 4,
	    		clickable: false
	    	}
	    	poly = new google.maps.Polyline(polyOptions);
	    	poly.setMap(map);
	    	
	    	arrows = new ArrowHandler();
	    	
	    	mapService = new gmaps.ags.MapService(arcGisServerUrl);
	    	var agsType = new  gmaps.ags.MapType(arcGisServerUrl,{name:'ArcGIS', opacity:1.0});
	        map.overlayMapTypes.insertAt(0, agsType);
	    	
	    	
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
	    			poly.setPath(google.maps.geometry.encoding.decodePath(obj.path));
	    			updateStartEndMarkers();
	    			
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
	    				place.id = poi.poiId;
	    				addPOI(place);
	    			}
	    			centerOverAllLocations();
	    			arrows.load(poly.getPath().getArray());
	    		}
	    	}
	    }
	    
	    function updateStartEndMarkers() {
	    	var path = poly.getPath();
	    	if (path.getLength() > 0){
    			var markerImage = new google.maps.MarkerImage('${pageContext.request.contextPath}/images/start.png');
    			startMarker = new google.maps.Marker({
    				position: path.getAt(0),
    				title: 'Start',
    				map: map,
    				draggable: false,
    				icon: markerImage
    			});
	    	}
	    	
	    	if (path.getLength() > 1){
    			var markerImage = new google.maps.MarkerImage('${pageContext.request.contextPath}/images/end.png');
    			stopMarker = new google.maps.Marker({
    				position: path.getAt(path.getLength()-1),
    				title: 'End',
    				map: map,
    				draggable: false,
    				icon: markerImage
    			});
	    	}
	    }

	    function addPOI(place) {
	    	var marker = new google.maps.Marker({
	    		position: place.location,
	    		title: place.name,
	    		map: map,
	    		draggable: false,
	    		icon: buildingMarkerImage
	    	});
	     
	    	marker.place = place;
	    	markers.push(marker);
	    	
	    	google.maps.event.addListener(marker, 'click', function() {
	    		showIW(marker);
	    	});
	    }
	    
	    function showIW(marker) {
	    	var place = marker.place;
	    	var content = '<p>' + place.name + '</p><a href="${pageContext.request.contextPath}/tours/details/' + place.id + '">View Details</a>';
	    	iw.setContent(content);
	    	iw.open(map, marker);
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
	    
	    function setUsersPosition(position) {
	    	var userLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
	    	userLocationMarker.setPosition(userLocation);
	    	
	    	if (position.coords.accuracy) {
		    	if (!userLocationCircle) {
					userLocationCircle = new google.maps.Circle({
						map : map,
						radius : position.coords.accuracy,
						fillColor : '#00BFFF',
						strokeColor : '#FFFFFF',
						zIndex: 1
					});
					userLocationCircle.bindTo('center', userLocationMarker, 'position');
				} else {
					userLocationCircle.setCenter(userLocation);
				}
	    	} else {
	    		if (userLocationCircle) {
	    			userLocationCircle.setMap(null);
	    		}
	    	}
	    }
    </script>
    <style type="text/css">
    	.ui-content {
		    padding: 0;
		}
    </style>
    <kme:content>
    	<c:set var="json"><c:out value="${tourJson}" escapeXml="true" /></c:set>
	    <input type="hidden" name="tourJson" id="tourJson" value="${json}" />
        <div id="map" style="height:400px;width:100%;"></div>
    </kme:content>
</kme:page>
