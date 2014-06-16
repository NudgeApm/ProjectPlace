package com.nla.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nla.domain.KbUsr;
import com.nla.domain.SchemaKb;

public class CentralBaseDAOImpl implements CentralBaseDAO{
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public List<String> getTechno(final String pCentralBaseName) {
		String query = "select s.snapshot_name ||'/'|| o.object_name ||  ' #'||s.snapshot_id||'#'||o.object_id SnapshotAppli from "+pCentralBaseName+".DSS_SNAPSHOTS s, "+pCentralBaseName+".DSS_OBJECTS o where s.application_id = o.object_id and o.OBJECT_TYPE_ID = -102 ";
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
