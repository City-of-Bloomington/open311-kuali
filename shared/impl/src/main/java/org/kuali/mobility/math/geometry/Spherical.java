package org.kuali.mobility.math.geometry;

public class Spherical {
	public static double computeHeading(Point from, Point to) {
		double deltaLng = degreesToRadians(to.getLongitude()-from.getLongitude());
		double lat1 = degreesToRadians(from.getLatitude());
		double lat2 = degreesToRadians(to.getLatitude());
		double y = Math.sin(deltaLng) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) - Math.sin(lat1)*Math.cos(lat2)*Math.cos(deltaLng);
		return radiansToDegrees(Math.atan2(y, x));
	}
	
	public static double computeDistanceBetween(Point from, Point to) {
		double R = 6378100; // radius of Earth in m
		double deltaLat = degreesToRadians(to.getLatitude()-from.getLatitude());
		double deltaLng = degreesToRadians(to.getLongitude()-from.getLongitude());
		double lat1 = degreesToRadians(from.getLatitude());
		double lat2 = degreesToRadians(to.getLatitude());

		double a = Math.sin(deltaLat/2.0) * Math.sin(deltaLat/2.0) +
		        Math.sin(deltaLng/2.0) * Math.sin(deltaLng/2.0) * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0-a)); 
		return R * c;
	}
	
	public static double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180.0;
	}
	
	public static double radiansToDegrees(double radians) {
		return radians * 180.0 / Math.PI;
	}
}
