package org.zkoss.demo.zksocial.data;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.zkoss.demo.zksocial.vo.AuthorBean;
import org.zkoss.demo.zksocial.vo.PostBean;

public class FakeData {
	// Used to randomize data
	private static Random RANDOM = new Random();
	
	private static String[] LAST_NAMES = {
		"Chen", "Chang", "Claire", "Lee", "Lin", "Pan", "Wang", "Shiao"
	};
	
	private static String[] FIRST_NAMES = {
		"Ian", "Jumper", "Monty", "Nancy", "Neil", "Tom", "Tim", "Wing"
	};
	
	private static String LOREM_IPSUM =
		"<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et " +
		"dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex " +
		"ea commodo consequat. Duis aute irure dolor in	reprehenderit in voluptate velit esse cillum dolore eu "	  +
		"fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt  " +
		"mollit	anim id est laborum.</p>";

	private static String[] IMAGES = {
		"images/posts/image1.jpg",
		"images/posts/image2.jpg",
		"images/posts/image3.jpg",
	};

	private static String[] COMMENTS = {
		"<p>Nice try</p>",
		"<p>Hahaha</p>",
		"<p>...</p>",
		"<p>Way to go</p>",
		"<p>Take care</p>"
	};
	
	public static AuthorBean randomAuthor() {
		int lastIndex  = RANDOM.nextInt(LAST_NAMES.length);
		int firstIndex = RANDOM.nextInt(FIRST_NAMES.length);
		
		String name = FIRST_NAMES[firstIndex] + " " + LAST_NAMES[lastIndex];

		return new AuthorBean(name, "images/avatars/userpic.png");
	}
	
	private static String randomContent() {
		// 1~2 paragraphs
		StringBuffer sb = new StringBuffer(LOREM_IPSUM);
		sb.append(RANDOM.nextInt(2) == 1 ? LOREM_IPSUM : "");
		
		// 0~1 image
		String imageSrc = IMAGES[RANDOM.nextInt(IMAGES.length)];
		String image = "<img src='" + imageSrc + "' />";
		
		sb.append(RANDOM.nextInt(2) == 1 ? image : "");
		return sb.toString();
	}

	public static PostBean randomPost(){
		PostBean result = new PostBean();
		result.setAuthor(randomAuthor());
		result.setContent(randomContent());
		result.setTime(new Date());
		
		List<AuthorBean> likeList = result.getLikeList();
		int likeCount = RANDOM.nextInt(20);
		for (int i=0; i < likeCount; i++) {
			likeList.add(randomAuthor());
		}
		
		List<PostBean> commentList = result.getCommentList();
		commentList.add(result);
		
		int commentCount = RANDOM.nextInt(10);
		for (int i=0; i < commentCount; i++) {
			commentList.add(randomComment());
		}
		
		return result;
	}
	
	private static PostBean randomComment(){
		PostBean result = new PostBean();
		result.setAuthor(randomAuthor());
		result.setContent(COMMENTS[RANDOM.nextInt(COMMENTS.length)]);
		result.setTime(new Date());
		
		List<AuthorBean> likeList = result.getLikeList();
		int likeCount = RANDOM.nextInt(20);
		for (int i=0; i < likeCount; i++) {
			likeList.add(randomAuthor());
		}
		return result;
	}
}
