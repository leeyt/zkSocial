package org.zkoss.demo.zkFacebook.vm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.demo.zkFacebook.vo.Author;
import org.zkoss.demo.zkFacebook.vo.Post;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Popup;

public class NewsfeedVM {
	@Wire 
	private Popup feedbackPopup;
	
	/**
	 * feedback target
	 */
	private Post currentPost = null;
	public Post getCurrentPost() {
		return currentPost;
	}
	// -------------------------------------------------------
	
	/**
	 * submitted comment
	 */
	private String currentComment = null;
	
	public String getCurrentComment() {
    	return currentComment;
    }
	
	public void setCurrentComment(String currentComment) {
    	this.currentComment = currentComment;
    }
	// -------------------------------------------------------
	
	/**
	 * If true, the modal filter is shown.
	 */
	private boolean modalShow = false;
	
	public boolean isModalShow() {
		return modalShow;
	}
	
	public void setModalShow(boolean modalShow) {
		this.modalShow = modalShow;
	}
	// -------------------------------------------------------
	
	/**
	 * If true, the menu panel is opened.
	 */
	private boolean menuOpen = false;
	
	public boolean isMenuOpen() {
		return menuOpen;
	}
	
	public void setMenuOpen(boolean menuOpen) {
		this.menuOpen = menuOpen;
	}
	// -------------------------------------------------------
	
	/**
	 * If true, the current post has been liked.
	 */
	private boolean likeStatus = false;

	public boolean isLikeStatus() {
		return likeStatus;
	}
	
	public void setLikeStatus(boolean likeStatus) {
		this.likeStatus = likeStatus;
	}
	// -------------------------------------------------------

	/**
	 * Generate fake newsfeed posts
	 * @return
	 */
	public List<Post> getPostModel(){
		List<Post> result = new ArrayList<Post>();
		for (int i=0; i<10; i++){
			result.add(Post.genPost());
		}
		return result;
	}
	
	@Init
	public void init() {
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("modalShow")
	public void hideModal(){
		modalShow = false;
	}
	
	@Command
	@NotifyChange({"currentPost","actionName", "modalShow"})
	public void feedback(@BindingParam("instance") Post post) {
		currentPost = post;
		modalShow = true;
		
		feedbackPopup.open(feedbackPopup.getFellow("mainWindow"), "bottom_right");
	}

	@Command
	@NotifyChange({"currentPost","currentComment"})
	public void addComment() {
		Post comment = new Post();
		comment.setAuthor(new Author("ZK Team"));
		comment.setContent(currentComment);
		comment.setTime(new Date());
		currentPost.getCommentList().add(comment);
		currentComment = "";
	}
	
	@Command
	@NotifyChange({"currentPost", "likeStatus"})
	public void likePost() {
		likeStatus = !likeStatus;
		
		if (likeStatus) {
			currentPost.getLikeList().add(new Author());
		} else {
			List<Author> likeList = currentPost.getLikeList();
			likeList.remove(likeList.size()-1);
		}
		
	}
	
	@Command
	@NotifyChange("menuOpen")
	public void toggleMenu() {
		menuOpen = !menuOpen;
	}
	
//	private String actionName = "Sort";
//	
//	public String getActionName() {
//    	return actionName;
//    }
//
//	public void setActionName(String actionName) {
//    	this.actionName = actionName;
//    }
	
//	@Command
//	@NotifyChange("actionName")
//	public void hideLikeBtn() {
//		actionName = "Sort";
//	}
//	
//	@Command("Sort")
//	public void doSort() {
//		System.out.println("Do Sort");
//	}
}
