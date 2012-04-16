package org.kuali.mobility.reporting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.web.multipart.MultipartFile;

// TODO: Move out of the reporting tool. 

@Entity
@Table(name="KME_FILE_T")
public class File {
		
    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false, updatable=false)
    private Long id;
	
    @Transient
    private MultipartFile file;

    @Lob
    @Column(name="FILE_DATA")
    private byte[] bytes;
    
    @Column(name="CNTNT_TYP")
    private String contentType;
    
    @Column(name="FILE_NM")
    private String fileName;
    
    @Version
	@Column(name = "VER_NBR")
	protected Long versionNumber;

	public File() {
	}

	public File(Long id, MultipartFile file) {
		this.id = id;
		this.file = file;
	}	
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	
}
