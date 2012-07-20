package org.zkoss.demo.zkFacebook.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

// Individual post on the newsfeed page
public class Post {
	// Share the random number generator
	private static Random RANDOM = new Random();
	
	private static String LOREM_IPSUM =
		"<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et " +
		"dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
		"ea commodo consequat. Duis aute irure dolor in	reprehenderit in voluptate velit esse cillum dolore eu "      +
		"fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt  " +
		"mollit	anim id est laborum.</p>";
	
	private static String[] COMMENTS = {
		"Nice try",
		"Hahaha",
		"...",
		"Way to go",
		"Take care"
	};

	// Posting Author
	private Author author;
	
	// Content of the post
	private String content;

	private Date time;

	private List<Author>  likeList = new ArrayList<Author>();
	private List<Post> commentList = new ArrayList<Post>();

	private static String randomContent() {
		// 1~2 paragraphs
		StringBuffer sb = new StringBuffer(LOREM_IPSUM);
		sb.append(RANDOM.nextInt(2) == 1 ? LOREM_IPSUM : "");
		
		// 0~1 image
		String image = "<img width='120px' height='90px' src='mockImage?index=" + RANDOM.nextInt(100) + "' />";
		sb.append(RANDOM.nextInt(2) == 1 ? image : "");
		return sb.toString();
	}
	
	public static Post genPost(){
		Post result = new Post();
		result.setAuthor(new Author());
		result.setContent(randomContent());
		result.setTime(new Date());
		
		List<Author> likeList = result.getLikeList();
		int likeCount = RANDOM.nextInt(20);
		for (int i=0; i < likeCount; i++) {
			likeList.add(new Author());
		}
		
		List<Post> commentList = result.getCommentList();
		commentList.add(result);
		
		int commentCount = RANDOM.nextInt(10);
		for (int i=0; i < commentCount; i++) {
			commentList.add(genComment());
		}
		
		return result;
	}
	
	private static Post genComment(){
		Post result = new Post();
		result.setAuthor(new Author());
		result.setContent(COMMENTS[RANDOM.nextInt(COMMENTS.length)]);
		result.setTime(new Date());
		
		List<Author> likeList = result.getLikeList();
		int likeCount = RANDOM.nextInt(20);
		for (int i=0; i < likeCount; i++) {
			likeList.add(new Author());
		}
		return result;
	}
	
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
	
	@Override
    public String toString() {
	    return String
	            .format("Post [author=%s, content=%s, time=%s, likeList=%s, commentList=%s]",
	                    author, content, time, likeList, commentList);
    }

	public static void main(String[] args) {
		System.out.println(new Post());
	}
}
