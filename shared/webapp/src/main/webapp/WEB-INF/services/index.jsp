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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:contains(header['User-Agent'],'iPhone') || fn:contains(header['User-Agent'],'iPad') || fn:contains(header['User-Agent'],'iPod') || fn:contains(header['User-Agent'],'Macintosh')}">
	<c:set var="platform" value="iOS"/>
</c:if>
<c:if test="${fn:contains(header['User-Agent'],'Android')}">
	<c:set var="platform" value="Android"/>
</c:if>

<c:set var="phonegap" value="${cookie.phonegap.value}"/>

<kme:page title="${title}" id="home" cssFilename="home" backButton="false" homeButton="false" preferencesButton="true" preferencesButtonURL="preferences" platform="${platform}" phonegap="${phonegap}" onBodyLoad="">
	<kme:content>

		<c:if test="${param.native == 'yes'}">
			<script type="text/javascript">
				$.cookie('native', 'yes', {expires: 365, path: '/'});
			</script>
		</c:if>			
		<c:if test="${param.native == 'no'}">
			<script type="text/javascript">
				$.cookie('native', 'no', {expires: 365, path: '/'});
			</script>
		</c:if>	

		<script type="text/javascript">
			$.cookie('phonegap', '${param.phonegap}', {expires: 365, path: '/'});
			/*  ${cookie.phonegap.value} */
		

			// Must call after DOM is ready. 
		    // This is shortcut for $(document).ready(...);
		    //$(function(){								
		    	document.addEventListener("deviceready",onDeviceReady,false);	
		    //});
				
			function onDeviceReady(){
				//navigator.notification.alert('Did onDeviceReady()', function(){}, 'IU Mobile', 'OK');		    
			}			
			
			
		</script>


	<%-- 
		<!-- <p><a id="manualUpdate" href="#">Check for an updated Cache</a></p> -->
	
		<div id="cacheProgressModal">
			<div id="cacheProgressMessage">
			<h3>Optimizing performance.  Please wait.</h3>
			<p><span id="cacheProgress">&nbsp;</span></p>
			
			<input id="reloadButton" type="button" value="Reload" onClick="window.location.reload()">
		 
			<!-- <ul id="applicationEvents">
			</ul> -->
			</div>
	 	</div>
 	--%>
 	
 		<c:set var="bCount" value="0"/>
 	
	    <kme:listView id="homeserviceslist" filter="false">
	        <c:forEach items="${tools}" var="homeTool" varStatus="status">	            
	            <kme:listItem hideDataIcon="true">
	            	<a href="${homeTool.tool.url}" style="background-image: url('${homeTool.tool.iconUrl}');">
			      		<h3>${homeTool.tool.title}</h3>
			      		<p class="wrap">${homeTool.tool.description}</p>
			      		<c:if test="${not empty homeTool.tool.badgeCount}"> 
			      			<span class="countBadge ui-btn-up-c ui-btn-corner-all">${homeTool.tool.badgeCount}</span>
			      		</c:if>
			      	</a>
	            </kme:listItem>
	            
	            <!-- Sum the badge counts. -->
				<c:set var="bCount" value="${bCount + homeTool.tool.badgeCount}"/>

			</c:forEach>	
			<c:if test="${cookie.native.value == 'yes' || param.native == 'yes'}">
	            <kme:listItem hideDataIcon="true">
	            	<a href="qrcode" style="background-image: url('${pageContext.request.contextPath}/images/service-icons/srvc-qrcode.png');">
			      		<h3>QR Code Reader</h3>
			      		<p class="wrap">Scan QR Codes for quick access to Information and Links</p>
			      		<c:if test="${not empty homeTool.tool.badgeCount}"> 
			      			<span class="countBadge ui-btn-up-c ui-btn-corner-all">${homeTool.tool.badgeCount}</span>
			      		</c:if>
			      	</a>
	            </kme:listItem>
	        </c:if>
	    </kme:listView>
	    <c:if test="${not empty ipAddress}">${ipAddress}</c:if>
	</kme:content>

	<script type="text/javascript">
		function otherDeviceReadyFunctions(){
			window.plugins.badge.set(${bCount});	
		}
	</script>
	
	<%-- 
	<!-- When the DOM is ready (ie. Now), run the scripts. -->
	<script type="text/javascript">
 
		// Get the DOM references we'll need to play with.
		var appStatus = $( "#applicationStatus" );
		var appEvents = $( "#applicationEvents" );
		var manualUpdate = $( "#manualUpdate" );
		var cacheProgress = $( "#cacheProgress" );
 
		// Get a short-hand for our application cache object.
		var appCache = window.applicationCache;
 
		// Create a cache properties object to help us keep track of
		// the progress of the caching.
		var cacheProperties = {
			filesDownloaded: 0,
			totalFiles: 0
		};
 
 
		// I log an event to the event list.
		function logEvent( event ){
			appEvents.prepend(
				"<li>" +
					(event + " ... " + (new Date()).toTimeString()) +
				"</li>"
			);
		}
 
 
		// I get the total number of files in the cache manifest.
		// I do this by manually parsing the manifest file.
		function getTotalFiles(){
			// First, reset the total file count and download count.
			cacheProperties.filesDownloaded = 0;
			cacheProperties.totalFiles = 0;
 
			// Now, grab the cache manifest file.
			$.ajax({
				type: "get",
				url: "./kme.appcache",
				dataType: "text",
				cache: false,
				success: function( content ){
					// Strip out the non-cache sections.
					// NOTE: The line break here is only to prevent
					// wrapping in the BLOG.
					content = content.replace(
						new RegExp(
							"(NETWORK|FALLBACK):" +
							"((?!(NETWORK|FALLBACK|CACHE):)[\\w\\W]*)",
							"gi"
						),
						""
					);
 
					// Strip out all comments.
					content = content.replace(
						new RegExp( "#[^\\r\\n]*(\\r\\n?|\\n)", "g" ),
						""
					);
 
					// Strip out the cache manifest header and
					// trailing slashes.
					content = content.replace(
						new RegExp( "CACHE MANIFEST\\s*|\\s*$", "g" ),
						""
					);
 
					// Strip out extra line breaks and replace with
					// a hash sign that we can break on.
					content = content.replace(
						new RegExp( "[\\r\\n]+", "g" ),
						"#"
					);
 
					// Get the total number of files.
					var totalFiles = content.split( "#" ).length;
 
					// Store the total number of files. Here, we are
					// adding one for *THIS* file, which is cached
					// implicitly as it points to the manifest.
					//cacheProperties.totalFiles = (totalFiles + 1);
					
					// For some reason we are getting one extra file in the count. Removing the +1 from above.
					cacheProperties.totalFiles = (totalFiles+0);
				}
			});
		}
 
 
		// I display the download progress.
		function displayProgress(){
			// Increment the running total.
			cacheProperties.filesDownloaded++;
 
			// Check to see if we have a total number of files.
			if (cacheProperties.totalFiles){
 
				// We have the total number of files, so output the
				// running total as a function of the known total.
				/*cacheProgress.text(
					cacheProperties.filesDownloaded +
					" of " +
					cacheProperties.totalFiles +
					" files downloaded."
				);*/
				var percentDownloaded = 0;
				
				//if (cacheProperties.totalFiles > 0) {
				percentDownloaded = (cacheProperties.filesDownloaded / cacheProperties.totalFiles) * 100;
				//}
				var progressBar = '<div id="appcacheProgressBar"><div id="appcacheProgressAmount" style="width:' + percentDownloaded.toFixed() + '%">&nbsp;' + percentDownloaded.toFixed() + '%</div></div>'; 
				cacheProgress.html(progressBar);
 
			} else {
 
				// We don't yet know the total number of files, so
				// just output the running total.
				cacheProgress.text(
					cacheProperties.filesDownloaded +
					" files downloaded."
				);
 
			}
		}
 
 
		// Bind the manual update link.
		manualUpdate.click(
			function( event ){
				// Prevent the default event.
				event.preventDefault();
 
				// Manually ask the cache to update.
				appCache.update();
			}
		);
 
 
		// Bind to online/offline events.
		$( window ).bind(
			"online offline",
			function( event ){
				// Update the online status.
				appStatus.text( navigator.onLine ? "Online" : "Offline" );
			}
		);
 
		// Set the initial status of the application.
		appStatus.text( navigator.onLine ? "Online" : "Offline" );
 
 
		// List for checking events. This gets fired when the browser
		// is checking for an udpated manifest file or is attempting
		// to download it for the first time.
		$( appCache ).bind(
			"checking",
			function( event ){
				logEvent( "Checking for manifest" );
			}
		);
 
		// This gets fired if there is no update to the manifest file
		// that has just been checked.
		$( appCache ).bind(
			"noupdate",
			function( event ){
				logEvent( "No cache updates" );
			}
		);
 
		// This gets fired when the browser is downloading the files
		// defined in the cache manifest.
		$( appCache ).bind(
			"downloading",
			function( event ){
				logEvent( "Downloading cache" );
				$('div#cacheProgressModal').fadeIn();
				$('div#cacheProgressMessage').css('top','40px');
				// Get the total number of files in our manifest.
				getTotalFiles();
			}
		);
 
		// This gets fired for every file that is downloaded by the
		// cache update.
		$( appCache ).bind(
			"progress",
			function( event ){
				logEvent( "File downloaded" );
 
				// Show the download progress.
				displayProgress();
			}
		);
 
		// This gets fired when all cached files have been
		// downloaded and are available to the application cache.
		$( appCache ).bind(
			"cached",
			function( event ){
				logEvent( "All files downloaded" );
				$('div#cacheProgressModal').fadeOut();
				$('div#cacheProgressMessage').fadeOut();
			}
		);
 
		// This gets fired when new cache files have been downloaded
		// and are ready to replace the *existing* cache. The old
		// cache will need to be swapped out.
		$( appCache ).bind(
			"updateready",
			function( event ){
				logEvent( "New cache available" );
 
				// Swap out the old cache.
				appCache.swapCache();
			}
		);
 
		// This gets fired when the cache manifest cannot be found.
		$( appCache ).bind(
			"obsolete",
			function( event ){
				logEvent( "Manifest cannot be found" );
			}
		);
 
		// This gets fired when an error occurs
		$( appCache ).bind(
			"error",
			function( event ){
				//alert('error!');
				logEvent( "An error occurred" );
				$('div#cacheProgressModal').fadeOut();
				$('div#cacheProgressMessage').fadeOut();
			}
		);
 
	</script>
	--%>
	
</kme:page>


