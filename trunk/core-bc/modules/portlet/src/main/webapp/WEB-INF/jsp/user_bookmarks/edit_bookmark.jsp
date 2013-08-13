<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />	

<div class="vgr-boxed-content rp-user-bookmarks">
	<div class="hd">
		<span>Mina bokm&auml;rken</span>
	</div>
	<div class="bd">
		
		<portlet:renderURL var="bookmarkListViewUrl">
			<portlet:param name="showView" value="" />
		</portlet:renderURL>

		<div class="bookmark-controls-main clearfix">
			<a class="rp-link-button" href="${bookmarkListViewUrl}">Tillbaka till visa bokm&auml;rken</a>
		</div>
		
		<portlet:actionURL name="editBookmark" var="editBookmarkUrl">
			<portlet:param name="action" value="editBookmark" />
			<portlet:param name="bookmarkId" value="${bookmark.id}" />
		</portlet:actionURL>
		
		<aui:form action="${editBookmarkUrl}" cssClass="edit-bookmark-form clearfix" method="POST">
		
		
			<aui:input type="text" name="title" label="Titel" helpMessage="Skriv in en titel f&ouml;r ditt bokm&auml;rke." value="${bookmark.title}" />
			
			<aui:input type="text" name="url" label="URL" helpMessage="Skriv in URL:en till ditt bokm&auml;rke." value="${bookmark.url}" />
			
			<aui:input type="textarea" name="description" label="Beskrivning" helpMessage="Skriv in en beskrivning till ditt bokm&auml;rke." value="${bookmark.description}" />
			
			<aui:button-row>
				<aui:button type="submit" value="Spara bokm&auml;rke" cssClass="rp-button" />
			</aui:button-row>
		
		</aui:form>
		
	</div>
</div>