package org.zkoss.demo.zksocial.vo;

public class ContactBean {
	private String picture;
	private String name;
	private String status;
	
	public ContactBean() {
	}
	
	public ContactBean(String picture, String name, String status) {
		this.picture = picture;
		this.name 	 = name;
		this.status  = status;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public void setIcon(String picture) {
		this.picture = picture;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
