package com.nla.domain;

public class Contribution extends ObjectBase implements java.io.Serializable  {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
double grade;
String name;
int id;
boolean critical;
int weight;
int parent;
public double getGrade() {
	return grade;
}
public void setGrade(double grade) {
	this.grade = grade;
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
public boolean isCritical() {
	return critical;
}
public void setCritical(boolean critical) {
	this.critical = critical;
}
public int getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}
public int getParent() {
	return parent;
}
public void setParent(int parent) {
	this.parent = parent;
}

}
