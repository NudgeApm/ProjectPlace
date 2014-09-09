package com.nla.dao;

import com.nla.domain.Account;
import com.nla.domain.Produit;
import com.nla.domain.City;
import com.nla.domain.Sale;
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
public class BenchmarkDAOImpl implements BenchmarkDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void loadApplication(KbUsr kbUsr) {
        int forumId = 1;
        String query = "Select * from pocpoc.account where id=?";

        String s = (String) new JdbcTemplate(dataSource).queryForObject(query, new Object[]{forumId},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getString("nom");
                    }
                });

        String t = s;

    }

    @Override
    public void deleteTables(){
   		jdbcTemplate.update("DELETE from account");
  		jdbcTemplate.update("DELETE from contrat");
  		jdbcTemplate.update("DELETE from sales");
    }
    	
    @Override
    public void initDB(){
    	
   		ArrayList<Produit> listProduct = new ArrayList<Produit>();
   		listProduct.add(new Produit("Pull à carreau noir col V","la description du produit ici blablablalba",35));
   		listProduct.add(new Produit("tee-shirt","la description du produit ici blablablalba",15));
   		listProduct.add(new Produit("Pantalon beige","la description du produit ici blablablalba",30));
   		listProduct.add(new Produit("Shirt","la description du produit ici blablablalba",12));
   		listProduct.add(new Produit("Chemise rayures noir et blanc","la description du produit ici blablablalba",45));
   		listProduct.add(new Produit("Veste en velour bordeaux","la description du produit ici blablablalba",55));
   		listProduct.add(new Produit("Chaussette","la description du produit ici blablablalba",3));
   		listProduct.add(new Produit("Manteau","la description du produit ici blablablalba",145));
   		listProduct.add(new Produit("Chapeau","la description du produit ici blablablalba",15));
   		listProduct.add(new Produit("gants en cuir","la description du produit ici blablablalba",45));
   		listProduct.add(new Produit("Chemise blanche","la description du produit ici blablablalba",40));
   		listProduct.add(new Produit("Chemise à carreau","la description du produit ici blablablalba",45));
   		listProduct.add(new Produit("Chemise Bleu","la description du produit ici blablablalba",55));
   		listProduct.add(new Produit("jean bleu classique","la description du produit ici blablablalba",45));
   		listProduct.add(new Produit("tee shirt blanc","la description du produit ici blablablalba",25));
   		listProduct.add(new Produit("Ceinture noir cuir","la description du produit ici blablablalba",10));
   		listProduct.add(new Produit("Veste cuir","la description du produit ici blablablalba",345));
   		listProduct.add(new Produit("Chaussure noire","la description du produit ici blablablalba",95));
   		listProduct.add(new Produit("Chaussure basket","la description du produit ici blablablalba",65));
   		listProduct.add(new Produit("Chaussure basket blanche","la description du produit ici blablablalba",58));
   		listProduct.add(new Produit("Chaussure tennis exterieur","la description du produit ici blablablalba",66));
   		
   		for(Produit p: listProduct){
   			jdbcTemplate.update("INSERT INTO reference_product(product,description,  price)  VALUES (?,?,?)",
                 new Object[]{p.getLabel(),p.getDescription(),p.getPrice()});
		}
   		
   		ArrayList<City> listCity = new ArrayList<City>();
   		listCity.add(new City("Paris"));
   		listCity.add(new City("Madrid"));
   		listCity.add(new City("Moscou"));
   		listCity.add(new City("New York"));
   		listCity.add(new City("Sydney"));
   		listCity.add(new City("Hong Kong"));
   		listCity.add(new City("Chicago"));
   		listCity.add(new City("San Francisco"));
   		listCity.add(new City("Rio de Janeiro"));
   		listCity.add(new City("Bueno Aires"));
   		listCity.add(new City("Tokio"));
   		listCity.add(new City("Mumbai"));
   		listCity.add(new City("Singapour"));
   		listCity.add(new City("Johannesburg"));
   		listCity.add(new City("Rome"));
   		listCity.add(new City("Munich"));
   		listCity.add(new City("Shanghai"));
   		listCity.add(new City("Miami"));
   		
   		for(City c: listCity){
   			jdbcTemplate.update("INSERT INTO reference_city(label)  VALUES (?)",
                 new Object[]{c.getLabel()});
		}
   		
    }
    	 
    @Override
    public void insertdata(KbUsr kbUsr) {
    	
        int randomNbInsertSale = (int)(Math.random() * 50) * 1000;
        
        for(int i=0;i<randomNbInsertSale;i++){
	        String query = "SELECT id FROM reference_city ORDER BY RAND() LIMIT 1";
	        Integer city_id = (Integer) jdbcTemplate.queryForObject(query, new Object[]{},
	                new RowMapper() {
	                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	                        return resultSet.getInt("id");
	                    }
	                });
	        
	        query = "SELECT id FROM reference_product ORDER BY RAND() LIMIT 1";
	        Integer product_id = (Integer) jdbcTemplate.queryForObject(query, new Object[]{},
	                new RowMapper() {
	                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	                        return resultSet.getInt("id");
	                    }
	                });
	        
	       	int randomNbSale = (int)(Math.random() * 5) * 10;
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
    
    
    @Override
    public void insertDataBatch(){
    	String[] prenoms = {"jean christophe","jean baptiste","Paul","Laure","Alex","Pierre","Henri","Philippe","Christophe","Nicolas","Jean","Marc","Marion","Aurelie"};
   		String[] noms = {"da silva","de vilmorin","luu","lim","Delaroche","Moncassion","Noireau","bonnemain","virot","laude","Martineau","charbonneau","morin","rust"};
   		
   		List<Account> listAccount = new ArrayList<Account>();
        int randomNbInsert = (int)(Math.random() * 50) * 10000;		
        for(int i=0;i<randomNbInsert;i++){
            int lower = 1;
            int higher = 14;

            int lowerPrenom = 1;
            int higherPrenom = 14;
            int random = (int) (Math.random() * (higher - lower)) + lower;

            int prenomRand = (int) (Math.random() * (higherPrenom - lowerPrenom)) + lowerPrenom;

            Account a = new Account();
            a.setNom(noms[prenomRand]);
            a.setPrenom(prenoms[random]);
            a.setAdresse("15 rue de paris");
            a.setTel("0546994573");
            listAccount.add(a);
        }
        insertBatchUpdate(listAccount);

        
        int randomNbInsertContrat = (int)(Math.random() * 50) * 10000; 
        for(int i=0;i<randomNbInsertContrat;i++){
        	jdbcTemplate.update("INSERT INTO contrat(numero,datedebut,datefin,tel)  VALUES (?,?,?,?)", 			
                    new Object[]{"Dupond", Calendar.getInstance(), Calendar.getInstance(), "0546994573"});
        }
    }
    
    
	private void insertBatchUpdate(final List<Account> pAccount) {
	        int[] updateCnt = jdbcTemplate.batchUpdate(
	                "insert into account(nom,prenom,adresse,tel) values (?, ?, ?, ?)",
	                new BatchPreparedStatementSetter() {
	                    public int getBatchSize() {
	                        return pAccount.size();
	                    }
						public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {
	                    	ps.setString(1,pAccount.get(i).getNom());
	                        ps.setString(2,pAccount.get(i).getPrenom());
	                        ps.setString(1,pAccount.get(i).getAdresse());
	                        ps.setString(1,pAccount.get(i).getTel());
	                        		
	                    }
	                } );
	 }
    
    
	@Override
	public ArrayList<Produit> getNumberOfSaleByProductByCity(int pIdProduct, int pIdCity) {
		
		String query2 = "select count(id) nb from reference_city rc where rc.id = ? ";
	      List<Produit> ListRefCity = jdbcTemplate.query(
	          query2,
	          new Object[]{Integer.valueOf(pIdCity)},
	          new RowMapper() {
	              public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                  Produit p = new Produit();
	                  p.setNbSales(rs.getInt("nb"));
	                  return p;
	              }
	          });
	      
	      if( ListRefCity.get(0).getNbSales() ==0){
	    	  String testIdCity = null;
	    	  testIdCity.indexOf("1");
	    	  //throw new Exception();
	      }
	      if( false){
	    	 // throw new Exception();
	      }
		
		
	  String query = "select numberSale from sales s where s.item_id = ? and s.city_id = ? ";
      int nbSales = 0;
      List<Produit> productList = jdbcTemplate.query(
          query,
          new Object[]{Integer.valueOf(pIdProduct),Integer.valueOf(pIdCity)},
          new RowMapper() {
              public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Produit p = new Produit();
                  p.setNbSales(rs.getInt("numberSale"));
                  return p;
              }
          });
          return (ArrayList)productList;
	}
	
	
	@Override
	public ArrayList<Produit> getSalesByProductByCity(int pIdProduct, int pIdCity){
	  String query = "select nbSales from sales s where s.item_id = ? and s.city_id = ? ";
      int nbSales = 0;
      List<Produit> productList = jdbcTemplate.query(
          query,
          new Object[]{Integer.valueOf(pIdProduct),Integer.valueOf(pIdCity)},
          new RowMapper() {
              public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Produit p = new Produit();
                  p.setNbSales(rs.getInt("nbSales"));
                  return p;
              }
          });
          return (ArrayList)productList;
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
    public int getNumberOfSale(String pCity){
	  String query = "select rp.product product, rc.label city,rp.id idProduct, rc.id idCity, sum(numberSale) nbSales from sales s, reference_product rp, reference_city rc where s.item_id = rp.id and s.city_id = rc.id and rc.label = '"+pCity+"' group by rp.product, rc.label, rp.id, rc.id ";
      int nbSales = 0;
      System.out.println("xx:" + pCity + " = "+ nbSales);
      List<Sale> saleList = jdbcTemplate.query(
              query,
              new Object[]{},
              new RowMapper() {
                  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                      Sale s = new Sale();
                      s.setProduct(rs.getString("product"));
                      s.setCity(rs.getString("city"));
                      s.setNumberOfSales(rs.getInt("nbSales"));
                      s.setIdCity(rs.getInt("idCity"));
                      s.setIdProduct(rs.getInt("idProduct"));
                      
                      System.out.println("idCity:" + s.getIdCity());
                      
                      s.setRevenue(0);
                      return s;
                  }
              }
      );
	  
	  for(Sale s: saleList){
		  nbSales += s.getNumberOfSales();
	  }
	  
      System.out.println("nombre de ventes à:" + pCity + " = "+ nbSales);	
      
      return nbSales;
    }
    
    @Override
    public ArrayList<Sale> getSalesSummary(String pCity){
	  String query = "select rp.product product, rc.label city,rp.id idProduct, rc.id idCity, sum(numberSale) nbSales from sales s, reference_product rp, reference_city rc where s.item_id = rp.id and s.city_id = rc.id and rc.label = '"+pCity+"' group by rp.product, rc.label, rc.id ";
      int nbSales = 0;
	  List<Sale> saleList = jdbcTemplate.query(
              query,
              new Object[]{},
              new RowMapper() {
                  public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                      Sale s = new Sale();
                      s.setProduct(rs.getString("product"));
                      s.setCity(rs.getString("city"));
                      s.setNumberOfSales(rs.getInt("nbSales"));
                      s.setIdCity(rs.getInt("idCity"));
                      s.setIdProduct(rs.getInt("idProduct"));
                      s.setRevenue(0);
                      return s;
                  }
              }
      );
	  
	  for(Sale s: saleList){
		  nbSales += s.getNumberOfSales();
	  }
	  
      
      return (ArrayList) saleList;
    }
    
    @Override
    public int getNumberAccount(){
    	
        String query = "select count(1) nb from account";

        Integer nbAccount = (Integer) jdbcTemplate.queryForObject(query, new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getInt("nb");
                    }
                });
        return nbAccount.intValue();
    }    
    

    @Override
    public void selectCartesianProduct() {
        int forumId = 1;
        String query = "select *, account.nom from contrat, account where contrat.id in (1,2,3,4)";


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

    @Override
    public void selectStar() {
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
    public ArrayList<Account> getListAccountDAO(int i) {
        int forumId = 1;
        String query = "select nom,prenom,adresse,tel from account order by id desc limit " + i;


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


    @Override
    public void selectStar100(int i) {
        int forumId = 1;
        String query = "Select * from account where id<>? limit " + i;

        String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getString("nom");
                    }
                });

        String t = s;
    }


    @Override
    public void selectOneColumn() {
        int forumId = 1;
        String query = "Select nom from account where id<>? limit 1";

        String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getString("nom");
                    }
                });

        String t = s;
    }
}
