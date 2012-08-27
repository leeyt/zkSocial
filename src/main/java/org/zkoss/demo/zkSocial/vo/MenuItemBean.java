package org.zkoss.demo.zksocial.vo;

public class MenuItemBean {
	private String icon;
	private String title;
	private int	   count;
	
	public MenuItemBean() {
		
	}
	
	public MenuItemBean(String icon, String title, int count) {
		this.icon = icon;
		this.title = title;
		this.count = count;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = (count < 0) ? 0 : count;
	}
}
