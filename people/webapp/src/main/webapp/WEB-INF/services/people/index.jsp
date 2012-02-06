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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<kme:page title="Directory" id="directory" backButton="true" homeButton="true" cssFilename="directory">
	<kme:content>
		<form:form action="${pageContext.request.contextPath}/people" method="post" commandName="search" data-ajax="false">
			<fieldset>
            <label for="searchText" style="position:absolute; left:-9999px;">Search:</label>
            <form:input path="searchText" cssClass="text ui-widget-content ui-corner-all" placeholder="Search" />
			<form:errors path="searchText" />
			</fieldset>
		</form:form>
		<div id="searchresults">

				<kme:listView id="peopleList" filter="false" dataTheme="c" dataInset="false">
		            <script type="text/javascript">
						$('[data-role=page][id=directory]').live('pagebeforeshow', function(event, ui) {
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
		        </kme:listView>


		
		</div>
	</kme:content>
</kme:page>
