package org.kuali.mobility.dining.entity;

public interface Place {
		
		public String getName();
		public void setName(String name);
		
		public String getCampus() ;
		public void setCampus(String campus);
		
		public String getType();
		public void setType(String type);
		
		public String getLocation();
		public void setLocation(String location);
		
		public Place compact ();
}
