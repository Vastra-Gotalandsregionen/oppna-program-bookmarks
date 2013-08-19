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
		
		<portlet:renderURL var="showAddBookmarkUrl">
			<portlet:param name="showView" value="showEditBookmark" />
		</portlet:renderURL>
		
		<div class="rp-bookmark-hd clearfix">
			<h2>Startsida intra</h2>
		</div>
		<c:choose>
			<c:when test="${not empty vgrBookmarks}">
				<ul class="rp-bookmark-list">
					<c:forEach var="bookmark" items="${vgrBookmarks}" varStatus="status">
					
						<c:set var="listItemCssClass" scope="page" value="" />
			
						<c:if test="${(status.index)%2 ne 0}">
							<c:set var="listItemCssClass" scope="page" value="rp-bookmark-item-odd" />
						</c:if>
						
						<c:if test="${status.last}">
							<c:set var="listItemCssClass" value="${listItemCssClass} rp-bookmark-item-last" scope="page" />
						</c:if>
					
						<li class="rp-bookmark-item ${listItemCssClass} rp-bookmark-item-minimized">
							<div class="rp-bookmark-title-wrap clearfix">
								<a class="rp-bookmark-title-toggle" href="#">Toggle </a>
								<a class="rp-bookmark-title" href="${bookmark.url}" target="_BLANK">${bookmark.title}</a>
							</div>
							<div class="rp-bookmark-more-info">
								<p>${bookmark.url}</p>
								<p>${bookmark.description}</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>		
		
		<div class="rp-bookmark-hd clearfix">
			<h2>Egna bokm&auml;rken</h2>
			<a class="rp-link-button rp-link-button-alt" href="${showAddBookmarkUrl}">L&auml;gg till nytt bokm&auml;rke</a>
		</div>
			
		<c:choose>
			<c:when test="${not empty customBookmarks}">
				<ul class="rp-bookmark-list">
					<c:forEach var="bookmark" items="${customBookmarks}" varStatus="status">
					
						<c:set var="listItemCssClass" scope="page" value="" />
			
						<c:if test="${(status.index)%2 ne 0}">
							<c:set var="listItemCssClass" scope="page" value="rp-bookmark-item-odd" />
						</c:if>
						
						<c:if test="${status.last}">
							<c:set var="listItemCssClass" value="${listItemCssClass} rp-bookmark-item-last" scope="page" />
						</c:if>
					
						<li class="rp-bookmark-item ${listItemCssClass} rp-bookmark-item-minimized">
							<div class="rp-bookmark-title-wrap clearfix">
								<a class="rp-bookmark-title-toggle" href="#">Toggle </a>
								<a class="rp-bookmark-title" href="${bookmark.url}" target="_BLANK">${bookmark.title}</a>
							</div>
							<div class="rp-bookmark-more-info">
								<div class="rp-bookmark-controls clearfix">
	
									<portlet:renderURL var="showEditBookmarkUrl">
										<portlet:param name="showView" value="showEditBookmark" />
										<portlet:param name="bookmarkId" value="${bookmark.id}" />
									</portlet:renderURL>
								
									<portlet:actionURL name="deleteBookmark" var="deleteBookmarkUrl">
										<portlet:param name="action" value="deleteBookmark" />
										<portlet:param name="bookmarkId" value="${bookmark.id}" />
									</portlet:actionURL>
									
									<a href="${showEditBookmarkUrl}" class="rp-bookmark-edit" title="Redigera">Redigera</a>
									<a href="${deleteBookmarkUrl}" class="rp-bookmark-delete requires-confirmation" title="Ta bort" data-confirm-msg="&Auml;r du s&auml;ker p&aring; att du vill ta bort detta bokm&auml;rke?">Ta bort</a>
								</div>
								<p>${bookmark.url}</p>
								<p>${bookmark.description}</p>
							</div>
						</li>
					</c:forEach>
				</ul>
				
				<liferay-util:include page="/WEB-INF/jsp/tpl/tpl_paginator.jsp" servletContext="<%= application %>" />
				
				
			</c:when>
			<c:otherwise>
				<p>
					Du har inte lagt till n&aring;gra egna bokm&auml;rken &auml;nnu.
				</p>
			</c:otherwise>
		</c:choose>
		
	</div>
</div>

<liferay-util:html-bottom>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/rp-bookmark-portlet.js"></script>
	<script type="text/javascript">
		AUI().ready('aui-base','rp-bookmark-portlet', function (A) {
			var rpBookmarkPortlet = new A.RpBookmarkPortlet({
				portletNamespace: '<portlet:namespace />',
				portletNode: '#p_p_id<portlet:namespace />'
			});
			rpBookmarkPortlet.render();
		});
	</script>
</liferay-util:html-bottom>