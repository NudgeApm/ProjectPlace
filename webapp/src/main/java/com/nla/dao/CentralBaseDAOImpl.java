package com.nla.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CentralBaseDAOImpl implements CentralBaseDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> getTechno(final String pCentralBaseName) {
        String query = "select s.snapshot_name ||'/'|| o.object_name ||  ' #'||s.snapshot_id||'#'||o.object_id SnapshotAppli from " + pCentralBaseName + ".DSS_SNAPSHOTS s, " + pCentralBaseName + ".DSS_OBJECTS o where s.application_id = o.object_id and o.OBJECT_TYPE_ID = -102 ";
        return jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return new String(resultSet.getString("SnapshotAppli"));
                    }
                });


    }

}
