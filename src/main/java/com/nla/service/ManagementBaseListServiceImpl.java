package com.nla.service;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nla.dao.DataBaseLoader;
import com.nla.dao.ForumDAO;
import com.nla.domain.Schema;
import com.nla.domain.SchemaMNGT;
import org.springframework.stereotype.Component;

@Component
public class ManagementBaseListServiceImpl implements ManagementBaseListService {

	ArrayList<Schema> listeSchemaMNGT;
	
	@Override
	public void loadSchema(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DataBaseLoader dataBaseLoader = (DataBaseLoader) context.getBean("listOfDatabases");
		ArrayList<String> listDatabaseName  =dataBaseLoader.getBaseNames();
		listeSchemaMNGT=new ArrayList<Schema>();
		for(String s:listDatabaseName){
			loadSchema(s);	
		}		
		
	}
	
	
	
	private void loadSchema(final String DAOAccess){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ForumDAO forumDAO = (ForumDAO) context.getBean(DAOAccess);		
		ArrayList<SchemaMNGT> listeSchemaTemp = new ArrayList<SchemaMNGT>();
		if(forumDAO.isConnectionOK()){			
			listeSchemaTemp = (ArrayList<SchemaMNGT>)forumDAO.getListSchemaMNGT();
			forumDAO.updateCentralBaseList(listeSchemaTemp);
			//forumDAO.updateKnowledgeBaseList(listeSchemaTemp);
			forumDAO.updateApplicationAndModule(listeSchemaTemp);
			for(Schema s:listeSchemaTemp){
				s.setConnectionName(DAOAccess);
			}
			
			listeSchemaMNGT.addAll(listeSchemaTemp);
		}

		
	}
	
	@Override
	public ArrayList<Schema> getListSchemaMNGT(){
		return listeSchemaMNGT;
	}

	@Override
	public int getNBSchema() {
		if(listeSchemaMNGT == null){
			return 0;
		}
		return listeSchemaMNGT.size();
	}
}
