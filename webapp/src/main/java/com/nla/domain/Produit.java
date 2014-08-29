package com.nla.domain;

public class Produit extends ObjectBase implements java.io.Serializable {
    String label;
    int price;
    String description;
    
    // for presentation purpose only
    int nbSales;
    
    public Produit() {
    	super();
    	this.label = "default Label";
    	this.price = 100;
    	this.description = "default description";
    }
    
    
    public Produit(String pLabel,String pDesc, int pPrice) {
    	super();
    	this.label = pLabel;
    	this.price = pPrice;
    	this.description = pDesc;
    }

    
    public int getNbSales(){
    	return nbSales;
    }
    
    
   public void setNbSales(int s){
	   nbSales= s;
   }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String pL) {
        this.label = pL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pL) {
        this.description = pL;
    }

    
    public int getPrice() {
        return price;
    }

    public void setPrice(int pP) {
        this.price = pP;
    }

}
