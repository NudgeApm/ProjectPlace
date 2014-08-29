package com.nla.domain;

public class Sale extends ObjectBase implements java.io.Serializable {
    String product;
    String city;
    int idCity;
    int idProduct;
    int numberOfSales;
    int revenue;

    public Sale() {
    	super();
    	
    }
    
    public Sale(String pProduct,String pCity,int pNbSales, int pRevenue) {
    	super();
    	this.product = pProduct;
    	this.city = pCity;
    	this.numberOfSales = pNbSales;
    	this.revenue = pRevenue;
    	
    }

    
    
    public String getProduct() {
        return product;
    }
    public void setProduct(String pL) {
        this.product = pL;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String pL) {
        this.city = pL;
    }
    
    public int getNumberOfSales() {
        return numberOfSales;
    }
    public void setNumberOfSales(int pI) {
        this.numberOfSales = pI;
    }
    
    public int getRevenue() {
        return revenue;
    }
    public void setRevenue(int pI) {
        this.revenue = pI;
    }
    
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int pI) {
        this.idProduct = pI;
    }
    
    public int getIdCity() {
        return idCity;
    }
    public void setIdCity(int pI) {
        this.idCity = pI;
    }
    
    
}
