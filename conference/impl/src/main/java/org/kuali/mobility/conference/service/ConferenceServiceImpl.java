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

package org.kuali.mobility.conference.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.kuali.mobility.conference.entity.Attendee;
import org.kuali.mobility.conference.entity.ContentBlock;
import org.kuali.mobility.conference.entity.MenuItem;
import org.kuali.mobility.conference.entity.Session;

public class ConferenceServiceImpl implements ConferenceService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConferenceServiceImpl.class);

	private String attendeesFeedUrl;
	private String featuredSpeakersFeedUrl;
	private String sessionsFeedUrl;
	private String welcomeFeedUrl;
	private String toEmailAddress;
	private String fromEmailAddress;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContentBlock> findAllContentBlocks() {
		List<ContentBlock> contentBlocks = new ArrayList<ContentBlock>();
		try {
			String json = retrieveJSON(welcomeFeedUrl);

			JSONArray simpleContentArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = simpleContentArray.iterator(); iter.hasNext();) {
				try {
					JSONObject contentBlockObject = iter.next();
					
					ContentBlock contentBlock = new ContentBlock();
					contentBlock.setContentBlock(contentBlockObject.getString("welcome"));

					contentBlocks.add(contentBlock);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return contentBlocks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContentBlock> findFeaturedSpeakers() {
		List<ContentBlock> contentBlocks = new ArrayList<ContentBlock>();
		try {
			String json = retrieveJSON(featuredSpeakersFeedUrl);

			JSONArray simpleContentArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = simpleContentArray.iterator(); iter.hasNext();) {
				try {
					JSONObject contentBlockObject = iter.next();
					
					ContentBlock contentBlock = new ContentBlock();
					contentBlock.setContentBlock(contentBlockObject.getString("welcome"));

					contentBlocks.add(contentBlock);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return contentBlocks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItem> findAllMenuItems(String lang) {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		try {
			String json = null;
			if (lang.equals("zh_CN")){
				json = retrieveJSON("http://www.indiana.edu/~iumobile/SWITC-2011/home_zh_cn.json");
				System.out.println(json);
			} else {
				json = retrieveJSON("http://www.indiana.edu/~iumobile/SWITC-2011/home_en.json");
			}
			
			// test
			String test1 = "[{\"title\":\"欢迎\"}]";
			System.out.println(test1);
			/*
			JSONArray menuItemArray1 = (JSONArray) JSONSerializer.toJSON(test1);
			System.out.println(menuItemArray1);
			for (Iterator<JSONObject> iter = menuItemArray1.iterator(); iter.hasNext();) {
				try {
					JSONObject menuItemObject = iter.next();
					MenuItem menuItem2 = new MenuItem();
					menuItem2.setTitle(menuItemObject.getString("title"));
					menuItems.add(menuItem2);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}*/
			//JSONArray menuItemArray2 = (JSONArray) JSONSerializer.toJSON(test2);
			
			// end of test
			
			JSONArray menuItemArray = (JSONArray) JSONSerializer.toJSON(json);
			for (Iterator<JSONObject> iter = menuItemArray.iterator(); iter.hasNext();) {
				try {
					JSONObject menuItemObject = iter.next();
					
					MenuItem menuItem = new MenuItem();
					menuItem.setTitle(menuItemObject.getString("title"));
					menuItem.setDescription(menuItemObject.getString("description"));
					menuItem.setLinkURL(menuItemObject.getString("linkURL"));
					menuItem.setIconURL(menuItemObject.getString("iconURL"));
					menuItems.add(menuItem);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return menuItems;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Attendee> findAllAttendees(char start, char end) {
		
		List<Attendee> attendees = new ArrayList<Attendee>();
		try {
			String json = retrieveJSON(attendeesFeedUrl);

			JSONArray attendeeArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = attendeeArray.iterator(); iter.hasNext();) {
				try {
					JSONObject attendeeObject = iter.next();
					
					Attendee attendee = new Attendee();
					attendee.setId(attendeeObject.getString("id"));
					attendee.setEmail(attendeeObject.getString("email"));
					attendee.setFirstName(attendeeObject.getString("firstName"));
					attendee.setLastName(attendeeObject.getString("lastName"));
					attendee.setInstitution(attendeeObject.getString("institution"));
					attendee.setCampus(attendeeObject.getString("campus"));
					attendee.setTitle(attendeeObject.getString("title"));
					
					char c = attendee.getLastName().toUpperCase().charAt(0);
					if (c >= start && c <= end) {						
						attendees.add(attendee);
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return attendees;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Session> findAllSessions(String date) {
	
		List<Session> sessions = new ArrayList<Session>();
		try {
			String dateString = (null == date ? "" : date);
			String json = retrieveJSON(sessionsFeedUrl + "?d=" + dateString);

			JSONArray sessionArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = sessionArray.iterator(); iter.hasNext();) {
				try {
					JSONObject sessionObject = iter.next();
					
					Session session = new Session();
					/*
					 * IU SWITC fields
					 * 
					 session.setId(sessionObject.getString("id"));
					session.setTitle(sessionObject.getString("title"));
					session.setDescription(sessionObject.getString("description"));
					session.setLocation(sessionObject.getString("location"));
					session.setLatitude(sessionObject.getString("latitude"));
					session.setLongitude(sessionObject.getString("longitude"));
					session.setLink(sessionObject.getString("link"));
					
					try {
						String str_startDate = sessionObject.getString("startTime");
						String str_endDate = sessionObject.getString("endTime");
						DateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						Date startDate = (Date)parser.parse(str_startDate);
						Date endDate = (Date)parser.parse(str_endDate);

						session.setdStartTime(startDate);
						session.setdEndTime(endDate);
						
						//DateFormat formatter = new SimpleDateFormat("E hh:mm a");
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String formattedStartDate, formattedEndDate;
						formattedStartDate = formatter.format(startDate);
						formattedEndDate = formatter.format(endDate);
						session.setStartTime(formattedStartDate);
						session.setEndTime(formattedEndDate);
					} catch (ParseException e) {
					}

					
					JSONArray tempSpeakers = sessionObject.getJSONArray("speakers");
		
					
				List<Attendee> speakers = new ArrayList<Attendee>();
					
					for (int i = 0; i < tempSpeakers.size(); i++) {
		            	JSONObject speakersObject = tempSpeakers.getJSONObject(i);

		            	Attendee speaker = new Attendee();
		            	speaker.setFirstName(speakersObject.getString("firstName"));
		            	speaker.setLastName(speakersObject.getString("lastName"));
		            	speaker.setEmail(speakersObject.getString("email"));
		            	speaker.setTitle(speakersObject.getString("title"));
		            	speaker.setInstitution(speakersObject.getString("organization"));
		            	speakers.add(speaker);
		            }
					
					session.setSpeakers(speakers);
			*/
					// take out non alpha-numberic chars to get a tidy new id for linking
					session.setId(URLEncoder.encode(sessionObject.getString("title").replaceAll("[^A-Za-z0-9 ]", ""), "UTF-8"));
					session.setTitle(sessionObject.getString("title").replace("\\",""));
					session.setDescription(sessionObject.getString("details"));
					session.setLocation(sessionObject.getString("room"));
					session.setTrack(sessionObject.getString("track"));
					// take out non alpha-numeric chars and spaces to make a simple css class name, prepend 'track-'
					session.setTrackCSSClass("track-" + sessionObject.getString("track").replaceAll("[^A-Za-z0-9]", ""));
					session.setLevel(sessionObject.getString("level"));
					session.setType(sessionObject.getString("type"));
					
					/*
					session.setLatitude(sessionObject.getString("latitude"));
					session.setLongitude(sessionObject.getString("longitude"));
					session.setLink(sessionObject.getString("link"));
					*/
					
					try {
						String str_startDate = sessionObject.getString("date") + " " + sessionObject.getString("time");
						String str_endDate = sessionObject.getString("date") + " " + sessionObject.getString("time");
						
						DateFormat parser = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
						//DateFormat parser = new SimpleDateFormat("HH:mm a");

						Date startDate = (Date)parser.parse(str_startDate);
						Date endDate = (Date)parser.parse(str_endDate);

						session.setdStartTime(startDate);
						session.setdEndTime(endDate);
						
						//DateFormat formatter = new SimpleDateFormat("E hh:mm a");
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						//DateFormat formatter = new SimpleDateFormat("HH:mm");

						String formattedStartDate, formattedEndDate;
						formattedStartDate = formatter.format(startDate);
						formattedEndDate = formatter.format(endDate);
						
						//System.out.println("startdate:" + formattedStartDate);
						//System.out.println("enddate:" + formattedEndDate);
						session.setStartTime(formattedStartDate);
						session.setEndTime(formattedEndDate);
						
						//session.setStartTime("TEST");
						//session.setEndTime("TEST");
						//session.setStartTime(str_startDate);
						//session.setEndTime(str_endDate);
					} catch (ParseException e) {
						//System.out.println("Error:" + e);
					}

					JSONArray tempSpeakers = sessionObject.getJSONArray("presenters");
					
					List<Attendee> speakers = new ArrayList<Attendee>();
					
					//JSONArray sessionArray = (JSONArray) JSONSerializer.toJSON(json);
					//for (Iterator<JSONObject> iter2 = tempSpeakers.iterator(); iter2.hasNext();) {
					
					//JSONObject speakersObject = iter2.next();
				
					for (int i = 0; i < tempSpeakers.size(); i++) {
		            	//JSONObject speakersObject = tempSpeakers.getJSONObject(i);

						//System.out.println("tempSpeakers:" + tempSpeakers.getString(i));
						
		            	Attendee speaker = new Attendee();
		            	//speaker.setFirstName(speakersObject);
		            	
		            	// flip the order of the person's name and skip the comma
		            	String speakerName = tempSpeakers.getString(i);
		            	int commaBetweenFirstAndLastName = speakerName.indexOf(",");
		            	String lastName = speakerName.substring(0, commaBetweenFirstAndLastName);
		            	String firstName = speakerName.substring(commaBetweenFirstAndLastName+1);
		            	speaker.setFirstName(firstName + " " + lastName);
		            	
		            	
		            	
		            	//speaker.setFirstName(tempSpeakers.getString(i));
		            	//System.out.println("Object:" + speakersObject);
		            	/*speaker.setLastName(speakersObject.getString("lastName"));
		            	speaker.setEmail(speakersObject.getString("email"));
		            	speaker.setTitle(speakersObject.getString("title"));
		            	speaker.setInstitution(speakersObject.getString("organization"));*/
		            	speakers.add(speaker);
		            }
					
					session.setSpeakers(speakers);
		
				sessions.add(session);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return sessions;
	}

	
	private String retrieveJSON(String feedUrl) throws MalformedURLException, IOException {
	    URL url = new URL(feedUrl);
	    URLConnection conn = url.openConnection();
	    String encoding = conn.getContentEncoding();
	    if (encoding == null) {
	    	encoding = "UTF-8";
	    }

	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
	    StringBuffer sb = new StringBuffer();
	    String line;
	    while ((line = rd.readLine()) != null) {
	    	sb.append(line);
	    }
	    rd.close();
	    String json = sb.toString();
	    return json;
    }

	@Override
    public Attendee findAttendeeById(String id) {
	    List<Attendee> attendees = findAllAttendees('A', 'Z');
	    for (Attendee attendee : attendees) {
	    	if (attendee.getId() != null && attendee.getId().equals(id)) {
	    		return attendee;
	    	}
        }
	    return null;
    }

	@Override
    public Session findSessionById(String id) {
		List<Session> sessions = findAllSessions("");
	    for (Session session : sessions) {
	     	if (session.getId() != null && session.getId().equals(id)) {
	    		return session;
	    	}
        }
	    return null;
    }

	public String getAttendeesFeedUrl() {
		return attendeesFeedUrl;
	}

	public void setAttendeesFeedUrl(String attendeesFeedUrl) {
		this.attendeesFeedUrl = attendeesFeedUrl;
	}

	public String getFeaturedSpeakersFeedUrl() {
		return featuredSpeakersFeedUrl;
	}

	public void setFeaturedSpeakersFeedUrl(String featuredSpeakersFeedUrl) {
		this.featuredSpeakersFeedUrl = featuredSpeakersFeedUrl;
	}

	public String getSessionsFeedUrl() {
		return sessionsFeedUrl;
	}

	public void setSessionsFeedUrl(String sessionsFeedUrl) {
		this.sessionsFeedUrl = sessionsFeedUrl;
	}

	public String getWelcomeFeedUrl() {
		return welcomeFeedUrl;
	}

	public void setWelcomeFeedUrl(String welcomeFeedUrl) {
		this.welcomeFeedUrl = welcomeFeedUrl;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}
	
}
