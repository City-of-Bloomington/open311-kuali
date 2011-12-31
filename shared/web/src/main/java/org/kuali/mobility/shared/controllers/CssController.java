package org.kuali.mobility.shared.controllers;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.shared.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/css/custom")
public class CssController {

	@Autowired
	private CoreService coreService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model uiModel) {      
    	uiModel.addAttribute("styles", coreService.getCssCustomizations());
    	return "institutional";
    }
}
