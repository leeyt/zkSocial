package org.zkoss.demo.zkSocial.vo;

import java.util.List;
import java.util.Random;

// Represent poster of news or commenter on posts
public class Author {
	private String name;
	private String avatar;

	public Author() {
	}
	
	public Author(String name, String avatar) {
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
