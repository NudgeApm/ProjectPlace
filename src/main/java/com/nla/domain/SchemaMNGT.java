package com.nla.domain;

import java.util.ArrayList;

public class SchemaMNGT extends Schema {

	ArrayList<String> listCB = new ArrayList<String>();
	ArrayList<String> listKB = new ArrayList<String>();
	ArrayList<Application> listApplication = new ArrayList<Application>();
	
	public ArrayList<String> getListKB() {
		return listKB;
	}

	public void setListKB(ArrayList<String> listKB) {
		this.listKB = listKB;
	}

	public ArrayList<String> getListCB() {
		return listCB;
	}

	public void setListCB(ArrayList<String> listCB) {
		this.listCB = listCB;
	}

	public SchemaMNGT(String name) {
		super(name);
	}

	@Override
	public String getType() {
		return null;
	}
	
	public int getNumberOfCentralBase(){
		return getListCB().size();
	}

	public int getNumberOfKnowledgeBase(){
		return getListKB().size();
	}

	public int getNumberOfApplications(){
		return getListApplication().size();
	}
	
	public String getApplicationsName(){
		String returnVal = "";
		for(Application a:listApplication){
			if( returnVal.equals("")){
				returnVal = a.getName()+"(<abbr title='"+getModules(a.getId())+"'>"+a.getModuleList().size()+"</abbr>)";//"("+a.getModuleList().size()+")";
			}else{
				returnVal += "<br>"+ a.getName()+"(<abbr title='"+getModules(a.getId())+"'>"+a.getModuleList().size()+"</abbr>)";//"("+a.getModuleList().size()+")";
			}
		}
		return returnVal;
	}
	
	
	//<abbr title="${schema.applicationsName}">${schema.numberOfModules}</abbr>
	
	public int getNumberOfModules(){
		int returnVal =0;
		for(Application a :getListApplication()){
			returnVal += a.getModuleList().size();
		}
		return returnVal;
	}

	public String getModules(int idApp){
		String returnVal ="";
		for(Application a :getListApplication()){
			if(a.getId() == idApp){
				for(Module m:a.getModuleList()){
					if(returnVal.equals("")){
						returnVal += m.getName();
					}else{
						returnVal += "\n"+m.getName();
					}
				}
					
			}
			
		}
		return returnVal;
	}
	
	public ArrayList<Application> getListApplication() {
		return listApplication;
	}

	public void setListApplication(ArrayList<Application> listApplication) {
		this.listApplication = listApplication;
	}
	
	
}
