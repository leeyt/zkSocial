package org.zkoss.demo.zksocial.composite;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listitem;

public class Contact extends Listitem implements IdSpace {
	private static final long serialVersionUID = -4653481165297843651L;

	private static final String STATUS_ACTIVE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAQCAYAAADagWXwAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAEVJREFUKFNjYBggkLrCIAGIdwLxVyidAHYKVOInkP6PhEH8BJAkSAeyBIy9EyQJMgqb5FeCOkGOwW4nkqMwXTtAoYfTWgCjPl0laAq3hwAAAABJRU5ErkJggg==";
	private static final String STATUS_MOBILE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAQCAYAAAArij59AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAadEVYdFNvZnR3YXJlAFBhaW50Lk5FVCB2My41LjEwMPRyoQAAAD5JREFUKFNjYKA9mDpvUxoQ/8eB0xhAEjBnQBWnIfH/gxXAJLDQEAW4PAKWGzIKiPYmUOFCEEYPKPxBTSi2AfdIitO9E5tOAAAAAElFTkSuQmCC";

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
		
		if (STATUS_ACTIVE.equals(statusImage))
			return "active";
		else if (STATUS_MOBILE.equals(statusImage))
			return "mobile";
		return "unknown";
	}
	
	public void setStatus(String status) {
		if ("active".equals(status))
			this.status.setSrc(STATUS_ACTIVE);
		else if ("mobile".equals(status))
			this.status.setSrc(STATUS_MOBILE);
		else
			this.status.setSrc("");
	}
}
