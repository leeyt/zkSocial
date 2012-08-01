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
import org.zkoss.demo.zkSocial.vo.Author;
import org.zkoss.demo.zkSocial.vo.ContactBean;
import org.zkoss.demo.zkSocial.vo.MenuItemBean;
import org.zkoss.demo.zkSocial.vo.Post;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.web.servlet.Servlets;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Toolbarbutton;

public class NewsfeedVM {
	@Wire 
	private Popup feedbackPopup;
	
	/**
	 * If true, the client is mobile devices such as iphone/ipad/android phones
	 */
	private boolean mobile;
	
	public boolean isMobile() {
		return mobile;
	}

	/**
	 * If mobile, assume started out as "landscape"
	 * Changed when orientation change is detected.
	 */
	private String orient = "landscape";
	
	/**
	 * Viewport Width
	 */
	private String viewportWidth;
	
	public String getViewportWidth() {
		return viewportWidth;
	}
	
	public void setViewportWidth(String viewportWidth) {
		this.viewportWidth = viewportWidth;
	}
	
	/**
	 * Tablet and desktop may use different set of CSS
	 */
	private String css;
	
	public String getCss() {
		return css;
	}
	
	public void setCss(String css) {
		this.css = css;
	}
	
	/**
	 * Menu Group #1: FAVORITES 
	 */
	private ListModel<MenuItemBean> menu1;
	
	public ListModel<MenuItemBean> getMenu1() {
		return menu1;
	}
	
	public void setMenu1(ListModel<MenuItemBean> menu1) {
		this.menu1 = menu1;
	}
	// -------------------------------------------------------

	/**
	 * Menu Group #2: GROUPS
	 */
	private ListModel<MenuItemBean> menu2;
	
	public ListModel<MenuItemBean> getMenu2() {
		return menu2;
	}
	
	public void setMenu2(ListModel<MenuItemBean> menu2) {
		this.menu2 = menu2;
	}
	// -------------------------------------------------------

	/**
	 * Menu Group #3: INTERESTS
	 */
	private ListModel<MenuItemBean> menu3;
	
	public ListModel<MenuItemBean> getMenu3() {
		return menu3;
	}
	
	public void setMenu3(ListModel<MenuItemBean> menu3) {
		this.menu3 = menu3;
	}
	// -------------------------------------------------------
	
	/**
	 * Menu Group #4: Management
	 */
	private ListModel<MenuItemBean> menu4;
	
	public ListModel<MenuItemBean> getMenu4() {
		return menu4;
	}
	
	public void setMenu4(ListModel<MenuItemBean> menu4) {
		this.menu4 = menu4;
	}
	// -------------------------------------------------------
	
	/**
	 * Frequent Contacts
	 */
	private ListModel<ContactBean> frequentContacts;
	
	public ListModel<ContactBean> getFrequentContacts() {
    	return frequentContacts;
    }

	public void setFrequentContacts(ListModel<ContactBean> frequentContacts) {
    	this.frequentContacts = frequentContacts;
    }
	
	/**
	 * Other Online Contacts
	 */
	private ListModel<ContactBean> otherContacts;
	
	public ListModel<ContactBean> getOtherContacts() {
    	return otherContacts;
    }

	public void setOtherContacts(ListModel<ContactBean> otherContacts) {
    	this.otherContacts = otherContacts;
    }

	/**
	 * feedback target
	 */
	private Post currentPost = null;
	
