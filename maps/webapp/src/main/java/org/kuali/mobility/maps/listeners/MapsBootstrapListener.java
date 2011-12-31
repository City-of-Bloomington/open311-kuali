package org.kuali.mobility.maps.listeners;

import java.util.ArrayList;
import java.util.List;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.shared.listeners.BootstrapListener;

public class MapsBootstrapListener extends BootstrapListener {

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
		HomeTool ht = new HomeTool(home, tool, 2);
		tools.add(ht);

		home.setHomeTools(tools);
		return home;
	}
	
}
