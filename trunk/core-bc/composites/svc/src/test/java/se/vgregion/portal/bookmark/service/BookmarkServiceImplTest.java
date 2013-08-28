package se.vgregion.portal.bookmark.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se.vgregion.portal.bookmark.domain.jpa.Bookmark;
import se.vgregion.portal.bookmark.exception.CreateBookmarkException;
import se.vgregion.portal.bookmark.exception.UpdateBookmarkException;
import se.vgregion.portal.bookmark.repository.BookmarkRepository;

public class BookmarkServiceImplTest {
	
	BookmarkServiceImpl bookmarkServiceImpl;
	Bookmark bookmark_new;
	Bookmark bookmark_1;
	
	long bookmarkId_1;
	long companyId;
	long groupId;
	long userId;
	
	@Before
	public void setup() {
	
		BookmarkRepository bookmarkRepository = Mockito.mock(BookmarkRepository.class);
		
		bookmarkServiceImpl = new BookmarkServiceImpl(bookmarkRepository){
			@Override 
			protected String[] getIntraUris(long companyId, long userId) {
				return new String[0];
			}
		};
		
		bookmark_new = new Bookmark();
		
		bookmarkId_1 = 1111;
		groupId = 2222;
		companyId = 3333;
		userId = 4444;
		
		bookmark_1 = new Bookmark();
		bookmark_1.setId(bookmarkId_1);
		
		Mockito.when(bookmarkRepository.find(bookmarkId_1)).thenReturn(bookmark_1);
	}

	@Test
	public void testAddBookmark() throws CreateBookmarkException {
		
		bookmarkServiceImpl.addBookmark(bookmark_new);
	}

	@Test
	public void testDeleteBookmarkLong() {
		
		bookmarkServiceImpl.deleteBookmark(bookmarkId_1);
	}

	@Test
	public void testDeleteBookmarkBookmark() {
		bookmarkServiceImpl.deleteBookmark(bookmark_1);
	}

	@Test
	public void testFind() {
		bookmarkServiceImpl.find(bookmarkId_1);
	}

	@Test
	public void testFindBookmarksByCompanyIdLong() {
		bookmarkServiceImpl.findBookmarksByCompanyId(companyId);
	}

	@Test
	public void testFindBookmarksByCompanyIdLongIntInt() {
		bookmarkServiceImpl.findBookmarksByCompanyId(companyId, 0, 10);
	}

	@Test
	public void testFindBookmarksCountByGroupId() {
		bookmarkServiceImpl.findBookmarksByGroupId(companyId, groupId);
	}

	@Test
	public void testFindBookmarksByGroupIdLongLong() {
		bookmarkServiceImpl.findBookmarksByGroupId(companyId, groupId);
	}

	@Test
	public void testFindBookmarksByGroupIdLongLongIntInt() {
		bookmarkServiceImpl.findBookmarksByGroupId(companyId, groupId, 1, 10);
	}

	@Test
	public void testFindUserBookmarksCount() {
		bookmarkServiceImpl.findUserBookmarks(companyId, groupId, userId);
	}

	@Test
	public void testFindUserBookmarksLongLongLong() {
		bookmarkServiceImpl.findUserBookmarks(companyId, groupId, userId);
	}

	@Test
	public void testFindUserBookmarksLongLongLongIntInt() {
		bookmarkServiceImpl.findUserBookmarks(companyId, groupId, userId, 0, 10);
	}

	@Test
	public void testFindVgrBookmarksForUser() {
		bookmarkServiceImpl.findVgrBookmarksForUser(companyId, userId);
	}

	@Test
	public void testUpdateBookmarkBookmark() throws UpdateBookmarkException {
		bookmarkServiceImpl.updateBookmark(bookmark_new);
		
		bookmarkServiceImpl.updateBookmark(bookmark_1);
	}

	@Test
	public void testUpdateBookmarkLongStringStringString() throws UpdateBookmarkException {
		bookmarkServiceImpl.updateBookmark(bookmarkId_1, "title", "url", "description");
	}

}
