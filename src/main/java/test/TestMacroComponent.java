package test;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

public class TestMacroComponent extends HtmlMacroComponent {
	@Wire
	private Label label1;
	@Wire
	private Label label2;
	
	public TestMacroComponent() {
		compose();
	}
	
	public String getLabel1() {
    	return label1.getValue();
    }
	
	public void setLabel1(String label1) {
    	this.label1.setValue(label1);
    }
	
	public String getLabel2() {
    	return label2.getValue();
    }
	
	public void setLabel2(String label2) {
    	this.label2.setValue(label2);
    }
}
