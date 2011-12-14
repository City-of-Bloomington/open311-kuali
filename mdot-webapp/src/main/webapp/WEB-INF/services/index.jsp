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

<kme:page title="IU Mobile" id="home" cssFilename="home" backButton="false" homeButton="false" preferencesButton="true" preferencesButtonURL="campus?toolName=home" loginButton="true" loginButtonURL="home?login=yes" appcacheFilename="iumobile.appcache">
	<kme:content>
		<div id="cacheProgressModal">
			<div id="cacheProgressMessage">
			<h3>Downloading Updates.<br/>Please wait.</h3>
			<p><span id="cacheProgress">&nbsp;</span></p>
			<input id="reloadButton" type="button" value="Reload" onClick="window.location.reload()">
			</div>
	 	</div>
	 	<script type="text/javascript">
	 		function setHomeScreenTitle() {
	 			
	 			var campusSelection = readCookie('campusSelection');
	 			if (campusSelection) {
		 			var titleMap = jQuery.parseJSON('${homeScreenMap}');
		 			var title = titleMap[campusSelection];
		 			if (title) {
		 				setPageTitle(title);
		 			}
	 			}
	 		}
	 		
	 		function readCookie(name) {
	 			var nameEQ = name + "=";
	 			var ca = document.cookie.split(';');
	 			for(var i=0;i < ca.length;i++) {
	 				var c = ca[i];
	 				while (c.charAt(0)==' ') c = c.substring(1,c.length);
	 				if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	 			}
	 			return null;
	 		}
	 		
	 		function showHideLoginButton(loggedIn) {
	 			if (loggedIn){
	 				$('.loginbutton').hide();
	 				$('.logoutbutton').show();
	 			} else {
	 				$('.logoutbutton').hide();
	 				$('.loginbutton').show();
	 			}
	 			return '';
	 		}
	 	</script>
	 	
	 	<script id="toolTemplate" type="text/x-jquery-tmpl">
      		\${showHideLoginButton(\$data.loggedIn)}

			{{each \$data.tools}}
      			<li data-icon="false" data-theme="c">
        			<a href="\${url}" style="background-image: url('\${iconUrl}');">
			    	  	<h3>\${title}</h3>
			    	  	<p class="wrap">\${description}</p>
						{{if badgeCount}}
							<span class="countBadge ui-btn-up-c ui-btn-corner-all">\${badgeCount}</span>
						{{/if}}
			    	 </a>
      			</li>
			{{/each}}	

				<%--<li data-icon="false" data-theme="c">
       			<a href="\${url}" style="background-image: url('\${iconUrl}');">
			      	<h3>\${title}</h3>
			      	<p class="wrap">\${description}</p>
					{{if badgeCount}}
						<span class="countBadge ui-btn-up-c ui-btn-corner-all">\${badgeCount}</span>
					{{/if}}
			     </a>
     			</li>--%>
		</script>
 	
	    <kme:listView id="homeserviceslist" filter="false">
	        
			
	        
	        <%-- <c:forEach items="${tools}" var="homeTool" varStatus="status">	            
	            <kme:listItem hideDataIcon="true">
	            	<a href="${homeTool.tool.url}" style="background-image: url('${homeTool.tool.iconUrl}');">
			      		<h3>${homeTool.tool.title}</h3>
			      		<p class="wrap">${homeTool.tool.description}</p>
			      		<c:if test="${not empty homeTool.tool.badgeCount}"> 
			      			<span class="countBadge ui-btn-up-c ui-btn-corner-all">${homeTool.tool.badgeCount}</span>
			      		</c:if>
			      	</a>
	            </kme:listItem>
			</c:forEach>--%>
		</kme:listView>
		<kme:listView cssClass="privacyStatement" filter="false">	
			<kme:listItem dataIcon="false"><a href="privacy">Privacy Notice</a></kme:listItem>
	    </kme:listView>
	</kme:content>
	
	<script type="text/javascript">
		$('[data-role=page][id=home]').live('pagebeforeshow', function(event, ui) {
			$('#toolTemplate').template('toolTemplate');
			refreshTemplate('home', '#homeserviceslist', 'toolTemplate', '<li>No Items</li>', function() {$('#homeserviceslist').listview('refresh');});
			setHomeScreenTitle();
		});
	</script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/appcacheMonitor.js"></script>
	
</kme:page>

