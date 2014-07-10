package com.nla.dao;


import com.nla.domain.Account;
import com.nla.domain.KbUsr;

import java.util.ArrayList;

public interface BenchmarkDAO {

    public void loadApplication(KbUsr kbUsr);

    public void insertdata(KbUsr kbUsr);

    public void selectStar();

    public void selectStar100(int i);

    public void selectOneColumn();

    public void selectCartesianProduct();

    public ArrayList<Account> getListAccountDAO(int i);
    
    public void deleteTables();
    
    public int getNumberContrat();
    
    public int getNumberAccount();
    
    public void insertDataBatch();
    
}