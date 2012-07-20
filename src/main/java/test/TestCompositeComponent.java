package test;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;

public class TestCompositeComponent extends Hlayout {
	@Wire
	private Label label1;
	@Wire
	private Label label2;
	
	public TestCompositeComponent() {
		Executions.createComponents("/test/TestCompositeComponent.zul", this, null);
		
		Selectors.wireComponents(this, this, false);
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
