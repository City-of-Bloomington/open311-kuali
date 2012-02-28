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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="alerts.title" var="title"/>

<kme:page title="${title}" id="campusalerts" backButton="true" homeButton="true" cssFilename="alerts">
	<kme:content>
	    <kme:listView id="alertlist" filter="false">
	        <script type="text/javascript">
				$('[data-role=page][id=campusalerts]').live('pagebeforeshow', function(event, ui) {
					$('#alertListTemplate').template('alertListTemplate');
					refreshTemplate('alerts', '#alertlist', 'alertListTemplate', '<li>No Alerts</li>', function() {$('#alertlist').listview('refresh');});
				});
			</script>
			<script id="alertListTemplate" type="text/x-jquery-tmpl">
				<li>
					<h3 class="wrap"><c:out value="\${campus} - \${title}"/></h3>
	            	<p class="wrap"><c:out value="\${mobileText}"/></p>
				</li>
			</script>	       	        
	        <%--
	        <c:forEach items="${alerts}" var="alert" varStatus="status">
	            <kme:listItem>
	            	<h3 class="wrap"><c:out value="${alert.campus} - ${alert.title}"/></h3>
	            	<p class="wrap"><c:out value="${alert.mobileText}"/></p>
	            </kme:listItem>
			</c:forEach>
			--%>
	    </kme:listView>
	</kme:content>
</kme:page>
