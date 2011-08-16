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

<kme:page title="Verify" id="mobCasAck" backButton="true" homeButton="true" backButtonURL="home">
	<kme:content>
		<h2>Mobile CAS (Central Authentication System)</h2>
		<ul>
			<li>Mobile CAS allows a 24 hour session.</li>
			<li>A PIN should be used to secure your phone.</li>
			<li>If you lose your phone you should change your passphrase to invalidate your Mobile CAS Session.</li>
		</ul>		    
		
		<a href="https://m.iu.edu/miu-prd/Base.do?s=KnowledgeBaseService&mtc=detail&query=umail&key=ayvj" data-icon="info" data-role="button">Learn More</a>
		
		<form:form action="${pageContext.request.contextPath}/mobileCasAck" commandName="command" data-ajax="false" method="post">
	      <div data-inline="true">
	        <div class="ui-grid-a">
	          <div class="ui-block-a"><a href="${pageContext.request.contextPath}/home" data-role="button" data-theme="c" >Cancel</a></div>
	          <div class="ui-block-b"><input class="submit" type="submit"  value="I Agree" data-theme="a"  /></div>
	        </div>
	      </div>
	    </form:form>
	</kme:content>
</kme:page>

