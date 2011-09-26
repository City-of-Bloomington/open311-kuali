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
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import org.kuali.mobility.conference.entity.Session;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl implements ConferenceService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConferenceServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentBlock> findAllContentBlocks() {
		List<ContentBlock> contentBlocks = new ArrayList<ContentBlock>();
		try {
			String json = retrieveJSON("http://statewideit.iu.edu/program/sessions/welcome.json");

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
	public List<Attendee> findAllAttendees() {
		List<Attendee> attendees = new ArrayList<Attendee>();
		try {
			String json = retrieveJSON("http://statewideit.iu.edu/program/sessions/attendeesfeed.php");

			JSONArray attendeeArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = attendeeArray.iterator(); iter.hasNext();) {
				try {
					JSONObject attendeeObject = iter.next();
					
					Attendee attendee = new Attendee();
					attendee.setId(attendeeObject.getString("id"));
					//attendee.setCellPhone(attendeeObject.getString("cellPhone"));
					//attendee.setWorkPhone(attendeeObject.getString("workPhone"));
					attendee.setEmail(attendeeObject.getString("email"));
					attendee.setFirstName(attendeeObject.getString("firstName"));
					attendee.setLastName(attendeeObject.getString("lastName"));
					attendee.setInstitution(attendeeObject.getString("institution"));
					//attendee.setWorkAddress1(attendeeObject.getString("workAddress1"));
					//attendee.setWorkAddress2(attendeeObject.getString("workAddress2"));
					//attendee.setWorkCity(attendeeObject.getString("workCity"));
					//attendee.setWorkState(attendeeObject.getString("workState"));
					//attendee.setWorkZip(attendeeObject.getString("workZip"));
					//attendee.setCountry(attendeeObject.getString("country"));
					
					attendees.add(attendee);
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
	//@Override
	public List<Session> findAllSessions(String date) {
	
		List<Session> sessions = new ArrayList<Session>();
		try {
			//String json = retrieveJSON("http://localhost:9999/mdot/testdata/conferenceSessions.json");
			String dateString = (null == date ? "" : date);
			String json = retrieveJSON("http://statewideit.iu.edu/program/sessions/programfeed.php?d=" + dateString);

			JSONArray sessionArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = sessionArray.iterator(); iter.hasNext();) {
				try {
					JSONObject sessionObject = iter.next();
					
					Session session = new Session();
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
						//DateFormat formatter = new SimpleDateFormat("E, MMM dd yyyy hh:mm a z");
						//DateFormat formatter = new SimpleDateFormat("hh:mm a");
						DateFormat formatter = new SimpleDateFormat("E hh:mm a");
						String formattedStartDate, formattedEndDate;
						formattedStartDate = formatter.format(startDate);
						formattedEndDate = formatter.format(endDate);
						session.setStartTime(formattedStartDate);
						session.setEndTime(formattedEndDate);
					} catch (ParseException e) {
						//System.out.println("Exception :" + e);
					}
					
					//System.out.println(session.getTitle());
					
					JSONArray tempSpeakers = sessionObject.getJSONArray("speakers");
					//for (Object object : tempSpeakers) {
	                //    System.out.println(object);	
                    //}
		
					
				List<Attendee> speakers = new ArrayList<Attendee>();
					
					for (int i = 0; i < tempSpeakers.size(); i++) {
		            	JSONObject speakersObject = tempSpeakers.getJSONObject(i);

		            	Attendee speaker = new Attendee();
		            	speaker.setFirstName(speakersObject.getString("firstName"));
		            	speaker.setLastName(speakersObject.getString("lastName"));
		            	speaker.setEmail(speakersObject.getString("email"));
		            	
		       
		            	//System.out.println(session.getTitle() + " - " + speaker.getFirstName() + " " + speaker.getLastName());
		            	
		            	
		            	
		                //Forum item = new Forum();
		                //item.setForumId(object.getString("forumId"));
		                //item.setTitle(object.getString("forumTitle"));
		                
		                /*
		                JSONArray sessionSpeakersArray = object.getJSONArray("sessionSpeakers");
		                int unreadCount = 0;
		                for (int j = 0; j < topicsArray.size(); j++) {
		                    unreadCount += topicsArray.getJSONObject(j).getInt("unreadMessagesCount");
		                }
		                */
		                /*item.setUnreadCount(unreadCount);*/
		                //forums.add(item);
		            	
	            	
		            	speakers.add(speaker);
		            }
					
					session.setSpeakers(speakers);
				
					/*for (int i = 0; i < itemArray.size(); i++) {
		            	JSONObject object = itemArray.getJSONObject(i);
		                Forum item = new Forum();
		                item.setForumId(object.getString("forumId"));
		                item.setTitle(object.getString("forumTitle"));
		                
		                JSONArray topicsArray = object.getJSONArray("topics");
		                int unreadCount = 0;
		                for (int j = 0; j < topicsArray.size(); j++) {
		                    unreadCount += topicsArray.getJSONObject(j).getInt("unreadMessagesCount");
		                }
		                item.setUnreadCount(unreadCount);
		                forums.add(item);
		            }*/
			
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

	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
	    List<Attendee> attendees = findAllAttendees();
	    for (Attendee attendee : attendees) {
	    	if (attendee.getId() != null && attendee.getId().equals(id)) {
	    		return attendee;
	    	}
        }
	    return null;
    }

	@Override
    public Session findSessionById(String id) {
	    // TODO Auto-generated method stub
	    return null;
    }
	
}
