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

<kme:page title="Campus Maps" id="maps" backButton="true" homeButton="true" cssFilename="home" jsFilename="maps" usesGoogleMaps="true">
	<kme:content>
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
	$('div#map_canvas').height($('div#maps').height()-$('#mapsearchform').height()-70);
});
</script>

	</kme:content>
</kme:page>
