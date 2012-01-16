package org.kuali.mobility.news.dao;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.mobility.news.entity.NewsArticle;
import org.kuali.mobility.news.entity.NewsFeed;
import org.kuali.mobility.news.entity.NewsSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

public class NewsCacheImpl implements NewsCache, ApplicationContextAware {

	private static final Logger LOG = Logger.getLogger( NewsCacheImpl.class );
	
	private ApplicationContext applicationContext;
	
	private Map<Long, NewsFeed> newsFeeds = new HashMap<Long, NewsFeed>();
	private Map<Long, NewsSource> newsSources = new HashMap<Long, NewsSource>();
		
	/**
	 * Update the cache for a single NewsSource. Called when a NewsSource is saved.
	 * 
	 * @param source the NewsSource that was saved
	 */
	public void updateCache(NewsSource source) {
		if (source.isActive()) {
			LOG.debug( "NewsSource "+source.getId()+" is active & will be refreshed." );
			if( !getNewsSources().containsKey( source.getId() ) )
			{
				getNewsSources().put(source.getId(), source);
			}
			NewsFeed feed = getNewsFeeds().get(source.getId());
			if (feed == null) {
				LOG.debug( "No news feed found for source id "+source.getId() );
				feed = (NewsFeed)getApplicationContext().getBean("newsFeed");
				getNewsFeeds().put(source.getId(), feed);
			}
			updateFeed(feed, source);
		} else {
			LOG.debug( "NewsSource is inactive & being removed." );
			getNewsSources().remove(source.getId());
			getNewsFeeds().remove(source.getId());
		}
	}
	
	/**
	 * Does the actual work of updating a news feed and its articles
	 * 
	 * @param feed the NewsFeed to update
	 * @param source the NewsSource that defines the feed to update
	 */
	@SuppressWarnings("unchecked")
	public void updateFeed(NewsFeed feed, NewsSource source) {
		LOG.debug( "Updating feed for source "+source.getId() );
		feed.setOrder(source.getOrder());
		
		URL feedUrl = null;
		try {
			feedUrl = new URL(source.getUrl());
		} catch (MalformedURLException e) {
			LOG.error("Bad feed url: " + source.getUrl(), e);
		}
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed syndFeed = null;
		try {
			syndFeed = input.build(new InputStreamReader(feedUrl.openStream()));
		} catch (Exception e) {
			LOG.error("Error reading feed: " + source.getName(), e);
		}
		
		if (syndFeed != null) {
			LOG.debug( "Feed data retrieved, populating articles for: "+syndFeed.getTitle() );
			feed.setTitle(syndFeed.getTitle());
			feed.setAuthor(syndFeed.getAuthor());
			feed.setDescription(syndFeed.getDescription());
			feed.setSourceId(source.getId());
			
			List<NewsArticle> articles = new ArrayList<NewsArticle>();
			for (SyndEntryImpl entry : (List<SyndEntryImpl>)syndFeed.getEntries()) {
				LOG.debug( "Processing article: "+entry.getTitle() );
				NewsArticle article = (NewsArticle)getApplicationContext().getBean("newsArticle");
				
				article.setTitle(entry.getTitle());
				article.setDescription(entry.getDescription().getValue());
				article.setLink(entry.getLink());
				article.setPublishDate(new Timestamp(entry.getPublishedDate().getTime()));
				article.setSourceId(source.getId());
				try {
					article.setArticleId(URLEncoder.encode(entry.getUri(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					article.setArticleId(entry.getUri());
				}
				
				articles.add(article);
			}
			feed.setArticles(articles);
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Map<Long, NewsFeed> getNewsFeeds() {
		return newsFeeds;
	}

	public void setNewsFeeds(Map<Long, NewsFeed> newsFeeds) {
		this.newsFeeds = newsFeeds;
	}

	public Map<Long, NewsSource> getNewsSources() {
		return newsSources;
	}

	public void setNewsSources(Map<Long, NewsSource> newsSources) {
		this.newsSources = newsSources;
	}

}
