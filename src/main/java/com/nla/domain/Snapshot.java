package com.nla.domain;

import java.util.ArrayList;

/**
 * 
 * @author NLA
 *
 */
public class Snapshot extends ObjectBase implements Comparable<Snapshot>,  java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * ID
 */
int id;
/**
 * name
 */
String name;

Application appli;

String functionalDate;

ArrayList<Contribution> listHealthFactor = new ArrayList<Contribution>();

ArrayList<Technology> listTechnologies = new ArrayList<Technology>();

public ArrayList<Contribution> getListHealthFactor() {
	return listHealthFactor;
}
public void setListHealthFactor(ArrayList<Contribution> listHealthFactor) {
	this.listHealthFactor = listHealthFactor;
}
public Application getAppli() {
	return appli;
}
public void setAppli(Application appli) {
	this.appli = appli;
}
public Snapshot() {
	super();
	appli = new Application();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFunctionalDate() {
	return functionalDate;
}
public void setFunctionalDate(String functionalDate) {
	this.functionalDate = functionalDate;
}
@Override
public int compareTo(Snapshot o) {
	// TODO Auto-generated method stub
	return 0;
}
public ArrayList<Technology> getListTechnologies() {
	return listTechnologies;
}
public void setListTechnologies(ArrayList<Technology> listTechnologies) {
	this.listTechnologies = listTechnologies;
}
public double getcontribution(int iId){
for(Contribution c: getListHealthFactor()){
	if(c.getId() == iId){
		return c.getGrade();
	}
}
return 0;
}
}
