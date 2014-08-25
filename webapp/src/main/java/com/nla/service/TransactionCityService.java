package com.nla.service;

import com.nla.domain.*;
import java.util.ArrayList;

public interface TransactionCityService {
	public void selectNewYork();
	public void selectJohannesburgData();
	public void selectBuenoAiresData();
	public ArrayList<Account> getListAccount(int i);
	public void getHighVolumeData();
	public void getOneSpecialTransaction();
	public void executeSydneyTransactions();
	
	// exercice1
	public void consultationNewYork();
	public void consultationMadrid();
	public void consultationMoscou();
	public void calculAggregation();
	
	
	//
	public void consolidateData();
	public void updateDataSalesValue();
}
