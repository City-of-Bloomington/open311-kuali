function findFoursquareVenues(latlng){
	var url = contextPath + "/maps/foursquare?lat="+ latlng.lat() + "&lng=" + latlng.lng();
	var jqxhr = $.ajax({
	  url: url,
	  dataType: 'json',
	  success: function (data) {parseVenues(data);},
	  crossDomain: true
	});
	jqxhr.error(function() { $("#venues").html("There was an error contacting the Foursquare service."); })
}

function parseVenues(data){
	var venuesHtml = "";
	venues = new Array();
	for (var i=0; i<data.response.venues.length; i++){
		var venue = data.response.venues[i];
		venuesHtml = venuesHtml + "<div onClick='javascript:selectAVenue(" + i + ")' class='venue " + (i%2==0? "even" : "odd") + (i+1==data.response.venues.length? " last" : "") + "'>";
		venuesHtml = venuesHtml + "<div class='image'>";
		if (venue.categories.length > 0){
			var category = null;
			for (var c=0; c<venue.categories.length; c++){
				var cat = venue.categories[c];
				if (cat.primary){
					category = cat;
					break;
				}
			}
			venuesHtml = venuesHtml + "<img src='" + category.icon + "'></img>";
		}
		venuesHtml = venuesHtml + "</div>"; //close image
		
		venuesHtml = venuesHtml + "<div class='info'>";
		venuesHtml = venuesHtml + "<div class='name'>" + venue.name + "</div>";
		
		venuesHtml = venuesHtml + "<div class='address'>";
		if (venue.location.address){
			venuesHtml = venuesHtml + "<span>" + venue.location.address + "</span><br/>";
		}
		if (venue.location.city){
			venuesHtml = venuesHtml + "<span>" + venue.location.city + "</span>";
		}
		if (venue.location.state && venue.location.city){
			venuesHtml = venuesHtml + ",&nbsp";
		}
		if (venue.location.state){
			venuesHtml = venuesHtml + "<span>" + venue.location.state + "</span>";
		}
		if ((venue.location.city || venue.location.state) && venue.location.postalCode){
			venuesHtml = venuesHtml + "&nbsp";
		}
		if (venue.location.postalCode){
			venuesHtml = venuesHtml + "<span>" + venue.location.postalCode + "</span>";
		}
		venuesHtml = venuesHtml + "</div>"; //close address
		
		venuesHtml = venuesHtml + "</div>"; //close info
		
		venuesHtml = venuesHtml + "</div>"; //close venue
		venues[i] = parseVenue(venue);
	}
	$("#venues").html(venuesHtml);
}

function parseVenue(venue){
	var v = new Object();
	v.name = venue.name;
	v.location = new google.maps.LatLng(venue.location.lat,venue.location.lng);
	v.id = venue.id;
	v.type= venueType;
	v.url = venue.url;
	return v;
}

function selectAVenue(index){
	var venue = venues[index];
	setTempMarker(venue);
	map.panTo(venue.location);
	selectedPlace = venue;
}