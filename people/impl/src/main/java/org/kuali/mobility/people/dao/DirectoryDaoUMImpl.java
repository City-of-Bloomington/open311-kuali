package org.kuali.mobility.people.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
import org.kuali.mobility.people.entity.GroupImpl;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.PersonImpl;
import org.kuali.mobility.people.entity.SearchCriteria;

import flexjson.JSONDeserializer;

public class DirectoryDaoUMImpl implements DirectoryDao {

	private static final Logger LOG = Logger.getLogger( DirectoryDaoUMImpl.class );
	
	private static final String SEARCH_URL = "https://mcommunity.umich.edu/mcPeopleService/people/compact/search";
	private static final String PERSON_LOOKUP_URL = "https://mcommunity.umich.edu/mcPeopleService/people/";
	private static final String GROUP_SEARCH_URL = "https://mcommunity.umich.edu/mcGroupService/group/search";
	private static final String GROUP_LOOKUP_URL = "https://mcommunity.umich.edu/mcGroupService/groupProfile/dn/";
	private static final String DEFAULT_CHARACTER_SET = "UTF-8";
	
	@Override
	public List<DirectoryEntry> findEntries(SearchCriteria search) {
		List<DirectoryEntry> de = new ArrayList<DirectoryEntry>();
	
		StringBuilder queryString = new StringBuilder();
		
		if (search.getSearchText() != null && !search.getSearchText().trim().isEmpty()) {
			queryString.append( "searchCriteria=" );
			queryString.append( search.getSearchText().trim() );
		}
		else
		{
			if( search.getFirstName() != null && !search.getFirstName().trim().isEmpty() )
			{
				queryString.append( "givenName=" );
				queryString.append( search.getFirstName().trim() );
				queryString.append( "&givenNameSearchType=startsWith&" );
			}
			if( search.getLastName() != null && !search.getLastName().trim().isEmpty() )
			{
				queryString.append( "sn=" );
				queryString.append( search.getFirstName().trim() );
				queryString.append( "&snNameSearchType=startsWith&" );
			}				
		}
		
		LOG.debug( "QueryString will be : "+queryString.toString() );
		
		try {
			URLConnection connection = new URL(SEARCH_URL).openConnection();
		
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", DEFAULT_CHARACTER_SET);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARACTER_SET);
			OutputStream output = null;

			output = connection.getOutputStream();
			output.write(queryString.toString().getBytes(DEFAULT_CHARACTER_SET));

			InputStream response = connection.getInputStream();
			String contentType = connection.getHeaderField("Content-Type");

			if (contentType != null && "application/json".equalsIgnoreCase(contentType) ) {
				LOG.debug( "Attempting to parse JSON using Gson." );
//		        Reader reader = new InputStreamReader(response);
				
			    BufferedReader reader = null;
			    try {
			        reader = new BufferedReader(new InputStreamReader(response, DEFAULT_CHARACTER_SET));
			        
			        for (String line; (line = reader.readLine()) != null;) {
			            // No people found {"person":[]}
			            // Too many results, exceed 300  {"person":[{}]}
			            LOG.debug(line);
			            
			            line = line.substring(10, line.length()-1);
			            
			            LOG.debug(line);

			            List<HashMap> people = new JSONDeserializer<List<HashMap>>().deserialize( line );
			            
				        if( people == null ) {
				        	LOG.debug( "Results were not parsed, no people found." );
				        } else {
				        	LOG.debug( "Found "+people.size()+" people results." );
				        }
				        
				        for( HashMap p : people )
				        {
//				        	if ( p.isEmpty() ) {
//				        		//Too many people found. Please supply more information
//				        		LOG.info("Empty result"); 
//				        	}
//				        	else {
					        	LOG.debug( "Found "+(String)p.get("uniqname") );
					        	Person entry = new PersonImpl();
					        	if( p.containsKey("uniqname") )
					        	{
					        		entry.setUserName( (String)p.get("uniqname") );
					        	}
					        	if( p.containsKey("name") )
					        	{
					        		entry.setDisplayName( (String)p.get("name") );
					        	}
					        	if( p.containsKey("aff") )
					        	{
					        		if( p.get("aff") instanceof String ) {
					        			List<String> aff = new ArrayList<String>();
					        			aff.add( (String)p.get("aff") );
					        			entry.setAffiliations( aff );
					        		} else {
					        			entry.setAffiliations( (List<String>)p.get("aff") );
					        		}
					        	}
	//				        	if( p.containsKey("title") )
	//				        	{
	//			        			entry.set( (String)p.get("title") );
	//				        	}
					        	de.add(entry);
//				        	}
				        }
			        }
			    } catch( UnsupportedEncodingException uee ) {
			    	LOG.error(uee.getLocalizedMessage());
			    } catch( IOException ioe ) {
			    	LOG.error( ioe.getLocalizedMessage() );
			    } finally {
			        if (reader != null) {
			        	try { 
			        		reader.close();
			        	} catch (IOException logOrIgnore) {
			        		LOG.error( logOrIgnore.getLocalizedMessage() );
			        	}
			        }
			    }
			} else {
				LOG.debug( "Content type was not application/json." );
			    // It's likely binary content, use InputStream/OutputStream.
			}
		} catch( IOException ioe ) {
			LOG.error( ioe.getLocalizedMessage() );
		}

//			
//			
//			for (Person p : findPeople(search)) {
//				de.add(p);
//			}
//		}

		return de;
	}

