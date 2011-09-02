var imageType = 'I';
var audioType = 'A';
var videoType = 'V';

var mediaList = [];
var mediaIdCounter = 0;

var editingMediaId;

$(function() {
	$('#mediaTabs').tabs();
	$("#videoTabs").tabs();
	$('.mediaCancel').hide();
});

function getNextMediaCount(){
	var value = mediaIdCounter;
	mediaIdCounter++;
	return value;
}

function addSelectedMediaToPoi(place) {
	place.media = new Array();
	$('#images li img').each(function (index) {
		var media = mediaList[this.id];
		place.media.push(media);
	});
}

function addImageToList() {
	var url = $('#imageUrl').val();
	var title = $('#imageTitle').val();
	var caption = $('#imageCaption').val();
	
	if (!url){
		alert('A url to an image is required.');
	} else {
		var media = new Object();
		var id;
		if (!editingMediaId){
			id = addMedia(url, title);
		} else {
			id = editingMediaId;
			$('img#' + id).attr('src', url);
		}
		
		media.type = imageType;
		media.url = url;
		media.title = title;
		media.caption = caption;
		mediaList[id] = media;
		stopEditingMedia();
	}
	
	return false;
}

function loadImageToList(url, title, caption) {
	var id = addMedia(url, title);
	
	var media = new Object();
	media.type = imageType;
	media.url = url;
	media.title = title;
	media.caption = caption;
	
	mediaList[id] = media;
}

function addVideoToList() {
	var title = $('#videoTitle').val();
	var caption = $('#videoCaption').val();
	var youTubeUrl;
	var oggUrl;
	var mp4Url;
	var webMUrl;
	
	oggUrl = $('#oggUrl').val();
	mp4Url = $('#mp4Url').val();
	webMUrl = $('#webMUrl').val();
	youTubeUrl = $('#youTubeUrl').val();
	
	if (!youTubeUrl && !(oggUrl || mp4Url || webMUrl)){
		alert('A url to a YouTube video, or to at least one file of type Ogg, Mp4, or WebM (all three is preferred) is required.');
	} else {
		var iconUrl = contextPath + '/images/video.png';
		var id;
		var media = new Object();
		if (!editingMediaId){
			id = addMedia(iconUrl, title);
		} else {
			id = editingMediaId;
			$('img#' + id).attr('src', iconUrl);
		}
		media.type = videoType;
		media.oggUrl = oggUrl;
		media.mp4Url = mp4Url;
		media.webMUrl = webMUrl;
		media.youTubeUrl = youTubeUrl;
		media.title = title;
		media.caption = caption;
		
		mediaList[id] = media;
		stopEditingMedia();
	}
	
	return false;
}

function loadVideoToList(oggUrl, mp4Url, webMUrl, youTubeUrl, title, caption) {
	var iconUrl = contextPath + '/images/video.png';
	
	var id = addMedia(iconUrl, title);
	
	var media = new Object();
	media.type = videoType;
	media.oggUrl = oggUrl;
	media.mp4Url = mp4Url;
	media.webMUrl = webMUrl;
	media.youTubeUrl = youTubeUrl;
	media.title = title;
	media.caption = caption;
	
	mediaList[id] = media;
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
	
	if (!(oggVorbisUrl || mp3Url || wavUrl)){
		alert('A url to at least one file of type OggVorbis, Mp3, or Wav (all three is preferred) is required.');
	} else {
		var iconUrl = contextPath + '/images/audio.png';
	
		var id;
		var media = new Object();
		if (!editingMediaId){
			id = addMedia(iconUrl, title);
		} else {
			id = editingMediaId;
			$('img#' + id).attr('src', iconUrl);
		}

		media.type = audioType;
		media.oggVorbisUrl = oggVorbisUrl;
		media.mp3Url = mp3Url;
		media.wavUrl = wavUrl;
		media.title = title;
		media.caption = caption;
		
		mediaList[id] = media;
		stopEditingMedia();
	}	
	return false;
}

function loadAudioToList(oggVorbisUrl, mp3Url, wavUrl, title, caption) {				
	var iconUrl = contextPath + '/images/audio.png';

	var id = addMedia(iconUrl, title);
	
	var media = new Object();
	media.type = audioType;
	media.oggVorbisUrl = oggVorbisUrl;
	media.mp3Url = mp3Url;
	media.wavUrl = wavUrl;
	media.title = title;
	media.caption = caption;
	
	mediaList[id] = media;
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
	img.bind('click', function (e) {
        editMedia(id);
    });
	
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

function editMedia(id) {
	var media = mediaList[id];
	editingMediaId = id;
	
	$('#editingMedia').text('Now editing: ' + media.title);
	$('#editingMedia').show();
	clearMediaForms();
	
	if (media.type == imageType){
		$('#imageUrl').val(media.url);
		$('#imageTitle').val(media.title);
		$('#imageCaption').val(media.caption);
		switchTabs(0);
	} else if (media.type == audioType){
		$('#audioTitle').val(media.title);
		$('#audioCaption').val(media.caption);
		$('#oggVorbisUrl').val(media.oggVorbisUrl);
		$('#mp3Url').val(media.mp3Url);
		$('#wavUrl').val(media.wavUrl);
		switchTabs(2);
	} else if (media.type == videoType){
		$('#videoTitle').val(media.title);
		$('#videoCaption').val(media.caption);
		$('#oggUrl').val(media.oggUrl);
		$('#mp4Url').val(media.mp4Url);
		$('#webMUrl').val(media.webMUrl);
		$('#youTubeUrl').val(media.youTubeUrl);
		switchTabs(1);
		if (media.youTubeUrl){
			$('#videoTabs').tabs('select', 1);
		} else {
			$('#videoTabs').tabs('select', 0);
		}
	}
	$('.mediaCancel').show();
}

function stopEditingMedia() {
	$('#editingMedia').text('');
	$('#editingMedia').hide();
	$('.mediaCancel').hide();
	clearMediaForms();
	editingMediaId = null;
}

function switchTabs(tab){
	$('#mediaTabs').tabs('select', tab);
}

function clearMediaForms() {
	$('#imageUrl').val('');
	$('#imageTitle').val('');
	$('#imageCaption').val('');
	$('#audioTitle').val('');
	$('#audioCaption').val('');
	$('#oggVorbisUrl').val('');
	$('#mp3Url').val('');
	$('#wavUrl').val('');
	$('#videoTitle').val('');
	$('#videoCaption').val('');
	$('#oggUrl').val('');
	$('#mp4Url').val('');
	$('#webMUrl').val('');
	$('#youTubeUrl').val('');
}

function clearSelectedMedia() {
	$('#images').html('');
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