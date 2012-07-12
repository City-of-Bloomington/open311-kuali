package org.kuali.mobility.util.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.kuali.mobility.util.mapper.entity.DataMapping;
import org.kuali.mobility.util.mapper.entity.MappingElement;

public class JAXBDataConfig {

	public List<DataMapping> loadConfiguation(final String fileName) throws IOException {
		final XStream xstream = configureXstream();
		return (List<DataMapping>) xstream.fromXML(getClass().getClassLoader().getResourceAsStream(fileName));
	}

	public List<DataMapping> loadConfiguation(final URL url, Integer connectionTimeout, Integer readTimeout) throws IOException {
		final XStream xstream = configureXstream();

		URLConnection con = url.openConnection();
		con.setConnectTimeout(connectionTimeout);
		con.setReadTimeout(readTimeout);
		InputStream in = con.getInputStream();

		return (List<DataMapping>) xstream.fromXML(in);
	}

	private XStream configureXstream() {
		final XStream xstream = new XStream(new DomDriver());
		xstream.alias("dataMappings", List.class);
		xstream.alias("dataMapping", DataMapping.class);
		xstream.addImplicitCollection(DataMapping.class, "mappings", "mapping", MappingElement.class);
		xstream.aliasAttribute(MappingElement.class, "mapTo", "mapTo");
		xstream.aliasAttribute(MappingElement.class, "mapFrom", "mapFrom");
		xstream.aliasAttribute(MappingElement.class, "path", "path");
		
		xstream.processAnnotations(DataMapping.class);
		return xstream;
	}
}
