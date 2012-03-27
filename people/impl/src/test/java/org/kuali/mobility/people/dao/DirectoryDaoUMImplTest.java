package org.kuali.mobility.people.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.SearchCriteria;

public class DirectoryDaoUMImplTest {

	@Test
	public void testFindEntries() {
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.setSearchText("joe");  
		List<DirectoryEntry> results = dao.findEntries( criteria );
		assertTrue( "failed to find directory entries for search criteria: " + criteria.getSearchText(), results != null && !results.isEmpty() );
		
		//Case: no result
		criteria.setSearchText("vvvv");  
		List<DirectoryEntry> results1 = dao.findEntries( criteria );
		assertTrue( "failed on the case of no resuls, find directory entries for search criteria: " + criteria.getSearchText(), results != null && results1.isEmpty() );

		
		// Case: too many results
		criteria.setSearchText("i");
		List<DirectoryEntry> results2 = dao.findEntries( criteria );
		assertTrue( "failed on the case of too many results, find directory entries for search criteria: " + criteria.getSearchText(), 
					results2!=null && results2.size()==1 && ((Person)results2.get(0)).getUserName()==null );

		
	}

	@Test
	public void testLookupPerson() {
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();

		Person person = dao.lookupPerson("joseswan");
		assertTrue( "failed to find joseswan in the directory", person != null );
		
		Person notFound = dao.lookupPerson("joe");
		assertTrue( "failed to find joe in the directory", notFound == null );
	}

//	@Test
//	public void testLookupGroup() {
//		fail("Not yet implemented");
//	}

}
