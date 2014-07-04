package com.nla.dao;

import com.nla.domain.Account;
import com.nla.domain.KbUsr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public void insertdata(KbUsr kbUsr) {
        String[] prenoms = {"Alex", "Pierre", "Henri", "Philippe", "Christophe", "Nicolas", "Jean", "Marc", "Marion", "Aurelie"};
        String[] noms = {"Delaroche", "Moncassion", "Noireau", "bonneMmain", "virot", "laude", "Martineau", "charbonneau", "morin", "rust"};

        for (int i = 0; i < 1000; i++) {
            int lower = 1;
            int higher = 10;

            int lowerPrenom = 1;
            int higherPrenom = 10;
            int random = (int) (Math.random() * (higher - lower)) + lower;

            int prenomRand = (int) (Math.random() * (higherPrenom - lowerPrenom)) + lowerPrenom;

            jdbcTemplate.update("INSERT INTO account(nom,prenom,adresse,tel)  VALUES (?,?,?,?);",
                    new Object[]{noms[prenomRand], prenoms[random], "15 rue de paris", "0546994573"});
        }

        for (int i = 0; i < 1000; i++) {
            jdbcTemplate.update("INSERT INTO contrat(numero,datedebut,datefin,tel)  VALUES (?,?,?,?);",
                    new Object[]{"Dupond", Calendar.getInstance(), Calendar.getInstance(), "0546994573"});
        }

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
        String query = "Select * from account where id=?";

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
        String query = "Select * from account where id=? limit " + i;

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
        String query = "Select nom from account where id=?";

        String s = (String) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getString("nom");
                    }
                });

        String t = s;
    }
}
