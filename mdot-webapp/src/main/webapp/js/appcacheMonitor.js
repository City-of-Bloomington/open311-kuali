/*
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
*/

var appStatus = $("#applicationStatus");
var appEvents = $("#applicationEvents");
var manualUpdate = $("#manualUpdate");
var cacheProgress = $("#cacheProgress");

// Short-hand for our appcache object.
var appCache = window.applicationCache;

// Create a cache properties object to help us keep track of the progress of the caching.
var cacheProperties = {
    filesDownloaded: 0,
    totalFiles: 0
};

// Get the total number of files in the cache manifest - manually parse the manifest file.
function getTotalFiles() {
    // Init.
    cacheProperties.filesDownloaded = 0;
    cacheProperties.totalFiles = 0;

    // Get the cache manifest file, hard-coded link.
    $.ajax({
        type: "get",
        url: "./iumobile.appcache",
        dataType: "text",
        cache: false,
        success: function (content) {
            // Strip out the non-cache sections.
            content = content.replace(new RegExp("(NETWORK|FALLBACK):" + "((?!(NETWORK|FALLBACK|CACHE):)[\\w\\W]*)", "gi"), "");

            // Strip out all comments.
            content = content.replace(new RegExp("#[^\\r\\n]*(\\r\\n?|\\n)", "g"), "");

            // Strip out the cache manifest header and
            // trailing slashes.
            content = content.replace(new RegExp("CACHE MANIFEST\\s*|\\s*$", "g"), "");

            // Strip out extra line breaks and replace with
            // a hash sign that we can break on.
            content = content.replace(new RegExp("[\\r\\n]+", "g"), "#");

            // Get the total number of files.
            var totalFiles = content.split("#").length;

            // Store the total number of files. Here, we are
            // adding one for *THIS* file, which is cached
            // implicitly as it points to the manifest.
            //cacheProperties.totalFiles = (totalFiles + 1);
            
            // For some reason we are getting one extra file in the count. Removing the +1 from above.
            cacheProperties.totalFiles = (totalFiles + 0);
        }
    });
}

// Display the download progress.
function displayProgress() {
    // Increment the running total.
    cacheProperties.filesDownloaded++;

    // Check to see if we have a total number of files.
    if (cacheProperties.totalFiles) {

        // We have the total number of files, so output the running total as a fraction of the known total.
        //cacheProgress.text(
        //cacheProperties.filesDownloaded + " of " + cacheProperties.totalFiles + " files downloaded.");
        var percentDownloaded = 0;

        percentDownloaded = (cacheProperties.filesDownloaded / cacheProperties.totalFiles) * 100;
        if (percentDownloaded > 100) { percentDownloaded = 100; }
        
        var progressBar = '<div id="appcacheProgressBar"><div id="appcacheProgressAmount" style="width:' + percentDownloaded.toFixed() + '%">&nbsp;' + percentDownloaded.toFixed() + '%</div></div>';
        cacheProgress.html(progressBar);
        
        if (percentDownloaded > 95) {
        	$('div#cacheProgressModal').fadeOut();
            $('div#cacheProgressMessage').fadeOut();
        }
    } else {
        // If we don't know the total number of files, just output the running total.
        cacheProgress.text(cacheProperties.filesDownloaded + " files downloaded.");
    }
}

// Bind the manual update link.
manualUpdate.click(function (event) {
	// Prevent the default event.
	event.preventDefault();
	// Manually ask the cache to update.
	appCache.update();
});

// Bind to online/offline events.
$(window).bind("online offline", function (event) {
    // Update the online status.
    appStatus.text(navigator.onLine ? "Online" : "Offline");
});

// Set the initial status of the application.
appStatus.text(navigator.onLine ? "Online" : "Offline");

// List for checking events. This gets fired when the browser
// is checking for an udpated manifest file or is attempting
// to download it for the first time.
$(appCache).bind("checking", function (event) {
    // Do nothing.
});

// There is no update to the manifest file that has just been checked.
$(appCache).bind("noupdate", function (event) {
	// Do nothing.
});

// The browser is downloading the files defined in the cache manifest.
$(appCache).bind("downloading", function (event) {
    $('div#cacheProgressModal').fadeIn();
    $('div#cacheProgressMessage').css('top', '60px');
    // Get the total number of files in our manifest.
    getTotalFiles();
});

// Run for every file that is downloaded by the cache update.
$(appCache).bind("progress", function (event) {
    // Show the download progress.
    displayProgress();
});

// All cached files have been downloaded and are available to the application cache.
$(appCache).bind("cached", function (event) {
    $('div#cacheProgressModal').fadeOut();
    $('div#cacheProgressMessage').fadeOut();
});

// New cache files have been downloaded and are ready to replace the existing cache. The old cache will need to be swapped out.
$(appCache).bind("updateready", function (event) {
    // Swap out the old cache.
    appCache.swapCache();
});

// The cache manifest cannot be found.
$(appCache).bind("obsolete", function (event) {
	// Do nothing.
});

// An error occurred.
$(appCache).bind("error", function (event) {
	// Do nothing.
});
