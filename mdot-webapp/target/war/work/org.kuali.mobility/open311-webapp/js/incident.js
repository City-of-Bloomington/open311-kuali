// Called when a photo is successfully retrieved
function onPhotoURISuccess(imageURI) {
	alert("something somthing " +  imageURI);
	// View the Photo's URI in the  console. Can use this to grap the photo with a form for 
	console.log(imageURI);

	// Get image handle
	var incidentImage = document.getElementById('incidentImage');

	// Unhide image elements
	incidentImage.style.display = 'block';

	// Show the captured photo
	// The inline CSS rules are used to resize the image
	incidentImage.src = imageURI;
}



// A button will call this function
function getPhoto(source) {
	alert("Cool!");
	// Retrieve image file location from specified source
	// The quality, targetWidth, targetHeight taken together probably affect file size. 
	navigator.camera.getPicture(onPhotoURISuccess, onFail, {quality: 50, 
															destinationType: navigator.camera.DestinationType.FILE_URI,
															sourceType: source, 
															targetWidth: 320,
															targetHeight: 320});
}

// Called if something bad happens or fail silently...
function onFail(message) {
	alert('Failed because: ' + message);
}

