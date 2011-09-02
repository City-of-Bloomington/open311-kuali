<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.js" type="text/javascript"></script>
<script src="https://maps-api-ssl.google.com/maps/api/js?libraries=geometry&v=3&sensor=false" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/arcgislink.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.contextmenu.r2.packed.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/foursquare.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/media.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/arcgis.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/toursBase.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/poiMaps.js" type="text/javascript"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/smoothness/jquery-ui-1.8.16.custom.css" rel="Stylesheet" />

<script>
	jQuery(window).load(function() {
		initializeMap('${pageContext.request.contextPath}');
		$("#selector").tabs({
			show : changeSelectorTabs
		});
	});
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

.ui-widget input,.ui-widget select,.ui-widget textarea,.ui-widget button
	{
	font-family: inherit;
	font-size: inherit;
}

#searchResults {
	border: 1px solid #CCCCCC;
	overflow: auto;
	height: 175px;
	font-size: 0.8em;
	/*width: 600px;*/
}

.venues,#selectedPOIs {
	border: 1px solid #CCCCCC;
	overflow: auto;
	height: 200px;
	font-size: 0.8em;
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

#venues .odd:hover {
	background-color: #ccffff;
}

#venues .even:hover {
	background-color: #ccffff;
}

#venues .odd {
	background-color: #ffffff;
}

#venues .even {
	background-color: #f4f4f4;
}

#venues .last {
	border-bottom: none;
}

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
	padding: 0;
}

