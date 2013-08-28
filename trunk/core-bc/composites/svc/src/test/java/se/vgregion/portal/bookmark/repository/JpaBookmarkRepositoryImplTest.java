package se.vgregion.portal.bookmark.repository;

import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import se.vgregion.dao.domain.patterns.repository.db.jpa.AbstractJpaRepository;

public class JpaBookmarkRepositoryImplTest {
	
	long bookmarkId_1;
	long companyId_1;
	long groupId_1;
	long userId_1;
	
	EntityManager entityManager;
	JpaBookmarkRepositoryImpl jpaBookmarkRepositoryImpl;
	Query query;
	
	@Before
	public void setup() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		bookmarkId_1 = 3333;
		companyId_1 = 1234;
		groupId_1 = 5678;
		userId_1 = 2222;
		
		entityManager = Mockito.mock(EntityManager.class);	
		jpaBookmarkRepositoryImpl = new JpaBookmarkRepositoryImpl();
		
		query = Mockito.mock(Query.class);
		
		Mockito.when(query.getSingleResult()).thenReturn(0);
		
		Field entityManagerField = AbstractJpaRepository.class.getDeclaredField("entityManager");
		entityManagerField.setAccessible(true);
		
		entityManagerField.set(jpaBookmarkRepositoryImpl, entityManager);
		
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
	}

	@Test
	public void testFindBookmarksCountByCompanyId() {
		jpaBookmarkRepositoryImpl.findBookmarksCountByCompanyId(companyId_1);
	}

	@Test
	public void testFindBookmarksByCompanyIdLong() {
		jpaBookmarkRepositoryImpl.findBookmarksByCompanyId(companyId_1);
	}

	@Test
	public void testFindBookmarksByCompanyIdLongIntInt() {
		jpaBookmarkRepositoryImpl.findBookmarksByCompanyId(companyId_1, 0, 10);
	}

	@Test
	public void testFindBookmarksCountByGroupId() {
		jpaBookmarkRepositoryImpl.findBookmarksCountByGroupId(companyId_1, groupId_1);
	}

	@Test
	public void testFindBookmarksByGroupIdLongLong() {
		jpaBookmarkRepositoryImpl.findBookmarksByGroupId(companyId_1, groupId_1);
	}

	@Test
	public void testFindBookmarksByGroupIdLongLongIntInt() {
		jpaBookmarkRepositoryImpl.findBookmarksByGroupId(companyId_1, groupId_1, 0, 10);
	}

	@Test
	public void testFindUserBookmarksCount() {
		jpaBookmarkRepositoryImpl.findUserBookmarksCount(companyId_1, groupId_1, userId_1);
	}

	@Test
	public void testFindUserBookmarksLongLongLong() {
		jpaBookmarkRepositoryImpl.findUserBookmarks(companyId_1, groupId_1, userId_1);
	}

	@Test
	public void testFindUserBookmarksLongLongLongIntInt() {
		jpaBookmarkRepositoryImpl.findUserBookmarks(companyId_1, groupId_1, userId_1, 0, 10);
	}

	@Test
	public void testRemoveLong() {
		jpaBookmarkRepositoryImpl.remove(bookmarkId_1);
	}

	
}
