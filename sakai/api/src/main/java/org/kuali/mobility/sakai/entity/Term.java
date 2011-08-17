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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Term implements Serializable, Comparable<Term> {

	private static final long serialVersionUID = 4671279471060244186L;

	private String term;
	private int year;
	private Collection<Site> courses;
	private Terms termEnumVal;

	enum Terms {
		SPRING, SUMMER, FALL
	};

	public Term() {
		courses = new ArrayList<Site>();
	}

	@Override
	public int compareTo(Term o) {
		if (year == o.year) {
			if (termEnumVal != null && o.termEnumVal != null) {
				return termEnumVal.compareTo(o.termEnumVal);
			} else {
				return 0;
			}
		} else {
			return year - o.year;
		}
	};

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;

		if (term != null) {
			String[] split = term.split(" ");

			try {
				termEnumVal = Terms.valueOf(split[0].toUpperCase());
			} catch (Exception e) {
			}

			try {
				year = Integer.parseInt(split[1]);
			} catch (Exception e) {
			}
		}
	}

	public Collection<Site> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Site> courses) {
		this.courses = courses;
	}

}
