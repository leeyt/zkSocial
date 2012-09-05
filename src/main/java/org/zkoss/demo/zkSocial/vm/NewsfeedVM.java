package org.zkoss.demo.zksocial.vm;

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
import org.zkoss.demo.zksocial.composite.Contact;
import org.zkoss.demo.zksocial.composite.MenuItem;
import org.zkoss.demo.zksocial.data.FakeData;
import org.zkoss.demo.zksocial.vo.AuthorBean;
import org.zkoss.demo.zksocial.vo.ContactBean;
import org.zkoss.demo.zksocial.vo.ContactGroupBean;
import org.zkoss.demo.zksocial.vo.MenuGroupBean;
import org.zkoss.demo.zksocial.vo.MenuItemBean;
import org.zkoss.demo.zksocial.vo.PostBean;
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
	
	private boolean mobile;
	
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
	
	private ContactGroupBean[] contactGroups;
	
	public ContactGroupBean[] getContactGroups() {
		return contactGroups;
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
	private boolean hideContact = false;

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
		return (currentPost == null) ? false : currentPost.getLikeList().contains(currentUser);
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
		mobile = Servlets.getBrowser(request, "mobile") != null;
		
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
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAutJREFUeNq0VU1IG0EUdmOi6yYmIT9EKaU1JFg0hxIJVEQNJPbixdKUFjw0OQg59OClQqmHgBJKPbSn2KSC0LMX8ZCLgSLUetCDYq6VnkoJarL9cdfort+TbFmSVDYGH3wsM7PzvXnvfTOvpeWGjdnY2GiKYGRk5Mp1fZ0NrfiYAQfA0SEa9CkDInAIHOtlWa7+oXN6ejo2Pj4+19bWxl0nqvPz8/Lm5ubi7OzsHFOJwgTQyf8AXSsrK58dDsedZlJXLpeFsbGx+0RuTSaTL51O5710Ov16e3tbZhiGpciOjo6Os9nsR0EQvmNOrpzOGgwGJ71ebz/mxbW1teVSqbSv0+kkkNpAGnW73R69Xs/id5Yc2Px+/wtKBzZ+gYN1Iifs7e3llpaWMvjnh+pwphOYx+N5x/P871QqlcPcN0AC7N3d3YGenh6PIiJy0A6yViI8PT01UqoUBzAeKOVyub8KeygUkgwGwy9aRxrtq6ury/DHX7IxTCvmXOq6XqpIIVQWqsZyHSH8mzMajSaCaqFWpmpy9fjs7IzyaAqHwyeqPRxyz9F6sVgszcAODg7yFXlaE4nEzNDQ0KimCHp7e0cnJiaecRzHKxuQRnZgYOBR5V8d6nd3cHCQeGSkysSybEdNiiRJuoTaAY1dLteteDz+pp4Mab0TNjU19aremtYayIVC4SdJUNMVlmXGarU6IALDlREo46+w+fn5t5gqaLxfllgs9jwSiTzVFEFfX58PNZyE/Ioaby/r8/mC/5VptYqQYvPw8PCTRp8JzfcAN5Xf3d1dRwSHWoghaw4RPMRlc15VA0YZ5/P5/YWFhU+N1CAajbZD2pGafkDkoihSH7ArzwX0/iCTyXxoREUWi8VRnSJZSUkgEHhss9n6zWazncYg1iHcriZqIJEDEdUX8by24yW8Taj3pjRqyAb1FkFHrW1ra+s9Jk7UxW4GeE6EnZ2dReJWOpqlkvuOa/Tgej1ZqPTkYstN24UAAwCE6hRrSCrxTwAAAABJRU5ErkJggg==",
								"News feed", 8),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAu5JREFUeNq0VVFoUlEY9k6didvUajUXJBujgh70NQhb9OSL4mYNWQ972cMGwh6rhw0yo4eBD/ZgCYPFGgzU2DTGHqLYQzFh1ghiMgeDpjTnnBs6U6f2/eMaardgagc+7j33nvP95//O95/D4/3nxiwvL9dFoNFo/h2AhQQ4B7QCglPw54EkEANSQKF6AJFJDAbDnYGBgWednZ3XTptBNBrd8nq9lunpaTe6B1wZKOfm5pY6Ojqu1ipTMpmMm0ymm4eHh+voFsv/NZEs7e3tPcVikVcrJBLJWbVafR1cl4Eu4DzQXJKIzzAMnwbW046OjhRut/t9Op3OQbbVycnJp5FIZIMyYOpZfQm5XK5VLpd3YR+vIBvT+Pi4nYxz4ph6V89yNJXzKJXKG3jIGhagmkcoFIrocRKgUCg0hJyDh2lYBiKRqJmLhwIUGhFALBYL/xYgjQLZb2lpkddKDmnye2hcUpNN436//0UerVaLrqysvIb/96q/k/qUwQGKwra2tvZRpVJpstnspd7eXiNcICxfSTAY/Lq5uRkiO5atPIN5qwiwPjIycrc8A8oKj2NmYWGBp9PpGLa0zwDdDofDhYLpLg0OBAJvnU6ne2Ji4iGfz29FEOa3TRimiA0WtbW1VUi8vb39eXR0VCegVObn5ymfDEGv1/9AqX9TKBTdtArcFy9tNpvDbrc/IKeQlFKp9EJ1hsSDeTtYwDH2NDQ1NfWI5BdwbEwqHo9/ymQyt5Hdk5mZmVcko9lsHmPvC7FWq701ODhoxSEnLU1KJBLx4eHh+3jdYu+GBPBTwGGtdCgUehOLxfwg/4L+vsfjybOTon19fU2Li4vhVCq1MzQ09Fwmk12kSbu7u3RUB4HvFS7i2Pljn8+3AfIPeN/DCVnhLvQp5QSkW7JYLPfC4fA6fUeAAB2qf5QyJtTk/f7+ftpoMeqnx2q1PoaTnLOzs+9IlooALperrgo2Go10qImBLMlbfaMJGnCIZlhwtl8CDAA8PQ/NwxQAIgAAAABJRU5ErkJggg==",
								"Messages",  0),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJJREFUeNqcVl9IU2EU35xtc7P9Y6z1JBUISSvpLUQkAiEECXG0t6CB+mRpQY+NNinSnA/2MJtC6ktCU0ZRCVo4EElQwbUHna621FrTtTZtztz6fXIv3G73zusO/GDfvec7v9853/nOnTiXy4mIicViEY9JACWgAkqBY0AWSAO/KGSAHNfmYhG/ianAJ1tbW82VlZXX9Hr9KY1Go89kMrubm5tr4XD44+DgYN/i4uIn+G0Bf/4LwpMBWWhqa2svW63Wx0aj8QyfCkI2NTXlttvtT7Bco7I5lEBVX19f19LS0qdQKErph1AdXVpamkUWJ8rLyy9KJJIi+t38/Ly3ra3tNn5GmJlwEUgNBoPJ5XK9JeWgH0aj0TUQ3ojH48tYKpqbm60Wi+UuU9XExMRTh8PxAD9/0GdSxJG1Bkra1Wq1npDTmJubG0fwAN6HgSAEvEgmkymmT1VV1U2TyXSWagQRFwFJQ1dRUVHH3EigUqkMeCenuqpYq9WqZDKZnOkjlUpLGhoaLFS3cRJIqqurTyuVSjWbAF10xWw2X4fPeZ1Od8lmszlwBsVsPzTEBVJCvjaVlJWVGelzYRrUyZuamh42NjbegYBSWj3b5HK5lhmXTbC/vr4e5dpIG0pzcPB8Pul0OsHsInaJ9icnJ5djsdhGNpsVFYJgMOhDnBQfAZEVw818xa6tEOzs7KRGRkZeIsY2HwGx5PDw8ABaMHlU9bhsnkgkspqvRMT24BScnp4eOIp63PINp9NJxkWcOfi4CMjLnz09PS7c3i9CgkN9bmxszJ5KpT4TgcxgRTzNQlL86vV6O/ZhhxH4/f53Ho/nNfNwDyMgtg1VbxYWFrz56p5IJLb6+/s74P+d+k4IJiDO0a6uLhtKFeJSTrIbHR29v7Ky4mePaSEEB+MedV0dGhq6hwuUZhPMzMw8R2k81FctVwjBQal8Pt8HjGInM3goFJrt7Ox8RI3mLN9mIQREWdztdj8LBALjJDjG9rfu7u5b1MdlL/9uSpEAk2HInevt7X1fU1NzFevj1HjPa2IB/yr+GZZACbAL/Oare6EZFGR/BRgALZ+XGZ2aoKsAAAAASUVORK5CYII=",
								"Nearby",	 5),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2VJREFUeNqsVl9IU2EUv1v7v8mcZbOxESgS+GI+mCDknxmob66nEgyCBCUfDNZT7EVGxFxIMOxBEtxLOoimswcHimn0MrAymbkedLiMmbatzbV/bp0j3x13624l88Dhu9/3ne/3u+d855x7OSsrKxRTWlpaBDBUgMpAI6A/wSZBsQibLWiOLTfvAAeG8+Pj48+Xl5e/4Ihzsk79jy1oji2HkEjIW1AajabWarW+5XK5nHQ6nenr62vz+Xwe2OLlcaSK2FLEoygeKuvv77/Z2Nh4h8fjpSYnJ+144MQ9GOvq6q6NjIwY4vG4iIkuFApjhWxTqRTP5XJZJyYmXiPBhZ6enqdSqVSBhmKx2JUA4fP5gmQymZBIJOrq6uobbHfAYquhbdVqdQMQrJ7DqIAHD+hDAoGAPzs76wBCzszMzJvW1tbrVSBsBOCF0G63Z207OjraLoLgHpCKpqamXqB7V5eWlj4wD+7s7Hj9fv93lUqlhjirqSKyu7vr29vb87HZarXahpOLy2QyOYcuE2Hbyxc1kUK2WYJoNBqLRCJBDoeTpkoQwOLKZLJyuA9RDoHH4/mk1+sfwtRPlSZKs9k8Wl9f35QlgBymIEXj8Li9sLDgOw1aZ2dn/lIUsRAzxwM6fsw4dnV18WFQMFpBilSrlJgcg4ZAf4D+ZoQpi1OQoLu7W4h3aDAYHjU3N99dW1t7Nj09/cpkMuU0r0Ag8KW3t1d7agKscIvFMgqFo8O1o6OjsmAwmIQUdobD4XK5XI5pqdrf398mnlBsBFz6DugFfM7Gj8ejHA6Hjd7HnB8cHLw3NjZmBgLx1tbW++HhYT0JUw4BjcFl84A8hwcGBu57vd5VMs+QMCSGhoZuQRrK5+fnLViXoPGiHrARzM3N4aGASCSKMvbRvqKmpkZ7cHDwbXFx8SMz9qcioN8auyY+Q29Bwkx7e/slaHLloVDoKwlNphjBX60ir9yPDw8PP29sbDyBfvMO5rHKysrA+vq6Ce4D+9evIhX97zpAAqfT6QF9TGogYbPZ3KBGkjmxQuBMggxO4COBRVWl0+lK7BRUFWLRIUaCOCwklUrlFaPRaIHUTJaCjuCAVYuYiI0Egc3NTRt87m4rFIom6gwE3j7tdrtfIjZ+cASk3+Dvh5A6G4mTX5jAHwEGAEF2JdGXXHDPAAAAAElFTkSuQmCC",
								"Events",	 0),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAaFJREFUeNpiPHToEAMlwNbWFq88CxrfCYi3AzEbsRYcPnwYmdsBxGuA+A7Q4o8gAab///8zIGF1IGZDEyMFhwDxOiC+AQwZsCOZ0Bx0EIizgPgymSF2Coi3ArEEEHOCgwhkMxSsAGIVIP4CxBFAfJWBCgDZgtdQC0C0EAOVALIFuUBsCcQJQLwOKnYNiI8QaVYaIQtAwABNISxOcAInJycwvW/fPqwWoKeivzhSRzUQn8GG9+7dqwUyCJs+bD64AMTLgNgbiPmRxFuhGAO4uLgwwCzAGkT//v1DDkNjIL4JxBOB+CSSugRo/GCAXbt2dbi5ud1HMgdnHGRC4+AdEK9EU7cbT954itcHSBKG0OQJsqQLKiYK9RU+oLtjxw6ikikIeADxUiR+CBSTnw9whR0tMhpNAHI+UATim0CsDcQFFJSoOPPBMWgpCAIatAgiTyA2A1UWQOxKCwtAuVgLmookaBXJnFDDt8IyEIlgMRBH4SvsVgLxU2iEkxOxsUAciK+wA9VmQdDaLY2CkLkJNQujVQGrV5WINSk4OBivPECAAQBTpZHKNpK1HgAAAABJRU5ErkJggg==",
								"Notes",	 3),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAa9JREFUeNrUlt9HQ2EYx8/WERExuoqldDtKdLXjpESUZmd3I0oZpf6AbqLrbkbpIjGii8QuEidz5BzrakRX00VERERXEdHW9+HZHK/HOus06eHjvD+25/s853nfZ4vU63WtkxbVOmx6uVwO5cAwjNYC/JwFJyDWroAS4D1IQbTaFOAakHMPXIZ8IyvgAEyqAhR5BRzy+g6YA6dgt5VH0zSbY8/zaJLAcwbPJ+xVdeEU0eY2j8c5s0qQ8H2+RkDBdd1BSeDzm3nDhsAbeBEEyAZAr16r1dQvXoF9kOSa3AjOu0ERPACrsSj40qKkKrAJxsCWby3mG++BUZB2HGfJn4FKlFQDEAd3IAsWQc63ly+VSvFGBip6gFbRBy5APzgGH8J+AUxLvqQaaOyMitfFThO8TvMe4fNTtm2viTdZEEhyxMt0zMFCwEuWEAWUtCjyM067+CvNTsmAetL5D31d8+0XM6DmlAa3IYKl1zkBHiWBFMhzawhj1E3XQVYVoI15Og3cR8JYDmyAVzrSapGfwRH3mTBGPWrVsqx3VYBqMdyOp0wm87e/yZF//6/iS4ABAJTXDst1neyvAAAAAElFTkSuQmCC",
								"Gallery",   0),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA0dJREFUeNrUVl1MUmEY9vBzDiAqEoE6ieVg+bMVZWSsQSZbOTfn5uaFrsyLrprdVF6RmuOi1k3ZupHm6obUvLAtYl6J4lUL5+bGHJsylZYDqdEskP/e1x3YEaHM1oXf9nDgfb/ve96f5/sORCqVKjjSg3A4HPuMer2eAw8hDT4gCfgO2AZEYU2KMfe3BJwcm5OlpaXy3t7eW5WVlZckEkmVUCgsttlsL30+38fx8XEHzAkASfwgGXBy9KCkv7//YX19/XWmsb29/S4+6+rqnoD/EawLHpagSC6XX87XfMjuHJbtXwhIDocjyEfA5XKxL2RjYyP2phggAMToHoUAibwETU1NbIyOxWJx8xEkEgmcU9Lc3Fzd0tJyB3pUHYlEtufn51/Pzs6+93g8n1EIGYJkMslcz4fmXhMIBEVZ9swoLy9X1dbWqru7ux9IpVJV2t7V1fWspqbmTF9f3334+S1fiTiwgRLlmy8DUJRIrVaf5fP5suw5JEkq4MFj2lgYaRqY2vr6+qednZ0I086E3+/3zs3NudlsNpnti0ajWD4SA8wQYBRpwIiYzeZ3Vqv1MdOeRjgcDhmNxns9PT0aiJaX7VepVBook3QPATMCOEyogK8jIyPmqampp7FYLMmILj45OWlaW1tzUhQVzZUdqI+SyWRk3hIhIHo8oVtA8hY2zWwUCAT8FovFBr7NgYGBYa/X62KuA3WlxsbGjKCkZZiT2tfk1tZWgr46UNcirVarBs1TaT9BEOg7Rmt/a2ho6Pbg4OCrioqKKvQ7nU4rBDABX4N7CJC9ra2Ngu/HQR2nGhoa9AqFQqdUKrWweUZNYrFYOjo6alldXbUvLy/PQAndLpdrpqysbJdgCQZ9yFj05bi7EKOmQHpKiOY5bHoFIiUOcgWEQqHt6enpN/F4nOro6LgJJdxcWFiYsNvtH9xu9wpmCQijrCQmk+kFKOAqs/t/Glg+UMz5xcXFjyKRSAzn5wRmbTAYbuh0OgMobgUE8QXTKYbOX8gly4NAo9Fc3NjY8DCkTkBfTnd2dg5j8EgQ4/F41GEJoDeFwWBwKYf95K424KMQIKPfXqy/fCNiU3/Sl5uQcU1gKj8AvoIjP4j//bfllwADAA3FOQiAHgGqAAAAAElFTkSuQmCC",
								"Friends",   15)
					}
				)
			),
			
			new MenuGroupBean(
				"GROUPS",
				new ListModelArray<MenuItemBean>(
					new MenuItemBean[] {
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA0dJREFUeNrUVl1MUmEY9vBzDiAqEoE6ieVg+bMVZWSsQSZbOTfn5uaFrsyLrprdVF6RmuOi1k3ZupHm6obUvLAtYl6J4lUL5+bGHJsylZYDqdEskP/e1x3YEaHM1oXf9nDgfb/ve96f5/sORCqVKjjSg3A4HPuMer2eAw8hDT4gCfgO2AZEYU2KMfe3BJwcm5OlpaXy3t7eW5WVlZckEkmVUCgsttlsL30+38fx8XEHzAkASfwgGXBy9KCkv7//YX19/XWmsb29/S4+6+rqnoD/EawLHpagSC6XX87XfMjuHJbtXwhIDocjyEfA5XKxL2RjYyP2phggAMToHoUAibwETU1NbIyOxWJx8xEkEgmcU9Lc3Fzd0tJyB3pUHYlEtufn51/Pzs6+93g8n1EIGYJkMslcz4fmXhMIBEVZ9swoLy9X1dbWqru7ux9IpVJV2t7V1fWspqbmTF9f3334+S1fiTiwgRLlmy8DUJRIrVaf5fP5suw5JEkq4MFj2lgYaRqY2vr6+qednZ0I086E3+/3zs3NudlsNpnti0ajWD4SA8wQYBRpwIiYzeZ3Vqv1MdOeRjgcDhmNxns9PT0aiJaX7VepVBook3QPATMCOEyogK8jIyPmqampp7FYLMmILj45OWlaW1tzUhQVzZUdqI+SyWRk3hIhIHo8oVtA8hY2zWwUCAT8FovFBr7NgYGBYa/X62KuA3WlxsbGjKCkZZiT2tfk1tZWgr46UNcirVarBs1TaT9BEOg7Rmt/a2ho6Pbg4OCrioqKKvQ7nU4rBDABX4N7CJC9ra2Ngu/HQR2nGhoa9AqFQqdUKrWweUZNYrFYOjo6alldXbUvLy/PQAndLpdrpqysbJdgCQZ9yFj05bi7EKOmQHpKiOY5bHoFIiUOcgWEQqHt6enpN/F4nOro6LgJJdxcWFiYsNvtH9xu9wpmCQijrCQmk+kFKOAqs/t/Glg+UMz5xcXFjyKRSAzn5wRmbTAYbuh0OgMobgUE8QXTKYbOX8gly4NAo9Fc3NjY8DCkTkBfTnd2dg5j8EgQ4/F41GEJoDeFwWBwKYf95K424KMQIKPfXqy/fCNiU3/Sl5uQcU1gKj8AvoIjP4j//bfllwADAA3FOQiAHgGqAAAAAElFTkSuQmCC",
								"Training",	   23),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAddJREFUeNq0lt9HQ2EYx8/ajIhxGBEjYolSoqvmXI1RdrPuuolSN1E3XaTrka66SBcR6R/I0t252ayr6eIwxhhjjIgYY4y2vi/f5d3r3Wm/zsPHOWfv6/n9Pu983W7X8FIC+Xx+IgWxWMx1fcbwWALK9y04A9dgm7gKMrCLxxtogA1EVO0zoNTgFcyCAvgGpSGcrIEHGvhSF33ZbLb3vgpOp5SZO8uyimoEO+BY2dhiJCF6OgeaIEKPFwYYEJEUdSkyNAaEIj8VB/kUxiouBvy6GrQ0G00iZF5ZW3FxrPFnoNPp9N5Fod6nVIOSLoIkeGYH5cDVGIqF5/vgw7btcjweX5YjEEV5Ym4rjGhUaUltW5MjEL1/AB7BIVgaQ/kL2BK1SiQSJ2oNNsEFcDSt2mTK6mzTRSq7UfaJE7xHI/agk9zQeBaU2i5EI8GhZxEjEJ6npfwNkjqJaPYVaDjXNyoymUxvyDkcbusTtOcRdZ0nk0lHTpGYoJcgynrIHodBG3xKh61J1GYQ6bNAgg73HTRDE3KZJ7nKOpisg8N03v9bA5dZFKVHJiftLIeYyTS2R7lwivKAkjompPwWHkLnj25UrPE7PWpVU6mU6y3p+Z3s8/pvy68AAwD4ZZoWZfZ1ZwAAAABJRU5ErkJggg==",
								"Study Group", 0),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAXVJREFUeNpi/P//PwMtAQspig8fPiwEpDYCsQEQN9na2nYT0sNEigVA36YBsQ0Q8wBx+6FDhzgp8sHBgwdBBmwB4tdAHA3EN5GkH9nb23+n1AegILEBYm8gZgYauB5IWwFxFhCbEONrRkKRfODAAWkg9cvBweE1OZHMSGwq2r9/vyiQ0nJ0dDxIEwtolkz37NkDSpJzkITYgFgCiH1cXFxOUWwB0IcgC4yxSOkC8SmK84Grq+sCoCWGQPwFFJxQ2gSIV1I1Dnbs2PEBSPED8UcPDw8BqhcV5CYGmltAVFm0detWIaAFbNA4YAPy+alqwb9//+YDMScQM0Dp+VSzYNOmTTlAV/tBXQ/DgSBxilPRxo0bQXngJDRzoYNfoILP39//LFkWbNiwARTOZ4BYBY/++0BsGBAQ8JHkIAJaPBOIVdCCBh0rAvEckuNg3bp1oJornIDhMBwCUk+SBUBNpUQaDsONpAbRZSD+TkJ+ujxg9QFAgAEAJz0cNDc6+doAAAAASUVORK5CYII=",
								"Research",	   1)
					}
				)
			),

			new MenuGroupBean(
				" ",
				new ListModelArray<MenuItemBean>(
					new MenuItemBean[] {
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAA2hJREFUWEftll1IU2EYx0VEvBAzr0rRDMQLkSLwIoLAkhRMooII8iaFgsy+hEgiDdMsHSPn/NicOvehZ9Mz5zb37Ta3tXSSDZHoQrwIiejCi5AY0sXb88hO5Gk7O0e99MAPDu/7f/7Pc573OWdLSTm8Djtw2AGBHfCFPmUB9YARiAAkxmpsDfeyBNoml/tCy+lAq/f98k+nL0xm3SFidgaJ0ebfAe9xDfdQA9o2ICO5Mw8FGBYAEZtnAZIFyLTVzwlqbHMLxBtcXsVYHikSSzzB5QLX/NLGjD1ADLPzgsAYjEWPPRXhCXxMd3kXI/jEtMX3H6NaY0Qq11iQIRUdnDR5omwdxoLHCnoJLsLlC7eiwZTZuwtq2rVZd6fxIRieAopjlJ4+U1YDRa2w9TtF+MLtggpw+5cyzY7AFjwVYfPgSXMTmGWjIehKgMKYeVpFZXWlzuiOsmNMDv8W6Pi/HVZ3qEEPyXUzc7tQUuZ1SJaDCZ3z4Vt2GEy7d5HAfX2siPSBUZ2HHYde8JY08O6Czug0UUY3YTMwQpkYE9ri9TD747RVwawr1DQVL1Y/43byL2DavTFhcBE2BhhGh2dxBzxr3NdOOaKNj5trGPMJg+NzvFjK4PzGuwAw2B6nnSQZKp1189qNWmx/JprDkdTrjXNx49CTdwGaKcc2QLhQT9p/36ytu80kt859aMaJ54jhX8Cw1vRdpbcRLsR9SjuTfNYVuohHwqVHT94d6FfoQkrKSrjolWv/Dp6KMrcl00tkWjfvAl697W0fGbcQLnDQTPC5RXDqk+k7uvv5f4zKK6qK5SpjVKExkUT0DlFrr0WDFkQiG//CpUWvquqrJbw7AMK0lo4epVw9QxLRKZYpmU9xW5dUyqVt7ZSo0FNIASm5eflFPYPaNdnYNImHZFAzyBj2DKi6EunQ43hePv5mCL5SL1y6fEUip34MKA2EjUxJ+6fMnruIbIx2xtNgLHpA5lTB2WMBGefLK6+L+zRr/SM0+ReF2kTU8KoiwzAr7H1xv2YdY8Fn3/+MMrKP5px9+uKNUjJE/ZIOTxIuUPPsZbfmSHbOuYNIznQOW1h44mRR5b1Hz0XtInlQJFV97VVMEgTvYS10v6nlHWpQu5+2cx0XTvIxoBQoA8pj4D2u5Qqe9r0Ox2HcQXTgD6iSQ7+NIwcpAAAAAElFTkSuQmCC",
								"Help Center",	  0),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAqRJREFUWEftVd9LU3EUlx6kpxAR/4QoGHuMiBg+S4HEiBApkejBQIp8EA1JrKi0QleZzv1wzm333m33bnPdzTk3K9sWhPTUnxJ7kOP5rHvjbk3w7loPsQsfzs75ns/nfDj33t2OjvbV3kB7A9Y3YGeJPobNulQLCkm1UCl+2SeOpRbopii93N1jZJw7b+tOZj9X5fQupTgib1BEP3iWr975haXZpy8WHrISVn6a0T3zeG5Y/rBL8XSREJGjrp3bn710TYNn2YQoq6OZQpkyhQop6Z3KjcGbgzNP5ibkzfz32GaBdCBHHefo+8Upk5TIjFpagZovXY+mCgQo6kfKFb9SZqdc+63X9TPUcW48Y77TkgGQBTmnSskdMgvwLA+HwPTs/CUhkScziCjbB1OPnl84CQNdr994JiPyNjVA5fwio1OLyOt6XrlWJthAV6sm8MQ7xiemxwQl+yMU3yIdG9FM09WuS6ps7AMPfOhob9DxvYhKtsIPUDW1tUeCkqeNaPY3gpIKc39cbMxu7AMPfOhA7/jT8eBpQ4NShhrRf/Va07Wi3qwfpqBnykBAVFXGTwY14t6DyYFmYvfHpwaa9Ws6rb0R/kja4Qkm9jmSjkAkuccG8AE6oxlBtPnDiV1jn8bD/bd2uQPxEW8oRTr4XlNQTH4aHRvHv1wfIvIQ14197rXYkLXJGvvW7btXVtcTZARWrf8DIiI3nrsDiYOh4Tv9J2GgxxuSSysBhczCxzw2UPcVNW1o2Rse8YVTtLwWN40aj/mmhxoJfF+Hlnwx0pDjeJbhfOuRvhnqpOVO7Rx9NQ74lgyAzOKDjLqv2juP6OAaBtfw3h+7bByEfvAsDz9KgN+EzkW3UHWtioTI+am/NuwoYZdbVBdXBEL858PbA/+LDRwC163enotUjD0AAAAASUVORK5CYII=",
								"Settings",		  0),
						new MenuItemBean(
								"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAHZJREFUOE9jYEhrYSAVL1694z8Ig/WBCJgAPhrJEjlkdSQZgG4B0FA2FAPAAji8hEUzxOvIXiBBszRcLSEDsNisgjMQsbiAE5vTiTYAl7+JNYAZSSEPsuuINQBnAhvcBggRkS9EycoL2PIJSZkJrwFEOBtnjAAA3+nYo4sOzcsAAAAASUVORK5CYII=", 
								"Edit Favorites", 0)
					}
				)
			)
		};
		// -------------------------------------------------------------------------------

		// Contacts in the Contact Panel
		contactGroups = new ContactGroupBean[] {
			new ContactGroupBean(
				null,
				new ListModelArray<ContactBean>(
					new ContactBean[] {
						new ContactBean("images/avatars/afro.png",		 "Alred",	"mobile"),
						new ContactBean("images/avatars/alien.png",		 "Bruce",	"active"),
						new ContactBean("images/avatars/anciano.png",	 "Selina",	""),
						new ContactBean("images/avatars/artista.png",	 "Robin",	"active"),
						new ContactBean("images/avatars/astronauta.png", "Harvey",  ""),
						new ContactBean("images/avatars/barbaman.png",   "Tony", 	"mobile"),
						new ContactBean("images/avatars/bombero.png",    "Nick",    "mobile"),
						new ContactBean("images/avatars/boxeador.png",   "Peter",   "active"),
						new ContactBean("images/avatars/bruce_lee.png",  "Clark",   "mobile"),
					}
				)
			),
			new ContactGroupBean(
				"Other Online Contacts",
				new ListModelArray<ContactBean>(
					new ContactBean[] {
						new ContactBean("images/avatars/caradebolsa.png", 	  "Gwen",	 "active"),
						new ContactBean("images/avatars/chavo.png", 	  	  "Natasha", "active"),
						new ContactBean("images/avatars/cientifica.png", 	  "Steve",	 "active"),
						new ContactBean("images/avatars/cientifico_loco.png", "Mary",	 "active"),
						new ContactBean("images/avatars/comisario.png",		  "Clint",	 "active"),
						new ContactBean("images/avatars/cupido.png", 		  "Phil",	 "active"),
						new ContactBean("images/avatars/diabla.png",		  "Pepper",  "active"),
						new ContactBean("images/avatars/director.png", 		  "Curt",    "active"),
						new ContactBean("images/avatars/dreds.png", 		  "Reed",    "active"),
						new ContactBean("images/avatars/elsanto.png",		  "Sue", 	 "active"),
						new ContactBean("images/avatars/elvis.png",			  "Johnny",  "active"),
						new ContactBean("images/avatars/emo.png", 			  "Ben", 	 "active")
					}
				)	
			)
		};
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
	@NotifyChange({"currentPost","modalShow","likeStatus"})
	public void feedback(@BindingParam("instance") PostBean post) {
		currentPost = post;
		likeStatus = currentPost.getLikeList().contains(currentUser);
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
		
		if (hideContact) contactOpen = false;
	}
	
	@Command
	@NotifyChange({"menuOpen", "contactOpen"})
	public void contentSwipe(@BindingParam("dir") String direction) {
		if ("up".equals(direction) || "down".equals(direction))
			return;
		
		menuOpen = "right".equals(direction);
		
		if (NewsfeedVM.isWideScreen(orient))
			contactOpen = !menuOpen;
		
		if (hideContact) contactOpen = false;
	}
	
	@Command
	@NotifyChange({"contactOpen", "viewportWidth"})
	public void updateDeviceStatus(
			@BindingParam("orient") String orient,
			@BindingParam("width")  int    width) {
		if (!mobile && width > 1366) {
			viewportWidth = "1366px";
		} else {
			viewportWidth = width + "px";
		}
		
		if (mobile && !this.orient.equals(orient)) {
			this.orient = orient;
			
			Clients.showNotification(orient, Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 2000);
			
			if ("portrait".equals(orient)) {
				if (contactOpen) contactOpen = false;
			} else {
				if (!menuOpen && !contactOpen) 	contactOpen = true;
			}
		}
		
		if (width < 700) {
			hideContact = true;
			contactOpen = false;
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
		
		Clients.showNotification(msg, Clients.NOTIFICATION_TYPE_WARNING, comp, pos, 2000);
	}
}