	public Post getCurrentPost() {
		return currentPost;
	}
	
	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}
	// -------------------------------------------------------
	
	/**
	 * Comment for current post 
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
	 * If true, the contact panel is opened
	 */
	private boolean contactOpen = true;
	
	public boolean isContactOpen() {
		return contactOpen;
	}
	
	public void setContactOpen(boolean contactOpen) {
		this.contactOpen = contactOpen;
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
			result.add(FakeData.randomPost());
		}
		return result;
	}

	@Init
	public void init() {
		// Detect if client is mobile
		ServletRequest request = ServletFns.getCurrentRequest();
		this.mobile = Servlets.getBrowser(request, "mobile") != null;

		// Set the default stylesheet
		if (this.mobile) {
			css = "css/tablet.css.dsp";
		} else {
			css = "css/desktop.css.dsp";
			viewportWidth = "1366px";
		}
		
		/**************************************************************************
		 * Menuitems in the Sidebar
		 **************************************************************************/
		
		// FAVORITES
		menu1 = new ListModelArray<MenuItemBean>(
			new MenuItemBean[] {
				new MenuItemBean("images/items/icon_newsfeed.png", "News feed", 8),
				new MenuItemBean("images/items/icon_messages.png", "Messages",  0),
				new MenuItemBean("images/items/icon_nearby.png",   "Nearby",    5),
				new MenuItemBean("images/items/icon_events.png",   "Events",    0),
				new MenuItemBean("images/items/icon_notes.png",    "Notes",     0),
				new MenuItemBean("images/items/icon_friends.png",  "Friends",   15)
			}
		);
		
		// GROUPS
		menu2 = new ListModelArray<MenuItemBean>(
			new MenuItemBean[] {
				new MenuItemBean("images/items/icon_group1.png", "Training",    23),
				new MenuItemBean("images/items/icon_group2.png", "Study Group", 0),
				new MenuItemBean("images/items/icon_group3.png", "Research",    1)
			}
		);
		
		// INTERESTS
		menu3 = new ListModelArray<MenuItemBean>(
			new MenuItemBean[] {
				new MenuItemBean("images/items/subscribe.png",  "Subscriptions", 0)
			}
		);
		
		// Management
		menu4 = new ListModelArray<MenuItemBean>(
			new MenuItemBean[] {
				new MenuItemBean("images/items/helpCenter.png",  "Help Center",    0),
				new MenuItemBean("images/items/settings.png",    "Settings",       0),
				new MenuItemBean("",                             "Edit Favorites", 0)
			}
		);
		// -------------------------------------------------------------------------------

		/**************************************************************************
		 * Contacts in the Contact Panel
		 **************************************************************************/

		frequentContacts = new ListModelArray<ContactBean>(
			new ContactBean[] {
				new ContactBean("images/avatars/luffy.jpg",   "Monkey D. Luffy",   "mobile"),
				new ContactBean("images/avatars/zoro.jpg",    "Roronoa Zoro",      "active"),
				new ContactBean("images/avatars/nami.jpg",    "Nami",              ""),
				new ContactBean("images/avatars/usopp.png",   "Usopp",             "active"),
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
	public void feedback(@BindingParam("instance") Post post) {
		currentPost = post;
		modalShow = true;
		
		feedbackPopup.open(feedbackPopup.getFellow("mainWindow"), "bottom_right");
	}

	@Command
	@NotifyChange({"currentPost","currentComment"})
	public void addComment() {
		Post comment = new Post();
		comment.setAuthor(new Author("ZK Team", "images/avatars/zk.jpg"));
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
			currentPost.getLikeList().add(FakeData.randomAuthor());
		} else {
			List<Author> likeList = currentPost.getLikeList();
			likeList.remove(likeList.size()-1);
		}
	}
	
	@Command
	@NotifyChange({"menuOpen", "contactOpen"})
	public void toggleMenu() {
		menuOpen = !menuOpen;
		
		if (orient.equals("landscape"))
			contactOpen = !menuOpen;
	}
	
	@Command
	@NotifyChange({"menuOpen", "contactOpen"})
	public void contentSwipe(@BindingParam("dir") String direction) {
		if (direction.equals("up") || direction.equals("down"))
			return;
		
		menuOpen = direction.equals("right");
		
		if (orient.equals("landscape"))
			contactOpen = !menuOpen;
	}
	
	
	@Command
	@NotifyChange({"contactOpen", "viewportWidth"})
	public void changeOrientation(@BindingParam("orient") String orient) {
		Clients.showNotification(orient, "info", null, "middle_center", 1000);
		
		this.orient = orient;
		
		if (orient.equals("portrait")) {
			viewportWidth = "768px";
			if (contactOpen) contactOpen = false;
		}
		
		if (orient.equals("landscape")) {
			viewportWidth = "1024px";
			if (!menuOpen && !contactOpen) 	contactOpen = true;
		}
	}
	
	@Command
	public void showMessage(
		@ContextParam(ContextType.COMPONENT) Component comp,
		@BindingParam("pos")                 String    pos
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
		
		Clients.showNotification(msg, "info", comp, pos, 1000);

	}
}
