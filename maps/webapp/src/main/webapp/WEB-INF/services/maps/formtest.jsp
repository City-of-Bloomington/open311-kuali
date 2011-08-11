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
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Maps" id="formtest" cssFilename="location" jsFilename="maps">
	<kme:content>

<style type="text/css">
<!--
div.overflow {
height: 200px;
width: 300px;
overflow: auto;
border: 1px solid #666;
padding: 8px;
}
-->
</style> 

<form:form action="${pageContext.request.contextPath}/maps/search" commandName="mapsearchform" data-ajax="false">
	<fieldset>
	<label for="searchCampus">Campus</label>
	<form:select path="searchCampus" multiple="false">
		<form:option value="" label="" data-placeholder="true" />
		<form:option value="BL" label="Bloomington" data-placeholder="true"/>
		<form:option value="IN" label="Indianapolis" data-placeholder="true"/>
	</form:select>
	<label for="searchText">Search</label>
	<form:input path="searchText" cssClass="text ui-widget-content ui-corner-all" />
	<form:errors path="searchText" />
	<div id="searchresults" class="overflow"></div>
	</fieldset>
	<fieldset>
	<label for="locationName">Location</label>
	<form:input path="locationName" cssClass="text ui-widget-content ui-corner-all" />
	</fieldset>
	<fieldset>
	<div id="venues" class="overflow"></div>
	</fieldset>
	<fieldset>
	<label for="searchLatitude">Latitude</label>
	<form:input path="searchLatitude" cssClass="text ui-widget-content ui-corner-all" />
	<label for="searchLongitude">Longitude</label>
	<form:input path="searchLongitude" cssClass="text ui-widget-content ui-corner-all" />
	<%--
	<label for="searchBuilding">Building Code</label> 
	<form:input path="searchBuilding" cssClass="text ui-widget-content ui-corner-all" />
	--%>
<%-- 	<label for="searchBuilding">Building Code</label>
	<form:select path="searchBuilding" multiple="false">
		<form:option value="" label="" data-placeholder="true"/>
	</form:select> --%>
	</fieldset>
</form:form>

<div id="map_canvas" style="height:300px;"></div>
	
<script type="text/javascript">
/* Maps */

var map;
var markersArray = [];
var userMarkersArray = [];
var edit = true;

$('#formtest').live("pagebeforeshow", function() {
	setContextPath("${pageContext.request.contextPath}");
	deleteOverlays(markersArray);
	map = initialize("map_canvas", 39.17, -86.5);
//		var buildingCode = getParameterByName("id");
//		showBuildingByCode(map, buildingCode);
//		drawUserLocation(map);
/* 		$("#searchCampus").change(function() {
			deleteOverlays(markersArray);
			var groupCode = $("#searchCampus").val();
			if (groupCode) {
				var buildingData = retrieveBuildingsForGroup(groupCode);
				var bounds = getCampusBounds(groupCode);
				if (bounds) {
					map.fitBounds(bounds);	
				}
			} else {
				alert("No group code");
			}
		}); */
	$("#searchBuilding").change(function() {
		deleteOverlays(markersArray);
		var buildingCode = $("#searchBuilding").val();
		//alert("Test");
		if (buildingCode) {
			retrieveBuildingByCode(buildingCode);	
		} else {
			alert("No building code");
		}
	});
    google.maps.event.addListener(map, 'click', function(event) {
    	//$('#searchCampus').val("");
    	//$('#searchCampus').selectmenu('refresh', true);
    	$('#searchBuilding').val("");
    	$('#searchBuilding').selectmenu('refresh', true);
    	$('#searchLatitude').val(event.latLng.lat());
    	$('#searchLongitude').val(event.latLng.lng());
    	deleteOverlays(markersArray);
    	addMarker(map, markersArray, event.latLng);
    	findFoursquareVenues(event.latLng);
    	//alert(event.latLng);
    });
    initializeMapSearch();
});

$('#formtest').live("pageshow", function() {
	deleteOverlays(markersArray);
	// If the browser keeps the search parameters, search on page load
	var lat = $('#searchLatitude').val();
	var lng = $('#searchLongitude').val();
	var buildingCode = $('#searchBuilding').val();
	if (buildingCode) {
		showBuildingByCode(buildingCode);
	} else if ((lat != "" && lng != "" && lat != 0 && lng != 0)) {
		showLocationByCoordinates(map, markersArray, lat, lng);
	} else {
		
	}
	initializeMapSearchDefault();
});

/* function retrieveBuildingByCode(buildingCode) {
	//clearOverlays(markersArray);
	$.getJSON('${pageContext.request.contextPath}/maps/building/' + buildingCode, function(data) {
		showLocationByCoordinates(map, markersArray, data.latitude, data.longitude);
	}).error(function() { 
		alert("Could not retrieve data at this time."); 
	});
} */





