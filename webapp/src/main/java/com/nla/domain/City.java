package com.nla.domain;

public class City extends ObjectBase implements java.io.Serializable {
    String label;


    public City(String pLabel) {
    	super();
    	this.label = pLabel;
    	
    }

    
    public String getLabel() {
        return label;
    }

    public void setLabel(String pL) {
        this.label = pL;
    }

}
