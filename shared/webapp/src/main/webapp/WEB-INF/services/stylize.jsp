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

<spring:message code="stylize.title" var="title"/>

<kme:page title="${title}" id="stylize" backButton="true" homeButton="true" jsFilename="stylize">
	<kme:content>
		<style type="text/css">
			input.ui-input-text {
			    width: 100%;
			    margin-bottom:15px;
			}
		</style>
		<form action = "${pageContext.request.contextPath}/stylize" method="post">
			<label for="pageBackgroundColor"><spring:message code="stylize.pagebackgroundcolor"/>: </label><br /><input type="text" name="pageBackgroundColor" value="${styles['page.background.color']}" /><br />
			
			<label for="headerLogoUrl"><spring:message code="stylize.headerlogourl"/>: </label><br /><input type="text" name="headerLogoUrl" value="${styles['header.logo.url']}" /><br />
			<label for="headerTextColor"><spring:message code="stylize.headertextcolor"/>: </label><br /><input type="text" name="headerTextColor" value="${styles['header.text.color']}" /><br />
			<label for="headerTextShadow"><spring:message code="stylize.headertextshadow"/>: </label><br /><input type="text" name="headerTextShadow" value="${styles['header.text.shadow']}" /><br />
			<label for="headerGradientStartColor"><spring:message code="stylize.headergradientstartcolor"/>: </label><br /><input type="text" name="headerGradientStartColor" value="${styles['header.gradient.start.color']}" /><br />
			<label for="headerGradientStopColor"><spring:message code="stylize.headergradientstopcolor"/>: </label><br /><input type="text" name="headerGradientStopColor" value="${styles['header.gradient.stop.color']}" /><br />
			
			<label for="buttonATextColor"><spring:message code="stylize.buttonatextcolor"/>: </label><br /><input type="text" name="buttonATextColor" value="${styles['buttonA.text.color']}" /><br />
			<label for="buttonAGradientStartColor"><spring:message code="stylize.buttonagradientstartcolor"/>: </label><br /><input type="text" name="buttonAGradientStartColor" value="${styles['buttonA.gradient.start.color']}" /><br />
			<label for="buttonAGradientStopColor"><spring:message code="stylize.buttonagradientstopcolor"/>: </label><br /><input type="text" name="buttonAGradientStopColor" value="${styles['buttonA.gradient.stop.color']}" /><br />
			<label for="buttonAHoverGradientStartColor"><spring:message code="stylize.buttonahovergradientstartcolor"/>: </label><br /><input type="text" name="buttonAHoverGradientStartColor" value="${styles['buttonA.hover.gradient.start.color']}" /><br />
			<label for="buttonAHoverGradientStopColor"><spring:message code="stylize.buttonahovergradientstopcolor"/>: </label><br /><input type="text" name="buttonAHoverGradientStopColor" value="${styles['buttonA.hover.gradient.stop.color']}" /><br />
			<label for="buttonADownGradientStartColor"><spring:message code="stylize.buttonadowngradientstartcolor"/>: </label><br /><input type="text" name="buttonADownGradientStartColor" value="${styles['buttonA.down.gradient.start.color']}" /><br />
			<label for="buttonADownGradientStopColor"><spring:message code="stylize.buttonadowngradientstopcolor"/>: </label><br /><input type="text" name="buttonADownGradientStopColor" value="${styles['buttonA.down.gradient.stop.color']}" /><br />
			
			<label for="buttonCTextColor"><spring:message code="stylize.buttonctextcolor"/>: </label><br /><input type="text" name="buttonCTextColor" value="${styles['buttonC.text.color']}" /><br />
			<label for="buttonCGradientStartColor"><spring:message code="stylize.buttoncgradientstartcolor"/>: </label><br /><input type="text" name="buttonCGradientStartColor" value="${styles['buttonC.gradient.start.color']}" /><br />
			<label for="buttonCGradientStopColor"><spring:message code="stylize.buttoncgradientstopcolor"/>: </label><br /><input type="text" name="buttonCGradientStopColor" value="${styles['buttonC.gradient.stop.color']}" /><br />
			<label for="buttonCHoverGradientStartColor"><spring:message code="stylize.buttonchovergradientstartcolor"/>: </label><br /><input type="text" name="buttonCHoverGradientStartColor" value="${styles['buttonC.hover.gradient.start.color']}" /><br />
			<label for="buttonCHoverGradientStopColor"><spring:message code="stylize.buttonchovergradientstopcolor"/>: </label><br /><input type="text" name="buttonCHoverGradientStopColor" value="${styles['buttonC.hover.gradient.stop.color']}" /><br />
			<label for="buttonCDownGradientStartColor"><spring:message code="stylize.buttoncdowngradientstartcolor"/>: </label><br /><input type="text" name="buttonCDownGradientStartColor" value="${styles['buttonC.down.gradient.start.color']}" /><br />
			<label for="buttonCDownGradientStopColor"><spring:message code="stylize.buttoncdowngradientstopcolor"/>: </label><br /><input type="text" name="buttonCDownGradientStopColor" value="${styles['buttonC.down.gradient.stop.color']}" /><br />
               
			<div data-inline="true">
                <div class="ui-grid-b">
                    <div class="ui-block-a">
                    	<a data-theme="c"  href="${pageContext.request.contextPath}/home" data-role="button"><spring:message code="stylize.cancel"/></a>
                    </div>
                    <div class="ui-block-b">
                    	<a data-theme="c"  href="#" data-role="button" id="clearButton"><spring:message code="stylize.clear"/></a>
                    </div>
                    <div class="ui-block-c">
                    	<input data-theme="a" class="submit" type="submit" id="saveButton" value="<spring:message code="stylize.apply"/>" />
                    </div>
                </div>
            </div>
		</form>
	</kme:content>
</kme:page>


