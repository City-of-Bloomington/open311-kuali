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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.kuali.mobility.conference.entity.Attendee;
import org.kuali.mobility.conference.entity.Session;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl implements ConferenceService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConferenceServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Attendee> findAllAttendees() {
		List<Attendee> attendees = new ArrayList<Attendee>();
		try {
			String json = retrieveJSON("http://localhost:9999/mdot/testdata/conferenceAttendees.json");

			JSONArray attendeeArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = attendeeArray.iterator(); iter.hasNext();) {
				try {
					JSONObject attendeeObject = iter.next();
					
					Attendee attendee = new Attendee();
					attendee.setCellPhone(attendeeObject.getString("cellPhone"));
					attendee.setWorkPhone(attendeeObject.getString("workPhone"));
					attendee.setEmail(attendeeObject.getString("email"));
					attendee.setFirstName(attendeeObject.getString("firstName"));
					attendee.setLastName(attendeeObject.getString("lastName"));
					attendee.setInstitution(attendeeObject.getString("institution"));
					attendee.setWorkAddress1(attendeeObject.getString("workAddress1"));
					attendee.setWorkAddress2(attendeeObject.getString("workAddress2"));
					attendee.setWorkCity(attendeeObject.getString("workCity"));
					attendee.setWorkState(attendeeObject.getString("workState"));
					attendee.setWorkZip(attendeeObject.getString("workZip"));
					attendee.setCountry(attendeeObject.getString("country"));
					
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
	@Override
	public List<Session> findAllSessions() {
	
		List<Session> sessions = new ArrayList<Session>();
		try {
			String json = retrieveJSON("http://localhost:9999/mdot/testdata/conferenceSessions.json");

			JSONArray sessionArray = (JSONArray) JSONSerializer.toJSON(json);
			
			for (Iterator<JSONObject> iter = sessionArray.iterator(); iter.hasNext();) {
				try {
					JSONObject sessionObject = iter.next();
					
					Session session = new Session();
					session.setTitle(sessionObject.getString("title"));
					session.setDescription(sessionObject.getString("description"));
					//System.out.println(session.getTitle());
					
					JSONArray tempSpeakers = sessionObject.getJSONArray("speakers");
					//for (Object object : tempSpeakers) {
	                //    System.out.println(object);	
                    //}
		
					
					List<Attendee> speakers = new ArrayList<Attendee>();
					
					for (int i = 0; i < tempSpeakers.size(); i++) {
		            	JSONObject speakersObject = tempSpeakers.getJSONObject(i);
		            	
		            	//System.out.println("\n");
		            	//System.out.println(i);
		            	//System.out.println(speakersObject);
		            	
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
	
}
