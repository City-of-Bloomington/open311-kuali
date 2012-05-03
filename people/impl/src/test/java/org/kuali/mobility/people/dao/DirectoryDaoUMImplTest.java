package org.kuali.mobility.people.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
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
		
		/*//Case: no result
		criteria.setSearchText("vvvv");  
		List<DirectoryEntry> results1 = dao.findEntries( criteria );
		assertTrue( "failed on the case of no resuls, find directory entries for search criteria: " + criteria.getSearchText(), results != null && results1.isEmpty() );

		
		// Case: too many results
		criteria.setSearchText("i");
		List<DirectoryEntry> results2 = dao.findEntries( criteria );
		assertTrue( "failed on the case of too many results, find directory entries for search criteria: " + criteria.getSearchText(), 
					results2!=null && results2.size()==1 && ((Person)results2.get(0)).getUserName()==null );
*/
		
	}
	/*@Test
	public void testgetURLContent()
	{
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();
		//cn=Climate%20Change,ou=User%20Groups,ou=Groups,dc=umich,dc=edu
		//String me = dao.getURLContent("https://mcommunity.umich.edu/mcGroupService/groupProfile/dn/cn=Climate%20Change,ou=User%20Groups,ou=Groups,dc=umich,dc=edu");
		//String me = dao.getURLContent("https://mcommunity.umich.edu/mcGroupService/groupProfile/dn/cn=Climate Change,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		//String me = dao.getURLContent("https://mcommunity.umich.edu/mcGroupService/groupProfile/dn/cn=ITS Android Dev,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		String me = dao.getURLContent("https://mcommunity.umich.edu/mcGroupService/groupProfile/dn/cn=ITS%20Android%20Dev,ou=User%20Groups,ou=Groups,dc=umich,dc=edu");

		//String me = dao.getURLContent("cn=ITS Android Deployment,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		assertTrue( "failed to find joseswan in the directory", me != null );
		
	}*/
	
	
	@Test
	public void testlookupGroup(){
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();
		//Group grp = dao.lookupGroup("joseswan");
	//	Group grp = dao.lookupGroup("cn=ITS Android Dev,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		//assertFail( "failed to find the dn in the directory", grp != null );
		//
		Group grp1 = dao.lookupGroup("cn=ITS Android Dev,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		assertTrue( "failed to find the dn in the directory", grp1 != null );
		
		Group grp2 = dao.lookupGroup("cn=MSuite Main Contact,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		assertTrue( "failed to find the dn in the directory", grp2 != null );
		
		Group grp3 = dao.lookupGroup("cn=embafax,ou=User Groups,ou=Groups,dc=umich,dc=edu");
		assertTrue( "failed to find the dn in the directory", grp3 != null );
		
		
		
	}

	@Test
	public void testfindSimpleGroup() 
	{
		DirectoryDaoUMImpl dao = new DirectoryDaoUMImpl();
        // no groupname case
		//List<Group> group = dao.findSimpleGroup("bdandamu");
		List<Group> group = dao.findSimpleGroup("ITS Android Deployment");
		assertTrue( "Found group in directory", group != null );
		
		
		//cn=ITS Android Dev,ou=User Groups,ou=Groups,dc=umich,dc=edu
		//yes groupname with one description
	    List<Group> group1 = dao.findSimpleGroup("android");
		 System.out.println("group1 size:" + group1.size());
		for( Group g : group1 )
        {
	       System.out.println("group DN :" + g.getDN() + ", Displayname :" + g.getDisplayName()); 	 
        }
		assertTrue( "failed to find group  in the directory", group1 != null );
		
		//yes groupname with more than one description
		List<Group> group2 = dao.findSimpleGroup("xin");
		 System.out.println("group2 size:" + group2.size());
		for( Group g : group2 )
       {
	       System.out.println("group DN :" + g.getDN() + ", Displayname :" + g.getDisplayName()); 	 
       }
		assertTrue( "failed to find group  in the directory", group2 != null );
		
		/*//yes groupname with too many 
		List<Group> groupmore = dao.findSimpleGroup("car");
		 System.out.println("group size:" + groupmore.size());
		for( Group g : groupmore )
       {
	       System.out.println("group DN :" + g.getDN() + ", Displayname :" + g.getDisplayName()); 	 
       }
		assertTrue( "failed to find group  in the directory", groupmore != null );
		
		//yes groupname with too many 
		List<Group> groupno = dao.findSimpleGroup("vvv");
		 System.out.println("group size:" + groupmore.size());
		for( Group g : groupno )
       {
	       System.out.println("group DN :" + g.getDN() + ", Displayname :" + g.getDisplayName()); 	 
       }
		assertTrue( "failed to find group  in the directory", groupno != null );*/
		
		List<Group> groupme1 = dao.findSimpleGroup("MSuite Main Contact");
		 System.out.println("groupme1 size:" + groupme1.size());
		for( Group g : groupme1 )
     {
	       System.out.println("group DN :" + g.getDN() + ", Displayname :" + g.getDisplayName()); 	 
     }
		assertTrue( "failed to find group  in the directory", groupme1 != null );
		
		List<Group> groupme = dao.findSimpleGroup("embafax");
		 System.out.println("groupme size:" + group2.size());
		for( Group g : groupme )
      {
	       System.out.println("group DN :" + g.getDN() + ", Displayname :" + g.getDisplayName() + " , phone:" + g.gettelephoneNumber() + ", fax:" + g.getfacsimileTelephoneNumber()); 	 
      }
		assertTrue( "failed to find group  in the directory", groupme != null );

	}
	


}
