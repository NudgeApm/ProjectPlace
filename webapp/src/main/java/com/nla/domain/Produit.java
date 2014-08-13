package com.nla.domain;

public class Produit extends ObjectBase implements java.io.Serializable {
    String label;
    int price;

    public Produit(String pLabel, int pPrice) {
    	super();
    	this.label = pLabel;
    	this.price = pPrice;
    }

    
    public String getLabel() {
        return label;
    }

    public void setLabel(String pL) {
        this.label = pL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int pP) {
        this.price = pP;
    }

}
