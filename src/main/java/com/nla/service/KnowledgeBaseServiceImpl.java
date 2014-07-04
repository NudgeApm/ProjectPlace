package com.nla.service;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.UncategorizedSQLException;

import com.nla.dao.DataBaseLoader;
import com.nla.dao.ForumDAO;
import com.nla.domain.Metric;
import com.nla.domain.ObjectCAST;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService{
	
	ArrayList<Metric> metricList;
	
	@Override
	public ArrayList<Metric> getMetricFromEnlightenView(String schemaName, int enlightenID) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DataBaseLoader dataBaseLoader = (DataBaseLoader) context.getBean("listOfDatabases");
		ArrayList<String> listDatabaseName  =dataBaseLoader.getBaseNames();
		ArrayList<Metric> temp = null;
		for(String s:listDatabaseName){
			temp = getMetricFromEnlightenView(s, schemaName,  enlightenID);
			if(temp != null){
				metricList = temp;
				return temp;
			}	
		}
		return metricList;
	}

	
	private ArrayList<Metric> getMetricFromEnlightenView(final String dataBaseType, String schemaName, int enlightenID) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ForumDAO forumDAO = (ForumDAO) context.getBean(dataBaseType);
		try{
			if(forumDAO.isConnectionOK()){
				return (ArrayList)forumDAO.getListMetricForEnlightenView(schemaName,  enlightenID);				
			}
			}catch(UncategorizedSQLException use){
				return null;
			}
		return null;
	}
}
