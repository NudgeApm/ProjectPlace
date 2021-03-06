package com.nla.service;


import com.nla.dao.BenchmarkDAO;
import com.nla.dao.CityDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nla.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


@Component
public class TransactionCityServiceImpl implements TransactionCityService{
	@Autowired
    private CityDAO cityDAO;

	
	@Override
	public void selectNewYork(){
		cityDAO.getNewYorkData();
	}
	
	@Override
	public void consultationNewYork(){
    	countSumSales();
  		countAverageSales();
  		countMaxSales();
  		countMinSales();
		cityDAO.consultationNewYork();
	}
	
	 private void countSumSales(){
	    	System.out.println("count sum sales");
	    }
	    
	    private void countAverageSales(){
	    	System.out.println("count average sales");
	    }
	    
	    private void countMaxSales(){
	    	System.out.println("count max sales");
	    }
	    
	    private void countMinSales(){
	    	System.out.println("count min sales");
	    }
	    
	    

	@Override
	public void consultationMadrid(){
		cityDAO.consultationMadrid();
	}

	@Override
	public void consultationMoscou(){
		cityDAO.consultationMoscou();
	}
	
	@Override
	public void consolidateData(){
		cityDAO.consolidateData();
	}
	
	@Override
	public void updateDataSalesValue(){
		cityDAO.updateDataSalesValue();
	}
	
	@Override
	public void calculAggregation(){
		cityDAO.calculAggregation();
	}
	
	@Override
	public void selectJohannesburgData(){
		cityDAO.getJohannesburgData();
	}
	
	@Override
	public void selectBuenoAiresData(){
		for (int i = 0; i < 1000; i++) {
            cityDAO.getOneData();
        }
	}
	
    @Override
    public ArrayList<Account> getListAccount(int i) {
        return cityDAO.getListAccountDAO(i);
    }

    @Override
    public void getHighVolumeData() {
    	cityDAO.getHighVolumeDataForTokio();
    }
    
    
    
    @Override
    public void getOneSpecialTransaction(){
    	Date dateBegin = new Date();
    	Date dateEnd = new Date();
    	 Date dateBeginAtSaturday0 = findSaturdayAtSameWeekAtZero(dateBegin);
         Date calEndAtSaturday0 = findSaturdayAtSameWeekAtZero(dateEnd);
         long MILISECOND_PER_DAY = 24 * 60 * 60 * 1000;

         long MILLISECOND_WORKING_DAYS =  MILISECOND_PER_DAY * 5;

         long intervalleEnMillis = intervalleEnMillis(dateBeginAtSaturday0, calEndAtSaturday0);
         long nbSemaines = Double.valueOf(Math.floor(Long.valueOf(intervalleEnMillis).doubleValue() / 604800000)).longValue();

         long workingDaysInMillis =  nbSemaines * MILLISECOND_WORKING_DAYS;

         boolean isBeginBeforeSaturday = dateBeginAtSaturday0.getTime() - dateBegin.getTime() > 0;
         boolean isEndBeforeSaturday = calEndAtSaturday0.getTime() - dateEnd.getTime() > 0;

         long correctionInMillis = 0;

         if(isBeginBeforeSaturday) {
             correctionInMillis-= (dateBeginAtSaturday0.getTime() - dateBegin.getTime());
         }

         if(isEndBeforeSaturday) {
             correctionInMillis+= (calEndAtSaturday0.getTime() - dateEnd.getTime());
         }
    	String s = null;
        int i = s.length();
    }
    
    
    public static Date findSaturdayAtSameWeekAtZero(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY :
                cal.add(Calendar.DAY_OF_MONTH, 5);
                break;
            case Calendar.TUESDAY :
                cal.add(Calendar.DAY_OF_MONTH, 4);
                break;
            case Calendar.WEDNESDAY :
                cal.add(Calendar.DAY_OF_MONTH, 3);
                break;
            case Calendar.THURSDAY :
                cal.add(Calendar.DAY_OF_MONTH, 2);
                break;
            case Calendar.FRIDAY :
                cal.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case Calendar.SUNDAY :
                cal.add(Calendar.DAY_OF_MONTH, -1);
                break;
        }
        return cal.getTime();
    }
    
    @Override
    public void executeSydneyTransactions(){
    	int nbContrat =  cityDAO.getNumberContrat();
    	
    	long start = System.currentTimeMillis();

    	
        //int random = (int) (Math.random() * nbContrat);
    	int random = nbContrat / 30;
    	
        // boucle tant que la dur�e de vie du thread est < � n secondes
        while (System.currentTimeMillis() < (start + (random))) {
        	System.out.println("very slow transactions... in progress " + random/1000 + "seconds");
        }
    	
        for (int i = 0; i < nbContrat; i++) {
        	cityDAO.getSydneyData();
        }
        
        cityDAO.getSydneyData(getIdCitySydney(false));
    }
    
    @Override
    public void executeSingapourTransactions(){
    	 cityDAO.getSalesSingapour();    	
    }
    
    /*
     * cette methode retourne l'ID de la ville que l'on veut récupérer.
     */
    private int getIdCitySydney(boolean b){
    	if( b){
    		return 4; // 4 correspond à l'ID de la ville de Sydney
    	}else {
    		return (int)(Math.random() * 13);
    	}
    }
	

    public static long intervalleEnMillis(Date dateBegin, Date dateEnd) {
        return (dateEnd.getTime() - dateBegin.getTime());
    }
}
