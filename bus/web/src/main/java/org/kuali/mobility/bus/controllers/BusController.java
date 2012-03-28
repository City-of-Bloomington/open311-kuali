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

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.kuali.mobility.bus.entity.BusRoute;
import org.kuali.mobility.bus.entity.BusStop;
import org.kuali.mobility.bus.service.BusService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
@RequestMapping("/bus")
public class BusController {
    
    private static Logger LOG = Logger.getLogger( BusController.class );

    private BusService service;
    public void setBusService(BusService service) {
        this.service = service;
    }
	
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String campus = null;
		if (user.getViewCampus() == null) {
			return "redirect:/campus?toolName=bus";
		} else {
			campus = user.getViewCampus();
		}

		List<BusRoute> routes = service.getRoutes(campus);
		LOG.debug( "Found "+routes.size()+" bus routes via local service for campus "+campus );
		uiModel.addAttribute("routes", routes);
		uiModel.addAttribute("campus", campus);
        
    	return "bus/index";
    }
    
    @RequestMapping(value = "/viewRoute", method = RequestMethod.GET)
    public String viewRoute(HttpServletRequest request, Model uiModel,@RequestParam(required = true) String routeId, @RequestParam(required = false) String campus ) {
		BusRoute route = service.getRoute(campus, Long.parseLong( routeId ) );

        uiModel.addAttribute("route", route);
		uiModel.addAttribute("campus", campus);
        
        return "bus/viewRoute";
    }

    @RequestMapping(value = "/viewStop", method = RequestMethod.GET)
    public String viewStop(HttpServletRequest request, Model uiModel,@RequestParam(required = true) String stopId, @RequestParam(required = false) String campus ) {
		BusStop stop = service.getStop(campus, Long.parseLong( stopId ) );

        if( stop != null ) {
            LOG.debug( "Found stop "+stop.getName() );
            LOG.debug( "Stop has "+stop.getSchedule().size()+" buses scheduled." );
        } else {
            LOG.debug( "Unable to find stop." );
        }
        uiModel.addAttribute("stop", stop);
		uiModel.addAttribute("campus", campus);
        
        return "bus/viewStop";
    }
}
