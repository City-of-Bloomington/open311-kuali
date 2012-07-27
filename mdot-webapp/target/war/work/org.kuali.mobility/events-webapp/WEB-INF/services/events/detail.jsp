
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url var="back" value="/events/viewEvents">
	<c:param name="categoryId" value="${categoryId}" />
	<c:param name="campus" value="${campus}" />
</c:url>

<kme:page title="Event Detail" id="eventdetail" backButton="true" homeButton="true" backButtonURL="${back}">
	<kme:content>
	<kme:listView id="detail" dataTheme="c" dataDividerTheme="b" filter="false">
		<script type="text/javascript">
			$('[data-role=page][id=eventdetail]').live('pagebeforeshow', function(event, ui) {
			
				$('#detailTemplate').template('detailTemplate');
				refreshTemplate('viewEvent?eventId=${event}', '#detail', 'detailTemplate', '<li>No detail available</li>', 
					function() {$('#detail').listview('refresh'); 
						}); 
			});
		</script>
		<script id="detailTemplate" type="text/x-jquery-tmpl">
			<li><h3 class="wrap">\${event.title}</h3>
				<p class="wrap">\${event.dateBegin}<br />\${event.timeBegin} - \${event.timeEnd}</p></li>
			<li><h3 class="wrap">Location</h3>
				<p class="wrap">\${event.buildingName}<br />\${event.room}</p></li>
			<li><h3 class="wrap">Description</h3>
				<p class="wrap">\${event.description}</p></li>
			<li><h3 class="wrap">Website</h3>
				{{if event.url}}
					<p class="wrap"><a href="\${event.url}">\${event.url}</a></p>
				{{else}}
					<p class="wrap">N/A</p>
				{{/if}}</li>
			<li><h3 class="wrap">Sponsor</h3>
				{{each event.sponsors}}
					<p class="wrap">\${groupName}</p>
				{{/each}}</li>
		</script>
	</kme:listView>

	</kme:content>
</kme:page>
