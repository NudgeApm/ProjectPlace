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
        String query = "Select nom from account where id<>? limit 1";

        String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getString("nom");
                    }
                });

        String t = s;
    }
    
    @Override
    public void getSydneyData(){
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
