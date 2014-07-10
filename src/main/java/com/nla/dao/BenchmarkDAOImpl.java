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
    }
    	 	
    	 
    @Override
    public void insertdata(KbUsr kbUsr) {
    	String[] prenoms = {"jean christophe","jean baptiste","Paul","Laure","Alex","Pierre","Henri","Philippe","Christophe","Nicolas","Jean","Marc","Marion","Aurelie"};
   		String[] noms = {"da silva","de vilmorin","luu","lim","Delaroche","Moncassion","Noireau","bonnemain","virot","laude","Martineau","charbonneau","morin","rust"};

        int randomNbInsert = (int)(Math.random() * 50) * 1000;
        		
        for(int i=0;i<randomNbInsert;i++){
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
