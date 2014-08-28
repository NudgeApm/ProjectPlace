package com.nla.dao;

import java.util.ArrayList;
import com.nla.domain.*;

public interface CityDAO {
	
	public void getNewYorkData();
	public void getOneData();
	public void getJohannesburgData();
	public ArrayList<Account> getListAccountDAO(int i);
	public void getHighVolumeDataForTokio();
	public void getSydneyData();
	public int getNumberContrat();
	
	// exercice 1
	public void consultationNewYork();
	public void consultationMadrid();
	public void consultationMoscou();
	public void calculAggregation();
	public void updateDataSalesValue();
	
	
	//
	public void consolidateData();
	
	// exercice 3
	public void getSalesSingapour();
}
