package org.kuali.mobility.util.mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.kuali.mobility.util.mapper.entity.DataMapping;
import org.kuali.mobility.util.mapper.entity.MappingElement;

public class JAXBMapperImpl implements JAXBDataMapper{


	private Integer connectionTimeoutMs = new Integer(5000);

	private Integer readTimeoutMs = new Integer(10000);;

	/*@Override
	public <B extends Object> B mapData(B responseObject, final URL source, final String mappingFile) throws ClassNotFoundException, IOException {
		return mapData(responseObject, source, mappingFile, null);
	}

	@Override
	public <B extends Object> B mapData(B responseObject, final URL source, final URL mappingFileUrl) throws ClassNotFoundException, IOException {
		return mapData(responseObject, source, mappingFileUrl, null);
	}
*/
		
	public <B, C extends Object> B mapData(B responseObject, C objectFactory, final String source, final boolean isUrl, final String mappingFile) throws ClassNotFoundException, IOException {
		
		 try {
				
				InputStream is=mapData(mappingFile);

				Map<String, Source> metadataSourceMap = new HashMap<String, Source>();
		        metadataSourceMap.put(responseObject.getClass().getPackage().getName(), new StreamSource(is));
		 
		        Map<String, Object> properties = new HashMap<String, Object>();
		        properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, metadataSourceMap);
		        
		        Class[] classes=new Class[2];
		        classes[0]=responseObject.getClass();
		        classes[1]=objectFactory.getClass();
		        
		        JAXBContext jaxbContext = JAXBContext.newInstance(classes, properties);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				
				if(isUrl) {
					URL sourceURL = new URL(source);
					URLConnection con = sourceURL.openConnection();
					con.setConnectTimeout(connectionTimeoutMs);
					con.setReadTimeout(readTimeoutMs);
					InputStream in = con.getInputStream();
					responseObject= (B) jaxbUnmarshaller.unmarshal(in);
				}
				else {
					responseObject= (B) jaxbUnmarshaller.unmarshal(this.getClass().getClassLoader().getResourceAsStream(source));
				}
				
			  } catch (JAXBException e) {
				e.printStackTrace();
			  } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }

		
		return responseObject;
	}

	
	public InputStream mapData(String mappingFile) throws ClassNotFoundException {

		InputStream is=null;

		JAXBDataConfig dc = new JAXBDataConfig();
		List<DataMapping> mappings = null;
		try {
			mappings = dc.loadConfiguation(mappingFile);
		} catch (IOException ioe) {
		}
		
		try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("xml-bindings");
		doc.appendChild(rootElement);
		
		Attr attr = doc.createAttribute("xmlns");
		attr.setValue("http://www.eclipse.org/eclipselink/xsds/persistence/oxm");
		rootElement.setAttributeNode(attr);
		
		attr = doc.createAttribute("version");
		attr.setValue("2.1");
		rootElement.setAttributeNode(attr);
		
		Element javatypes = doc.createElement("java-types");
		rootElement.appendChild(javatypes);
		
		for (DataMapping mapping : mappings)
		{
			Element javatype = doc.createElement("java-type");
			attr = doc.createAttribute("name");
			attr.setValue(mapping.getClassName());
			javatype.setAttributeNode(attr);
			
			if(mapping.getRootElement()!=null)
			{
				Element xmlRootElement = doc.createElement("xml-root-element");
				javatype.appendChild(xmlRootElement);
				attr = doc.createAttribute("name");
				attr.setValue(mapping.getRootElement());
				xmlRootElement.setAttributeNode(attr);
				
			}
			
			Element javaattributes = doc.createElement("java-attributes");
			javatype.appendChild(javaattributes);
			
			for (MappingElement map : mapping.getMappings()) {
				
				if((map.getMapFrom()!=map.getMapTo())||(map.getPath()!=null))
				{
					Element xmlelement = doc.createElement("xml-element");
					javaattributes.appendChild(xmlelement);
					attr = doc.createAttribute("java-attribute");
					attr.setValue(map.getMapTo());
					xmlelement.setAttributeNode(attr);
					
					if(map.getMapFrom()!=map.getMapTo())
					{
						String path=map.getMapFrom()+"/text()";
						attr = doc.createAttribute("xml-path");
						attr.setValue(path);
						xmlelement.setAttributeNode(attr);
					}
					else if(map.getPath()!=null)
					{
						attr = doc.createAttribute("xml-path");
						attr.setValue(map.getPath());
						xmlelement.setAttributeNode(attr);
					}
				}
			}
			
			
			javatypes.appendChild(javatype);
		}
	
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result =  new StreamResult(new File("C:\\file.xml"));
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		StreamResult outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
		is = new ByteArrayInputStream(outputStream.toByteArray());

		transformer.transform(source, result);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return is;
	}
}
