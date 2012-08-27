package org.zkoss.demo.zksocial.vo;

import org.zkoss.zul.ListModel;

public class ContactGroupBean {
	private String groupName;
	private ListModel<ContactBean> contactList;
	
	public ContactGroupBean() {
	}
	
	public ContactGroupBean(String groupName, ListModel<ContactBean> contactList) {
		this.groupName   = groupName;
		this.contactList = contactList;
	}
	
	public String getGroupStatus() {
		return groupName == null ? null : groupName + " (" + contactList.getSize() + ")"; 
	}

	public ListModel<ContactBean> getContactList() {
		return contactList;
	}
}