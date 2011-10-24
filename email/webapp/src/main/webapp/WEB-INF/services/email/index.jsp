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

<kme:page title="Email" id="email" backButton="true" homeButton="true"  appcacheFilename="iumobile.appcache">
	<kme:content>
    <p>Indiana University has partnered with Microsoft and Google to provide email and other online communications services for IU students.</p>
    <fieldset class="ui-grid-a">
      <div class="ui-block-a">
        <div style="text-align:center"><img src="${pageContext.request.contextPath}/images/eml-ms.png" width="96" height="96" alt="Imail"></div>
        <a style="text-decoration:none" href="https://imail.iu.edu/">
        <button type="submit" data-theme="c">Imail</button>
        </a>
      </div>
      <div class="ui-block-b">
        <div style="text-align:center"><img src="${pageContext.request.contextPath}/images/eml-google.png" width="96" height="96" alt="Umail"></div>
        <a style="text-decoration:none" href="https://umail.iu.edu/">
        <button type="submit" data-theme="c">Umail</button>
        </a> </div>
        
    </fieldset>	
    <a href="${pageContext.request.contextPath}/knowledgebase/bami" data-icon="info" data-theme="c" data-role="button">Learn More</a>
	</kme:content>
</kme:page>
