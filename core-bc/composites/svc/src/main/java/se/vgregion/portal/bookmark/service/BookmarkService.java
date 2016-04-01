package se.vgregion.portal.bookmark.service;

import java.util.List;

import se.vgregion.portal.bookmark.domain.jpa.Bookmark;
import se.vgregion.portal.bookmark.exception.CreateBookmarkException;
import se.vgregion.portal.bookmark.exception.UpdateBookmarkException;

/**
 * Service interface for managing {@link Bookmark}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface BookmarkService {

    /**
     * Add a {@link Bookmark}.
     *
     */
    Bookmark addBookmark(Bookmark bookmark) throws CreateBookmarkException;
    
    /**
     * Remove a {@link Bookmark}.
     *
     * @param bookmarkId the primaryKey (id) of the {@link Bookmark} to remove
     */
    void deleteBookmark(long bookmarkId);
    
    
    /**
     * Remove a {@link Bookmark}.
     *
     * @param bookmark the {@link Bookmark} to remove
     */
    void deleteBookmark(Bookmark bookmark);
    
    /**
     * Find a {@link Bookmark}.
     *
     * @param ookmarkId - the primaryKey of the bookmark
     *
     * @return the {@link Bookmark}.
     */
    Bookmark find(long bookmarkId);    
	
    /**
     * Find all {@link Bookmark} for a given company.
     *
     * @param companyId the companyId
     * @return all {@link Bookmark} for the given company
     */
    List<Bookmark> findBookmarksByCompanyId(long companyId);
    
    /**
     * Find {@link Bookmark} for a given company.
     *
     * @param companyId the companyId
     * @param start   start index
     * @param end   maxIndex 
     * @return all {@link Bookmark} for the given company
     */
    List<Bookmark> findBookmarksByCompanyId(long companyId, int start, int offset);

    /**
     * Find the number of {@link Bookmark}s for a group in a company.
     *
     * @param companyId the companyId
     * @return an int with the number of Bookmarks
     */
    int findBookmarksCountByGroupId(long companyId, long groupId);
    

    /**
     * Find all {@link Bookmark} for a given company and group.
     *
     * @param companyId the companyId
     * @return all {@link Bookmark} for the given company and group
     */
    List<Bookmark> findBookmarksByGroupId(long companyId, long groupId);
    
    /**
     * Find all {@link Bookmark} for a given company and group.
     *
     * @param companyId     the companyId
     * @param currentPage   currentPage
     * @param offset        offset
 	 *
     * @return all {@link Bookmark} for the given company and group
     */
    List<Bookmark> findBookmarksByGroupId(long companyId, long groupId, int currentPage, int offset);
    
    /**
     * Find the number of {@link Bookmark}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param screenName   the userId
     * @return an int with the number of Bookmarks
     */
    int findUserBookmarksCount(long companyId, long groupId, String screenName);
    

    /**
     * Find all {@link Bookmark}s for a user in a group in a company.
     *
     *
     * @param id
     * @param companyId the companyid
     * @param screenName
     * @param groupId   the groupId
     * @param screenName   the screenName
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findUserBookmarks(long id, long companyId, String screenName, long groupId);
    
    /**
     * Find {@link Bookmark}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param screenName   the screenName
     * @param start   start index
     * @param offset   offset
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findUserBookmarks(long companyId, long groupId, String screenName, int start, int offset);
    
    /**
     * Find all {@link Bookmark}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param screenName   the userId
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findVgrBookmarksForUser(long companyId, String screenName);
    
    

    /**
     * Update a {@link Bookmark}.
     *
     */
    Bookmark updateBookmark(Bookmark bookmark) throws UpdateBookmarkException;
    
    /**
     * Update a {@link Bookmark}.
     *
     */
    Bookmark updateBookmark(long bookmarkId, String title, String url, String description) throws UpdateBookmarkException;
    
    
}
