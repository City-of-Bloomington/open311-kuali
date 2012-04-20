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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="native" value="${cookie.native.value}"/>

<kme:page title="Incident" id="incident" backButton="true" homeButton="true" cssFilename="incident">

	<script type="text/javascript">
    var pictureSource;   // picture source
    var destinationType; // sets the format of returned value 

    // Wait for PhoneGap to connect with the device
    //
    document.addEventListener("deviceready",onDeviceReady,false);

    // PhoneGap is ready to be used!
    function onDeviceReady() {
    	//alert("OnDeviceReady");
		// Setup variable names shorter than the stock phonegap ones.  
        pictureSource	= navigator.camera.PictureSourceType;
        destinationType	= navigator.camera.DestinationType;
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
		                <label style="margin-top:10px; font-weight:normal; font-size:14px;" for="summary">Summary:</label>
		                <form:textarea path="summary" cols="40" rows="8" class="required" />
		                <form:errors path="summary" />


						<c:if test="${cookie.native.value == 'yes'}">
							<fieldset class="ui-grid-a">
								<legend>Photo</legend>
									<div class="ui-block-a"><button onclick="getPhoto(pictureSource.CAMERA);return false;">Camera</button></div>
				    				<div class="ui-block-b"><button onclick="getPhoto(pictureSource.PHOTOLIBRARY);return false;">Album</button></div>
							</fieldset>
							<img style="border:1px solid black; display:none; width:100px; height:100px;" id="largeImage" src="" />
						</c:if>
						<c:if test="${fn:contains(header['User-Agent'],'Windows') || fn:contains(header['User-Agent'],'Macintosh')}">
							<label style="margin-top:10px; font-weight:normal; font-size:14px;" for="file">File:</label>
							<input type="file" name="somename" size="40"> 
						</c:if>

					    											    						
		                <label style="margin-top:10px; font-weight:normal; font-size:14px;" for="email">Email:</label>
		                <form:input path="email" type="text" value="" placeholder="Anonymous" class="email" />
						
						<label style="margin-top:10px; font-weight:normal; font-size:14px;" for="affiliation">&nbsp;<br/>Affiliation:</label>
			            <fieldset data-role="controlgroup" data-theme="c">
			    	    					
				            <form:checkbox data-theme="c" path="affiliationStudent" value="YES" style="left:0; width:25px; height:25px;" label="Student" />
				            <form:checkbox data-theme="c" path="affiliationFaculty" value="YES" style="left:0; width:25px; height:25px;" label="Faculty" />
				            <form:checkbox data-theme="c" path="affiliationStaff"   value="YES" style="left:0; width:25px; height:25px;" label="Staff" />
				            <form:checkbox data-theme="c" path="affiliationOther"   value="YES" style="left:0; width:25px; height:25px;" label="Other" />
						</fieldset>
						
						<label style="margin-top:10px; font-weight:normal; font-size:14px;" for="contactMe">Contact Me:</label>
			            <fieldset data-role="controlgroup" data-theme="c">
			    	    					
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
