package org.zkoss.demo.zkSocial.vm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.demo.zkSocial.composite.Contact;
import org.zkoss.demo.zkSocial.composite.MenuItem;
import org.zkoss.demo.zkSocial.data.FakeData;
import org.zkoss.demo.zkSocial.vo.AuthorBean;
import org.zkoss.demo.zkSocial.vo.ContactBean;
import org.zkoss.demo.zkSocial.vo.MenuGroupBean;
import org.zkoss.demo.zkSocial.vo.MenuItemBean;
import org.zkoss.demo.zkSocial.vo.PostBean;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.web.servlet.Servlets;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Toolbarbutton;

public class NewsfeedVM {
	@Wire 
	private Popup feedbackPopup;
	
	private static boolean isWideScreen(String orient) {
		return "landscape".equals(orient);
	}
	
	// If mobile, assume started out as "landscape"
	// Changed when orientation change is detected.
	private String orient = "landscape";
	
	// -------------------------------------------------------
	
	private String viewportWidth;

	public String getViewportWidth() {
		return viewportWidth;
	}
	
	public void setViewportWidth(String viewportWidth) {
		this.viewportWidth = viewportWidth;
	}
	// -------------------------------------------------------
	
	private String css;

	/**
	 * Tablet and desktop may use different set of CSS
	 */
	public String getCss() {
		return css;
	}
	
	public void setCss(String css) {
		this.css = css;
	}
	// -------------------------------------------------------
	
	private AuthorBean currentUser;
	
	/**
	 * Currently logged in user
	 */
	public AuthorBean getCurrentUser() {
		return this.currentUser;
	}
	
	// -------------------------------------------------------
	
	private MenuGroupBean[] menuGroups;

	/**
	 * Menu items are grouped into categories.
	 */
	public MenuGroupBean[] getMenuGroups() {
		return menuGroups;
	}

	public void setMenuGroups(MenuGroupBean[] menuGroups) {
		this.menuGroups = menuGroups;
	}
	// -------------------------------------------------------
	