	@Override
	public Person lookupPerson(String personId) {
		Person person = null;
	
	    BufferedReader reader = null;
		try {
			URL service = new URL(PERSON_LOOKUP_URL+personId);
		    LOG.debug("Personlookupurl :"  + PERSON_LOOKUP_URL+personId);
	        reader = new BufferedReader(new InputStreamReader(service.openStream(), DEFAULT_CHARACTER_SET));
	        
	        for (String line; (line = reader.readLine()) != null;) {
	            LOG.debug(line);
	            
	            line = line.substring(10, line.length()-1);
	            
	            LOG.debug(line);
	            
	            HashMap raw = new JSONDeserializer<HashMap>().deserialize( line );
	            
		        if( raw == null ) {
		        	LOG.debug( "Results were not parsed, "+personId+" not found." );
		        } 
		        else if ( raw.containsKey( "errors" ) && raw.get("errors")!=null && raw.get("errors").hashCode()!=0 ){
		        	// TODO get error description
		        	// Object v = raw.get("errors");
		        
		        	LOG.debug("errors:" + raw.get("errors"));
		        }		        
		        else {
		        	person = new PersonImpl();
		        	if( raw.containsKey( "affiliation" ) ) {
		        		if( raw.get( "affiliation" ) instanceof List ) {
		        			person.setAffiliations( (List<String>)raw.get( "affiliation" ) );
		        		}
		        		else {
		        			List<String> aff = new ArrayList<String>();
		        			aff.add( (String)raw.get( "affiliation" ) );
		        			person.setAffiliations( aff );
		        		}
		        	}
		        	if( raw.containsKey( "aliases" ) ) {
		        	}
		        	if( raw.containsKey( "displayName" ) ) {
		        		person.setDisplayName( (String)raw.get( "displayName" ) );
		        	}
		        	if( raw.containsKey( "email" ) ) {
		        		person.setEmail( (String)raw.get( "email" ) );
		        	}
		        	if( raw.containsKey( "title" ) ) {
		        		if( raw.get( "title" ) instanceof List ) {
		        			person.setDepartments( (List<String>)raw.get( "title" ) );
		        		}
		        		else {
		        			List<String> aff = new ArrayList<String>();
		        			aff.add( (String)raw.get( "title" ) );
		        			person.setDepartments( aff );
		        		}
		        	}
		        	if( raw.containsKey( "uniqname" ) ) {
		        		person.setUserName( (String)raw.get( "uniqname" ) );
		        	}
		        	if( raw.containsKey( "workAddress" ) ) {
		        		String rawAddress = null;
		        		if( raw.get( "workAddress" ) instanceof List ) {
		        			rawAddress = ((List<String>)raw.get( "workAddress" )).get(0);
		        		}
		        		else {
		        			rawAddress = (String)raw.get("workAddress");
		        		}
		        		if( rawAddress != null ) {
		        			rawAddress = rawAddress.replace( " $ ", "\n" );
		        		}
		        		person.setAddress(rawAddress);
		        	}
		        	if( raw.containsKey( "workPhone" ) ) {
		        		String rawPhone = null;
		        		if( raw.get( "workPhone" ) instanceof List ) {
		        			rawPhone = ((List<String>)raw.get( "workPhone" )).get(0);
		        		}
		        		else {
		        			rawPhone = (String)raw.get("workPhone");
		        		}
		        		if( rawPhone != null ) {
		        			rawPhone = rawPhone.replace( "/", "-" );
		        		}
		        		person.setPhone( rawPhone );
		        	}
		        	
		        }
	        }
	    } catch( UnsupportedEncodingException uee ) {
	    	LOG.error(uee);
	    } catch( IOException ioe ) {
	    	LOG.error( ioe );
	    } finally {
	        if (reader != null) {
	        	try { 
	        		reader.close();
	        	} catch (IOException logOrIgnore) {
	        		LOG.error( logOrIgnore.getLocalizedMessage() );
	        	}
	        }
	    }
		return person;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Group lookupGroup(String dn) {
		Group group = null;
	
	    BufferedReader reader = null;
		try {
			//as URLEncoder.encode  isn't encoding the DN properly , had to replace white space with "%20"
		    String newdn = dn.replaceAll("\\s", "%20").trim();
			URL service = new URL(GROUP_LOOKUP_URL+newdn);
		    LOG.debug("grouplookupurl :"  + GROUP_LOOKUP_URL+newdn);
	        reader = new BufferedReader(new InputStreamReader(service.openStream(), DEFAULT_CHARACTER_SET));
	        //
	        for (String line; (line = reader.readLine()) != null;) {
	            LOG.debug(line);
	            
	            line = line.substring(9, line.length()-1);
	            
	            LOG.debug(line);
	            
	           HashMap raw = new JSONDeserializer<HashMap>().deserialize( line );
	         
		        if( raw == null ) 
		        {
		        	LOG.debug( "Results were not parsed, "+dn+" not found." );
		        } 
		        else if ( raw.containsKey( "errors" ) && raw.get("errors")!=null && raw.get("errors").hashCode()!=0 ){
		        	// TODO get error description
		        	// Object v = raw.get("errors");
		        
		        	LOG.debug("errors:" + raw.get("errors"));
		        }		        
		        else 
		        {
		        	group = new GroupImpl();
		        	//
		        	if(raw.containsKey("distinguishedName") )
		        	{
		        		group.setDN((String)raw.get("distinguishedName") );
			        	LOG.debug( "entry distinguishedName -- "+(String)group.getDN() );

		        	}
		        	if( raw.containsKey("displayName") )
		        	{
		        		group.setDisplayName((String)raw.get("displayName") );
			        	LOG.debug( "entry displayName -- "+(String)group.getDisplayName() );

		        	}
		        	if( raw.containsKey("description") )
		        	{
		        		if( raw.get("description") instanceof String ) {
		        			List<String> desc = new ArrayList<String>();
		        			desc.add( (String)raw.get("description") );
		        			group.setDescriptions( desc );
		        			
		        		} else {
		        			group.setDescriptions( (List<String>)raw.get("description") );
		        		}
		        	}
		        	if( raw.containsKey("email") )
		        	{
		        		group.setEmail((String)raw.get("email") );
		        	}
		        	if( raw.containsKey("telephoneNumber") )
		        	{
		        		group.settelephoneNumber((String)raw.get("telephoneNumber") );
		        	}
		        	if( raw.containsKey("facsimileTelephoneNumber") )
		        	{
		        		group.setfacsimileTelephoneNumber((String)raw.get("facsimileTelephoneNumber") );
		        	}
		        	
		        }//else
	        }
	    } catch( UnsupportedEncodingException uee ) {
	    	LOG.error(uee);
	    } catch( IOException ioe ) {
	    	LOG.error( ioe );
	    }
	    catch( Exception ex ) {
		    	LOG.error( ex );	
		    	ex.printStackTrace();
	    } finally {
	        if (reader != null) {
	        	try { 
	        		reader.close();
	        	} catch (IOException logOrIgnore) {
	        		LOG.error( logOrIgnore.getLocalizedMessage() );
	        	}
	        }
	    }
		return group;
	}
	
	public List<Group> findSimpleGroup(String search) {
	
		List<Group> group = new ArrayList<Group>();
		Group entry = null;
		StringBuilder queryString = new StringBuilder();
		
		if (search != null && !search.trim().isEmpty()) {
			queryString.append( "searchCriteria=" );
			queryString.append( search.trim() );
		}
	
		LOG.debug( "Group serach QueryString will be : "+ queryString.toString() );
		try {
			URLConnection connection = new URL(GROUP_SEARCH_URL).openConnection();
		
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", DEFAULT_CHARACTER_SET);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + DEFAULT_CHARACTER_SET);
			OutputStream output = null;

			output = connection.getOutputStream();
			output.write(queryString.toString().getBytes(DEFAULT_CHARACTER_SET));

			InputStream response = connection.getInputStream();
			String contentType = connection.getHeaderField("Content-Type");

			if (contentType != null && "application/json".equalsIgnoreCase(contentType) ) {
				LOG.debug( "Attempting to parse JSON using Gson." );
			    BufferedReader reader = null;
			    try {
			  //      reader = new BufferedReader(new InputStreamReader(response, DEFAULT_CHARACTER_SET));
			    	reader = new BufferedReader(new InputStreamReader(response));
			        for (String line; (line = reader.readLine()) != null;) {
			            // No group found {"group":[]}
			            // Too many results, exceed 300  {"group":[{}]}
			            LOG.debug(line);
			            
			            line = line.substring(9, line.length()-1);
			            
			            LOG.debug(line);

			            List<HashMap> grp = new JSONDeserializer<List<HashMap>>().deserialize( line );
			            
				        if( grp == null ) {
				        	LOG.debug( "Results were not parsed, no groups found." );
				        } else {
				        	LOG.debug( "Found "+grp.size()+" group results." );
				        }
				        for( HashMap g : grp )
				        {
					            entry = new GroupImpl();
					        	if( g.containsKey("distinguishedName") )
					        	{
					        		entry.setDN((String)g.get("distinguishedName") );
						        	//LOG.debug( "entry distinguishedName -- "+(String)entry.getDN() );

					        	}
					        	if( g.containsKey("displayName") )
					        	{
					        		entry.setDisplayName( (String)g.get("displayName") );
						        	//LOG.debug( "entry displayName -- "+(String)g.get("displayName") );

					        	}
					        	if( g.containsKey("description") )
					        	{
					        		if( g.get("description") instanceof String ) {
					        			List<String> desc = new ArrayList<String>();
					        			desc.add( (String)g.get("description") );
					        			entry.setDescriptions( desc );
					        			
					        		} else {
					        			entry.setDescriptions( (List<String>)g.get("description") );
					        		}
					        		
					        		/*//for display of all desc
					        		List<String> descs = new ArrayList<String>();
					        		descs = entry.getDescriptions();
					        		int i=0;
					        		for (String d:descs)
					        		{
					        			LOG.debug( "entry description " + i + ", value: " + d);
					        			i++;
					        		}*/
					        	}
					        	group.add(entry);
				        }
				         
			        }  
			    } catch( UnsupportedEncodingException uee ) {
			    	LOG.error(uee.getLocalizedMessage());
			    } catch( IOException ioe ) {
			    	LOG.error( ioe.getLocalizedMessage() );
			    } finally {
			        if (reader != null) {
			        	try { 
			        		reader.close();
			        	} catch (IOException logOrIgnore) {
			        		LOG.error( logOrIgnore.getLocalizedMessage() );
			        	}
			        }
			    }
			} else {
				LOG.debug( "Content type was not application/json." );
			    // It's likely binary content, use InputStream/OutputStream.
			}
		} catch( IOException ioe ) {
			LOG.error( ioe.getLocalizedMessage() );
		}
		return group;
	}
	
	
	//
	public String getURLContent(String url) {
		  URLConnection conn;
		  StringBuilder sb = null;
		try {
			conn = new URL(url).openConnection();
			 LOG.debug("connection opened ok" + url);
		 /* String encoding = conn.getContentEncoding();
		  LOG.debug("encoding : " + encoding);
		  if (encoding == null) {
		    encoding = "ISO-8859-1";
		  }*/
		  BufferedReader br = new BufferedReader(new
			      InputStreamReader(conn.getInputStream(),DEFAULT_CHARACTER_SET));
		      sb = new StringBuilder(16384);
		  try {
		    String line;
		  
		    while ((line = br.readLine()) != null) {
		    	
		     LOG.debug(line);
		      sb.append(line);
		      sb.append('\n');
		    }
		  } finally {
		    br.close();
		  }
		  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  return sb.toString();
		}
	
}



