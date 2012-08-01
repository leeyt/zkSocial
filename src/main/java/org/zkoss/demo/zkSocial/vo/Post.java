package org.zkoss.demo.zkSocial.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Individual post on the newsfeed page
public class Post {
	// Posting Author
	private Author author;
	
	// Content of the post
	private String content;

	private Date time;

	private List<Author> likeList    = new ArrayList<Author>();
	private List<Post>   commentList = new ArrayList<Post>();
	
	public Post() {
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getTime() {
		return time;
    }

	public void setTime(Date time) {
    	this.time = time;
    }
	
	public List<Author> getLikeList() {
    	return likeList;
    }

	public void setLikeList(List<Author> likeList) {
    	this.likeList = likeList;
    }

	public List<Post> getCommentList() {
    	return commentList;
    }

	public void setCommentList(List<Post> commentList) {
    	this.commentList = commentList;
    }

	public int getLikeCount() {
		return likeList.size();
	}

	public int getCommentCount() {
		// Original post included in commentList
		// Need to compensate
		return commentList.size()-1;
	}

}