function setCampusMapCenter(map, campus) {
	var latlng = new google.maps.LatLng(39.794187,-86.178589);
	var zoom = 6;
	if (campus == "BL") {
		latlng = new google.maps.LatLng(39.168486,-86.523455);
		zoom = 15;
	} else if (campus == "CO") {
		latlng = new google.maps.LatLng(39.251214,-85.901606);
		zoom = 16;
	} else if (campus == "EA") {
		latlng = new google.maps.LatLng(39.868306,-84.880856);
		zoom = 17;
	} else if (campus == "FW") {
		latlng = new google.maps.LatLng(41.117480,-85.108680);
		zoom = 15;
	} else if (campus == "IN") {
		latlng = new google.maps.LatLng(39.773186,-86.175041);
		zoom = 15;
	} else if (campus == "KO") {
		latlng = new google.maps.LatLng(40.459160,-86.132769);
		zoom = 15;
	} else if (campus == "NW") {
		latlng = new google.maps.LatLng(41.556685,-87.338390);
		zoom = 17;
	} else if (campus == "SB") {
		latlng = new google.maps.LatLng(41.663855,-86.219276);
		zoom = 15;
	} else if (campus == "SE") {
		latlng = new google.maps.LatLng(38.344542,-85.819562);
		zoom = 15;
	} 
	map.setCenter(latlng);
	map.setZoom(zoom);
	//findFoursquareVenues(latlng);
}

/*
 * From maps home
 */

function initializeMapSearch() {
	$('#searchText').keypress(function (event) {
		// Prevent enter key from submitting the form
		lastTypedKeyCode = event.keyCode;
		//console.log(lastTypedKeyCode);
		if (lastTypedKeyCode == 13) {
			event.preventDefault();
			return false;
		} else {
			if (timeout) { 
				clearTimeout(timeout);
			}
			timeout = setTimeout(function(){
				mapSearch();
			}, 200);
		}
	});
	$("#searchCampus").change(function() {
		mapSearch();
	});
}

function initializeMapSearchDefault() {
	// If the browser keeps the search parameters, search on page load
	mapSearch();	
}

var timeout;
var previousSearchKey;
var mapsRemoteCallCount = 0;
var mapsCurrentDisplayNumber = 0;

function mapSearch() {
	var inputString = $("#searchText").val();
	var groupCode = $("#searchCampus").val();
	mapsRemoteCallCount++;
	var mapsRemoteCallCountAtStartOfRequest = mapsRemoteCallCount;	
	var searchKey = inputString + ":" + groupCode;

	if (searchKey != previousSearchKey) {
		if (inputString.length < 2 || groupCode == "UA" || groupCode == "") {
			// Hide the suggestion box.
			$('#searchresults').html('');
		} else {
			var requestUrlString = '${pageContext.request.contextPath}/maps/building/searchassist?criteria=' + encodeURI(inputString) + '&groupCode=' + encodeURI(groupCode);
			$.get(requestUrlString, function(data) {
				//console.log("" + requestUrlString + " " + mapsRemoteCallCount + " " + mapsCurrentDisplayNumber);
				if (mapsRemoteCallCountAtStartOfRequest >= mapsCurrentDisplayNumber) {
					mapsCurrentDisplayNumber = mapsRemoteCallCount;
					// Show results
					var pagehtml = '<div id="resultdata"></div>'
					$('#searchresults').html(pagehtml);
					$("#resultdata").html(data).page();
					mapSearchPostProcess();
				}
			});
		}
	}
	previousSearchKey = searchKey;
} // mapSearch

function mapSearchPostProcess() {
    $('a[kmetype="quicksearch"]').click(function(event) {
		event.preventDefault();
		var latitude = $(this).attr("kmelatitude");
		var longitude = $(this).attr("kmelongitude");
		var name = $(this).attr("kmename");
    	$('#searchLatitude').val(latitude);
    	$('#searchLongitude').val(longitude);
    	$('#locationName').val(name);
		deleteOverlays(markersArray);
		showLocationByCoordinates(map, markersArray, latitude, longitude);		
		//alert("Test");
    });
}

function findFoursquareVenues(latlng){
	if (edit){
		var url = "https://test.uisapp2.iu.edu/ccl-unt" + "/maps/markers/foursquare?lat="+ latlng.lat() + "&lng=" + latlng.lng();
		var jqxhr = $.getJSON(url, function(data) {
			alert("Test");
		});
		var jqxhr = $.getJSON(url, function(data) {
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
			// Show results
			var pagehtml = '<div id="venuesresultdata"></div>'
			$('#venues').html(pagehtml);
			$("#venuesresultdata").html(data).page();
		});
		jqxhr.error(function() {
			// Show results
			var pagehtml = '<div id="venuesresultdata"></div>'
			$('#venues').html(pagehtml);
			$("#venuesresultdata").html("There was an error contacting the Foursquare service.").page(); 
		})
	}
}

</script>
	</kme:content>
</kme:page>
