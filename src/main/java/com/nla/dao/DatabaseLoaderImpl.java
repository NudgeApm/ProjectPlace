package com.nla.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class DatabaseLoaderImpl implements DataBaseLoader{

	ArrayList<String> listOfDatabase;
	String temp;
	String dataBaseName;
	
	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	
	public ArrayList<String> getBaseNames(){
		ArrayList<String> returnList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(dataBaseName,",");
		while(st.hasMoreElements()){
			String s = (String)st.nextElement();
			returnList.add(s);
		}
		return returnList;
	}
	
}
