package com.nla.service;


import java.util.ArrayList;

import com.nla.domain.Account;
import com.nla.domain.Action;
import com.nla.domain.Application;
import com.nla.domain.Metric;
import com.nla.domain.StringBean;
import com.nla.domain.Technology;
import com.nla.domain.ViewCentralBaseList;
import com.nla.domain.Schema;


public interface CentralBaseListService {

	
	public void add(ViewCentralBaseList castlog);
	public void loadSchema();
	
	public ArrayList<Schema> getListSchemaCb();
	public ArrayList<Schema> getListSchemaKb();
	public ArrayList<Metric> getActionListFromCB(String pSchema);
	public void deleteFromAP(String pSchema,String pMetricID, String pObjID);
	public void compareSchema(Schema pSchemaName, Schema pSchemaNameRef);
	
	public ArrayList<Application> getListApplication();
	public ArrayList<Technology> getListTechnologies();
	public ArrayList<StringBean> getListVersion();
	public void loadApplications();
	public void setMaxLocForAllApp();
	
	public void insertData();
	public void selectStar();
	public void selectStar100(int i);
	public void selectOneColumn();
	public void selectOneColumnLoop();
	public void selectOneColumnRecursifEntryPoint();
	public void selectCartesianProduct();
	public void generateNullPointerException();
	
	public ArrayList<Account> getListAccount(int i);
	
	
}