ul#images li {
	display: inline;
	margin: 0;
	padding: 0;
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
	<input type="hidden" name="poiId" id ="poiId" value="" />
   	<input type="hidden" name="poiVersion" id ="poiVersion" value="" />
   	<c:set var="json"><c:out value="${poiJson}" escapeXml="true" /></c:set>
   	<input type="hidden" name="poiJson" id="poiJson" value="${json}" />
	<table border="0" style="width: 100% px;">
		<tr>
			<td>
				<div id="selector" style="height: 300px;">
					<ul>
						<li><a href="#building">IU Building</a>
						</li>
						<li><a href="#point">Custom Point</a>
						</li>
						<li><a href="#foursquare">Foursquare Venue</a>
						</li>
					</ul>
					<div id="building">
						<p>Find a building.</p>
						<input type="text" name="buildingName" id="buildingName" />
						<button type="button" onclick="searchBuildings();"
							id="findButton" disabled>Find</button>
						<span id="busy" style="color: red; display: none;">Searching...</span>
						<div id="searchResults"></div>
					</div>
					<div id="point">
						<p>Click the map to select a location.</p>
						Selected Location: <span id="selectedLocation"></span><br />
					</div>
					<div id="foursquare">
						<p>Click the map to find nearby venues.</p>
						<div id="venues" class="venues"></div>
					</div>
				</div>
			</td>
			<td style="width: 500px" valign="top">
				<div id="map_canvas" style="width: 500px; height: 300px"></div>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<p>Location Name: <input type="text" name="poiName" id="poiName" size="40" /></p>
				<p>Coordinates: <input type="text" name="latitude" id="latitude" />, <input type="text" name="longitude" id="longitude" /></p>
				<p>Url: <input type="text" name="url" id="url" size="40" /></p>
				<p>Description</p>
				<textarea rows="10" cols="50" id="description"></textarea>
			</td>
			<td>
				<p>Media <span id="editingMedia" style="color: red; display: none;"></span></p>
				<div id="mediaTabs">
					<ul>
						<li><a href="#imageTab">Image</a></li>
						<li><a href="#videoTab">Video</a></li>
						<li><a href="#audioTab">Audio</a></li>
					</ul>
					<div id="imageTab">
						<table>
							<tr align="left" valign="top">
								<td>URL:</td>
								<td><input type="text" name="imageUrl" id="imageUrl"
									size="50" />
								</td>
							</tr>
							<tr align="left" valign="top">
								<td>Title:</td>
								<td><input type="text" name="imageTitle" id="imageTitle"
									size="50" />
								</td>
							</tr>
							<tr align="left" valign="top">
								<td>Caption:</td>
								<td><textarea rows="5" cols="50" id="imageCaption"></textarea>
								</td>
							</tr>
						</table>
						<button type="button" onclick="addImageToList();">Save Image</button><button type="button" onclick="stopEditingMedia();" class="mediaCancel">Cancel Edit</button>
					</div>
					<div id="videoTab">
						<table>
							<tr align="left" valign="top">
								<td>Source:</td>
								<td>
									<div id="videoTabs">
										<ul>
											<li><a href="#videoFile">File</a>
											</li>
											<li><a href="#videoYouTube">YouTube</a>
											</li>
										</ul>
										<div id="videoFile">
											<table>
												<tr>
													<th>Format</th>
													<th>URL</th>
												</tr>
												<tr>
													<td>Ogg</td>
													<td><input type="text" name="oggUrl" id="oggUrl"
														size="50" />
													</td>
												</tr>
												<tr>
													<td>MPEG 4</td>
													<td><input type="text" name="mp4Url" id="mp4Url"
														size="50" />
													</td>
												</tr>
												<tr>
													<td>WebM</td>
													<td><input type="text" name="webMUrl" id="webMUrl"
														size="50" />
													</td>
												</tr>
											</table>
										</div>
										<div id="videoYouTube">URL: <input type="text" name="youTubeUrl" id="youTubeUrl" size="50" />
										</div>
									</div></td>
							</tr>
							<tr align="left" valign="top">
								<td>Title:</td>
								<td><input type="text" name="videoTitle" id="videoTitle"
									size="50" />
								</td>
							</tr>
							<tr align="left" valign="top">
								<td>Caption:</td>
								<td><textarea rows="5" cols="50" id="videoCaption"></textarea>
								</td>
							</tr>
						</table>
						<button type="button" onclick="addVideoToList();">Save Video</button><button type="button" onclick="stopEditingMedia();" class="mediaCancel">Cancel Edit</button>
					</div>
					<div id="audioTab">
						<table>
							<tr align="left" valign="top">
								<td>Source:</td>
								<td>
									<table>
										<tr>
											<th>Format</th>
											<th>URL</th>
										</tr>
										<tr>
											<td>Ogg Vorbis</td>
											<td><input type="text" name="oggVorbisUrl"
												id="oggVorbisUrl" size="50" />
											</td>
										</tr>
										<tr>
											<td>MP3</td>
											<td><input type="text" name="mp3Url" id="mp3Url"
												size="50" />
											</td>
										</tr>
										<tr>
											<td>Wav</td>
											<td><input type="text" name="wavUrl" id="wavUrl"
												size="50" />
											</td>
										</tr>
									</table></td>
							</tr>
							<tr align="left" valign="top">
								<td>Title:</td>
								<td><input type="text" name="audioTitle" id="audioTitle"
									size="50" />
								</td>
							</tr>
							<tr align="left" valign="top">
								<td>Caption:</td>
								<td><textarea rows="5" cols="50" id="audioCaption"></textarea>
								</td>
							</tr>
						</table>
						<button type="button" onclick="addAudioToList();">Save Audio</button><button type="button" onclick="stopEditingMedia();" class="mediaCancel">Cancel Edit</button>
					</div>
				</div>
				<p>Selected Media</p>
				<div id="selectedMedia">
					<ul id="images">

					</ul>
				</div>
			</td>
		</tr>
	</table>
	<button type="button" onclick="savePoi();">Save Point of Interest</button><button type="button" onclick="window.location.href='${pageContext.request.contextPath}/tours'">Cancel</button>
	<form id="postForm" action="${pageContext.request.contextPath}/tours/poi/edit" method="post" >
   		<input type="hidden" id="data" name="data" value="" />
   	</form>
</body>
</html>