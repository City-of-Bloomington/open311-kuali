package org.kuali.mobility.admin.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private ConfigParamService configParamService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model uiModel) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String ipAddress = "";
    	if (user.isMember(configParamService.findValueByName("Admin.Group.Name"))) {
    		try {
				ipAddress = "<p><i><small>Server: " + InetAddress.getLocalHost().getHostName() + "</small></i></p>";
			} catch (UnknownHostException e) {}
    	}
    	uiModel.addAttribute("ipAddress", ipAddress);
		
    	return "system/index";
    }
}
