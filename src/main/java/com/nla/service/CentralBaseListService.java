package com.nla.service;


import com.nla.domain.*;

import java.util.ArrayList;


public interface CentralBaseListService {


    public void add(ViewCentralBaseList log);

    public void loadSchema();

    public ArrayList<Schema> getListSchemaCb();

    public ArrayList<Schema> getListSchemaKb();

    public ArrayList<Metric> getActionListFromCB(String pSchema);

    public void deleteFromAP(String pSchema, String pMetricID, String pObjID);

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

    public void deleteTables();
    
    public int getNumberContrat();
    
    public int getNumberAccount();
    
    public void insertDataBatch();
    
    public void selectOneColumnSlowLoop();
    
    
    public void generateNullPointerException();

    public ArrayList<Account> getListAccount(int i);

}
