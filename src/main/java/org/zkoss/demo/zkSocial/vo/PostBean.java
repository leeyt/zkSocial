package org.zkoss.demo.zksocial.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Individual post on the newsfeed page
public class PostBean {
	// Posting Author
	private AuthorBean author;
	
	// Content of the post
	private String content;

	private Date time;

	private List<AuthorBean> likeList	 = new ArrayList<AuthorBean>();
	private List<PostBean>   commentList = new ArrayList<PostBean>();
	
	public PostBean() {
	}
	
	public AuthorBean getAuthor() {
		return author;
	}

	public void setAuthor(AuthorBean author) {
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
	
	public List<AuthorBean> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<AuthorBean> likeList) {
		this.likeList = likeList;
	}

	public List<PostBean> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<PostBean> commentList) {
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
