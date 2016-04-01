package se.vgregion.portal.bookmark.userbookmarks.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import se.vgregion.portal.bookmark.domain.jpa.Bookmark;
import se.vgregion.portal.bookmark.domain.util.pageiterator.PageIterator;
import se.vgregion.portal.bookmark.domain.util.pageiterator.PageIteratorConstants;
import se.vgregion.portal.bookmark.exception.CreateBookmarkException;
import se.vgregion.portal.bookmark.exception.UpdateBookmarkException;
import se.vgregion.portal.bookmark.service.BookmarkService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

/**
 * Controller class for the view mode in User Bookmarks portlet.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Controller
@RequestMapping(value = "VIEW")
public class UserBookmarksViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBookmarksViewController.class.getName());
    
    private BookmarkService bookmarkService;

    /**
     * Constructor.
     *
     */
    @Autowired
    public UserBookmarksViewController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }    

    /**
     * The default render method - provides user bookmarks to the view
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     * @return the view
     */
    @RenderMapping()
    public String showBookmarks(RenderRequest request, RenderResponse response, final ModelMap model) {

    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long scopeGroupId = themeDisplay.getScopeGroupId();
        long companyId = themeDisplay.getCompanyId();
        String screenName = themeDisplay.getUser().getScreenName();
        boolean isSignedIn = themeDisplay.isSignedIn();
        
        int currentPage = ParamUtil.getInteger(request, "pageNumber", PageIteratorConstants.PAGINATOR_START_DEFAULT);
        int pageSize = PageIteratorConstants.PAGE_SIZE_DEFAULT;
        int maxPages = PageIteratorConstants.MAX_PAGES_DEFAULT;
        int totalCount = 0;
        
        int start = (currentPage - 1) * pageSize;
        
        List<Bookmark> vgrBookmarks = bookmarkService.findVgrBookmarksForUser(companyId, screenName);
        List<Bookmark> customBookmarks = bookmarkService.findUserBookmarks(companyId, scopeGroupId, screenName, start, pageSize);
        totalCount = bookmarkService.findUserBookmarksCount(companyId, scopeGroupId, screenName);
        
        PageIterator pageIterator = new PageIterator(totalCount, currentPage, pageSize, maxPages);
        pageIterator.setShowFirst(false);
        pageIterator.setShowLast(false);
        
        model.addAttribute("customBookmarks", customBookmarks);
        model.addAttribute("vgrBookmarks", vgrBookmarks);
        model.addAttribute("pageIterator", pageIterator);

        return "view";
    }
    
    /**
     * The show edit bookmark render method - will provide view with a Bookmark from the database if a valid bookmarkId is passed throught the request
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     * @return the view
     */
    @RenderMapping(params = "showView=showEditBookmark")
    public String showEditBookmark(RenderRequest request, RenderResponse response, final ModelMap model) {

    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long scopeGroupId = themeDisplay.getScopeGroupId();
        long companyId = themeDisplay.getCompanyId();
        long userId = themeDisplay.getUserId();
        boolean isSignedIn = themeDisplay.isSignedIn();
        
        long bookmarkId = ParamUtil.getLong(request, "bookmarkId", 0);
        
        Bookmark bookmark = new Bookmark();
        
        if(bookmarkId != 0) {
        	bookmark = bookmarkService.find(bookmarkId);
        }
        
        model.addAttribute("bookmark", bookmark);

        return "edit_bookmark";
    }
    
    /**
     * Method handling Action request for adding or updating a bookmark
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     */
    @ActionMapping(params = "action=editBookmark")
    public final void editBookmark(ActionRequest request, ActionResponse response, final ModelMap model) {
    	
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        long groupId = themeDisplay.getScopeGroupId();
        String screenName = themeDisplay.getUser().getScreenName();
        
        String title = ParamUtil.getString(request, "title", "");
        String url = ParamUtil.getString(request, "url", "");
        String description = ParamUtil.getString(request, "description", "");
        
        long bookmarkId = ParamUtil.getLong(request, "bookmarkId", 0);
        
        // Check http
        if(!url.startsWith("http")) {
        	url = "http://" + url;
        }
        
        if(bookmarkId == 0) {
            Bookmark bookmark = new Bookmark(companyId, groupId, screenName, title, url, description);
            
            try {
    			bookmarkService.addBookmark(bookmark);
    		} catch (CreateBookmarkException e) {
    			LOGGER.error(e.getMessage(), e);
    		}
        } else {
            try {
    			bookmarkService.updateBookmark(bookmarkId, title, url, description);
    		} catch (UpdateBookmarkException e) {
    			LOGGER.error(e.getMessage(), e);
    		}
        }
        
        response.setRenderParameter("showView", "");
    }
    
    /**
     * Method handling Action request for deleting a bookmark
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     */
    @ActionMapping(params = "action=deleteBookmark")
    public final void deleteBookmark(ActionRequest request, ActionResponse response, final ModelMap model) {
    	
        long bookmarkId = ParamUtil.getLong(request, "bookmarkId", 0);
        
        if(bookmarkId != 0) {
        	bookmarkService.deleteBookmark(bookmarkId);	
        }
        
        response.setRenderParameter("showView", "");
    }

}

