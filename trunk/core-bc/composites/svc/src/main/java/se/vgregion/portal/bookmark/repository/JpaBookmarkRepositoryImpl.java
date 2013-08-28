package se.vgregion.portal.bookmark.repository;

import java.util.List;

import javax.persistence.Query;

import se.vgregion.dao.domain.patterns.repository.db.jpa.DefaultJpaRepository;
import se.vgregion.portal.bookmark.domain.jpa.Bookmark;

/**
 * Implementation of {@link JpaBookmarkRepository}.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public class JpaBookmarkRepositoryImpl extends DefaultJpaRepository<Bookmark, Long>
        implements JpaBookmarkRepository {
	
    @Override
    public int findBookmarksCountByCompanyId(long companyId) {
        
        String queryString = "" 
        		+ " SELECT COUNT(DISTINCT n) FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1";
        
        Object[] queryObject = new Object[]{companyId};

        int count = findCountByQuery(queryString, queryObject);
        
        return count;
    }
	

    @Override
    public List<Bookmark> findBookmarksByCompanyId(long companyId) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " ORDER BY n.id ASC";
        
    	Object[] queryObject = new Object[]{companyId};

        List<Bookmark> bookmarks = findByQuery(queryString, queryObject);

        return bookmarks;
    }
    
    @Override
    public List<Bookmark> findBookmarksByCompanyId(long companyId, int start, int offset) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " ORDER BY n.id ASC";
        
    	Object[] queryObject = new Object[]{companyId};

        List<Bookmark> bookmarks = findByPagedQuery(queryString, queryObject, start, offset);

        return bookmarks;
    }
    
    @Override
    public int findBookmarksCountByGroupId(long companyId, long groupId) {
        
        String queryString = "" 
        		+ " SELECT COUNT(DISTINCT n) FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2";
        
        Object[] queryObject = new Object[]{companyId, groupId};

        int count = findCountByQuery(queryString, queryObject);
        
        return count;
    }
    

    @Override
    public List<Bookmark> findBookmarksByGroupId(long companyId, long groupId) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2" 
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId};
        
        List<Bookmark> bookmarks = findByQuery(queryString, queryObject);
        
        return bookmarks;
    }
    
    @Override
    public List<Bookmark> findBookmarksByGroupId(long companyId, long groupId, int start, int offset) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2" 
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId};
        
        List bookmarks = findByPagedQuery(queryString, queryObject, start, offset);
        
        return bookmarks;
    }
    
    
    
    
    
    @Override
    public int findUserBookmarksCount(long companyId, long groupId, long userId) {
        
        String queryString = "" 
        		+ " SELECT COUNT(DISTINCT n) FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3";
        
        Object[] queryObject = new Object[]{companyId, groupId, userId};

        int count = findCountByQuery(queryString, queryObject);
        
        return count;
    }
    

    @Override
    public List<Bookmark> findUserBookmarks(long companyId, long groupId, long userId) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3"
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId, userId};
        
        List bookmarks = findByQuery(queryString, queryObject);
        
        return bookmarks;
    }
    
    @Override
    public List<Bookmark> findUserBookmarks(long companyId, long groupId, long userId, int start, int offset) {
        
        String queryString = "" 
        		+ " SELECT DISTINCT n FROM Bookmark n" 
        		+ " WHERE n.companyId = ?1" 
        		+ " AND n.groupId = ?2"
        		+ " AND n.userId = ?3"
        		+ " ORDER BY n.id ASC";
        

        Object[] queryObject = new Object[]{companyId, groupId, userId};
        
        List bookmarks = findByPagedQuery(queryString, queryObject, start, offset);
        
        return bookmarks;
    }
    
    @Override
    public void remove(long bookmarkId) {
        
        Bookmark bookmark = find(bookmarkId);
        
        remove(bookmark);
    }

    private List<Bookmark> findByPagedQuery(String queryString, Object[] queryObject, int firstResult, int maxResult) {

    	Query query = createQuery(queryString, queryObject);
        
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        
        return query.getResultList();
    }
    
    private int findCountByQuery(String queryString, Object[] queryObject) {
        Query query = createQuery(queryString, queryObject);
        
        Number result = (Number) query.getSingleResult();
        
        return result.intValue();
    }
    
    private Query createQuery(String queryString, Object[] queryObject) {
    	
        Query query = entityManager.createQuery(queryString);
        if (queryObject != null) {
            for (int i = 0; i < queryObject.length; i++) {
                query.setParameter(i + 1, queryObject[i]);
            }
        }
        
        return query;
    }
    
    
}
