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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Campus Maps" id="maps" backButton="true" homeButton="true" cssFilename="mapsHome" jsFilename="maps" usesGoogleMaps="true">
	<kme:content>
		<script src="${pageContext.request.contextPath}/js/arcgislink.js" type="text/javascript"></script>
		<form:form action="${pageContext.request.contextPath}/maps/building/search" commandName="mapsearchform" data-ajax="false">
			<fieldset>
			<!-- <label for="searchCampus">Campus:</label> -->
			<form:hidden path="searchCampus" cssClass="text ui-widget-content ui-corner-all" />
<%-- 			<form:select path="searchCampus" multiple="false">
				<option value="UA" label="">select:</option>			  
				<option value="BL" label="">IU Bloomington</option>
				<option value="CO" label="">IUPUC Columbus</option>
				<option value="EA" label="">IU East</option>
				<option value="FW" label="">IPFW Fort Wayne</option>
				<option value="IN" label="">IUPUI Indianapolis</option>
				<option value="KO" label="">IU Kokomo</option>
				<option value="NW" label="">IU Northwest</option>
				<option value="SB" label="">IU South Bend</option>
				<option value="SE" label="">IU Southeast</option>
			</form:select> --%>
			</fieldset>
			<fieldset>
            <label for="searchText" style="position:absolute; left:-9999px;">Search:</label>
            <form:input path="searchText" cssClass="text ui-widget-content ui-corner-all" />
			<!-- <input id="searchText" name="searchText" class="text ui-widget-content ui-corner-all" type="search" /> -->
			<form:errors path="searchText" />
			</fieldset>
		</form:form>
		<div id="searchresults" class="overlay">
		<jsp:include page="search.jsp" />
		</div>
		<div id="map_canvas" style="height:300px;" class="map"></div>

	    
<script type="text/javascript">
var map;
var markersArray = [];
var userMarkersArray = [];

$('#maps').live("pagebeforeshow", function() {
	configureArcGIS();
	setContextPath("${pageContext.request.contextPath}");
	deleteOverlays(markersArray);
	map = initialize("map_canvas", 39.788, -86.165);
	var campus = $("#searchCampus").val();
	// Bounds will be for the state of Indiana for anything other than the valid values.
	var bounds = getCampusBounds(campus);
	map.fitBounds(bounds);
	
	$('#searchText').keypress(function (event) {
		$('#searchresults').show();
		/* Prevent enter key from submitting the form */
		lastTypedKeyCode = event.keyCode;
		console.log(lastTypedKeyCode);
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
});

$('#maps').live("pageshow", function() {
	// If the browser keeps the search parameters, search on page load
	var searchText = $("#searchText").val();
	var groupCode = $("#searchCampus").val();
	console.log(searchText + " " + groupCode);
	mapSearch(searchText, groupCode);	
});

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
		if (inputString.length < 2 || groupCode == "UA") {
			// Remove previous results
			$('#searchresults').html('');
		} else {
			if (groupCode == "BL") {
				if (mapsRemoteCallCountAtStartOfRequest >= mapsCurrentDisplayNumber) {
					mapsCurrentDisplayNumber = mapsRemoteCallCount;
					// Show Results
					findBuildings(inputString, processFindResults);
				}
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
	}
	previousSearchKey = searchKey;
} // mapSearch

/*
 * ArcGIS
 */
 var iuBuildingType = 'I';
 var venueType = 'V';
 var customPointType = 'C';
 var predefinedPointType = 'P';

function processFindResults(rs) {
	var html = '';
	var x=0;
	for (var i = 0, c = rs.results.length; i < c; i++) {
		html += processResult(rs.results[i]);
	}
	var pagehtml = '<div id="resultdata"><ul id="mapsearchresults" data-role="listview" data-theme="c" data-inset="true" data-dividertheme="b" data-filter="false"></ul></div>'
	$('#searchresults').html(pagehtml);
	$("#mapsearchresults").html(html).page();
	//$('#mapsearchresults').listview("refresh");
	mapSearchPostProcessIUB();
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
		b.iuBuildingCode = a['IU BLDG NUMBER'];
		//b.street = a['ADDRESS'];
		b.type = iuBuildingType;
		//b.state = venue.location.state;
		//b.city = venue.location.city;
		//b.zip = venue.location.postalCode;
		b.poly = poly;
		//poly.setMap(map);
		searchResults.push(b);
		//poly.place = b;
/* 		google.maps.event.addListener(poly, 'click', function() {
			setTempMarker(b);
		}); */
		// return '<div onclick="selectSearchResult(' + (searchResults.length - 1) + ')" onmouseover="this.style.backgroundColor=\'#AAAAEE\'" onmouseout="this.style.backgroundColor=\'#FFFFFF\'">' + title + '</div>';
		return '<li data-theme="c"><a href="#" kmetype="quicksearchiub" kmeresult="' + (searchResults.length - 1) + '"><p class="wrap">' + title + '</p></a></li>';
	}
	return '';
}

function mapSearchPostProcessIUB() {
    $('a[kmetype="quicksearchiub"]').click(function(event) {
		event.preventDefault();
		$('#searchresults').hide();
		var index = $(this).attr("kmeresult");
		var place = searchResults[index];
		var name = place.name;
		var latitude = place.location.lat();
		var longitude = place.location.lng();
		//var latitude = $(this).attr("kmelatitude");
		//var longitude = $(this).attr("kmelongitude");
		//var name = $(this).attr("kmename");
    	$('#searchText').val(name);
		deleteOverlays(markersArray);
		
		showLocationByCoordinates(map, markersArray, latitude, longitude);		
		//alert("Test");
    });
}

/*
 * Old System
 */
function mapSearchPostProcess() {
    $('a[kmetype="quicksearch"]').click(function(event) {
		event.preventDefault();
		$('#searchresults').hide();
		var latitude = $(this).attr("kmelatitude");
		var longitude = $(this).attr("kmelongitude");
		var name = $(this).attr("kmename");
    	$('#searchText').val(name);
		deleteOverlays(markersArray);
		showLocationByCoordinates(map, markersArray, latitude, longitude);		
		//alert("Test");
    });
}

/* display "Search" in search field until focus */
$(window).load(function () {
	$('#searchText').val('Search');
});

$('#searchText').focus(function() {
	$('input#searchText').val('');
});


/* resize map to full height after page load */
$(window).load(function () {
	var newHeight = 500;
	if ($('div#maps').height() > 1000) {
		newHeight = $('div#maps').height()/2;
		newHeight = newHeight - 140;
	} else {
		newHeight = $('div#maps').height();
		newHeight = newHeight - 70;
	}
	$('div#map_canvas').height(newHeight);
});
</script>

	</kme:content>
</kme:page>
