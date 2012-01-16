package org.kuali.mobility.news.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.mobility.news.entity.NewsSource;
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

	@Override
	public List<NewsSource> findAllActiveNewsSources() {
		initData();
		
		return new ArrayList<NewsSource>(getCache().getNewsSources().values());
	}

	@Override
	public List<NewsSource> findAllNewsSources() {
		initData();
		return new ArrayList<NewsSource>(getCache().getNewsSources().values());
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
		if( source == null || source.isEmpty() )
		{
			try
			{
				source = new HashMap<Long, NewsSource>();
				List<NewsSource> sources = new ArrayList<NewsSource>();
				sources = (List<NewsSource>)mapper.mapData( source, getNewsSourceFile(), getNewsMappingFile() );
				int i = 0;
				for( NewsSource s : sources )
				{
					s.setActive(true);
					if( s.getId() == null )
					{
						s.setId( new Long( i ) );
					}
					source.put( s.getId(), s );
					i++;
				}
				getCache().setNewsSources( source );
			}
			catch( ClassNotFoundException cnfe )
			{
				LOG.error( cnfe.getMessage() );
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
