package com.nla.dao;


import com.nla.domain.Account;
import com.nla.domain.Sale;
import com.nla.domain.Produit;
import com.nla.domain.KbUsr;

import java.util.ArrayList;

public interface BenchmarkDAO {

    public void loadApplication(KbUsr kbUsr);

    public void insertdata(KbUsr kbUsr);

    public void initDB();
    
    public void selectStar();

    public void selectStar100(int i);

    public void selectOneColumn();

    public void selectCartesianProduct();

    public ArrayList<Account> getListAccountDAO(int i);
    
    public void deleteTables();
    
    public int getNumberContrat();
    
    public int getNumberAccount();
    
    public void insertDataBatch();
    
    public ArrayList<Sale> getSalesSummary(String pCity);
    
    public int getNumberOfSale(String pCity);
    
    public ArrayList<Produit> getNumberOfSaleByProductByCity(int pIdProduct, int pIdCity);
    public ArrayList<Produit> getSalesByProductByCity(int pIdProduct, int pIdCity);
    
}
