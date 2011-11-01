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

package org.kuali.mobility.coursedescriptions.controllers;

import java.util.List;

import org.kuali.mobility.coursedescriptions.entity.Course;
import org.kuali.mobility.coursedescriptions.entity.CourseSubject;
import org.kuali.mobility.coursedescriptions.service.CourseDescriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller 
@RequestMapping("/courseDescriptions")
public class CourseDescriptionsController {
    
    @Autowired
    private CourseDescriptionsService courseDescriptionsService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model uiModel) {
    	return "coursedescriptions/index";
    }
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getCourseSubjectsJson() {
    	List<CourseSubject> courseSubjects = courseDescriptionsService.getCourseSubjects("Optometry");
    	return new JSONSerializer().exclude("*.class").deepSerialize(courseSubjects);
    }
    
	@RequestMapping(value = "/courseDetails", method = RequestMethod.GET)
	public String courseDetails(Model uiModel, @RequestParam(required = true) String id, @RequestParam(required = true) String number) {
		uiModel.addAttribute("id", id);
		uiModel.addAttribute("number", number);
		return "coursedescriptions/courseDetails";
	}
    
    @RequestMapping(value = "/courseDetails", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getCourseJson(Model uiModel, @RequestParam(value="number", required = true) String courseNumber, @RequestParam(value="id", required = true) String subjectId) {
    	Course course = courseDescriptionsService.getCourseByNumber(subjectId, courseNumber);
    	return new JSONSerializer().exclude("*.class").deepSerialize(course);
    }
}
