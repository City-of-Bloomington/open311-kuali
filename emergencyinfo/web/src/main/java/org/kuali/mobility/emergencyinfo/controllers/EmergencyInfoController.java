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
 
package org.kuali.mobility.emergencyinfo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.emergencyinfo.entity.EmergencyInfo;
import org.kuali.mobility.emergencyinfo.service.EmergencyInfoService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
@RequestMapping("/emergencycontacts")
public class EmergencyInfoController {
    
    @Autowired
    private EmergencyInfoService emergencyInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model uiModel, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String selectedCampus = "UA";
    	if (user.getViewCampus() == null) {
    		return "redirect:/campus?toolName=emergencycontacts";
    	} else {
    		selectedCampus = user.getViewCampus();
    	}
    	List<EmergencyInfo> infos = emergencyInfoService.findAllEmergencyInfoByCampus(selectedCampus);
    	uiModel.addAttribute("emergencyinfos", infos);
    	return "emergencyinfo/list";
    }
    
    @RequestMapping(value = "/{emergencyInfoId}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Object get(@PathVariable("emergencyInfoId") Long emergencyInfoId) {
        EmergencyInfo emergencyInfo = emergencyInfoService.findEmergencyInfoById(emergencyInfoId);
        if (emergencyInfo == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return emergencyInfo.toJson();
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public String getListJson(HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String selectedCampus = user.getViewCampus();
    	List<EmergencyInfo> infos = emergencyInfoService.findAllEmergencyInfoByCampus(selectedCampus);
    	return emergencyInfoService.toJson(infos);
    } 
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> post(@RequestBody String json) {
        emergencyInfoService.saveEmergencyInfo(emergencyInfoService.fromJsonToEntity(json));
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/collection", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> postAll(@RequestBody String json) {
        for (EmergencyInfo emergencyInfo: emergencyInfoService.fromJsonToCollection(json)) {
            emergencyInfoService.saveEmergencyInfo(emergencyInfo);
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> put(@RequestBody String json) {
        if (emergencyInfoService.findEmergencyInfoById(emergencyInfoService.fromJsonToEntity(json).getEmergencyInfoId()) == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        } else {
            if (emergencyInfoService.saveEmergencyInfo(emergencyInfoService.fromJsonToEntity(json)) == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/collection", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putAll(@RequestBody String json) {
        for (EmergencyInfo emergencyInfo: emergencyInfoService.fromJsonToCollection(json)) {
            if (emergencyInfoService.findEmergencyInfoById(emergencyInfo.getEmergencyInfoId()) == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            } else {
                if (emergencyInfoService.saveEmergencyInfo(emergencyInfo) == null) {
                    return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
                }
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{emergencyInfoId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> delete(@PathVariable("emergencyInfoId") Long emergencyInfoId) {
        EmergencyInfo emergencyInfo = emergencyInfoService.findEmergencyInfoById(emergencyInfoId);
        if (emergencyInfo == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        emergencyInfoService.deleteEmergencyInfoById(emergencyInfo.getEmergencyInfoId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
        
}
