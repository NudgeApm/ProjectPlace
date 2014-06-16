package com.nla.domain;

public class Violation {
private String name;
private int loc;
private String filePath;
private String module;
private int objId;
private boolean selectedForAP=false;
private int metricID;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getLoc() {
	return loc;
}
public void setLoc(int loc) {
	this.loc = loc;
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public int getObjId() {
	return objId;
}
public void setObjId(int objId) {
	this.objId = objId;
}
public boolean isSelectedForAP() {
	return selectedForAP;
}
public void setSelectedForAP(boolean selectedForAP) {
	this.selectedForAP = selectedForAP;
}
public int getMetricID() {
	return metricID;
}
public void setMetricID(int metricID) {
	this.metricID = metricID;
}






}
