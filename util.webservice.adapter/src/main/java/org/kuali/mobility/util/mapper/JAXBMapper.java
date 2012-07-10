package org.kuali.mobility.util.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JAXBMapper {
	
	public <B extends Object> B mapData(B responseObject, final URL source, final String mappingFile) throws ClassNotFoundException, IOException {
		return mapData(responseObject, source, mappingFile, null);
	}

	public <B extends Object> B mapData(B responseObject, final URL source, final URL mappingFileUrl) throws ClassNotFoundException, IOException {
		return mapData(responseObject, source, mappingFileUrl, null);
	}

	public <B extends Object> B mapData(B responseObject, final String dataFile, final String mappingFile) throws ClassNotFoundException {
		return mapData(responseObject, dataFile, mappingFile, null);
	}

	public <B> B mapData(B responseObject, URL source, String mappingFile, String listName) throws ClassNotFoundException, IOException {
		
		return responseObject;
	}

	public <B> B mapData(B responseObject, String dataFile, String mappingFile, String listName) throws ClassNotFoundException {
		
		 try {
 
		JAXBContext jaxbContext = JAXBContext.newInstance(responseObject.getClass());
 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		responseObject = (B) jaxbUnmarshaller.unmarshal(this.getClass().getClassLoader().getResourceAsStream(dataFile));
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
		return responseObject;
	}

	public <B extends Object> B mapData(B responseObject, final URL source, final URL mappingFileUrl, String listName) throws ClassNotFoundException, IOException {
		
		return responseObject;
	}

}