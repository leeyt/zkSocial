package org.zkoss.demo.zksocial.data;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	private static String[] NEWSFEEDS = { 
		"<p>ZK 6.5 introduces new features that enable developers to design user interfaces that take advantage of " +
		"tablet-specific user interaction methods such as swiping and changing device orientations. You can also view" +
		" the <a href='http://blog.zkoss.org/index.php/2012/08/14/zk-6-5-tablet-ui-design/'>blog here</a>.</p>" +
		"<p><img src='http://books.zkoss.org/images/6/6e/ResponsiveApproaches.png'/></p>",
		
		"<p>The timeline below shows ZK and our history. For more information please take a look at our " +
		"<a href='http://www.zkoss.org/support/about'>about page</a>.</p>" +
		"<p><img src='https://lh4.googleusercontent.com/-g636wpLGAU0/UEchnFWxC4I/AAAAAAAAAF4/eRYOVmQ6vRQ/s740/zk_timeline.png'/></p>",
		
		"<p>We have just released a new tutorial named \"Practices Of Using CDI In ZK\". This tutorial introduces " +
		"developers to some programming practices of each layer in CDI & possible solutions for common scenarios. For " +
		"the article, please <a href='http://books.zkoss.org/wiki/Small_Talks/2012/September/Practices_Of_Using_CDI_In_ZK'>" +
		"click here</a>.</p>" +
		"<p><img src='http://books.zkoss.org/images/a/a1/Zk_cdi_integration_demo.png'/></p>",
		
		"<p><a href='https://plus.google.com/106973993942617532337'>Chanwit Kaewkasi</a> released version 2 of the Grails" +
		" plugin for the ZK Framework. New features include the following:</p><ul><li>Model-View-ViewModel pattern</li>" +
		"<li>jQuery-style API</li><li>URL Mapping</li><li>Built-in JavaScript declarative style</li><li>ZK 6.0.2</li></ul>" +
		"<p>For more information please <a href='http://grails.org/plugin/zk'>click here</a>.</p>",
		
		"<p>ZK has released an Online Themer to help you create different themes & colours for your ZK application in " +
		"just a few clicks! You can try now by <a href='http://blog.zkoss.org/index.php/2012/08/23/zk-6-online-theme-generator/'>" +
		"clicking here</a>.</p>" +
		"<p><img src='http://blog.zkoss.org/wp-content/uploads/2012/08/zkthemer00-e1344590891262.png'/></p>"
	};

	private static String[][] COMMENTS = {
		{
			"<p>This looks great!</p>",
			"<p>I am really looking forward to trying it out!</p>"
		},
		
		{
			"<p>Thanks for the information</p>",
			"<p>I like the timeline, really shows how long ZK has been going!</p>",
			"<p>6 years, htat's great!</p>"
		},
		
		{
			"<p>CDI with ZK, I am going to use this ASAP!</p>",
			"<p>If this is as good as the Spring integration then it will be brilliant!</p>"
		},
		
		{
			"<p>I love ZK Grails</p>",
			"<p>Thanks Chanwit, this is great!</p>"
		},
		
		{
			"<p>I love the online themer</p>",
			"<p>This truly is excellent</p>",
			"<p>It is so easy to create a new theme now! That's awesome!</p>"
		}
	};
	
	public static AuthorBean randomAuthor() {
		int lastIndex  = RANDOM.nextInt(LAST_NAMES.length);
		int firstIndex = RANDOM.nextInt(FIRST_NAMES.length);
		
		String name = FIRST_NAMES[firstIndex] + " " + LAST_NAMES[lastIndex];

		return new AuthorBean(name, "images/avatars/userpic.png");
	}
	
	private static List<PostBean> posts = null;

	public static List<PostBean> getPosts() {
		if (posts == null) {
			posts = new ArrayList<PostBean>();
			
    		Calendar now = Calendar.getInstance();
    		
    		for (int i=0; i < NEWSFEEDS.length; i++) {
    			PostBean post = new PostBean();
    			post.setAuthor(randomAuthor());
    			post.setContent(NEWSFEEDS[i]);
    			post.setTime(now.getTime());
    			now.add(Calendar.WEEK_OF_YEAR, -1);
    			now.add(Calendar.MINUTE, -16);
    		
    			List<AuthorBean> likeList = post.getLikeList();
    			int likeCount = RANDOM.nextInt(20);
    			for (int j=0; j < likeCount; j++) {
    				likeList.add(randomAuthor());
    			}
    				
    			List<PostBean> commentList = post.getCommentList();
    			commentList.add(post);
    				
    			int commentCount = COMMENTS[i].length;
    			for (int k=0; k < commentCount; k++) {
    				PostBean comment = new PostBean();
    				comment.setAuthor(randomAuthor());
    				comment.setContent(COMMENTS[i][k]);
    				comment.setTime(now.getTime());
    				commentList.add(comment);
    			}
    			
    			posts.add(post);
    		}
    	}
		
		return posts;
	}
}
