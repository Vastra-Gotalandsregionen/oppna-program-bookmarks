package se.vgregion.portal.bookmark.userbookmarks.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import se.vgregion.portal.bookmark.domain.jpa.Bookmark;
import se.vgregion.portal.bookmark.service.BookmarkService;

public class UserBookmarksViewControllerTest {
	
	BookmarkService bookmarkService;
	UserBookmarksViewController userBookmarksViewController;

	ActionRequest actionRequest;
	ActionResponse actionResponse;
	
	
	RenderRequest renderRequest;
	RenderResponse renderResponse;
	
	ModelMap model;
	
	@Before
	public void setup() {
		
		model = mock(ModelMap.class);
		
		bookmarkService = mock(BookmarkService.class);
		userBookmarksViewController = new UserBookmarksViewController(bookmarkService);
		
		actionRequest = mock(ActionRequest.class);
		actionResponse = mock(ActionResponse.class);
		
		renderRequest = mock(RenderRequest.class);
		renderResponse = mock(RenderResponse.class);
	}

	@Test
	public void testShowBookmarks() {
	
		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);

        User user = mock(User.class);
        when(themeDisplay.getUser()).thenReturn(user);

        ArrayList<Bookmark> vgrBookmarks = new ArrayList<Bookmark>();
		ArrayList<Bookmark> customBookmarks = new ArrayList<Bookmark>();
		
		when(bookmarkService.findVgrBookmarksForUser(Mockito.anyLong(), Mockito.anyString())).thenReturn(vgrBookmarks);
		when(bookmarkService.findUserBookmarks(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(customBookmarks);
		
		when(renderRequest.getAttribute(WebKeys.THEME_DISPLAY)).thenReturn(themeDisplay);
		
		userBookmarksViewController.showBookmarks(renderRequest, renderResponse, model);
		
		Mockito.verify(model).addAttribute("customBookmarks", customBookmarks);
		Mockito.verify(model).addAttribute("vgrBookmarks", vgrBookmarks);
	}

	@Test
	public void testShowEditBookmark() {
		
		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);
		
		when(renderRequest.getAttribute(WebKeys.THEME_DISPLAY)).thenReturn(themeDisplay);
		
		// No bookmarkId	
		userBookmarksViewController.showEditBookmark(renderRequest, renderResponse, model);	
		
		// BookmarkId provided
		Bookmark bookmarkEdit = new Bookmark();
		
		when(renderRequest.getParameter("bookmarkId")).thenReturn("10");
		when(bookmarkService.find(10)).thenReturn(bookmarkEdit);
		
		userBookmarksViewController.showEditBookmark(renderRequest, renderResponse, model);
		
		Mockito.verify(model).addAttribute("bookmark", bookmarkEdit);
	}

	@Test
	public void testEditBookmark() {
		
		ThemeDisplay themeDisplay = mock(ThemeDisplay.class);

		User user = mock(User.class);
		when(themeDisplay.getUser()).thenReturn(user);
		
		when(actionRequest.getAttribute(WebKeys.THEME_DISPLAY)).thenReturn(themeDisplay);
		
		userBookmarksViewController.editBookmark(actionRequest, actionResponse, model);
		
		when(renderRequest.getParameter("bookmarkId")).thenReturn("10");
		
		userBookmarksViewController.editBookmark(actionRequest, actionResponse, model);
	}

	@Test
	public void testDeleteBookmark() {
		
		userBookmarksViewController.deleteBookmark(actionRequest, actionResponse, model);
		
		when(renderRequest.getParameter("bookmarkId")).thenReturn("10");
		
		userBookmarksViewController.deleteBookmark(actionRequest, actionResponse, model);
		
	}

}
