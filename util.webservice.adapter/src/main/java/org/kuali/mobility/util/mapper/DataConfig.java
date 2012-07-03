package org.kuali.mobility.util.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.kuali.mobility.util.mapper.entity.DataMapping;
import org.kuali.mobility.util.mapper.entity.MappingElement;

import com.thoughtworks.xstream.XStream;

public class DataConfig {
	private static final Logger logger = Logger.getLogger(DataConfig.class);

	public DataMapping loadConfiguation(final String fileName) throws IOException {
		final XStream xstream = configureXstream();
		return (DataMapping) xstream.fromXML(getClass().getClassLoader().getResourceAsStream(fileName));
	}

	public DataMapping loadConfiguation(final URL url, Integer connectionTimeout, Integer readTimeout) throws IOException {
		final XStream xstream = configureXstream();

		URLConnection con = url.openConnection();
		con.setConnectTimeout(connectionTimeout);
		con.setReadTimeout(readTimeout);
		InputStream in = con.getInputStream();

		return (DataMapping) xstream.fromXML(in);
	}

	private XStream configureXstream() {
		final XStream xstream = new XStream();
		xstream.alias("dataMapping", DataMapping.class);
		xstream.addImplicitCollection(DataMapping.class, "mappings", "mapping", MappingElement.class);
		xstream.aliasAttribute(MappingElement.class, "mapTo", "mapTo");
		xstream.aliasAttribute(MappingElement.class, "mapFrom", "mapFrom");
		xstream.aliasAttribute(MappingElement.class, "isAttribute", "attribute");
		xstream.aliasAttribute(MappingElement.class, "list", "list");
		xstream.aliasAttribute(MappingElement.class, "type", "type");
		xstream.aliasAttribute(MappingElement.class, "definedIn", "definedIn");
		xstream.aliasAttribute(MappingElement.class, "className", "className");
		
		xstream.processAnnotations(DataMapping.class);
		return xstream;
	}
}
