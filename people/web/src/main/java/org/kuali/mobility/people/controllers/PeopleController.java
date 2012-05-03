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

package org.kuali.mobility.people.controllers;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.SearchCriteria;
import org.kuali.mobility.people.service.DirectoryService;
import org.kuali.mobility.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/people")
public class PeopleController {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PeopleController.class);

	@Autowired
	private DirectoryService peopleService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel, HttpServletRequest request) {
		SearchCriteria s = new SearchCriteria();
		uiModel.addAttribute("search", s);
		//removeFromCache(request.getSession(), "People.Search.Results");
		request.setAttribute("watermark", "[Keyword] or [Last, First] or [First Last]");
		return "people/index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postSimpleSearch(Model uiModel, @ModelAttribute("search") SearchCriteria search, BindingResult result, HttpServletRequest request) {
		if (validateSimpleSearch(search, result, request)) {
			List<DirectoryEntry> people = peopleService.findEntries(search);
			
			//LOG.debug("people POST size: " + people.size());

			Map<String, String> userNameHashes = new HashMap<String, String>();
			for (Iterator d = people.iterator(); d.hasNext();) {
				Person p = (Person) d.next();
				if (p.getUserName()!=null)
				{
					//LOG.debug("p.getHashedUserName() " + p.getHashedUserName());
					userNameHashes.put(p.getHashedUserName(), p.getUserName());
				}
				if (p.getUserName() != null && search != null && search.getSearchText() != null && p.getUserName().trim().equals(search.getSearchText().trim()))
				{
					putInCache(request.getSession(), "People.Search.UniqueResult", p);
					d.remove();
				}
			}
			
			request.getSession().setAttribute("People.UserNames.Hashes", userNameHashes);
			putInCache(request.getSession(), "People.Search.Results", people);
			putInCache(request.getSession(), "People.Search.Criteria", search);
			//group info
			List<Group> group = peopleService.findSimpleGroup(search.getSearchText().trim());
			//
			Map<String, String> groupDNHashes = new HashMap<String, String>();
			for (Iterator d = group.iterator(); d.hasNext();) 
			{
				Group g = (Group) d.next();
				if (g.getDisplayName()!=null)
				{
					LOG.debug("g.getHashedDN() " + g.getHashedDN());
					groupDNHashes.put(g.getHashedDN(), g.getDN());
				}
			}
			request.getSession().setAttribute("Group.distinguishedName.Hashes", groupDNHashes);
			//LOG.debug("group POST size: " + group.size());
			putInCache(request.getSession(), "Group.Search.Results", group);
			request.setAttribute("watermark", "[Keyword] or [Last, First] or [First Last]");

			return "people/index";
		} else {
			return "people/index";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getListJson(HttpServletRequest request) {
		List<Person> people = (List<Person>) getFromCache(request.getSession(), "People.Search.Results");
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Person up = (Person) getFromCache(request.getSession(), "People.Search.UniqueResult");
		
		//
		if (up != null) 
		{
			// LOG.debug("up data..");
			List<Person> u = new ArrayList<Person>();
			u.add(up);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("heading", "Exact network id match");
			m.put("directoryEntries", u);
			results.add(m);
			
		}
	
		if (people == null || people.size() == 0) 
		{
			LOG.debug("No People Info");
		}
		else
		{
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("heading", "People");
			if (people.size()==1 && people.get(0) != null && people.get(0).getUserName()==null) {
				//Too many people found. DirectoryDaoUMImpl creates an empty Person object in this case. 
				m.put("error", "Too many people found. Please be more specific.");
			}
			else {
				m.put("directoryEntries", people);
			}
			results.add(m);
		}
		List<Group> group = (List<Group>) getFromCache(request.getSession(), "Group.Search.Results");
		if (group == null || group.size() == 0) 
		{
			LOG.debug("No Group Info");
		}
		else
		{
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("heading", "Group");
				//
				if (group.size()==1 && group.get(0) != null && group.get(0).getDN().equalsIgnoreCase("error"))
				{
					//Too many people found. DirectoryDaoUMImpl creates an empty Person object in this case. 
					m.put("error", "Too many groups found. Please be more specific.");
				}
				else 
				{
					m.put("directoryEntriesgroup", group);
					LOG.debug(" results map size " + m.size());
				}
			
				results.add(m);
			
		}
		return new JSONSerializer().exclude("*.class").deepSerialize(results);
    }
	
	
	@RequestMapping(value = "/advanced", method = RequestMethod.GET)
	public String viewSearchForm(Model uiModel) {
		SearchCriteria s = new SearchCriteria();
		s.setStatus("Any");
		uiModel.addAttribute("search", s);
		uiModel.addAttribute("locations", Constants.CAMPUS_NAMES);

		Map<String, String> statusTypes = getStatusTypes();
		uiModel.addAttribute("statusTypes", statusTypes);

		return "people/form";
	}

	@RequestMapping(value = "/advanced", method = RequestMethod.POST)
	public String postSearchForm(Model uiModel, @ModelAttribute("search") SearchCriteria search, BindingResult result, HttpServletRequest request) {
		if (validateAdvancedSearch(search, result)) {
			List<DirectoryEntry> people = peopleService.findEntries(search);

			Map<String, String> userNameHashes = new HashMap<String, String>();
			for (DirectoryEntry d : people) {
				Person p = (Person)d;
				userNameHashes.put(p.getHashedUserName(), p.getUserName());
			}
			request.getSession().setAttribute("People.UserNames.Hashes", userNameHashes);
			
			putInCache(request.getSession(), "People.Search.Results", people);

			return "people/list";
		} else {
			Map<String, String> statusTypes = getStatusTypes();
			uiModel.addAttribute("statusTypes", statusTypes);
			uiModel.addAttribute("locations", Constants.CAMPUS_NAMES);
			return "people/form";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{userNameHash}", method = RequestMethod.GET)
	public String getUserDetails(Model uiModel, HttpServletRequest request, @PathVariable("userNameHash") String userNameHash) {

		Map<String, Object> details = new HashMap<String, Object>();

		Map<String, String> userNameHashes = (Map<String, String>) request.getSession().getAttribute("People.UserNames.Hashes");
		Person p = null;
		if (userNameHashes != null) {
			String userName = userNameHashes.get(userNameHash);
			p = peopleService.lookupPerson(userName);
		}
		
		details.put("person", p);
		details.put("loggedIn", false);

		putInCache(request.getSession(), "People.Search.Results.Person", details);
		
		return "people/details";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/group/{hashedDN}", method = RequestMethod.GET)
	public String getGroupDetails(Model uiModel, HttpServletRequest request, @PathVariable("hashedDN") String hashedDisplayName) {

		Map<String, Object> details = new HashMap<String, Object>();

		Map<String, String> GroupNameHashes = (Map<String, String>) request.getSession().getAttribute("Group.distinguishedName.Hashes");
		Group g = null;
		if (hashedDisplayName != null) {
			String groupName = GroupNameHashes.get(hashedDisplayName);
			LOG.debug("group:" + hashedDisplayName + "groupname:" + groupName);
			g = peopleService.lookupGroup(groupName);
			/*if(g != null)
			LOG.debug("group detail " + g.getDisplayName());*/
		}
		details.put("group", g);
		details.put("loggedIn", false);

		putInCache(request.getSession(), "Group.Search.Results.Group", details);
		
		return "people/groupdetails";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/groupdetails", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getGroupDetailsJson(HttpServletRequest request) {
		Map<String, Object> details = (Map<String, Object>) getFromCache(request.getSession(), "Group.Search.Results.Group");
		
		return new JSONSerializer().exclude("*.class").deepSerialize(details);
    }

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/details", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getUserDetailsJson(HttpServletRequest request) {
		Map<String, Object> details = (Map<String, Object>) getFromCache(request.getSession(), "People.Search.Results.Person");
		
		return new JSONSerializer().exclude("*.class").deepSerialize(details);
    }

	
	@RequestMapping(value = "/image/{hash}", method = RequestMethod.GET)
	public void getImage(@PathVariable("hash") String imageKeyHash, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
		byte[] byteArray = (byte[]) request.getSession().getAttribute("People.Image.Email." + imageKeyHash);
		if (byteArray != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(byteArray.length);
			baos.write(byteArray);
			if (baos != null) {
				ServletOutputStream sos = null;
				try {
					response.setContentLength(baos.size());
					sos = response.getOutputStream();
					baos.writeTo(sos);
					sos.flush();
				} catch (Exception e) {
					LOG.error("error creating image file", e);
				} finally {
					try {
						baos.close();
						sos.close();
					} catch (Exception e1) {
						LOG.error("error closing output stream", e1);
					}
				}
			}
		}
	}

	private boolean validateSimpleSearch(SearchCriteria search, BindingResult result, HttpServletRequest request) {
		boolean hasErrors = false;
		//Errors errors = ((Errors) result);
		if ((search.getSearchText() == null || search.getSearchText().trim().isEmpty())) {
			//errors.rejectValue("searchText", "", "You must provide at least one letter to search.");
			request.setAttribute("watermark", "You must provide at least one letter to search.");
			hasErrors = true;
		}
		return !hasErrors;
	}

	private boolean validateAdvancedSearch(SearchCriteria search, BindingResult result) {
		boolean hasErrors = false;
		Errors errors = ((Errors) result);
		if ((search.getLastName() == null || search.getLastName().trim().isEmpty()) && (search.getFirstName() == null || search.getFirstName().trim().isEmpty()) && (search.getUserName() == null || search.getUserName().trim().isEmpty())) {
			errors.rejectValue("lastName", "", "You must provide at least one letter of the name or the entire username of the person you are searching for.");
			hasErrors = true;
		}
		return !hasErrors;
	}

	public void setPeopleService(DirectoryService peopleService) {
		this.peopleService = peopleService;
	}


	private void putInCache(HttpSession session, String key, Object item) {
		session.setAttribute(key, item);
	}

	private Object getFromCache(HttpSession session, String key) {
		return session.getAttribute(key);
	}

	private void removeFromCache(HttpSession session, String key) {
		session.removeAttribute(key);
	}
	
	private Map<String, String> getStatusTypes() {
		Map<String, String> statusTypes = new LinkedHashMap<String, String>();
		statusTypes.put("Any", "Any Status");
		statusTypes.put("Student", "Student");
		statusTypes.put("Faculty", "Faculty");
		statusTypes.put("Employee", "Employee");
		return statusTypes;
	}


}
