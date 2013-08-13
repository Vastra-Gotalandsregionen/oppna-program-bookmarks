package se.vgregion.portal.bookmark.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;

/**
 * JPA entity class representing a Bookmark
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Entity
@Table(name = "vgr_bookmark")
public class Bookmark extends AbstractEntity<Long> {

	// Primary Key
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	// Liferay Related
	
    @Column(name = "company_id")
    private long companyId;
	
    @Column(name = "group_id")
    private long groupId;

    @Column(name = "user_id")
    private long userId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "url")
    private String url;
    
    @Lob
    @Column(name = "description")
    private String description;
    
    
    /**
     * Constructor.
     */
    public Bookmark() {
    }
    
    public Bookmark(long companyId, long groupId, long userId) {
    	this.companyId = companyId;
    	this.groupId = groupId;
    	this.userId = userId;
    }
    
    public Bookmark(long companyId, long groupId, long userId, String title, String url, String description) {
    	this.companyId = companyId;
    	this.groupId = groupId;
    	this.userId = userId;
    	this.title = title;
    	this.url = url;
    	this.description = description;
    }
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
