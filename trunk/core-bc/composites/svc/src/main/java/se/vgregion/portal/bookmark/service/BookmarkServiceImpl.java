package se.vgregion.portal.bookmark.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import se.vgregion.portal.bookmark.domain.jpa.Bookmark;
import se.vgregion.portal.bookmark.domain.util.BookmarkConstants;
import se.vgregion.portal.bookmark.exception.CreateBookmarkException;
import se.vgregion.portal.bookmark.exception.UpdateBookmarkException;
import se.vgregion.portal.bookmark.repository.BookmarkRepository;

/**
 * Implementation of {@link BookmarksService}.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public class BookmarkServiceImpl implements BookmarkService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkServiceImpl.class.getName());
    private BookmarkRepository bookmarkRepository;

    /**
     * Constructor.
     *
     * @param bookmarkRepository the {@link BookmarkRepository}
     */
    @Autowired
    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }
    
    @Override
    @Transactional
    public Bookmark addBookmark(Bookmark bookmark) throws CreateBookmarkException {
    	return bookmarkRepository.merge(bookmark);
    }
    
    @Override
    @Transactional
    public void deleteBookmark(long bookmarkId) {
    	Bookmark bookmark = bookmarkRepository.find(bookmarkId);
    	
    	if(bookmark != null) {
    		bookmarkRepository.remove(bookmarkId);	
    	}
    }
    
    @Override
    @Transactional
    public void deleteBookmark(Bookmark bookmark) {
    	bookmarkRepository.remove(bookmark);
    }

    @Override
    public Bookmark find(long bookmarkId) {
        return bookmarkRepository.find(bookmarkId);
    }
    
    @Override
    public List<Bookmark> findBookmarksByCompanyId(long companyId) {
        return bookmarkRepository.findBookmarksByCompanyId(companyId);
    }
    
    @Override
    public List<Bookmark> findBookmarksByCompanyId(long companyId, int start, int offset) {
        return bookmarkRepository.findBookmarksByCompanyId(companyId, start, offset);
    }

    @Override
    public int findBookmarksCountByGroupId(long companyId, long groupId) {
        return bookmarkRepository.findBookmarksCountByGroupId(companyId, groupId);
    }
    
    
    @Override
    public List<Bookmark> findBookmarksByGroupId(long companyId, long groupId) {
        return bookmarkRepository.findBookmarksByGroupId(companyId, groupId);
    }
    
    @Override
    public List<Bookmark> findBookmarksByGroupId(long companyId, long groupId, int currentPage, int offset) {
    	
    	int start = (currentPage -1) * offset;
    	if(start < 0) {
    		start = 0;
    	}
    	
        return bookmarkRepository.findBookmarksByGroupId(companyId, groupId, start, offset);
    }
    
    @Override
    public int findUserBookmarksCount(long companyId, long groupId, long userId) {
    	return bookmarkRepository.findUserBookmarksCount(companyId, groupId, userId);
    }
    
    @Override
    public List<Bookmark> findUserBookmarks(long companyId, long groupId, long userId) {
    	return bookmarkRepository.findUserBookmarks(companyId, groupId, userId);
    }
    
    @Override
    public List<Bookmark> findUserBookmarks(long companyId, long groupId, long userId , int start, int offset) {
    	return bookmarkRepository.findUserBookmarks(companyId, groupId, userId, start, offset);
    }
    
    @Override
    public List<Bookmark> findVgrBookmarksForUser(long companyId, long userId) {
    	
    	ArrayList<Bookmark> vgrUserBookmarks = new ArrayList<Bookmark>();
    	
		String[] intraUris =  getIntraUris(companyId, userId);
		
		if(intraUris.length == 0) {
			Bookmark bookmark = new Bookmark();
			bookmark.setTitle(BookmarkConstants.URL_VGR_INTRA);
			bookmark.setUrl(BookmarkConstants.URL_VGR_INTRA);
			
			vgrUserBookmarks.add(bookmark);
		} else {
			for(String intraUri : intraUris) {
				
				Bookmark bookmark = new Bookmark();
				
				if(intraUri.contains(BookmarkConstants.URL_VGR_INTRA_INSIDAN)) {
					bookmark.setUrl(BookmarkConstants.URL_VGR_INTRA_AUTOLOGIN);
				} else {
					bookmark.setUrl(intraUri);
				}
				
				bookmark.setTitle(intraUri);
				
				vgrUserBookmarks.add(bookmark);
			}
		}
			
    	return vgrUserBookmarks;
    }
    

    @Override
    @Transactional
    public Bookmark updateBookmark(Bookmark bookmark) throws UpdateBookmarkException {
    	return bookmarkRepository.merge(bookmark);
    }

    @Override
    @Transactional
    public Bookmark updateBookmark(long bookmarkId, String title, String url, String description) throws UpdateBookmarkException {
    	
    	Bookmark bookmark = bookmarkRepository.find(bookmarkId);
    	
    	if(bookmark != null) {
    		bookmark.setTitle(title);
    		bookmark.setUrl(url);
    		bookmark.setDescription(description);
    	
    		bookmark = bookmarkRepository.merge(bookmark);
    	}
    	
    	return bookmark;
    }
    
    protected String[] getIntraUris(long companyId, long userId) {
    	
    	try {
			String[] intraUris =  ExpandoValueLocalServiceUtil.getData(
					companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, BookmarkConstants.EXPANDO_COLUMN_VGR_LABELED_URI, userId, new String[0]);
			
			return intraUris;
		} catch (PortalException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (SystemException e) {
			LOGGER.error(e.getMessage(), e);
		}
    	
    	return new String[0];
    }

}
