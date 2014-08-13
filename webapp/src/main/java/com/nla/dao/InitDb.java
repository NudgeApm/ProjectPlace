package com.nla.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

public class InitDb {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {

        new JdbcTemplate(dataSource).update("" +
                "CREATE TABLE account (" +
                "  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "  nom varchar(50)," +
                "  prenom varchar(50)," +
                "  adresse varchar(50)," +
                "  tel varchar(50)" +
                ")");

        new JdbcTemplate(dataSource).update("" +
                "CREATE TABLE contrat (" +
                "  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "  numero varchar(50)," +
                "  datedebut date," +
                "  datefin date," +
                "  tel varchar(50)" +
                ")");
        
        new JdbcTemplate(dataSource).update("" +
                "CREATE TABLE sales (" +
                "  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "  item_id int," +
                "  city_id int," +
                "  numberSale int" +
                ")");
        
        new JdbcTemplate(dataSource).update("" +
                "CREATE TABLE reference_product (" +
                "  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "  product varchar(50)," +
                "  price int" +
                ")");
        
        new JdbcTemplate(dataSource).update("" +
                "CREATE TABLE reference_city (" +
                "  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "  label varchar(50)" +
                ")");
    }
}
