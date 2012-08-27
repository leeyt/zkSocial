package org.zkoss.demo.zksocial.composite;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listitem;

public class MenuItem extends Listitem implements IdSpace {
	private static final long serialVersionUID = -4653481165297843651L;
	
	@Wire
	private Image icon;
	@Wire
	private Label title;
	@Wire
	private Label count;
	
	public MenuItem() {
		Executions.createComponents("/WEB-INF/composite/MenuItem.zul", this, null);
		Selectors.wireComponents(this, this, false);
	}
	
	public String getIcon() {
		return icon.getSrc();
	}
	
	public void setIcon(String icon) {
		this.icon.setSrc(icon);
	}
	
	public String getTitle() {
		return title.getValue();
	}
	
	public void setTitle(String title) {
		this.title.setValue(title);
	}

	public String getCount() {
		return count.getValue();
	}
	
	public void setCount(String count) {
		int value = 0;
		try {
			value = Integer.parseInt(count);
		} catch (NumberFormatException e) {
		}
		
		if (value > 20)
			count = "20+";
		if (value == 0) {
			this.count.setSclass("");
			count = "";
		}
		if (value < 10)
			count = " " + count;
		this.count.setValue(count);
	}
}
