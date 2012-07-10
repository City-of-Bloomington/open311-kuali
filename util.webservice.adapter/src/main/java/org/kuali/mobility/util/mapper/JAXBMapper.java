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

	public <B, C extends Object> B mapData(B responseObject, C objectFactory, final String dataFile, final String mappingFile) throws ClassNotFoundException {
		return mapData(responseObject, objectFactory, dataFile, mappingFile, null);
	}

	public <B> B mapData(B responseObject, URL source, String mappingFile, String listName) throws ClassNotFoundException, IOException {
		
		return responseObject;
	}

	public <B, C> B mapData(B responseObject, C objectFactory, String dataFile, String mappingFile, String listName) throws ClassNotFoundException {
		
		 try {
		 
		 System.out.println("Inside JAXBMapper map function");
		System.out.println(responseObject);
		System.out.println(responseObject.getClass()+" "+objectFactory.getClass());
		JAXBContext jaxbContext = JAXBContext.newInstance(responseObject.getClass(), objectFactory.getClass());
 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		System.out.println(this.getClass().getClassLoader().getResourceAsStream(dataFile).toString());
		responseObject = (B) jaxbUnmarshaller.unmarshal(this.getClass().getClassLoader().getResourceAsStream(dataFile));
		System.out.println(responseObject);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
		return responseObject;
	}

	public <B extends Object> B mapData(B responseObject, final URL source, final URL mappingFileUrl, String listName) throws ClassNotFoundException, IOException {
		
		return responseObject;
	}

}