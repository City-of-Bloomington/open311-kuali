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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<kme:page title="Open311 Service" id="service" backButton="true" homeButton="true">
	
	<style type="text/css">
      #map_canvas { height: 200px }
	  
	</style>
	
	<script type="text/javascript"
      src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCbNj-QcmmxWeqIyJOO9yv-_49LhY7BRuw&sensor=true">
    </script>
	<script type="text/javascript">
    var pictureSource;   // picture source
    var destinationType; // sets the format of returned value 

    // Wait for PhoneGap to connect with the device
    //
	
	window.onload = function() { giveTimeout(); }
	
    document.addEventListener("deviceready",onDeviceReady,false);

	function giveTimeout() {
		window.setTimeout(function() {
		var e = document.createEvent('Events'); 
		e.initEvent("deviceready"); 
		document.dispatchEvent(e);
			}, 50);
		}
	
	
	function onError(error) {
        alert('code: '    + error.code    + '\n' +
              'message: ' + error.message + '\n');
    }
	
    // PhoneGap is ready to be used!
    function onDeviceReady() {
		// Setup variable names shorter than the stock phonegap ones.  
		navigator.geolocation.getCurrentPosition(win, fail);
      //  pictureSource	= navigator.camera.PictureSourceType;
      //  destinationType	= navigator.camera.DestinationType;
    }

    // Called when a photo is successfully retrieved
    function onPhotoURISuccess(imageURI) {
		console.log(imageURI);
		uploadPhoto(imageURI);
		var largeImage = document.getElementById('largeImage');
		console.log("largeImage:" + largeImage);
		largeImage.src = imageURI;
		console.log("post" + largeImage.src);
    }

    function uploadPhoto(imageURI) {
    	console.log("Attemping to Upload File: " + imageURI);
    	var options = new FileUploadOptions();
        options.fileKey="file";											// This is the form element name, had this been submitted by an
        																// 		actual form. 
        options.fileName=imageURI.substr(imageURI.lastIndexOf('/')+1);	// FileName
        options.mimeType="image/jpeg";									// mimeType

        var params = new Object();
        params.value1 = "test";
        params.value2 = "param";

        options.params = params;

		var server	= "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}";
        var save 	= server + "/files/save"; 
        var get 	= server + "/files/get/";
        
        var ft = new FileTransfer();
        ft.upload(	imageURI, 
        			save, 
        			function(r){
        	            var response = jQuery.parseJSON(r.response);
        				var largeImage = document.getElementById('largeImage');
        				var fileId = document.getElementById('fileId');
        				largeImage.src = get + response.fileid;
						largeImage.style.display = 'block';
        			}, 
        			function(r){
        	            alert("An error has occurred: Code = " + error.code);
        	            console.log("upload error source " + error.source);
        	            console.log("upload error target " + error.target);
        			}, 
        			options);
    }

    // A button will call this function
    function getPhoto(source) {
		navigator.camera.getPicture(onPhotoURISuccess, onFail, {quality: 50, 
																destinationType: destinationType.FILE_URI,
																sourceType: source, 
																targetWidth: 420,
																targetHeight: 420});

    }
    
    // Called if something bad happens or fail silently...
    function onFail(message) {
		alert('Failed because: ' + message);
    }
	
		var geocoder;
    function win(position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;

        var myOptions = {
            center: new google.maps.LatLng(lat, lng),
            zoom: 5,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(lat, lng),
			map: map,
			draggable: true,
			title: 'Click to zoom'
		  });

		  geocoder = new google.maps.Geocoder();
          
		  updateLatitude(marker.getPosition());
     	  updateLongitude(marker.getPosition());
     	  geocodePosition(marker.getPosition());

		  // Add dragging event listeners.
		  google.maps.event.addListener(marker, 'dragstart', function() {
		  });
		  
		  google.maps.event.addListener(marker, 'drag', function() {
			updateLatitude(marker.getPosition());
			updateLongitude(marker.getPosition());
		  });
		  
		  google.maps.event.addListener(marker, 'dragend', function() {
			updateLatitude(marker.getPosition());
			updateLongitude(marker.getPosition());
			geocodePosition(marker.getPosition());
		  });
		  
		  google.maps.event.addListener(map, 'center_changed', function() {
			// 3 seconds after the center of the map has changed, pan back to the
			// marker.
			
		  });

		  google.maps.event.addListener(marker, 'click', function() {
			map.setZoom(8);
			map.setCenter(marker.getPosition());
		  });

    }

	
	function geocodePosition(pos) {
	  geocoder.geocode({
		'latLng': pos
	  }, function(responses, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
			  
		if (responses && responses.length > 0) {
		  updateMarkerAddress(responses[0].formatted_address);
		} else {
		  updateMarkerAddress('Cannot determine address at this location.');
		}
	  }
	  else {
		alert("Geocode was not successful for the following reason: " + status);
	  }});
	}

	

	function updateLatitude(latLng) {
	  document.getElementById('lat').value = latLng.lat();
		
	}
	
	function updateLongitude(latLng) {
	  document.getElementById('lng').value = latLng.lng();
		
	}

	function updateMarkerAddress(str) {
	  document.getElementById('address').value = str;
	}


    function fail(e) {
        $.mobile.hidePageLoadingMsg();
        alert('Can\'t retrieve position.\nError: ' + e);
    }

   </script>

   
   
	<kme:content>
        <form:form action="${pageContext.request.contextPath}/open311/servicePost" commandName="service" data-ajax="false" method="post"> 	
		<kme:listView>
		
		<kme:listItem>
			First Name : <form:input path="fname" id="fname" />
			Last Name : <form:input path="lname" id="lname" />
			Email : <form:input path="email" id="email" />
			Phone : <form:input path="phone" id="phone" />
			Latitude : <form:textarea path="latitude" id="lat" class="required" /><br/><br/>
			Longitude : <form:textarea path="longitude" id="lng" class="required" /><br/><br/>
			Address : <form:textarea path="addressString" id="address" cols="10" rows="8" />
			
			<div id="map_canvas"></div>

		</kme:listItem>
		
		<form:hidden path="responseServiceCode" />
		
		<c:forEach items="${serviceAttributes}" var="attribute" varStatus="index">
            <kme:listItem>
			
				<form:hidden path="attributes[${index.index}].key" />

				<c:if test="${attribute.datatype == 'string'}">
					<c:out value="${attribute.description}"/>   <form:input path="attributes[${index.index}].value" class="${attribute.required}"/>
				</c:if>

				<c:if test="${attribute.datatype == 'number'}">
					<c:out value="${attribute.description}"/>	<form:input path="attributes[${index.index}].value" class="${attribute.required}"/>
				</c:if>
				
				<c:if test="${attribute.datatype == 'datetime'}">
				    <c:out value="${attribute.description}"/>	<form:input path="attributes[${index.index}].value" type="date" data-role="datebox" data-options='{"mode": "calbox"}' class="${attribute.required}"/>
				</c:if>
				
				<c:if test="${attribute.datatype == 'text'}">
					<c:out value="${attribute.description}"/>	<form:textarea path="attributes[${index.index}].value" class="${attribute.required}"/>
				</c:if>
				
				<c:if test="${attribute.datatype == 'singlevaluelist'}">
					<c:out value="${attribute.description}"/>   <form:select path="attributes[${index.index}].value" items="${attribute.valueMap}" class="${attribute.required}" />
				</c:if>
	
				<c:if test="${attribute.datatype == 'multivaluelist'}">
					<c:out value="${attribute.description}"/>
						<fieldset data-role="controlgroup" data-theme="c">
							<c:forEach items="${attribute.valueMap}" var="attributevalue" varStatus="valueind">
								<form:checkbox data-theme="c" path="attributes[${index.index}].value" value="${attributevalue.key}" style="left:0; width:25px; height:25px;" label="${attributevalue.value}" />
				            </c:forEach>
						</fieldset>
						
				</c:if>
				
			</kme:listItem>
        </c:forEach> 
			Description : <form:textarea path="description" id="description" />
		            	<input data-theme="a" class="submit" type="submit" value="Submit" />

    </kme:listView>
	</form:form>
		
	</kme:content>
</kme:page>