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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="Campus Alerts" id="campusalerts" backButton="true" homeButton="true" cssFilename="alerts" backButtonURL="${pageContext.request.contextPath}/home">
	<kme:content>
	    <kme:listView id="alertlist" filter="false">
	        <script type="text/javascript">
				$('[data-role=page][id=campusalerts]').live('pagebeforeshow', function(event, ui) {
					$('#alertListTemplate').template('alertListTemplate');
					refreshTemplate('alerts', '#alertlist', 'alertListTemplate', '<li>No Alerts</li>');
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
