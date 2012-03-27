/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.mobility.news.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.kuali.mobility.news.entity.NewsSource;
import org.kuali.mobility.news.util.NewsSourcePredicate;
import org.kuali.mobility.util.mapper.DataMapper;
import org.springframework.context.ApplicationContext;

public class NewsDaoImpl implements NewsDao {

	public static Logger LOG = Logger.getLogger( NewsDaoImpl.class );

	private ApplicationContext applicationContext;
	private NewsCache cache;
	private DataMapper mapper;
	
	private String newsSourceFile;
	private String newsSourceUrl;
	private String newsMappingFile;
	private String newsMappingUrl;

	public List<NewsSource> findNewsSources( final Long parentId, final Boolean isActive )
	{
		initData();
		return (List<NewsSource>)CollectionUtils.select( getCache().getNewsSources().values(), new NewsSourcePredicate( parentId, isActive ) );
	}
	
	@Override
	public List<NewsSource> findAllActiveNewsSources() {
		initData();
		return (List<NewsSource>)CollectionUtils.select( getCache().getNewsSources().values(), new NewsSourcePredicate( null, new Boolean(true) ) );
	}
	
	public List<NewsSource> findAllActiveNewsSources( final Long parentId ) {
		initData();
		return (List<NewsSource>)CollectionUtils.select( (List<NewsSource>)getCache().getNewsSources(), new NewsSourcePredicate( parentId, new Boolean(true) ) );
	}
	
	@Override
	public List<NewsSource> findAllNewsSources() {
		initData();
		return new ArrayList<NewsSource>(getCache().getNewsSources().values());
	}
	
	public List<NewsSource> findAllNewsSources( final Long parentId ) {
		initData();
		return (List<NewsSource>)CollectionUtils.select( (List<NewsSource>)getCache().getNewsSources(), new NewsSourcePredicate( parentId, null ) );
	}

	@Override
	public NewsSource lookup(Long id) {
		initData();
		NewsSource source = null;
		source = getCache().getNewsSources().get(id);
		return source;
	}

	@Override
	public NewsSource save(NewsSource newsSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsSource delete(NewsSource newsSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	private void initData()
	{
		Map<Long, NewsSource> source = getCache().getNewsSources();
		if (source == null || source.isEmpty()) {
			source = new HashMap<Long, NewsSource>();
			List<NewsSource> sources = new ArrayList<NewsSource>();

			boolean isNewsSourceUrlAvailable = (getNewsSourceUrl() != null ? true : false);
			boolean isNewsMappingUrlAvailable = (getNewsMappingUrl() != null ? true : false);

			try {
				if (isNewsSourceUrlAvailable) {
					if (isNewsMappingUrlAvailable) {
						sources = (List<NewsSource>) mapper.mapData(source,
								new URL(getNewsSourceUrl()), 
								new URL(getNewsMappingUrl()));
					} else {
						sources = (List<NewsSource>) mapper.mapData(source,
								new URL(getNewsSourceUrl()),
								getNewsMappingFile());

					}
				} else {
					if (isNewsMappingUrlAvailable) {
						// not supported in mapper.mapData
						LOG.error("DataMapper does NOT support this case!");
						return;
					} else {
						sources = (List<NewsSource>) mapper.mapData(source,
								getNewsSourceFile(), getNewsMappingFile());
					}

				}

				int i = 0;
				for (NewsSource s : sources) {
					s.setActive(true);
					if (s.getId() == null) {
						s.setId(new Long(i));
					}
					source.put(s.getId(), s);
					i++;
				}
				
				getCache().setNewsSources(source);

			} catch (MalformedURLException e) {
				LOG.error(e.getMessage());
				// e.printStackTrace();
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
				// e.printStackTrace();
			} catch (IOException e) {
				LOG.error(e.getMessage());
				// e.printStackTrace();
			}

		}
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public NewsCache getCache() {
		return cache;
	}

	public void setCache(NewsCache cache) {
		this.cache = cache;
	}

	public DataMapper getMapper() {
		return mapper;
	}

	public void setMapper(DataMapper mapper) {
		this.mapper = mapper;
	}

	public String getNewsSourceFile() {
		return newsSourceFile;
	}

	public void setNewsSourceFile(String newsSourceFile) {
		this.newsSourceFile = newsSourceFile;
	}

	public String getNewsSourceUrl() {
		return newsSourceUrl;
	}

	public void setNewsSourceUrl(String newsSourceUrl) {
		this.newsSourceUrl = newsSourceUrl;
	}

	public String getNewsMappingFile() {
		return newsMappingFile;
	}

	public void setNewsMappingFile(String newsMappingFile) {
		this.newsMappingFile = newsMappingFile;
	}

	public String getNewsMappingUrl() {
		return newsMappingUrl;
	}

	public void setNewsMappingUrl(String newsMappingUrl) {
		this.newsMappingUrl = newsMappingUrl;
	}

}
