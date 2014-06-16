package com.nla.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nla.domain.Action;
import com.nla.domain.Application;
import com.nla.domain.Contribution;
import com.nla.domain.Module;
import com.nla.domain.Snapshot;
import com.nla.domain.StringBean;
import com.nla.domain.Technology;

public class MapperManager {
	public static final class ModuleMapper implements RowMapper<Module> {
	    public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Module m= new Module();
        	m.setId(rs.getInt("object_id"));
        	m.setName(rs.getString("object_name"));
        	//m.setAnalysisMode(rs.getString("analysis_mode"));	
        	m.setIdApp(rs.getInt("application_id"));
        	return m;
	    }        
	}

	public static final class TechnologyMapper implements RowMapper<Technology> {
	    public Technology mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Technology t = new Technology();
	    	t.setCentralBase(rs.getString("centralBase"));
	    	t.setSnapshotID(rs.getInt("snapshotID"));
	    	t.setId(rs.getInt("OBJECT_TYPE_ID"));
        	t.setName(rs.getString("OBJECT_NAME"));
        	return t;
	    }        
	}
	
	public static final class ContributionMapper implements RowMapper<Contribution> {
	    public Contribution mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Contribution c = new Contribution();
	    	c.setCentralBase(rs.getString("centralBase"));
	    	c.setSnapshotID(rs.getInt("snapshotID"));
	    	c.setId(rs.getInt("METRIC_ID"));
        	c.setGrade(rs.getDouble("valeur"));
        	return c;
	    }        
	}
	
	public static final class ActionMapper implements RowMapper<Action> {
	    public Action mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Action a = new Action();
	    	a.setCentralBase(rs.getString("centralBase"));
        	a.setRuleId(rs.getInt("metric_id"));
        	a.setObjectId(rs.getInt("object_id"));
        	a.setSetDate(rs.getString("sel_date"));				        	
        	a.setPriority(rs.getString("priority"));
        	return a;
	    }        
	}

	public static final class SnapshotMapper implements RowMapper<Snapshot> {
	    public Snapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Snapshot s = new Snapshot();
	    	s.setCentralBase(rs.getString("centralBase"));
        	s.setName(rs.getString("snapshot_name"));
        	s.setId(rs.getInt("snapshot_id"));
        	s.setFunctionalDate(rs.getString("functional_date"));
        	Application appli = new Application();
        	appli.setId(rs.getInt("object_id"));
        	appli.setName(rs.getString("object_name"));
        	s.setAppli(appli);
            return s;
	    }        
	}
	
	public static final class SnapshotHFMapper implements RowMapper<Snapshot> {
	    public Snapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Snapshot s = new Snapshot();
	    	Contribution c = new Contribution();
        	c.setId(rs.getInt("METRIC_ID"));
        	c.setGrade(rs.getInt("valeur"));
        	s.getListHealthFactor().add(c);
        	s.setCentralBase(rs.getString("centralBase"));
        	return s;
	    }        
	}
	

	public static final class ApplicationMapper implements RowMapper<Application> {
	    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Application a = new Application();
        	a.setId(rs.getInt("object_id"));
        	a.setName(rs.getString("object_name"));	
        	a.setCentralBaseName(rs.getString("centralBaseName"));
        	return a;
	    }        
	}
	
	/**
	 * Mapper for 1 list of String
	 * @author NLA
	 *
	 */
	public static final class StringSMapper implements RowMapper<String> {
	    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	String ss = rs.getString("S");
            return ss;
	    }        
	}
	
	/**
	 * Mapper for 1 list of String
	 * @author NLA
	 *
	 */
	public static final class StringBeanMapper implements RowMapper<StringBean> {
	    public StringBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	StringBean sb = new StringBean();
	    	sb.setS(rs.getString("S"));
	       	sb.setCentralBase(rs.getString("centralBase"));	        
            return sb;
	    }        
	}	
}
