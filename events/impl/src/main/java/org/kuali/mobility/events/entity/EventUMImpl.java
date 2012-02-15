package org.kuali.mobility.events.entity;

import java.util.ArrayList;
import java.util.List;

public class EventUMImpl extends EventImpl {

	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
		if( getDescription() == null )
		{
			List<String> myDescription = new ArrayList<String>();
			myDescription.add( desc );
			this.setDescription( myDescription );
		}
	}
	
	public List<String> getDescription()
	{
		List<String> myDescription = new ArrayList<String>();
		myDescription.add( desc );
		return myDescription;
	}
	
}
