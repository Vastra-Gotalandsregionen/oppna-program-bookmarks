package se.vgregion.portal.bookmark.userbookmarks.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import se.vgregion.portal.bookmark.domain.jpa.Bookmark;
import se.vgregion.portal.bookmark.domain.util.pageiterator.PageIterator;
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
		
		model = Mockito.mock(ModelMap.class);
		
		bookmarkService = Mockito.mock(BookmarkService.class);	
		userBookmarksViewController = new UserBookmarksViewController(bookmarkService);
		
		actionRequest = Mockito.mock(ActionRequest.class);
		actionResponse = Mockito.mock(ActionResponse.class);
		
		renderRequest = Mockito.mock(RenderRequest.class);
		renderResponse = Mockito.mock(RenderResponse.class);
	}

	@Test
	public void testShowBookmarks() {
	
		ThemeDisplay themeDisplay = Mockito.mock(ThemeDisplay.class);
		
		ArrayList<Bookmark> vgrBookmarks = new ArrayList<Bookmark>();
		ArrayList<Bookmark> customBookmarks = new ArrayList<Bookmark>();
		
		Mockito.when(bookmarkService.findVgrBookmarksForUser(Mockito.anyLong(), Mockito.anyLong())).thenReturn(vgrBookmarks);
		Mockito.when(bookmarkService.findUserBookmarks(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(customBookmarks);
		
		Mockito.when(renderRequest.getAttribute(WebKeys.THEME_DISPLAY)).thenReturn(themeDisplay);
		
		userBookmarksViewController.showBookmarks(renderRequest, renderResponse, model);
		
		Mockito.verify(model).addAttribute("customBookmarks", customBookmarks);
		Mockito.verify(model).addAttribute("vgrBookmarks", vgrBookmarks);
	}

	@Test
	public void testShowEditBookmark() {
		
		ThemeDisplay themeDisplay = Mockito.mock(ThemeDisplay.class);
		
		Mockito.when(renderRequest.getAttribute(WebKeys.THEME_DISPLAY)).thenReturn(themeDisplay);
		
		// No bookmarkId	
		userBookmarksViewController.showEditBookmark(renderRequest, renderResponse, model);	
		
		// BookmarkId provided
		Bookmark bookmarkEdit = new Bookmark();
		
		Mockito.when(renderRequest.getParameter("bookmarkId")).thenReturn("10");
		Mockito.when(bookmarkService.find(10)).thenReturn(bookmarkEdit);
		
		userBookmarksViewController.showEditBookmark(renderRequest, renderResponse, model);
		
		Mockito.verify(model).addAttribute("bookmark", bookmarkEdit);
	}

	@Test
	public void testEditBookmark() {
		
		ThemeDisplay themeDisplay = Mockito.mock(ThemeDisplay.class);
		
		Mockito.when(actionRequest.getAttribute(WebKeys.THEME_DISPLAY)).thenReturn(themeDisplay);
		
		userBookmarksViewController.editBookmark(actionRequest, actionResponse, model);
		
		Mockito.when(renderRequest.getParameter("bookmarkId")).thenReturn("10");
		
		userBookmarksViewController.editBookmark(actionRequest, actionResponse, model);
	}

	@Test
	public void testDeleteBookmark() {
		
		userBookmarksViewController.deleteBookmark(actionRequest, actionResponse, model);
		
		Mockito.when(renderRequest.getParameter("bookmarkId")).thenReturn("10");
		
		userBookmarksViewController.deleteBookmark(actionRequest, actionResponse, model);
		
	}

}
