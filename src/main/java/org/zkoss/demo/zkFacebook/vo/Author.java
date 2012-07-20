package org.zkoss.demo.zkFacebook.vo;

import java.util.List;
import java.util.Random;

// Individual or Group Account
public class Author {
	// Share the random number generator
	private static Random RANDOM = new Random();
	
	// For randomizing author's name
	private static String[] LAST_NAMES = {
		"Chen", "Chang", "Claire", "Lee", "Lin", "Pan", "Wang", "Shiao"
	};
	
	private static String[] FIRST_NAMES = {
		"Ian", "Jumper", "Monty", "Nancy", "Neil", "Tom", "Tim", "Wing"
	};
	
	private String name;
	private String avatar = "images/avatars/unknown.jpg";

	public Author() {
		int lastIndex  = RANDOM.nextInt(LAST_NAMES.length);
		int firstIndex = RANDOM.nextInt(FIRST_NAMES.length);
		
		this.name = FIRST_NAMES[firstIndex] + " " + LAST_NAMES[lastIndex];
	}
	
	public Author(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return String.format(
				"[name = '%s', avatar = '%s']", 
				name, avatar);
	}
	
	public static void main(String[] args) {
		System.out.println(new Author());
	}
}
