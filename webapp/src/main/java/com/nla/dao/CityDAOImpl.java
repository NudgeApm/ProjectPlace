package com.nla.dao;

import com.nla.domain.Account;
import com.nla.domain.KbUsr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component

public class CityDAOImpl implements CityDAO{

	@Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void calculAggregation(){
    	countSumSales();
  		countAverageSales();
  		countMaxSales();
  		countMinSales();
  		
    	jdbcTemplate.update("DELETE from account");
  		jdbcTemplate.update("DELETE from contrat");
  		jdbcTemplate.update("DELETE from sales");
  		
  		
  		
  		
    }
    
    private int getNumberOfCity(){
    	// fake function return the number of city available
    	return 15;
    	
    }
    
    private int getNumberOfProduct(){
    	return 19;
    }
    
    
    @Override
    public void consolidateData(){
    	int randomNbInsertSale = (int)(Math.random() * 50) * 1000;
        
        for(int i=0;i<randomNbInsertSale;i++){
       	int idRef = (int)(Math.random() * getNumberOfCity())+1;
	        String query = "SELECT id FROM reference_city where id=? LIMIT 1";
	        Integer city_id = (Integer) jdbcTemplate.queryForObject(query, new Object[]{idRef},
	                new RowMapper() {
	                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	                        return resultSet.getInt("id");
	                    }
	                });
	        
	       int idProduct = (int)(Math.random() * getNumberOfProduct())+1;
	        query = "SELECT id FROM reference_product where id=? LIMIT 1";
	        Integer product_id = (Integer) jdbcTemplate.queryForObject(query, new Object[]{idProduct},
	                new RowMapper() {
	                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	                        return resultSet.getInt("id");
	                    }
	                });
	        
