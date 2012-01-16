package org.kuali.mobility.news.dao;

import java.util.Map;

import org.kuali.mobility.news.entity.NewsFeed;
import org.kuali.mobility.news.entity.NewsSource;

public interface NewsCache {

	public void updateCache(NewsSource source);
	
	public void updateFeed( NewsFeed feed, NewsSource source);
	
	public void setNewsFeeds( Map<Long, NewsFeed> feeds );
	public Map<Long, NewsFeed> getNewsFeeds();
	public void setNewsSources( Map<Long, NewsSource> sources );
	public Map<Long, NewsSource> getNewsSources();
}