	public ListModel<String[]> getToolbarModel() {
		return 	new ListModelList<String[]>(
			new String[][] {
				{ "Status",   "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAANFJREFUeNpiZEhrYSADTAPis0A8lwkqADLlPx78CkmzHBD7AfFMIDZjgQoWEbDxHZTuBWIDIHYD4nwgvgAzgBNKswPxLxyG9CJZBKJTQAwmNEW4NHchaX4ExPUwCSYiAgwUPqVQ9msgdgbijUC8DiTAQkAzyGsVUPZ3IPYB4jtAbAzEKsQYIA31FgjHAfEpdAX4DGCGOt0WiG8C8RdsivAZ8BeI0wkFEBMDhYDqBggRoUcUWxj8hQbaWxIs/4Xsgj5coYwngEF6GBjJzM5wABBgALS1KzDd2YvyAAAAAElFTkSuQmCC" },
				{ "Photo",    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAJlJREFUeNpiZEhrYaAEMAHxfxLxXnQDSAVOyBwWKM1IpOb/SOxvQHyKiQLvcwKxIrIBh6EYGYSjOxkdsCCxv6PJiQLxNCC+BsT7cOjnRzbADYh5gLgLiH8BsToQCwGxDdQV+wi5IASIe4FYDou6amIMWAPFJAEWLNFDckokFZxCdwEjJXmBBUcqI8sL68nQvw/ZBUHkuAAgwADkuSHAjHyEIwAAAABJRU5ErkJggg==" },
				{ "Check In", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAO5JREFUeNpi/P//PwMIMKa3MpAK/s+sZmDCIccPxL1AfA+kDoifAPFMIBZFV4jNAHsgvgrERUCsCBWTBuI0qLgTPgNANqyAagCBLJDvgLgASX41EEvgMqAFSfIvEE+HsqdD+SAgBMTtuAzwRmIzA3EOEHNCaWYkOVcYgwXNACE0/mQoZsClDt0FZ4mMwbO4DJhFpAELcBmwEojvENAMkl+My4BfQJxCwIACqDqcCekgEC/DoXkrFONNiSBQBsTv0MTeQRMWAzEGPMXilUQgfkSsASCwHojnQtlTgHgTNkUsBAIsF4jZgLgYlwKAAAMAbegrRQ3UKeAAAAAASUVORK5CYII=" }
			}
		);
	}
	
	// -------------------------------------------------------
	
	private ListModel<ContactBean> frequentContacts;

	/**
	 * Frequent Contacts
	 */
	public ListModel<ContactBean> getFrequentContacts() {
		return frequentContacts;
	}

	public void setFrequentContacts(ListModel<ContactBean> frequentContacts) {
		this.frequentContacts = frequentContacts;
	}
	
	private ListModel<ContactBean> otherContacts;

	/**
	 * Other Online Contacts
	 */
	public ListModel<ContactBean> getOtherContacts() {
		return otherContacts;
	}

	public void setOtherContacts(ListModel<ContactBean> otherContacts) {
		this.otherContacts = otherContacts;
	}

	// -------------------------------------------------------

	private PostBean currentPost = null;

	/**
	 * Current post that the user is interested in getting feedback
	 */
	public PostBean getCurrentPost() {
		return currentPost;
	}
	
	public void setCurrentPost(PostBean currentPost) {
		this.currentPost = currentPost;
	}
	// -------------------------------------------------------
	
	private String currentComment = null;

	/**
	 * Comment for current post 
	 */
	public String getCurrentComment() {
		return currentComment;
	}
	
	public void setCurrentComment(String currentComment) {
		this.currentComment = currentComment;
	}
	// -------------------------------------------------------
	
	private boolean modalShow = false;

	/**
	 * If true, the modal filter is shown.
	 */
	public boolean isModalShow() {
		return modalShow;
	}
	
	public void setModalShow(boolean modalShow) {
		this.modalShow = modalShow;
	}
	// -------------------------------------------------------
	
	private boolean menuOpen = false;

	/**
	 * If true, the menu panel is opened.
	 */
	public boolean isMenuOpen() {
		return menuOpen;
	}
	
	public void setMenuOpen(boolean menuOpen) {
		this.menuOpen = menuOpen;
	}
	// -------------------------------------------------------
	
	private boolean contactOpen = true;

	/**
	 * If true, the contact panel is opened
	 */
	public boolean isContactOpen() {
		return contactOpen;
	}
	
	public void setContactOpen(boolean contactOpen) {
		this.contactOpen = contactOpen;
	}
	
	// -------------------------------------------------------

	private boolean likeStatus = false;

	/**
	 * If true, the current post has been liked.
	 */
	public boolean isLikeStatus() {
		return likeStatus;
	}
	
	public void setLikeStatus(boolean likeStatus) {
		this.likeStatus = likeStatus;
	}
	// -------------------------------------------------------

	/**
	 * Generate fake newsfeed posts
	 */
	public List<PostBean> getPostModel(){
		List<PostBean> result = new ArrayList<PostBean>();
		for (int i=0; i<10; i++){
			result.add(FakeData.randomPost());
		}
		return result;
	}

	@Init
	public void init() {
		// Detect if client is mobile (such as Android or iOS devices)
		ServletRequest request = ServletFns.getCurrentRequest();
		boolean mobile = Servlets.getBrowser(request, "mobile") != null;

		// Set the default stylesheet
		if (mobile) {
			css = "css/tablet.css.dsp";
		} else {
			css = "css/desktop.css.dsp";
			viewportWidth = "1366px";
		}
		
		// -------------------------------------------------------------------------------
		
		currentUser = new AuthorBean("ZK Team", "images/avatars/zk.jpg");
		
		// -------------------------------------------------------------------------------
		// For generating categorized Menuitems in the Sidebar

		menuGroups = new MenuGroupBean[] {
			new MenuGroupBean(
				"FAVORITES",
				new ListModelArray<MenuItemBean>(
					new MenuItemBean[] {
						new MenuItemBean("images/items/icon_newsfeed.png", "News feed", 8),
						new MenuItemBean("images/items/icon_messages.png", "Messages",  0),
						new MenuItemBean("images/items/icon_nearby.png",   "Nearby",	5),
						new MenuItemBean("images/items/icon_events.png",   "Events",	0),
						new MenuItemBean("images/items/icon_notes.png",	   "Notes",		3),
						new MenuItemBean("images/items/icon_photos.png",   "Gallery",   0),
						new MenuItemBean("images/items/icon_friends.png",  "Friends",   15)
					}
				)
			),
			
			new MenuGroupBean(
				"GROUPS",
				new ListModelArray<MenuItemBean>(
					new MenuItemBean[] {
						new MenuItemBean("images/items/icon_group1.png", "Training",	23),
						new MenuItemBean("images/items/icon_group2.png", "Study Group", 0),
						new MenuItemBean("images/items/icon_group3.png", "Research",	1)
					}
				)
			),
			
			new MenuGroupBean(
				"INTERESTS",
				new ListModelArray<MenuItemBean>(
					new MenuItemBean[] {
						new MenuItemBean("images/items/subscribe.png",  "Subscriptions", 0)
					}
				)
			),
			
			new MenuGroupBean(
				null,
				new ListModelArray<MenuItemBean>(
					new MenuItemBean[] {
						new MenuItemBean("images/items/helpCenter.png",  "Help Center",	   0),
						new MenuItemBean("images/items/settings.png",	 "Settings",	   0),
						new MenuItemBean("images/items/icon_status.png", "Edit Favorites", 0)
					}
				)
			)
		};
		// -------------------------------------------------------------------------------

		// Contacts in the Contact Panel

		frequentContacts = new ListModelArray<ContactBean>(
			new ContactBean[] {
				new ContactBean("images/avatars/luffy.jpg",   "Monkey D. Luffy",   "mobile"),
				new ContactBean("images/avatars/zoro.jpg",	  "Roronoa Zoro",	   "active"),
				new ContactBean("images/avatars/nami.jpg",	  "Nami",			   ""),
				new ContactBean("images/avatars/usopp.png",   "Usopp",			   "active"),
				new ContactBean("images/avatars/sanji.png",   "Sanji",             ""),
				new ContactBean("images/avatars/chopper.jpg", "Tony Tony Chopper", "mobile"),
				new ContactBean("images/avatars/robin.png",   "Nico Robin",        "mobile"),
				new ContactBean("images/avatars/franky.jpg",  "Franky",            "active"),
				new ContactBean("images/avatars/brook.jpg",   "Brook",             "mobile"),
			}
		);
		
		otherContacts = new ListModelArray<ContactBean>(
			new ContactBean[] {
				new ContactBean("images/avatars/userpic.png", "Online #1", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #2", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #3", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #4", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #5", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #6", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #7", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #8", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #9", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #10", "active"),
				new ContactBean("images/avatars/userpic.png", "Online #11", "active"),
				new ContactBean("images/avatars/userpic.png", "Contact #12", "active")
			}
		);
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
	public void feedback(@BindingParam("instance") PostBean post) {
		currentPost = post;
		modalShow = true;
		
		feedbackPopup.open(feedbackPopup.getFellow("mainWindow"), "bottom_right");
	}

	@Command
	@NotifyChange({"currentPost","currentComment"})
	public void addComment() {
		PostBean comment = new PostBean();
		comment.setAuthor(currentUser);
		comment.setContent(currentComment);
		comment.setTime(new Date());
		currentPost.getCommentList().add(comment);
		currentComment = "";
	}
	
	@Command
	@NotifyChange({"currentPost", "likeStatus"})
	public void likePost() {
		List<AuthorBean> likeList = currentPost.getLikeList();
		likeStatus = likeList.contains(currentUser);
		
		if (likeStatus) {
			likeList.remove(currentUser);
		} else {
			likeList.add(currentUser);
		}
		
		likeStatus = !likeStatus;
	}
	
	@Command
	@NotifyChange({"menuOpen", "contactOpen"})
	public void toggleMenu() {
		menuOpen = !menuOpen;
		
		if (NewsfeedVM.isWideScreen(orient))
			contactOpen = !menuOpen;
	}
	
	@Command
	@NotifyChange({"menuOpen", "contactOpen"})
	public void contentSwipe(@BindingParam("dir") String direction) {
		if ("up".equals(direction) || "down".equals(direction))
			return;
		
		menuOpen = "right".equals(direction);
		
		if (NewsfeedVM.isWideScreen(orient))
			contactOpen = !menuOpen;
	}
	
	@Command
	@NotifyChange({"contactOpen", "viewportWidth"})
	public void changeOrientation(@BindingParam("orient") String orient) {
		Clients.showNotification(orient, Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 1000);
		
		this.orient = orient;
		
		if ("portrait".equals(orient)) {
			viewportWidth = "768px";
			if (contactOpen) contactOpen = false;
		} else {
			viewportWidth = "1024px";
			if (!menuOpen && !contactOpen) 	contactOpen = true;
		}
	}
	
	@Command
	public void showMessage(
		@ContextParam(ContextType.COMPONENT) Component comp,
		@BindingParam("pos")				 String	   pos
	) {
		String msg = null;

		if (comp instanceof Listbox) {
			Listitem item = ((Listbox) comp).getSelectedItem();

			if (item instanceof Contact)
				msg = ((Contact) item).getName();
			else if (item instanceof MenuItem)
				msg = ((MenuItem) item).getTitle();

			((Listbox) comp).setSelectedItem(null);
			comp = item;
		} else if (comp instanceof Toolbarbutton) {
			msg = ((Toolbarbutton) comp).getLabel();
		} else
			return;
		
		Clients.showNotification(msg, Clients.NOTIFICATION_TYPE_WARNING, comp, pos, 1000);
	}
}
