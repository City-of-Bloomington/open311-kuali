package org.kuali.mobility.news.util;

import org.apache.commons.collections.Predicate;
import org.kuali.mobility.news.entity.NewsSource;

public class NewsSourcePredicate implements Predicate {

	private Long parentId;
	private Boolean active;
	
	public NewsSourcePredicate( final Long parentId, final Boolean active )
	{
		this.setParentId(parentId);
		this.setActive(active);
	}
	
	@Override
	public boolean evaluate(Object obj) {
		boolean parentMatch = false;
		boolean activeMatch = false;
		
		if( null != obj
			&& obj instanceof NewsSource ) {
			if( getParentId() == null ) {
				parentMatch = true;
			} else if( ((NewsSource)obj).getParentId() == null ) {
				if( getParentId().intValue() == 0 ) {
					parentMatch = true;
				} else {
					parentMatch = false;
				}
			} else if( getParentId().compareTo( ((NewsSource)obj).getParentId() ) == 0 ) {
				parentMatch = true;
			}
			if( isActive() == null ) {
				activeMatch = true;
			} else if( isActive().booleanValue() == ((NewsSource)obj).isActive() ) {
				activeMatch = true;
			}
		}
		return parentMatch && activeMatch;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
