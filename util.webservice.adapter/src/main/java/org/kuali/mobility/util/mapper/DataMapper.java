package org.kuali.mobility.util.mapper;

import java.io.IOException;
import java.net.URL;

public interface DataMapper {

	public <B extends Object> B mapData(B responseObject, final URL source, final String mappingFile, String listName) throws ClassNotFoundException, IOException;

	public <B extends Object> B mapData(B responseObject, final URL source, final URL mappingFileUrl, String listName) throws ClassNotFoundException, IOException;

	public <B extends Object> B mapData(B responseObject, final String dataFile, final String mappingFile, String listName) throws ClassNotFoundException;

	public <B extends Object> B mapData(B responseObject, final URL source, final String mappingFile) throws ClassNotFoundException, IOException;

	public <B extends Object> B mapData(B responseObject, final URL source, final URL mappingFileUrl) throws ClassNotFoundException, IOException;

	public <B extends Object> B mapData(B responseObject, final String dataFile, final String mappingFile) throws ClassNotFoundException;

}
