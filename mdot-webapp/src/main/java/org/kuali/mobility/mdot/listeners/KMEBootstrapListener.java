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

package org.kuali.mobility.mdot.listeners;

import java.util.ArrayList;
import java.util.List;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.shared.listeners.BootstrapListener;

public class KMEBootstrapListener extends BootstrapListener {
	
	@Override
	public HomeScreen bootstrapHomeScreenTools(AdminService adminService) {
		HomeScreen home = new HomeScreen();
		home.setAlias("PUBLIC");
		home.setTitle("Kuali Mobile");
		adminService.saveHomeScreen(home);

		List<HomeTool> tools = new ArrayList<HomeTool>();
		
		Tool tool = new Tool();
		tool.setAlias("maps");
		tool.setTitle("Maps");
		tool.setUrl("maps");
		tool.setDescription("Get from here to there. Search for buildings by name.");
		tool.setIconUrl("images/service-icons/srvc-maps.png");
		adminService.saveTool(tool);
		HomeTool ht = new HomeTool(home, tool, 0);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("conference");
		tool.setTitle("Conference");
		tool.setUrl("conference");
		tool.setDescription("2011 Conference: \"My digital life @ IU\"");
		tool.setIconUrl("images/service-icons/srvc-itstatewide.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 1);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("dining");
		tool.setTitle("Dining");
		tool.setUrl("dining");
		tool.setDescription("Find out what's cooking at your dining hall!");
		tool.setIconUrl("images/service-icons/srvc-dining.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 2);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("news");
		tool.setTitle("News");
		tool.setUrl("news");
		tool.setDescription("The latest buzz on IU's exciting events and achievements.");
		tool.setIconUrl("images/service-icons/srvc-news.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 3);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("emergencycontacts");
		tool.setTitle("Emergency Contacts");
		tool.setUrl("emergencycontacts");
		tool.setDescription("Police and medical phone numbers.");
		tool.setIconUrl("images/service-icons/srvc-emergency.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 4);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("labs");
		tool.setTitle("Computer Labs");
		tool.setUrl("computerlabs");
		tool.setDescription("See which campus computers labs have an open seat.");
		tool.setIconUrl("images/service-icons/srvc-stc.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 5);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("alerts");
		tool.setTitle("Campus Alerts");
		tool.setUrl("alerts");
		tool.setDescription("See a list of active campus alert messages.");
		tool.setIconUrl("images/service-icons/srvc-alerts-green.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 6);
		tools.add(ht);

		tool = new Tool();
		tool.setAlias("feedback");
		tool.setTitle("Feedback");
		tool.setUrl("feedback");
		tool.setDescription("Submit questions and comments about Kuali Mobile.");
		tool.setIconUrl("images/service-icons/srvc-feedback.png");
		adminService.saveTool(tool);
		ht = new HomeTool(home, tool, 7);
		tools.add(ht);

		home.setHomeTools(tools);
		return home;
	}
	
}
