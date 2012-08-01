package org.zkoss.demo.zkSocial.composite;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listitem;

public class Contact extends Listitem implements IdSpace {
    private static final long serialVersionUID = -4653481165297843651L;
    
    private static final String STATUS_ACTIVE = "images/status/active.png";
    private static final String STATUS_MOBILE = "images/status/mobile.png";
    
	@Wire
	private Image picture;
	@Wire
	private Label name;
	@Wire
	private Image status;
	
	public Contact() {
		Executions.createComponents("/WEB-INF/composite/Contact.zul", this, null);
		Selectors.wireComponents(this, this, false);
	}
	
	public String getPicture() {
		return picture.getSrc();
	}
	
	public void setPicture(String picture) {
		this.picture.setSrc(picture);
	}
	
	public String getName() {
		return name.getValue();
	}
	
	public void setName(String name) {
		this.name.setValue(name);
	}

	public String getStatus() {
		String statusImage = this.status.getSrc();
		
		if (statusImage.equals(STATUS_ACTIVE))
			return "active";
		if (statusImage.equals(STATUS_MOBILE))
			return "mobile";
		return "unknown";
	}
	
	public void setStatus(String status) {
		if (status.equals("active"))
			this.status.setSrc(STATUS_ACTIVE);
		else if (status.equals("mobile"))
			this.status.setSrc(STATUS_MOBILE);
		else
			this.status.setSrc("");
	}
}
