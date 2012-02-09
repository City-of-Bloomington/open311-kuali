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
		SearchCriteria criteria = new SearchCriteria();
		criteria.setSearchText("joe");
		
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();
		
		List<DirectoryEntry> results = dao.findEntries( criteria );
		
		assertTrue( "failed to find directory entries", results != null && !results.isEmpty() );
		
	}

	@Test
	public void testLookupPerson() {
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();

		Person person = dao.lookupPerson("joseswan");
		
		assertTrue( "failed to find joseswan in the directory", person != null );
	}

//	@Test
//	public void testLookupGroup() {
//		fail("Not yet implemented");
//	}

}
