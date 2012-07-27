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

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:contains(header['User-Agent'],'iPhone') || fn:contains(header['User-Agent'],'iPad') || fn:contains(header['User-Agent'],'iPod')}">
	<c:set var="platform" value="iOS"/>
</c:if>
<c:if test="${fn:contains(header['User-Agent'],'Android')}">
	<c:set var="platform" value="Android"/>
</c:if>
<c:set var="phonegap" value="${cookie.phonegap.value}"/>

<kme:page title="Search Results" id="people" backButton="true" homeButton="true" cssFilename="people" appcacheFilename="iumobile.appcache" onBodyLoad="init()" platform="${platform}" phonegap="${phonegap}">
<script type="text/javascript" charset="utf-8">

// Wait for PhoneGap to load
//
function init() {
	// alert($('#savecontact').html());
	document.addEventListener("deviceready", onDeviceReady, false);
}

// PhoneGap is ready
//
function onDeviceReady() {

}


var IsiPhone 		= navigator.userAgent.indexOf("iPhone") != -1 ;
var IsiPod 			= navigator.userAgent.indexOf("iPod") != -1 ;
var IsiPad 			= navigator.userAgent.indexOf("iPad") != -1 ;
var IsiOS			= IsiPhone || IsiPod || IsiPad;
var IsAndroid 		= navigator.userAgent.indexOf("Android") != -1 ;

var fname 		= "";
var lname 		= "";
var contactID 	= "";
var newContact 	= "";


// Passing the arguments rather than scanning the DOM. It would require re-write of the template below. 
function saveContact(firstname, lastname, dept, email, phone){
	
	fname = firstname;
	lname = lastname;
	
	
	/* Android/iOS Block */
	if(IsAndroid || IsiOS){ 
		/*
		if(existing contact)
			add missing info	
		else
			add new contact
		*/


		
		// find all contacts with 'Bob' in any name field
		var options 		= new ContactFindOptions();
		options.filter 		= lastname;
		options.multiple 	= true;
		var fields 			= ["displayName", "name", "emails"];
		navigator.contacts.find(fields, onFindSuccess, onFindError, options);
		
		var myContact = navigator.contacts.create();
		myContact.displayName = firstname + " " + lastname;	

	    var name = new ContactName();
    	name.givenName = firstname;
    	name.familyName = lastname;
    	myContact.name = name; 
    
    	var phoneNumbers = [];
    	phoneNumbers[0] = new ContactField('IU', phone, true);
    	myContact.phoneNumbers = phoneNumbers;

    	var emails = [];
    	emails[0] = new ContactField('IU', email, true);
    	myContact.emails = emails;
    
    	// Saves the Contact to Android. 

    	newContact = myContact;
    	
/*
    	if(confirm("Add New Contact")){		
	    	myContact.save(onSaveSuccess, onSaveError);
    	}else{
    		
    	}
*/
	}
    /* End Android/iOS Block */

    
	/* Blackberry Block */
	// TODO
	/* End Black Berry Block */
}

function onFindSuccess(contacts) {

	c = 0;
	for(i = 0; i < contacts.length; i++){
		if(contacts[i].name.formatted.indexOf(fname) != -1){
			c++;
			contactID = contacts[i].id;
		}
	}
	
	if(contactID != ""){
		alert('This Contact might already exist in your address book. ID is ' + contactID);
	}else{
		if(confirm('Add as New Contact.')){
			newContact.save(onSaveSuccess, onSaveError);
		}
	}
	
};


function onFindError(contactError) {
    alert('onError!');
};

function onSaveSuccess(contact) {
	//Only Displays on Android.
    alert("Saved Contact");
};

function onSaveError(contactError) {
    alert("Save Error = " + contactError.code);
};


</script>
	<kme:content>
		<kme:listView id="detailsGroupList" filter="false" dataTheme="c" dataInset="false">
			<script type="text/javascript">		
				var nativeCookie 	= $.cookie('native');
				var pgCookie 		= $.cookie('phonegap')
				var canSaveContact = 0;
				if(nativeCookie == "yes" && pgCookie != ''){
					canSaveContact = 1;
				}
				$('[data-role=page][id=people]').live('pagebeforeshow', function(event, ui) {
					$('#detailsTemplate').template('detailsTemplate');
					refreshTemplate('${pageContext.request.contextPath}/people/groupdetails', '#detailsGroupList', 'detailsTemplate', '<li>The Group was not found.</li>', function() {$('#detailsGroupList').listview('refresh');});				
				});
			</script>
			<script id="detailsTemplate" type="text/x-jquery-tmpl">			
			  {{if group}}
					{{if group.displayName}}
						<li data-role="list-divider" ><h3 class="wrap">Group Name:
						      	<span style="font-weight:normal;">\${group.displayName}</span></h3></li>
					{{/if}}
					
					{{if group.descriptions && group.descriptions.length > 0}}
							<li><h3 class="wrap">Group Description:
						      	<span style="font-weight:normal;">
									{{each(i,description) group.descriptions}}
										\${description}{{if i+1 < group.descriptions.length}}, {{/if}}
									{{/each}}
								</span>
							</h3></li>
						{{/if}}
					
					{{if group.email}}
						<li class="link-email">
							<a href="mailto:\${group.email}" >\${group.email}</a>
						</li>
				   {{/if}}
				   
				  {{if group.telephoneNumber && group.telephoneNumber.length > 0}}
					<li class="link-phone"><a href="tel:\${group.telephoneNumber}">\${group.telephoneNumber}</a></li>		
				 {{/if}}
				 {{if group.facsimileTelephoneNumber && group.facsimileTelephoneNumber.length > 0}}
					<li>
						<span style="font-weight:normal;">\${group.facsimileTelephoneNumber}</span>
					</li>
				 {{/if}}
				 
			{{/if}}	
			</script>
		</kme:listView>	
		

	</kme:content>
</kme:page>
