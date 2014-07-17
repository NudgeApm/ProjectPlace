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
}
