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
package org.kuali.mobility.people.entity;

import java.util.List;

public interface Group extends DirectoryEntry {
	
	public String  getHashedDN();
	
	public String getDN();

	public void setDN(String userName);

	public String getDisplayName();

	public void setDisplayName(String displayName);
	
	public List<String> getDescriptions();

	public void setDescriptions(List<String> descriptions);
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public String gettelephoneNumber();
	
	public void settelephoneNumber(String telephoneNumber);
	
	public String getfacsimileTelephoneNumber();
	
	public void setfacsimileTelephoneNumber(String facsimileTelephoneNumber);

	
}