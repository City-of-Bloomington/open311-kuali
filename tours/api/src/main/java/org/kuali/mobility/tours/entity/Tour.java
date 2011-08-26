package org.kuali.mobility.tours.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name="Tour")
@Table(name="TOUR_T")
public class Tour {

	@Id
    @SequenceGenerator(name="tour_sequence", sequenceName="SEQ_TOUR_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tour_sequence")
    @Column(name="TOUR_ID")
	private Long tourId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PATH")
	private String path;
	
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy="tourId")
	private List<POI> pointsOfInterest;
	
	@Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<POI> getPointsOfInterest() {
		return pointsOfInterest;
	}

	public void setPointsOfInterest(List<POI> pointsOfInterest) {
		this.pointsOfInterest = pointsOfInterest;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
}
