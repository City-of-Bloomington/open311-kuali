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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<kme:page title="Ad" id="Classified-ad" backButton="true" homeButton="true" cssFilename="classifieds" backButtonURL="${pageContext.request.contextPath}/classifieds/myAds">
	<kme:content>

		<form:form method="post" action="${pageContext.request.contextPath}/classifieds/saveAd" commandName="ad" id="adform">
			<fieldset>
				<label for="campus">Campus:</label>
				<form:select path="campus" cssClass="ui-widget-content ui-corner-all" data-native-menu="false" items="${campuses}" itemLabel="name" itemValue="code" />
				<div class="error">
					<form:errors path="campus" />
				</div>
				<br /> <label for="title">Title:</label>
				<form:input path="title" />
				<form:hidden path="adId" />
				<form:hidden path="lockingNumber" />
				<div class="error">
					<form:errors path="title" />
				</div>
				<br /> <label for="contact">Contact:</label>
				<form:input path="contact" />
				<div class="error">
					<form:errors path="contact" />
				</div>
				<br /> <label for="categoryId">Category:</label>
				<form:select path="categoryId" cssClass="ui-widget-content ui-corner-all" data-native-menu="false">
					<form:options items="${ad.categories}" />
				</form:select>
				<div class="error">
					<form:errors path="categoryId" />
				</div>
				<br /> <label for="price">Price:</label>
				<form:input path="price" />
				<div class="error">
					<form:errors path="price" />
				</div>
				<br /> <label for="expires">Expire date:</label>
				<form:select path="expires" cssClass="ui-widget-content ui-corner-all" data-native-menu="false">
					<form:options items="${ad.expireDays}" />
				</form:select>
				<div class="error">
					<form:errors path="expires" />
				</div>
				<br /> <label for="description">Description:</label>
				<form:textarea path="description" />
				<div class="error">
					<form:errors path="description" />
				</div>
				<br />
			</fieldset>
			<input data-theme="a" name="save" type="submit" value="Save" />
		</form:form>
	</kme:content>
</kme:page>