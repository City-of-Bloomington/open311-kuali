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

<spring:message code="dining.title" var="title"/>

<kme:page title="${title}" id="dining" institutionCss="michigan" backButton="true" homeButton="true" cssFilename="dining">
	<kme:content>
		<kme:listView id="menulist" dataTheme="c" dataDividerTheme="b" filter="false">
			<script type="text/javascript">
				$('[data-role=page][id=dining]').live('pagebeforeshow', function(event, ui) {
					$('#menuListTemplate').template('menuListTemplate');
					refreshTemplate('dining', '#menulist', 'menuListTemplate', '<li>No Menus</li>', function() {$('#menulist').listview('refresh');});
				});
			</script>
			<script id="menuListTemplate" type="text/x-jquery-tmpl">
				<li data-role="list-divider">\${dateFormatted}</li>
				{{each(i,item) items}}
      				<li>
        				<h3 class="wrap">\${name}</h3>
						<p class="wrap">\${priceFormatted}</p>
      				</li>
				{{/each}}
			</script>			
			<%--
			<c:choose>
				<c:when test="${not empty menus}">
					<c:forEach items="${menus}" var="menu" varStatus="status">
						<kme:listItem dataTheme="b" dataRole="list-divider">${menu.dateFormatted}</kme:listItem>
						<c:forEach items="${menu.items}" var="item" varStatus="status">
							<kme:listItem>
									<h3 class="wrap">${item.name}</h3>
									<p class="wrap">${item.priceFormatted}</p>
							</kme:listItem>
						</c:forEach>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<kme:listItem>
						No menus
					</kme:listItem>
				</c:otherwise>
			</c:choose>
			--%>
		</kme:listView>
	</kme:content>
</kme:page>
