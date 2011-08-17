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
package org.kuali.mobility.sakai.entity;

import java.util.ArrayList;
import java.util.List;

public class Home {
	private List<Term> courses;
	private List<Site> projects;
	private List<Site> other;
	private List<Site> todaysCourses;
	private boolean showTodayTab = false;

	public Home() {
		courses = new ArrayList<Term>();
		projects = new ArrayList<Site>();
		other = new ArrayList<Site>();
		todaysCourses = new ArrayList<Site>();
	}

	public List<Term> getCourses() {
		return courses;
	}

	public void setCourses(List<Term> courses) {
		this.courses = courses;
	}

	public List<Site> getProjects() {
		return projects;
	}

	public void setProjects(List<Site> projects) {
		this.projects = projects;
	}

	public List<Site> getOther() {
		return other;
	}

	public void setOther(List<Site> other) {
		this.other = other;
	}

	public List<Site> getTodaysCourses() {
		return todaysCourses;
	}

	public void setTodaysCourses(List<Site> todaysCourses) {
		this.todaysCourses = todaysCourses;
	}

	public boolean isShowTodayTab() {
		return showTodayTab;
	}

	public void setShowTodayTab(boolean showTodayTab) {
		this.showTodayTab = showTodayTab;
	}
}
