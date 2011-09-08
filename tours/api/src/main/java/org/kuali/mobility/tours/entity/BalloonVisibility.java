package org.kuali.mobility.tours.entity;

import de.micromata.opengis.kml.v_2_2_0.gx.TourPrimitive;

public class BalloonVisibility extends TourPrimitive {

	public static enum Visibility {SHOW, HIDE};
	
	private Visibility visibility;
	
	public BalloonVisibility (Visibility visibility) {
		this.setVisibility(visibility);
	}
	
	@Override
	public String toString() {
		String value = "<gx:balloonVisibility>";
		switch (visibility) {
		case SHOW:
			value += "1";
			break;
		case HIDE:
			value += "0";
		}
		value += "</gx:balloonVisibility>";
		return value;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
}
