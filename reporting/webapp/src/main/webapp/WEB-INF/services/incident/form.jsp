<%--
  Copyright 2011-2012 The Kuali Foundation Licensed under the Educational Community
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

<kme:page title="Incident" id="incident" backButton="true" homeButton="true" cssFilename="incident">

	<script type="text/javascript">
    var pictureSource;   // picture source
    var destinationType; // sets the format of returned value 

    // Wait for PhoneGap to connect with the device
    //
    document.addEventListener("deviceready",onDeviceReady,false);

    // PhoneGap is ready to be used!
    function onDeviceReady() {
    	alert("OnDeviceReady");
		// Setup variable names shorter than the stock phonegap ones.  
        pictureSource	= navigator.camera.PictureSourceType;
        destinationType	= navigator.camera.DestinationType;
    }

    // Called when a photo is successfully retrieved
    function onPhotoURISuccess(imageURI) {
		// View the Photo's URI in the  console. Can use this to grap the photo with a form for 
		console.log(imageURI);

		// Get image handle
		var largeImage = document.getElementById('largeImage');

		console.log("largeImage:" + largeImage);
		// Unhide image elements
		largeImage.style.display = 'block';

		// Show the captured photo
		// The inline CSS rules are used to resize the image
		console.log("pre" + largeImage.src);
		largeImage.src = imageURI;
		console.log("post" + largeImage.src);
    }



    // A button will call this function
    function getPhoto(source) {
    	alert("Destination type:" + destinationType);
    	
		// Retrieve image file location from specified source
		// The quality, targetWidth, targetHeight taken together probably affect file size. 
		navigator.camera.getPicture(onPhotoURISuccess, onFail, {quality: 50, 
																destinationType: destinationType.FILE_URI,
																sourceType: source, 
																targetWidth: 320,
																targetHeight: 320});
    }
    
    // Called if something bad happens or fail silently...
    function onFail(message) {
		alert('Failed because: ' + message);
    }
   </script>

    <kme:content>
    	<kme:listView>
		    <kme:listItem cssClass="link-phone">
			    <a href="tel:911">
			    	<h3>Call 911</h3>
			    	<p class="wrap">If you believe that you are in immediate danger or that the incident you are reporting could be a classified as a hate crime, call 911 now.</p>
			    </a>
		    </kme:listItem>
		    <kme:listItem dataRole="list-divider">
		    	Report Incident
		    </kme:listItem>
			<kme:listItem>
		        <form:form action="${pageContext.request.contextPath}/reporting/incidentPost" commandName="incident" data-ajax="false" method="post"> 
					<form:hidden path="userAgent" value="${header['User-Agent']}"/>
					
		            <fieldset>            
		                <label for="summary">Summary</label>
		                <form:textarea path="summary" cols="40" rows="8" class="required" />
		                <form:errors path="summary" />
						<%-- <div onclick="getPhoto(pictureSource.CAMERA);" style="display:block; height:30px; border:1px solid green">CAMERA!!!!</div>
						<div onclick="getPhoto(pictureSource.PHOTOLIBRARY);" style="display:block; height:30px; border:1px solid green">LIBRARY!!!</div>
						
					    <button onclick="getPhoto(pictureSource.CAMERA);return false;">From CAMERA</button><br>
					    <button onclick="getPhoto(pictureSource.PHOTOLIBRARY);">From PHOTOLIBRARY</button><br>
					    <button onclick="navigator.notification.alert('Phonegap Alert', function(){}, 'Alert', 'OK');">alert</button><br>
					    <img style="border:1px solid red; display:block; width:300px; height:200px;" id="largeImage" src="" />
					    <a href="/mdot/reportingindex.html">test html</a>	
					    --%>
					    						
		                <label for="email">Email</label>
		                <form:input path="email" type="text" value="" placeholder="Anonymous" class="email" />
						
			            <fieldset data-role="controlgroup" data-theme="c">
			    	    	<label for="affiliation">Affiliation</label>				
				            <form:checkbox data-theme="c" path="affiliationStudent" value="YES" style="left:0; width:25px; height:25px;" label="Student" />
				            <form:checkbox data-theme="c" path="affiliationFaculty" value="YES" style="left:0; width:25px; height:25px;" label="Faculty" />
				            <form:checkbox data-theme="c" path="affiliationStaff"   value="YES" style="left:0; width:25px; height:25px;" label="Staff" />
				            <form:checkbox data-theme="c" path="affiliationOther"   value="YES" style="left:0; width:25px; height:25px;" label="Other" />
						</fieldset>
						
			            <fieldset data-role="controlgroup" data-theme="c">
			    	    	<label for="contactMe">Contact me</label><br/>				
			                <form:radiobutton data-theme="c" path="contactMe" value="YES" label="Yes, please follow up with me. I would like to receive support from the Teams or provide more information as needed" />
			                <form:radiobutton data-theme="c" path="contactMe" value="NO"  label="No" />
						</fieldset>
		            </fieldset>
		            
		            <div data-inline="true">
		            	<input data-theme="a" class="submit" type="submit" value="Submit" />
		            </div>
		        </form:form>
	        </kme:listItem>
        </kme:listView>
    </kme:content>
</kme:page>	
