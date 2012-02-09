package org.kuali.mobility.people.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.PersonImpl;
import org.kuali.mobility.people.entity.SearchCriteria;

import flexjson.JSONDeserializer;

public class DirectoryDaoUMImpl implements DirectoryDao {

	private static final Logger LOG = Logger.getLogger( DirectoryDaoUMImpl.class );
	
	private static final String SEARCH_URL = "https://mcommunity.umich.edu/mcPeopleService/people/compact/search";
	private static final String PERSON_LOOKUP_URL = "https://mcommunity.umich.edu/mcPeopleService/people/";
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
		    
	        reader = new BufferedReader(new InputStreamReader(service.openStream(), DEFAULT_CHARACTER_SET));
	        
	        for (String line; (line = reader.readLine()) != null;) {
	            LOG.debug(line);
	            
	            line = line.substring(10, line.length()-1);
	            
	            LOG.debug(line);
	            
	            HashMap raw = new JSONDeserializer<HashMap>().deserialize( line );
	            
		        if( raw == null ) {
		        	LOG.debug( "Results were not parsed, "+personId+" not found." );
		        } else {
		        	person = new PersonImpl();
		        	if( raw.containsKey( "affiliation" ) ) {
		        		
//		        		person.setAffiliations(affiliations)
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
		        	}
		        	if( raw.containsKey( "uniqname" ) ) {
		        		person.setUserName( (String)raw.get( "uniqname" ) );
		        	}
		        	if( raw.containsKey( "workAddress" ) ) {
		        	}
		        	if( raw.containsKey( "workPhone" ) ) {
		        		person.setPhone( (String)raw.get( "workPhone" ) );
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

	@Override
	public Group lookupGroup(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

}
