package org.zkoss.demo.zksocial.vo;

import org.zkoss.zul.ListModel;

public class MenuGroupBean {
	private String groupName;
	private ListModel<MenuItemBean> menuItems;
	
	public MenuGroupBean() {
	}
	
	public MenuGroupBean(String groupName, ListModel<MenuItemBean> menuItems) {
		this.groupName = groupName;
		this.menuItems = menuItems;
	}
	
	public String getGroupName() {
		return groupName; 
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ListModel<MenuItemBean> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ListModel<MenuItemBean> menuItems) {
		this.menuItems = menuItems;
	}
}
