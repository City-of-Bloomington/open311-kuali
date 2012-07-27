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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="localeCode" value="${pageContext.response.locale}" />

<spring:message code="conference.title" var="title"/>
<kme:page title="${title}" id="conference" backButton="true" homeButton="true">
	<kme:content>
		<kme:listView id="menulist" dataTheme="c" dataDividerTheme="b" filter="false">
			<script type="text/javascript">
				$('[data-role=page][id=conference]').live('pagebeforeshow', function(event, ui) {
					$('#menuListTemplate').template('menuListTemplate');
					refreshTemplate('${pageContext.request.contextPath}/conference?lang=${localeCode}', '#menulist', 'menuListTemplate', '<li>No Menus</li>', function() {$('#menulist').listview('refresh');});
				});
			</script>
			<script id="menuListTemplate" type="text/x-jquery-tmpl">
      			<li>
					<a href="${pageContext.request.contextPath}/\${linkURL}">
        				<h3 class="wrap">\${title}</h3>
						<p class="wrap">\${description}</p>
					</a>
      			</li>
			</script>	
		</kme:listView>
	<%-- accesses a json file for the menu items
		<kme:listView>
			<c:forEach items="${menuItems}" var="menuItem" varStatus="status">
				<kme:listItem>
					<a href="${menuItem.linkURL}">
						<h3 class="wrap">
							${menuItem.title}
						</h3>
						<p>${menuItem.description}</p>
					</a>
				</kme:listItem>
			</c:forEach>
		</kme:listView>
	--%>
	<%--  hard coded
		<kme:listView>
			<kme:listItem>
	    		<a href="conference/welcome">
	    			<h3 class="wrap">
	    				<spring:message code="conference.welcome"/>
	    			</h3>
	    			<p class="wrap"><spring:message code="conference.welcome.sub"/></p>
	    		</a>
	    	</kme:listItem>
			<kme:listItem>
	    		<a href="conference/sessions?date=${today}">
	    			<h3 class="wrap">
	    				<spring:message code="conference.schedule"/>
	    			</h3>
	    			<p class="wrap"><spring:message code="conference.schedule.sub"/></p>
	    		</a>
	    	</kme:listItem>
	    	<kme:listItem>
	    		<a href="conference/featuredSpeakers">
	    			<h3 class="wrap">
	    				<spring:message code="conference.featuredspeakers"/>
	    			</h3>
	    			<p class="wrap"><spring:message code="conference.featuredspeakers.sub"/></p>
	    		</a>
	    	</kme:listItem>
	    	<kme:listItem>
	    		<a href="conference/attendeeGroups">
	    			<h3 class="wrap">
	    				<spring:message code="conference.attendeelist"/>
	    			</h3>
	    			<p class="wrap"><spring:message code="conference.attendeelist.sub"/></p>
	    		</a>
	    	</kme:listItem>
	    	<kme:listItem>
	    		<a href="http://localhost:9999/mdot/testdata/imumap10.jpg">
	    			<h3 class="wrap">
	    				<spring:message code="conference.IMUmap"/>
	    			</h3>
	    			<p class="wrap"><spring:message code="conference.IMUmap.sub"/></p>
	    		</a>
	    	</kme:listItem>
		</kme:listView>
		--%>
	</kme:content>
</kme:page>
