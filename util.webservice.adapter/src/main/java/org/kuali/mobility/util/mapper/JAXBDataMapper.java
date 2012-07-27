package org.kuali.mobility.util.mapper;

import java.io.IOException;
import java.net.URL;

public interface JAXBDataMapper {

	public <B, C extends Object> B mapData(B responseObject, C objectFactory, final String source, final boolean isUrl, final String mappingFile) throws ClassNotFoundException, IOException;

}
