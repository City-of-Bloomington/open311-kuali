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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:message code="computerlabs.title" var="title"/>
<spring:message code="computerlabs.seats" var="seats"/>

<kme:page title="${title}" id="computerlabs" homeButton="true" backButton="true" cssFilename="computerlabs">
	<kme:content>
		<kme:listView id="computerlablist" dataTheme="c" dataDividerTheme="b"
			filter="false">

			<script type="text/javascript">
				$('[data-role=page][id=computerlabs]').live(
						'pagebeforeshow',
						function(event, ui) {
							$('#clListTemplate').template('clListTemplate');
							refreshTemplate('computerlabs?campus=${campus}',
									'#computerlablist', 'clListTemplate',
									'<li>No labs were found</li>', function() {
										$('#computerlablist').listview(
												'refresh');
									});
						});
			</script>
			<script id="clListTemplate" type="text/x-jquery-tmpl">
				<li data-role="list-divider" data-theme="b" data-icon="listview" >\${name}</li>
    			{{each labs}}
    				<li>
						<a href="${pageContext.request.contextPath}/maps?id=\${buildingCode}">
		    	   			<h3>\${lab}</h3><p>\${availability} ${seats}</p>
  						</a>
					</li>
				{{/each}}
		</script>

		</kme:listView>
	</kme:content>
</kme:page>
