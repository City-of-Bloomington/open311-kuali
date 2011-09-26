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

<kme:page title="POI Details" id="tours" backButton="true" homeButton="true" cssFilename="tours" usesGoogleMaps="true">
    <script src="${pageContext.request.contextPath}/js/foursquare.js" type="text/javascript"></script>
    <script type="text/javascript">
	    var imageType = 'I';
	    var audioType = 'A';
	    var videoType = 'V';
	    var iuBuildingType = 'I';
	    var venueType = 'V';
	    var customPointType = 'C';
	    var predefinedPointType = 'P';
	    
	    var contextPath = '${pageContext.request.contextPath}';
    
	    jQuery(window).load(function() {
	    	var obj = jQuery.parseJSON('${poi.media}');
	    	if (obj) {
	    		var images = [];
	    		var videos = [];
	    		var audio = [];
				for (var i=0; i<obj.length; i++){
					var media = obj[i];
					if (media.type == imageType){
						images.push(media);
					} else if (media.type == videoType) {
						videos.push(media);
					} else if (media.type == audioType) {
						audio.push(media);
					}
				}
				if (images.length > 0) {
					var imageDiv = $('<div></div>');
					for (var i=0; i<images.length; i++){
						var media = images[i];
						var a = $('<a></a>');
						a.attr('href', media.url);
						var img = $("<img />");
						img.attr('src', media.url);
						if ( media.title){
							img.attr('title',  media.title);
						}
						a.append(img);
						imageDiv.append(a);
					}
					$('#mediaContainer').append(imageDiv);
				}
				if (videos.length > 0) {
					//Do something here
				}
				if (audio.length > 0) {
					//Do something here
				}
	    	}
	    	if ('${poi.type}' == 'V') { //will change when pois can have venue AND building codes
	    		findFoursquareVenue('${poi.locationId}', setVenueTips);
	    	}
		});
	    
	    function setVenueTips(venue) {
	    	var a = $('<a></a>');
	    }
    </script>
    <kme:content>
        <kme:listView filter="false">
            <kme:listItem dataRole="list-divider" dataTheme="b" dataIcon="listview">
				${poi.name}
			</kme:listItem>
            <kme:listItem cssClass="tightPadding">
            	<p class="wrap">${poi.description}</p>
            	<c:if test="${not empty poi.url}">
            		<p><a href="${poi.url}">${poi.url}</a></p>
            	</c:if>
            	<div id="mediaContainer"></div>
            	<div id="fourSquareTips"></div>
            </kme:listItem>
        </kme:listView>
    </kme:content>
</kme:page>
