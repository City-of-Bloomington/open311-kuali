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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Course Details" id="coursedetails" homeButton="true" backButton="true" cssFilename="coursedescriptions">
	<kme:content>
		<kme:listView id="menulist" dataTheme="c" dataDividerTheme="b" filter="false">
			<script type="text/javascript">
				$('[data-role=page][id=coursedetails]').live('pagebeforeshow', function(event, ui) {
					$('#courseTemplate').template('courseTemplate');
					refreshTemplate('${pageContext.request.contextPath}/courseDescriptions/courseDetails?number=${number}&id=${id}', '#menulist', 'courseTemplate', '<li>No course informations at this time.</li>', function() {$('#menulist').listview('refresh');});
				});
			</script>
			<script id="courseTemplate" type="text/x-jquery-tmpl">
				<li data-role="list-divider">\${fullTitle}</li>
	      		<li><p class="wrap">\${description}</p>
				</li>
			</script>			
		</kme:listView>
	</kme:content>
</kme:page>