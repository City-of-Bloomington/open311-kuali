package org.kuali.mobility.people.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GroupImpl implements Serializable, Group {

	private static final long serialVersionUID = 5993041300536395824L;

	private String distinguishedName;
	private String displayName;
	private List<String> descriptions;
	private String email;
	private String telephoneNumber;
	private String facsimileTelephoneNumber;
	
	public GroupImpl() {
		descriptions = new ArrayList<String>();
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Group#getHashedDisplayName()
	 */
	@Override
	public String getHashedDN(){
		return Integer.toString(Math.abs(distinguishedName.hashCode()));
	}
	@Override
	public String getDN() {
		return distinguishedName;
	}
	
	@Override
	public String getDisplayName() {
		return displayName;
	}
	
	@Override
	public List<String> getDescriptions() {
		return descriptions;
	}
	
	@Override
	public void setDN(String distinguishedName) {
		this.distinguishedName = distinguishedName;	
	}
	@Override
	public void setDisplayName(String displayName) {
		this.displayName =	displayName;
	}
	@Override
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Group#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Group#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Group#getPhone()
	 */
	@Override
	public String gettelephoneNumber() {
		return telephoneNumber;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Group#setPhone(java.lang.String)
	 */
	@Override
	public void settelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getfacsimileTelephoneNumber() {
		return facsimileTelephoneNumber;
	}

	public void setfacsimileTelephoneNumber(String facsimileTelephoneNumber) {
		this.facsimileTelephoneNumber = facsimileTelephoneNumber;
	}

	
}