	       	int randomNbSale = (int)(Math.random() * 5);
		    jdbcTemplate.update("INSERT INTO sales(item_id,city_id,numberSale)  VALUES (?,?,?)",
		                new Object[]{product_id.intValue(),city_id.intValue(),randomNbSale});
	     
        
        }
    }
    @Override
    public void updateDataSalesValue(){
    	 int randomNbInsertSale = (int)(Math.random() * 50) * 1000;
         
         for(int i=0;i<randomNbInsertSale;i++){
        	int idRef = (int)(Math.random() * 14);
 	        String query = "SELECT id FROM reference_city where id=? LIMIT 1";
 	        Integer city_id = (Integer) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(idRef)},
 	                new RowMapper() {
 	                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
 	                        return resultSet.getInt("id");
 	                    }
 	                });
 	        
 	       int idProduct = (int)(Math.random() * 21);
 	        query = "SELECT id FROM reference_product where id=? LIMIT 1";
 	        Integer product_id = (Integer) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(idProduct)},
 	                new RowMapper() {
 	                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
 	                        return resultSet.getInt("id");
 	                    }
 	                });
 	        
 	       	int randomNbSale = (int)(Math.random() * 5);
 		    jdbcTemplate.update("INSERT INTO sales(item_id,city_id,numberSale)  VALUES (?,?,?)",
 		                new Object[]{product_id.intValue(),city_id.intValue(),randomNbSale});
 	     
         
         } 
         
         
         String[] prenoms = {"jean christophe","jean baptiste","Paul","Laure","Alex","Pierre","Henri","Philippe","Christophe","Nicolas","Jean","Marc","Marion","Aurelie"};
    		String[] noms = {"da silva","de vilmorin","luu","lim","Delaroche","Moncassion","Noireau","bonnemain","virot","laude","Martineau","charbonneau","morin","rust"};

    		
         for(int i=0;i<randomNbInsertSale;i++){
             int lower = 1;
             int higher = 14;

             int lowerPrenom = 1;
             int higherPrenom = 14;
             int random = (int) (Math.random() * (higher - lower)) + lower;

             int prenomRand = (int) (Math.random() * (higherPrenom - lowerPrenom)) + lowerPrenom;

             jdbcTemplate.update("INSERT INTO account(nom,prenom,adresse,tel)  VALUES (?,?,?,?)",
                     new Object[]{noms[prenomRand], prenoms[random], "15 rue de paris", "0546994573"});
         }

         int randomNbInsertContrat = (int)(Math.random() * 50) * 1000;
         
         for(int i=0;i<randomNbInsertContrat;i++){
         	jdbcTemplate.update("INSERT INTO contrat(numero,datedebut,datefin,tel)  VALUES (?,?,?,?)", 			
                     new Object[]{"Dupond", Calendar.getInstance(), Calendar.getInstance(), "0546994573"});
         }
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
    public int getNumberContrat(){
    	
        String query = "select count(1) nb from contrat";

        Integer nbContrat = (Integer) jdbcTemplate.queryForObject(query, new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getInt("nb");
                    }
                });
        return nbContrat.intValue();
    }


    @Override
    public void getOneData(){
	    int forumId = 1;
	    String query = "Select nom, prenom from account where id <> ? and nom <> 'lam' and prenom like '%jean%' order by 1 limit 1";
	
	    String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
	            new RowMapper() {
	                public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	                    return resultSet.getString("nom");
	                }
	            });
	
	    String t = s;
    }
    
    
    @Override
    public void getHighVolumeDataForTokio(){
    	 int forumId = 1;
         String query = "select c.*, case a.nom when null then 'pas de nom' else a.nom end , a.prenom from contrat c, account a where cast(c.id as numeric) < 10 and c.id not in (1,2,3,4)";


         List<Object> metricList = jdbcTemplate.query(
                 query,
                 new Object[]{},
                 new RowMapper() {
                     public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                         return new Object();
                     }
                 }
         );
    }
   
    /**
     * Select in table: Account limit 300
     * + system.out.println * 300
     */
    @Override
    public void consultationMoscou(){
    	int forumId = 1;
        String query = "select a.nom,a.prenom,a.adresse,a.tel from account a where nom not like Lower('%jean%') and a.tel not in ('054503546544','054522945645') order by id desc limit 300" ;

        List<Account> accountList = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Account acc = new Account();
                        acc.setNom(rs.getString("nom"));
                        acc.setPrenom(rs.getString("prenom"));
                        acc.setAdresse(rs.getString("adresse"));
                        acc.setTel(rs.getString("tel"));
                        return acc;
                    }
                }
        );
        for(Account a: accountList){
        	System.out.println(a.getTel());
        }
    }
    
    
    /**
     * Select in table: Account limit 1000
     * + system.out.println * 1000
     */
    @Override
    public void consultationNewYork(){
    	int forumId = 1;
        String query = "select a.nom,a.prenom,a.adresse,a.tel from account a where nom not like '%John%' and  a.adresse not in ('123') and a.tel not in ('054503546544','054522945645') order by id desc limit 1000" ;


        List<Account> accountList = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Account acc = new Account();
                        acc.setNom(rs.getString("nom"));
                        acc.setPrenom(rs.getString("prenom"));
                        acc.setAdresse(rs.getString("adresse"));
                        acc.setTel(rs.getString("tel"));
                        return acc;
                    }
                }
        );
        
        for(Account a: accountList){
        	System.out.println(a.getNom());
        }
          
    }
    
    /**
     * Select in table: Account limit 1000
     * + system.out.println * 200
     */
    @Override
    public void consultationMadrid(){
    	int forumId = 1;
        String query = "select a.nom,a.prenom,'adresse' adresse, a.tel from account a where nom not like UPPER('%jean%') and a.tel not in ('054503546544','054522945645') order by id desc limit 200" ;

        List<Account> accountList = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Account acc = new Account();
                        acc.setNom(rs.getString("nom"));
                        acc.setPrenom(rs.getString("prenom"));
                        acc.setAdresse(rs.getString("adresse"));
                        acc.setTel(rs.getString("tel"));
                        return acc;
                    }
                }
        );
        
        for(Account a: accountList){
        	System.out.println(a.getAdresse());
        }
        
        
    }
    
    @Override
    public void getNewYorkData(){
	    int forumId = 1;
	    String query = "Select * from account where id <> ? limit 1";
	
	    String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
	            new RowMapper() {
	                public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	                    return resultSet.getString("nom");
	                }
	            });
	
	    String t = s;
    }
    
    @Override
    public void getJohannesburgData() {
        int forumId = 1;
        String query = "Select nom, prenom from account where id=? limit 1";

        String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getString("nom");
                    }
                });
        
        String t = s;
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
    
    
    
    @Override
    public void getSydneyData(){
    	 int forumId = 1;
    	 int idCity = getIdCitySydney(true);
    	 
         String query = "Select label from reference_city where id = ?  limit 1";
         System.out.println("identifiant de la reference city= "+ idCity);
         String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(idCity)},
                 new RowMapper() {
                     public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                         return resultSet.getString("label");
                     }
                 });
    }

    @Override
    public void getSydneyData(int i){
    	 String query = "Select label from reference_city where id = ?  limit 1";
         System.out.println("identifiant de la reference city= "+ i);
         String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(i)},
                 new RowMapper() {
                     public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                         return resultSet.getString("label");
                     }
                 });
    }
    
    
    
    @Override
    public void getSalesSingapour(){
    	
    	int nbSalesReturn = 0;
    	for(int i=0;i<15;i++){
    		String query = "Select label,id from reference_city where label='Singapour'";
    		int idCity = (int) jdbcTemplate.queryForObject(query, new Object[]{},
                 new RowMapper() {
                     public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                         return resultSet.getInt("id");
                     }
                 });
    		
    		String query2 = "Select sum(numberSale) nbSales from sales, reference_city, account where city_id=?";
    		nbSalesReturn = (int) jdbcTemplate.queryForObject(query2, new Object[]{Integer.valueOf(idCity)},
                    new RowMapper() {
                        public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                            return resultSet.getInt("nbSales");
                        }
                    });
    		
        	long start = System.currentTimeMillis();

            //int random = (int) (Math.random() * nbContrat);
        	int random = idCity / 10;
        	
            while (System.currentTimeMillis() < (start + (random))) {
            	System.out.println("count number of sales... in progress " + random/1000 + "seconds");
            }
            
            
    	}
         
    }
    
    @Override
    public ArrayList<Account> getListAccountDAO(int i) {
        int forumId = 1;
        String query = "select a.nom,a.prenom,a.adresse,a.tel from account a where nom not like UPPER('%jean%') and a.tel not in ('054503546544','054522945645') order by id desc limit " + i;


        List<Account> accountList = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Account acc = new Account();
                        acc.setNom(rs.getString("nom"));
                        acc.setPrenom(rs.getString("prenom"));
                        acc.setAdresse(rs.getString("adresse"));
                        acc.setTel(rs.getString("tel"));
                        return acc;
                    }
                }
        );
        return (ArrayList) accountList;
    }
}
