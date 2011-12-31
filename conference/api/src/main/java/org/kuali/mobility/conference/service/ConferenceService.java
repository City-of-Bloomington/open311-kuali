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

import java.util.List;

import org.kuali.mobility.conference.entity.Attendee;
import org.kuali.mobility.conference.entity.ContentBlock;
import org.kuali.mobility.conference.entity.MenuItem;
import org.kuali.mobility.conference.entity.Session;

public interface ConferenceService {

	List<ContentBlock> findAllContentBlocks();
	List<ContentBlock> findFeaturedSpeakers();
	List<Attendee> findAllAttendees(char start, char end);
	List<Session> findAllSessions(String date);
	Attendee findAttendeeById(String id);
	Session findSessionById(String id);
	String getToEmailAddress();
	String getFromEmailAddress();
	List<MenuItem> findAllMenuItems(String lang);
}
