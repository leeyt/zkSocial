package org.zkoss.demo.zksocial.vo;

// Represent poster of news or commenter on posts
public class AuthorBean {
	private String name;
	private String avatar;

	public AuthorBean() {
	}
	
	public AuthorBean(String name, String avatar) {
		this.name = name;
		this.avatar = avatar;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
