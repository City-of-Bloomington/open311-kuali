package org.kuali.mobility.tours.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name="POI")
@Table(name="TOUR_POI_T")
public class POI {

	@Id
    @SequenceGenerator(name="poi_sequence", sequenceName="SEQ_TOUR_POI_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="poi_sequence")
    @Column(name="POI_ID")
	private Long poiId;
	
	@Basic
    @Column(name="TOUR_ID", insertable=false, updatable=false)
	private Long tourId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TOUR_ID", nullable=false)
	protected Tour tour;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="POI_TYPE")
	private String type;
	
	@Column(name="LOCATION_ID")
	private String locationId;
	
	@Column(name="LAT")
	private double latitude;
	
	@Column(name="LNG")
	private double longitude;
	
	@Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

	public Long getPoiId() {
		return poiId;
	}

	public void setPoiId(Long poiId) {
		this.poiId = poiId;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}
}
