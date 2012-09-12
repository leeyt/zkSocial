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

	// Date/time of the post
	private Date time;

	// List of authors who liked this post
	private List<AuthorBean> likeList	 = new ArrayList<AuthorBean>();
	
	// List of comments on this post
	private List<PostBean>   commentList = new ArrayList<PostBean>();
	
	// Is this post liked by the current user?
	private boolean liked = false;
	
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
	
	public boolean isLiked() {
		return this.liked;
	}
	
	public void setLiked(boolean liked) {
		this.liked = liked;
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
