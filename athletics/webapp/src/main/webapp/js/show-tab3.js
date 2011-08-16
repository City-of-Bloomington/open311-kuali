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
var userScoreTimeout;
$(window).load(function() {
     $('.tabs-tab3').addClass('selected');
     $('.tabs-panel3').show();
     
     if (userScoreTimeout) {
 		clearTimeout(userScoreTimeout);
 	}
 	userScoreTimeout = setTimeout(function() {
 		refreshScores();
 	}, 30000);
 });

function refreshScores() {
	var dynamicDataResp = $.ajax({
		url : "athletics/autoUpdate",
		dataType : 'text',
		async : false,
		cache : false
	});
	if (dynamicDataResp.status == 200) {
		var pagehtml = '<div id="scoreUpdater"></div>';
		$('#matches').html(pagehtml);
		$("#scoreUpdater").html(dynamicDataResp.responseText).page();
	}

	if (userScoreTimeout) {
		clearTimeout(userScoreTimeout);
	}
	userScoreTimeout = setTimeout(function() {
		refreshScores();
	}, 30000);
}