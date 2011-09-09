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

package org.kuali.mobility.news.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.news.entity.NewsArticle;
import org.kuali.mobility.news.entity.NewsDay;
import org.kuali.mobility.news.entity.NewsSource;
import org.kuali.mobility.news.entity.NewsStream;
import org.kuali.mobility.news.service.NewsService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller 
@RequestMapping("/news")
public class NewsController {
    
    @Autowired
    private NewsService newsService;
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String newsHome(Model uiModel, HttpServletRequest request) {	
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
//    	String selectedCampus = null;
    	if (user.getViewCampus() == null) {
    		return "redirect:campus?toolName=news";
    	} else {
//    		selectedCampus = user.getViewCampus();
    	}

//      Disable static rendering data source
//    	List<NewsSource> sources = newsService.getAllNewsSourcesByLocation(selectedCampus);
//    	List<NewsStream> newsStreams = new ArrayList<NewsStream>();
//    	String defaultSourceId = newsService.getDefaultNewsSourceId(selectedCampus);
//    	NewsArticle topNewsArticle = null; 
//    	for (NewsSource source : sources) {
//    		NewsStream news = newsService.getNewsStream(source.getSourceId(), selectedCampus, true);
//    		if (news != null) {
//    			newsStreams.add(news);
//    			if (source.getSourceId().equals(defaultSourceId)) {
//    				if (news.getArticles() != null && !news.getArticles().isEmpty()) {
//    					NewsDay day = news.getArticles().get(0);
//    					if (day.getArticles() != null && !day.getArticles().isEmpty()) {
//        					topNewsArticle = day.getArticles().get(0);
//        				}
//    				}
//    			}
//    		}
//    	}
//    	uiModel.addAttribute("newsStreams", newsStreams);
//    	uiModel.addAttribute("topArticle", topNewsArticle);
//    	uiModel.addAttribute("topArticleSourceId", defaultSourceId);
    	
    	return "news/newsHome";
    }
    
    @RequestMapping(value = "/topJson", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String newsTopJson(Model uiModel, HttpServletRequest request) {	
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String selectedCampus = user.getViewCampus();

    	List<NewsSource> sources = newsService.getAllNewsSourcesByLocation(selectedCampus);
    	String defaultSourceId = newsService.getDefaultNewsSourceId(selectedCampus);
    	NewsArticle topNewsArticle = null; 
    	for (NewsSource source : sources) {
    		NewsStream news = newsService.getNewsStream(source.getSourceId(), selectedCampus, true);
    		if (news != null) {
    			if (source.getSourceId().equals(defaultSourceId)) {
    				if (news.getArticles() != null && !news.getArticles().isEmpty()) {
    					NewsDay day = news.getArticles().get(0);
    					if (day.getArticles() != null && !day.getArticles().isEmpty()) {
        					topNewsArticle = day.getArticles().get(0);
        					topNewsArticle.setSourceId(source.getSourceId());
        				}
    				}
    			}
    		}
    	}
    	List<NewsArticle> topArticles = new ArrayList<NewsArticle>();
    	topArticles.add(topNewsArticle);
    	return new JSONSerializer().exclude("*.class").deepSerialize(topArticles);
    }
    
    @RequestMapping(value = "/samplesJson", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String newsSamplesJson(Model uiModel, HttpServletRequest request) {	
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String selectedCampus = user.getViewCampus();

    	List<NewsSource> sources = newsService.getAllNewsSourcesByLocation(selectedCampus);
    	List<NewsStream> newsStreams = new ArrayList<NewsStream>();
    	for (NewsSource source : sources) {
    		NewsStream news = newsService.getNewsStream(source.getSourceId(), selectedCampus, true);
    		if (news != null) {
    			newsStreams.add(news);
    		}
    	}
    	
    	return new JSONSerializer().exclude("*.class").deepSerialize(newsStreams);
    }
    
    @RequestMapping(value = "/{sourceId}", method = RequestMethod.GET)
    public String getNewsArticle(HttpServletRequest request, @PathVariable("sourceId") String sourceId, @RequestParam(value = "articleId", required = false) String articleId, @RequestParam(value = "referrer", required = false) String referrer, Model uiModel) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	if (user.getViewCampus() == null) {
    		return "redirect:/campus?toolName=news";
    	}
    	if (articleId != null && articleId != "") {
//          Disable static rendering data source
//    		NewsArticle newsArticle = newsService.getNewsArticle(sourceId, articleId, selectedCampus);
    		NewsSource news = newsService.getNewsSourceById(sourceId);
//        	uiModel.addAttribute("newsArticle", newsArticle);
        	uiModel.addAttribute("sourceId", sourceId);
        	uiModel.addAttribute("articleId", articleId);
        	uiModel.addAttribute("sourceTitle", news.getSourceName());
        	uiModel.addAttribute("referrer", referrer);
        	return "news/newsArticle";
    	} else {
//          Disable static rendering data source
//    		NewsStream news = newsService.getNewsStream(sourceId, selectedCampus, false);
//        	uiModel.addAttribute("newsStream", news);
        	uiModel.addAttribute("sourceId", sourceId);
        	
//        	List<NewsSource> sources = newsService.getAllNewsSourcesByLocation(selectedCampus);
//        	uiModel.addAttribute("newsSources", sources);
        	
        	return "news/newsStream";
    	}
    }
    
    @RequestMapping(value = "/{sourceId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getNewsStreamJson(HttpServletRequest request, @PathVariable("sourceId") String sourceId, @RequestParam(value = "referrer", required = false) String referrer, Model uiModel) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String selectedCampus = user.getViewCampus();
    	
		NewsStream news = newsService.getNewsStream(sourceId, selectedCampus, false);
    	uiModel.addAttribute("newsStream", news);
    	
    	return new JSONSerializer().exclude("*.class").deepSerialize(news);
    }
    
    @RequestMapping(value = "/{sourceId}", method = RequestMethod.GET, headers = "Accept=application/json", params = "articleId")
    @ResponseBody
    public String getNewsArticleJson(HttpServletRequest request, @PathVariable("sourceId") String sourceId, @RequestParam(value = "articleId") String articleId, Model uiModel) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String selectedCampus = user.getViewCampus();
    	
		NewsArticle newsArticle = newsService.getNewsArticle(sourceId, articleId, selectedCampus);
    	return new JSONSerializer().exclude("*.class").deepSerialize(newsArticle);
    }
}
