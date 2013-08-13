package se.vgregion.portal.bookmark.repository;

import java.util.List;

import se.vgregion.dao.domain.patterns.repository.Repository;
import se.vgregion.portal.bookmark.domain.jpa.Bookmark;

/**
 * Repository interface for managing {@code Bookmark}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface BookmarkRepository extends Repository<Bookmark, Long> {

    /**
     * Find the number of {@link Bookmark}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return an int with the number of Bookmarks
     */
    int findBookmarksCountByCompanyId(long companyId);
	
	
    /**
     * Find all {@link Bookmark}s for a company.
     *
     * @param companyId the companyid
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findBookmarksByCompanyId(long companyId);
    
    /**
     * Find {@link Bookmark}s for a company.
     *
     * @param companyId the companyid
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findBookmarksByCompanyId(long companyId, int start, int offset);

    /**
     * Find the number of {@link Bookmark}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return an int with the number of Bookmarks
     */
    int findBookmarksCountByGroupId(long companyId, long groupId);
    

    /**
     * Find all {@link Bookmark}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findBookmarksByGroupId(long companyId, long groupId);
    
    /**
     * Find {@link Bookmark}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findBookmarksByGroupId(long companyId, long groupId, int start, int offset);
    
    /**
     * Find the number of {@link Bookmark}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @return an int with the number of Bookmarks
     */
    int findUserBookmarksCount(long companyId, long groupId, long userId);
    

    /**
     * Find all {@link Bookmark}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findUserBookmarks(long companyId, long groupId, long userId);
    
    /**
     * Find {@link Bookmark}s for a user in a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @param userId   the userId 
     * @param start   start index
     * @param end   maxIndex
     * @return a {@link List} of {@link Bookmark}s
     */
    List<Bookmark> findUserBookmarks(long companyId, long groupId, long userId , int start, int offset);
    
    /**
     * Remove the {@link Bookmark} with the id
     *
     * @param bookmarkId the id of the bookmark to remove
     * @return void
     */
    void remove(long bookmarkId);

}
