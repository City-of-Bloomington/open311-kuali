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

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme"  uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Search Results" id="people" backButton="true" homeButton="true" appcacheFilename="iumobile.appcache">
	<kme:content>
		<%--<c:choose>
			<c:when test="${not empty people}">--%>
				<kme:listView id="peopleList" filter="false" dataTheme="c" dataInset="false">
		            <script type="text/javascript">
						$('[data-role=page][id=people]').live('pagebeforeshow', function(event, ui) {
							$('#peopleTemplate').template('peopleTemplate');
							refreshTemplate('people', '#peopleList', 'peopleTemplate', '<li>No people found.</li>', function() {$('#peopleList').listview('refresh');});
						});
					</script>
					<script id="peopleTemplate" type="text/x-jquery-tmpl">
						<li>
        					<a href="${pageContext.request.contextPath}/people/\${hashedUserName}">
								<h3>\${lastName}, \${firstName}</h3>
								<p><strong>Location:</strong>
								{{each(i,location) locations}}
									\${location}{{if i+1 < locations.length}}, {{/if}}
								{{/each}}
								</p>
								<p><strong>Affiliation:</strong>
								{{each(i,affiliation) affiliations}}
									\${affiliation}{{if i+1 < affiliations.length}}, {{/if}}
								{{/each}}
								</p>				    		  	
				    		 </a>
      					</li>
					</script>
		            
		            <%--
		            <c:forEach items="${people}" var="person" varStatus="status">
		                <kme:listItem>
		                	<c:url value="/people/${person.hashedUserName}" var="url">
		                		<%--<c:param name="lName" value="${search.lastName}" />
		                		<c:param name="fName" value="${search.firstName}" />
		                		<c:param name="uName" value="${search.userName}" />
		                		<c:param name="exact" value="${search.exactness}" />
		                		<c:param name="status" value="${search.status}" />
		                		<c:param name="location" value="${search.location}" /> --%>
		                	<%--</c:url>
							<a href="${url}">
								<h3><c:out value="${person.lastName}" />, <c:out value="${person.firstName}" /></h3>
								<p><strong>Location:</strong>
						       		<c:forEach items="${person.locations}" var="location" varStatus="status">
						       			<c:out value="${location}" /><c:if test="${not status.last}">, </c:if>
						       		</c:forEach>
								</p>
								<p><strong>Affiliation:</strong>
						       		<c:forEach items="${person.affiliations}" var="affiliation" varStatus="status">
						       			<c:out value="${affiliation}" /><c:if test="${not status.last}">, </c:if>
						       		</c:forEach>
								</p>
							</a>
		                </kme:listItem>
		            </c:forEach>--%>
		        </kme:listView>
			<%--</c:when>
			<c:otherwise>
				<p>No people found.</p>
			</c:otherwise>
		</c:choose>--%>
	</kme:content>
</kme:page>
