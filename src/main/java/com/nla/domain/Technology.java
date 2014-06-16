package com.nla.domain;

public class Technology extends ObjectBase implements java.io.Serializable  {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String name;
int id;
int applicationCount=0;
public int getApplicationCount() {
	return applicationCount;
}
public void setApplicationCount(int applicationCount) {
	this.applicationCount = applicationCount;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public void incrementAppCount(){
	applicationCount++;
}
public Technology() {
	super();
}
}
