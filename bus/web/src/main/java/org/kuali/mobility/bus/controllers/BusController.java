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

package org.kuali.mobility.bus.controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.mobility.bus.service.BusService;
import org.kuali.mobility.configparams.entity.ConfigParam;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/bus")
public class BusController {
    
	@Autowired
	private ConfigParamService configParamService;
	
    public void setConfigParamService(ConfigParamService configParamService) {
		this.configParamService = configParamService;
	}

	@Autowired
    private BusService busService;
    public void setBusService(BusService busService) {
        this.busService = busService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {
    	return "bus/index";
    }
    
    @RequestMapping(value = "iubcampusbus", method = RequestMethod.GET)
    public String iubcampusbus(Model uiModel) {
    	return "bus/iubcampusbus";
    }
    
    @RequestMapping(value = "doublemap", method = RequestMethod.GET)
    public String doublemap(Model uiModel) {
    	return "bus/doublemap";
    }
    
    @RequestMapping(value = "bloomingtontransit", method = RequestMethod.GET)
    public String bloomingtontransit(Model uiModel) {
    	List<ConfigParam> params = configParamService.findAllByNameStartsWith("Bus.BT.");
        Collections.sort(params);	
    	uiModel.addAttribute("routes", params);
    	return "bus/bloomingtontransit";
    }
    
}
