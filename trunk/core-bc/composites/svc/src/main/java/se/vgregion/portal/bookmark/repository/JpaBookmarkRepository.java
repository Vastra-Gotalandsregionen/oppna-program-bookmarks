package se.vgregion.portal.bookmark.repository;

import se.vgregion.dao.domain.patterns.repository.db.jpa.JpaRepository;
import se.vgregion.portal.bookmark.domain.jpa.Bookmark;

/**
 * JPA Repository interface for managing {@link Bookmark}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface JpaBookmarkRepository extends JpaRepository<Bookmark, Long, Long>,
        BookmarkRepository {

}
