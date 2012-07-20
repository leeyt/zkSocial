package test;

import org.zkoss.bind.annotation.Init;

public class TestViewModel {
	private String label1 = "Label 1";
	private String label2 = "Label 2";
	
	@Init
	public void init() {
		System.out.println("VM initialized");
	}

	public String getLabel1() {
		System.out.println("getLabel1 is called");
    	return label1;
    }
	
	public void setLabel1(String label1) {
    	this.label1 = label1;
    }
	
	public String getLabel2() {
    	return label2;
    }
	
	public void setLabel2(String label2) {
    	this.label2 = label2;
    }
}
