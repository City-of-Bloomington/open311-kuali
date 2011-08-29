<!DOCTYPE html>
<html>
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.js" type="text/javascript"></script>
		<script src="https://maps-api-ssl.google.com/maps/api/js?libraries=geometry&v=3&sensor=false" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/arcgislink.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/polylineEdit.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.contextmenu.r2.packed.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/math.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/mapArrows.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/tourMaps.js" type="text/javascript"></script>
        <link type="text/css" href="${pageContext.request.contextPath}/css/smoothness/jquery-ui-1.8.16.custom.css" rel="Stylesheet" />	

		<script>
			
			
			function getNextMediaCount(){
				var value = mediaIdCounter;
				mediaIdCounter++;
				return value;
			}
			
			jQuery(window).load(function() {
				initializeMap();
				$("#selector").tabs({
					show: changeSelectorTabs
				});
				$("#wizard").tabs({
					show: changeWizardTabs
				});
				$("#mediaTabs").tabs();
				$("#videoTabs").tabs();
			});
			
			function addImageToList() {
				var url = $('#imageUrl').val();
				var title = $('#imageTitle').val();
				var caption = $('#imageCaption').val();
				
				var id = addMedia(url, title);
				
				var media = new Object();
				media.type = 'image';
				media.url = url;
				media.title = title;
				media.caption = caption;
				
				mediaList[id] = media;
				
				return false;
			}
			
			function addVideoToList() {
				var $tabs = $('#videoTabs').tabs();
				var selected = $tabs.tabs('option', 'selected');
				
				var title = $('#videoTitle').val();
				var caption = $('#videoCaption').val();
				var youTubeUrl;
				var oggUrl;
				var mp4Url;
				var webMUrl;
				
				if (selected == 0) { //files
					oggUrl = $('#oggUrl').val();
					mp4Url = $('#mp4Url').val();
					webMUrl = $('#webMUrl').val();
				} else if (selected == 1) { //youtube
					youTubeUrl = $('#youTubeUrl').val();
				}
				
				var iconUrl = '${pageContext.request.contextPath}/images/video.png';
				
				var id = addMedia(iconUrl, title);
				
				var media = new Object();
				media.type = 'video';
				media.oggUrl = oggUrl;
				media.mp4Url = mp4Url;
				media.webMUrl = webMUrl;
				media.youTubeUrl = youTubeUrl;
				media.title = title;
				media.caption = caption;
				
				mediaList[id] = media;
				
				return false;
			}
			
			function addAudioToList() {				
				var title = $('#audioTitle').val();
				var caption = $('#audioCaption').val();
				var oggVorbisUrl;
				var mp3Url;
				var wavUrl;
				
				oggVorbisUrl = $('#oggVorbisUrl').val();
				mp3Url = $('#mp3Url').val();
				wavUrl = $('#wavUrl').val();
				
				var iconUrl = '${pageContext.request.contextPath}/images/audio.png';
		
				var id = addMedia(iconUrl, title);
				
				var media = new Object();
				media.type = 'audio';
				media.oggVorbisUrl = oggVorbisUrl;
				media.mp3Url = mp3Url;
				media.wavUrl = wavUrl;
				media.title = title;
				media.caption = caption;
				
				mediaList[id] = media;
				
				return false;
			}
			
			function addMedia(imgSrc, title){
				var li = $("<li></li>");
				var img = $("<img />");
				img.attr('src', imgSrc);
				if (title){
					img.attr('title', title);
				}
				li.append(img);
				
				var id = 'media' + getNextMediaCount();
				img.attr('id', id);
				
				$('#images').append(li);
				
				img.contextMenu('myMenu', {
					bindings: {
						'move_left': moveUp,
				        'move_right': moveDown,
				        'remove': remove
					}
				});
				
				return id;
			}
			
			function moveUp(domElement) {
				var element = $('img#' + domElement.id).parent(); //get the containing li
				var prevElement = element.prev();
				if (prevElement){
					prevElement.before(element);
				}
			}
			
			function moveDown(domElement) {
				var element = $('img#' + domElement.id).parent(); //get the containing li
				var nextElement = element.next();
				if (nextElement){
					nextElement.after(element);
				}
			}
			
			function remove(domElement) {
				var element = $('img#' + domElement.id).parent().remove();
			}
		</script>
        <style>
			p {
				margin: 0;
				padding: 0;
			}
		
			.ui-widget {
				font-family: inherit;
				font-size: inherit;
			}
			
			.ui-widget input, .ui-widget select, .ui-widget textarea, .ui-widget button {
				font-family: inherit;
				font-size: inherit;
			}
			
			#searchResults {
				border: 1px solid #CCCCCC;
				overflow:auto;
				height:175px;
				font-size:0.8em;
				/*width: 600px;*/
			}
			
			.venues, #selectedPOIs {
				border: 1px solid #CCCCCC;
				overflow:auto;
				height:200px;
				font-size:0.8em;
				/*width: 600px;*/
			}
			
			.venue {
				border-bottom: 1px solid #CCC;
				border-top: 1px solid #EEE;
				padding: 5px 10px 5px 10px;
				cursor: pointer;
				overflow: hidden;
			}
			
			.venue .info .name {
				font-weight: bold;
			}
			
			#venues .odd:hover { background-color: #ccffff; }
			#venues .even:hover { background-color: #ccffff; }
			#venues .odd { background-color: #ffffff; }
			#venues .even { background-color: #f4f4f4; }
			#venues .last { border-bottom: none; }
			
			.venue .image {
				float: left;
				height: 38px;
				width: 50px;
			}
			
			.venue .image img {
				background-color: #FFFFFF;
				padding: 3px;
			}
			
			ul#images {
				padding:0;
			}
			
			ul#images li {
				display: inline;
				margin:0;
				padding:0;
				position: relative;
			}
			
			ul#images li img {
				border: 1px solid #AAA;
				margin: 0 2px 2px 0;
				padding: 2px;
				height: 48px;
			}
			
			div#selectedMedia {
				border: 1px solid #AAAAAA;
				border-radius: 4px;
				padding: 4px;
			}

		</style>
	</head>
	<body>
		<div class="contextMenu" id="myMenu">
			<ul>			
				<li id="move_left">Move Up</li>				
				<li id="move_right">Move Down</li>				
				<li id="remove">Remove</li>						
			</ul>		
		</div>
		<table border="0" style="width:100%px;">
            <tr>
				<td style="width:600px" valign="top"><div id="map_canvas" style="width:600px; height:400px"></div></td>
                <td valign="top" align="left">
                	<div id="wizard">
                        <ul>
                            <li><a href="#name">1. Name the Tour</a></li>
                            <li><a href="#poi">2. Add Points of Interest</a></li>
                            <li><a href="#route">3. Create the Route</a></li>
                            <li><a href="#save">4. Save the Tour</a></li>
                        </ul>
                        <div id="name">
                        	<input type="hidden" name="tourId" id ="tourId" value="" />
                        	<input type="hidden" name="tourVersion" id ="tourVersion" value="" />
                        	<input type="hidden" name="tourJson" id="tourJson" value='${tourJson}' />
                            <table>
                                <tr align="left" valign="top">
                                    <td>Name: </td>
                                    <td><input type="text" name="tourName" id="tourName" /></td>
                                </tr>
                                <tr align="left" valign="top">
                                    <td>Description: </td>
                                    <td><textarea rows="3" cols="50" name="tourDescription" id="tourDescription"></textarea></td>
                                </tr>
                            </table>
                        </div>
                        <div id="poi">
                            <table style="width:100%;">
                                <tr>
                                    <th align="left">1. Select a location</th>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="selector" style="height:300px;">
                                            <ul>
                                                <li><a href="#building">Choose a Building</a></li>
                                                <li><a href="#point">Choose a Point</a></li>
                                                <li><a href="#foursquare">Select a Foursquare Venue</a></li>
                                            </ul>
                                            <div id="building">
                                            	<p>Find a building.</p>
                                                <input type="text" name="buildingName" id="buildingName" /><button type="button" onclick="searchBuildings();" id="findButton" disabled>Find</button><span id='busy' style="color:red;display:none;"> Searching...</span>
                                                <div id="searchResults"></div>
                                            </div>
                                            <div id="point">
                                                <p>Click the map to select a location.</p>
                                                Selected Location: <span id="selectedLocation"></span><br />
                                                Location Name: <input type="text" name="pointName" id="pointName" />
                                            </div>
                                            <div id="foursquare">
                                                <p>Click the map to find nearby venues.</p>
                                                <div id="venues" class="venues"></div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>2. Add A Text Description</th>
                                </tr>
                                <tr>
                                    <td>
                                        <textarea rows="5" cols="50" id="description"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th>3. Add Media</th>
                                </tr>
                                <tr align="left" valign="top">
                                	<td>
                                		<div id="mediaTabs">
                                            <ul>
                                                <li><a href="#imageTab">Images</a></li>
                                                <li><a href="#videoTab">Video</a></li>
                                                <li><a href="#audioTab">Audio</a></li>
                                            </ul>
                                            <div id="imageTab">
                                            	<table>
			                                		<tr align="left" valign="top">
			                                			<td>URL: </td>
			                                			<td><input type="text" name="imageUrl" id="imageUrl" size="50" /></td>
			                                		</tr>
			                                		<tr align="left" valign="top">
			                                			<td>Title: </td>
			                                			<td><input type="text" name="imageTitle" id="imageTitle" size="50" /></td>
			                                		</tr>
			                                		<tr align="left" valign="top">
			                                			<td>Caption: </td>
			                                			<td><textarea rows="5" cols="50" id="imageCaption"></textarea></td>
			                                		</tr>
			                                	</table>
			                                	<button type="button" onclick="addImageToList();">Add Image</button>
                                            </div>
                                            <div id="videoTab">
                                            	<table>
			                                		<tr align="left" valign="top">
			                                			<td>Source: </td>
				                                		<td>
					                                		<div id="videoTabs">
					                                            <ul>
					                                                <li><a href="#videoFile">File</a></li>
					                                                <li><a href="#videoYouTube">YouTube</a></li>
					                                            </ul>
					                                            <div id="videoFile">
					                                            	<table>
					                                            		<tr>
					                                            			<th>Format</th>
					                         								<th>URL</th>
					                         							</tr>
					                         							<tr>
					                         								<td>Ogg</td>
					                         								<td><input type="text" name="oggUrl" id="oggUrl" size="50" /></td>
					                         							</tr>
					                         							<tr>
					                         								<td>MPEG 4</td>
					                         								<td><input type="text" name="mpg4Url" id="mpg4Url" size="50" /></td>
					                         							</tr>
					                         							<tr>
					                         								<td>WebM</td>
					                         								<td><input type="text" name="webMUrl" id="webMUrl" size="50" /></td>
					                         							</tr>
					                                            	</table>
					                                            </div>
					                                            <div id="videoYouTube">
																	URL: <input type="text" name="youTubeUrl" id="youTubeUrl" size="50" />
					                                            </div>
				                                            </div>
			                                            </td>
			                                		</tr>
			                                		<tr align="left" valign="top">
			                                			<td>Title: </td>
			                                			<td><input type="text" name="videoTitle" id="videoTitle" size="50" /></td>
			                                		</tr>
			                                		<tr align="left" valign="top">
			                                			<td>Caption: </td>
			                                			<td><textarea rows="5" cols="50" id="videoCaption"></textarea></td>
			                                		</tr>
			                                	</table>
	                                            <button type="button" onclick="addVideoToList();">Add Video</button>
                                            </div>
                                            <div id="audioTab">
                                            	<table>
			                                		<tr align="left" valign="top">
			                                			<td>Source: </td>
				                                		<td>
			                                            	<table>
			                                            		<tr>
			                                            			<th>Format</th>
			                         								<th>URL</th>
			                         							</tr>
			                         							<tr>
			                         								<td>Ogg Vorbis</td>
			                         								<td><input type="text" name="oggVorbisUrl" id="oggVorbisUrl" size="50" /></td>
			                         							</tr>
			                         							<tr>
			                         								<td>MP3</td>
			                         								<td><input type="text" name="mp3Url" id="mp3Url" size="50" /></td>
			                         							</tr>
			                         							<tr>
			                         								<td>Wav</td>
			                         								<td><input type="text" name="wavUrl" id="wavUrl" size="50" /></td>
			                         							</tr>
			                                            	</table>
			                                            </td>
			                                		</tr>
			                                		<tr align="left" valign="top">
			                                			<td>Title: </td>
			                                			<td><input type="text" name="audioTitle" id="audioTitle" size="50" /></td>
			                                		</tr>
			                                		<tr align="left" valign="top">
			                                			<td>Caption: </td>
			                                			<td><textarea rows="5" cols="50" id="audioCaption"></textarea></td>
			                                		</tr>
			                                	</table>
	                                            <button type="button" onclick="addAudioToList();">Add Audio</button>
                                            </div>
                                        </div>
                                	</td>
                                </tr>
                                <tr>
                                    <td>
                                    	<p>Selected Media</p>
										<div id="selectedMedia">
											<ul id="images">
											
											</ul>
										</div>
                                    </td>
                                </tr>
                                <tr>
                                    <th align="left">4. Add Point of Interest to Tour</th>
                                </tr>
                                <tr>
                                    <td>
                                        <button type="button" onclick="addToRoute();">Add Point</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="route">
                        	<table>
                                <tr align="left" valign="top">
                                	<td>Create A Rough Route: </td>
                                    <td><button type="button" onclick="startEditingRoute();">Edit Route</button></td><td><button type="button" onclick="stopEditingRoute();">Stop Editing</button></td>
                                    </tr>
                                <tr align="left" valign="top">
                                    <td>Fine-Tune The Route: </td>
                            		<td><button type="button" onclick="fineTuneRoute();">Fine-Tune Route</button></td><td><button type="button" onclick="stopFineTuning();">Stop Fine-Tuning</button></td>
                                </tr>
                                <tr>
                                	<td><span>Status: </span><span id="editStatus">Not Editing</span></td>
                                </tr>
                                <tr>
                                	<td><span>Route Distance: </span><span id="routeDistance">0.0 Miles</span></td>
                                </tr>
                            </table>
                        </div>
                        <div id="save">
                        	<p>Review Selected Points of Interest</p>
                            <div id="selectedPOIs"></div><br />
                        	<button type="button" onclick="saveTour();">Save</button><!--<button type="button" onclick="generateRouteKML();">Generate KML</button>-->
                        	<form id="postForm" action="${pageContext.request.contextPath}/tours/edit" method="post" >
                        		<input type="hidden" id="data" name="data" value="" />
                        	</form>
                        </div>
                    </div>
                </td>
			</tr>
<!--
            <tr>
				<td style="padding-top:10px;">
					<br />
					Markers:
					<input type="radio" name="group1" value="Show" onclick="showMarkers2();" checked />Show
					<input type="radio" name="group1" value="Hide" onclick="hideMarkers();" />Hide
				</td>				
			</tr>-->
		</table>
	</body>
</html>