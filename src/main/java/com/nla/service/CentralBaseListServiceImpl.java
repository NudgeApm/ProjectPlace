package com.nla.service;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.UncategorizedSQLException;

import com.nla.dao.BenchmarkDAO;
import com.nla.dao.DataBaseLoader;
import com.nla.dao.ForumDAO;
import com.nla.domain.Account;
import com.nla.domain.Application;
import com.nla.domain.Metric;
import com.nla.domain.SchemaCb;
import com.nla.domain.Snapshot;
import com.nla.domain.StringBean;
import com.nla.domain.Technology;
import com.nla.domain.ViewCentralBaseList;
import com.nla.domain.Schema;
import com.nla.domain.KbUsr;

public class CentralBaseListServiceImpl implements CentralBaseListService{

	ArrayList<Schema> listeSchemaCb;
	ArrayList<Schema> listeSchemaKb;
	ArrayList<Metric> metricList;
	
	@Override
	public void add(ViewCentralBaseList castlog) {
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		ForumDAO forumDAO = (ForumDAO) context.getBean("DAOCSS");
//		KbUsr springForum = new KbUsr("1", "login");
//		forumDAO.insertForum(springForum);	
		
	}

	
	public void generateNullPointerException(){
		String s = null;
		int i = s.length();
	}

public void loadSchema(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DataBaseLoader dataBaseLoader = (DataBaseLoader) context.getBean("listOfDatabases");
		ArrayList<String> listDatabaseName  =dataBaseLoader.getBaseNames();
		listeSchemaCb= new ArrayList<Schema>();
		listeSchemaKb= new ArrayList<Schema>();
		Calendar c = Calendar.getInstance();
		//c.getTimeInMillis();
		System.out.println("NLA debut");
		for(String s:listDatabaseName){
			loadSchema(s);	
		}		
		Calendar c_fin = Calendar.getInstance();
		c_fin.getTimeInMillis();		
		System.out.println("NLA fin duree:"+(c_fin.getTimeInMillis() - c.getTimeInMillis()));
	}
	
	private void loadSchema(final String DAOAccess){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		ForumDAO forumDAOCSS2 = (ForumDAO) context.getBean(DAOAccess);
		if(forumDAOCSS2.isConnectionOK()){
			ArrayList<Schema> listeSchemaCb2 = (ArrayList)forumDAOCSS2.getListSchemaCb();
			ArrayList<Schema> listeSchemaKb2 = (ArrayList)forumDAOCSS2.getListSchemaKB();
			forumDAOCSS2.updateVersionCAST(listeSchemaCb2);
			forumDAOCSS2.updateLocalSite(listeSchemaCb2);
			forumDAOCSS2.updateSnapshotCAST(listeSchemaCb2);
			forumDAOCSS2.updateEnlightenViews(listeSchemaCb2);
			forumDAOCSS2.updateActions(listeSchemaCb2);			
			forumDAOCSS2.updateHealthFactors(listeSchemaCb2);
			forumDAOCSS2.updateVolumes(listeSchemaCb2);
			forumDAOCSS2.updateTechnologies(listeSchemaCb2);
			
			for(Schema s:listeSchemaCb2){
				s.setConnectionName(DAOAccess);
			}
			
			
			listeSchemaCb.addAll(listeSchemaCb2);
			listeSchemaKb.addAll(listeSchemaKb2);
		}

	}

	public ArrayList<Schema> getListSchemaCb(){
		return listeSchemaCb;
	}

	public ArrayList<Schema> getListSchemaKb(){
		return listeSchemaKb;
	}

	@Override
	public ArrayList<Metric> getActionListFromCB(String pSchema) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DataBaseLoader dataBaseLoader = (DataBaseLoader) context.getBean("listOfDatabases");
		ArrayList<String> listDatabaseName  =dataBaseLoader.getBaseNames();
		for(String s:listDatabaseName){
			ArrayList<Metric> temp = getActionListFromCB(s, pSchema);
			if(temp !=null){
				metricList = temp;
				return metricList;
			}
		}
		return metricList;
	}
	
	private ArrayList<Metric> getActionListFromCB(final String databaseType, final String pSchema){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ForumDAO forumDAO = (ForumDAO) context.getBean(databaseType);
		try{
			if(forumDAO.isConnectionOK()){				
				return  (ArrayList<Metric>)forumDAO.getActionListFromCB(pSchema);		
			}
			}catch(UncategorizedSQLException use){
				return null;
			}
		return null;
		
		
	}

	@Override
	public void deleteFromAP(String pSchema,String pMetricID, String pObjID) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DataBaseLoader dataBaseLoader = (DataBaseLoader) context.getBean("listOfDatabases");
		ArrayList<String> listDatabaseName  =dataBaseLoader.getBaseNames();
		for(String s:listDatabaseName){
			if(deleteFromAp(s, pSchema, pMetricID,pObjID)){
				return;
			}
			
		}
	}
	
	private boolean deleteFromAp(final String databaseType, String pSchema,String pMetricID, String pObjID) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ForumDAO forumDAO = (ForumDAO) context.getBean(databaseType);
		try{
			if(forumDAO.isConnectionOK()){				
				forumDAO.deleteFromAP(pSchema, pMetricID,pObjID);		
			}
			}catch(UncategorizedSQLException use){
				return false;
			}
		return true;		
	}

	@Override
	public void compareSchema(Schema pSchema, Schema pSchemaRef) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DataBaseLoader dataBaseLoader = (DataBaseLoader) context.getBean("listOfDatabases");
		ArrayList<String> listDatabaseName  =dataBaseLoader.getBaseNames();
		for(String s:listDatabaseName){
			if(compareSchema(s, pSchema, pSchemaRef)){
				return;
			}
		}
	}
	
	public boolean compareSchema(final String databaseType, Schema pSchema, Schema pSchemaRef){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ForumDAO forumDAO = (ForumDAO) context.getBean(databaseType);
		try{
			if(forumDAO.isConnectionOK()){				
				forumDAO.updateSchemaParamModifiedWithSTD(pSchema);		
			}
		}catch(UncategorizedSQLException use){
			return false;
		}
		return true;		
		
//		forumDAO.updateSchemaWithRemovedMetric(pSchema, pSchemaRef);
//		forumDAO.updateSchemaWithAddedMetric(pSchema, pSchemaRef);  DONE
//		forumDAO.updateSchemaDisabledMetric(pSchema,pSchemaRef);
//		forumDAO.updateSchemaParamModified(pSchema,pSchemaRef);   DONE
		
	}

	@Override
	public ArrayList<Application> getListApplication() {
		ArrayList<Application> returnVal = new ArrayList<Application>();
		for(Schema sche:listeSchemaCb){
			SchemaCb cb= (SchemaCb)sche;
			for(Snapshot snap: cb.getSnapshotList()){
				Application a = snap.getAppli();				
				Application  b = existApplication(returnVal,a.getName());
				if(b!=null){
					b.getListSnapshot().add(snap);
				}else{
					Application app = new Application();
					app.setName(a.getName());						
					app.getListSnapshot().add(snap);
					app.setVersionCAST(cb.getVersion());
					app.setCentralBaseName(cb.getName());
					app.setMaxLocValue(a.getMaxLocValue());
					returnVal.add(app);
				}				
			}
		}
		return returnVal;
	}
	
	@Override
	public void setMaxLocForAllApp(){
		double returnVal = 1;
		for(Schema sche:listeSchemaCb){
			SchemaCb cb= (SchemaCb)sche;
			for(Snapshot snap: cb.getSnapshotList()){
				double loc = snap.getcontribution(10151);
				if(loc != 0 && loc > returnVal){
					returnVal = loc;
				}				
			}
		}
		
		for(Schema sche:listeSchemaCb){
			SchemaCb cb= (SchemaCb)sche;
			for(Snapshot snap: cb.getSnapshotList()){
				Application a = snap.getAppli();				
				a.setMaxLocValue(returnVal);
			}
		}
		
	}

	@Override
	public ArrayList<StringBean> getListVersion() {
		ArrayList<StringBean> returnVal = new ArrayList<StringBean>();
		for(Application a:getListApplication()){
			StringBean sbb = null;
			for(StringBean sb:returnVal){
				if(sb.getS().equals(a.getVersionCAST())){
					sbb = sb;
				}
			}
			if(sbb != null){
				sbb.increment();
			}else{
				sbb = new StringBean();
				sbb.setS(a.getVersionCAST());
				sbb.increment();
				returnVal.add(sbb);
			}
		}	
		return returnVal;
	}
	
	@Override
	public ArrayList<Technology> getListTechnologies() {
		ArrayList<Technology> returnVal = new ArrayList<Technology>();
		for(Application a:getListApplication()){
			for(Technology t:a.getTechnologiesLastSnapshot()){
				Technology technoTemp = null;
				for(Technology tt:returnVal){
					if(tt.getId()==t.getId()){
						technoTemp = tt;
					}
				}
				if( technoTemp != null){
					technoTemp.incrementAppCount();
				}else{
						Technology nt = new Technology();
						nt.setApplicationCount(1);
						nt.setId(t.getId());
						nt.setName(t.getName());
						returnVal.add(nt);
					}
				
				}
			}
		
		return returnVal;
	}
	
	
	private Application existApplication(ArrayList<Application> listapp, String AppName){
		for(Application a:listapp){
			if(a.getName().equals(AppName)){
				return a;
			}
		}
		return null;
	}

	@Override
	public void loadApplications() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		benchmarkDAO.loadApplication(null);
	}

	
	@Override
	public void selectCartesianProduct(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		benchmarkDAO.selectCartesianProduct();
	}
	
	@Override
	public void insertData() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		benchmarkDAO.insertdata(null);
	}
	
	@Override
	public void selectStar() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		benchmarkDAO.selectStar();
	}
	
	@Override
	public void selectStar100(int i) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		benchmarkDAO.selectStar100(100);
	}
	
	@Override
	public  ArrayList<Account> getListAccount(int i){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		return benchmarkDAO.getListAccountDAO(i);
	}
	
	

	@Override
	public void selectOneColumn() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		benchmarkDAO.selectOneColumn();
	}

	@Override
	public void selectOneColumnLoop() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		for(int i=0;i<100;i++){
			benchmarkDAO.selectOneColumn();
		}
	}
	
	@Override
	public void selectOneColumnRecursifEntryPoint() {
		int lower = 1;
		int higher = 100;

		int random = (int)(Math.random() * (higher-lower)) + lower;
		selectOneColumnRecursif(random);
	}

	
	
	private void selectOneColumnRecursif(int i) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BenchmarkDAO benchmarkDAO = (BenchmarkDAO) context.getBean("dataSourceForPOC");
		if(i == 50) {
			System.out.println("i= 50 sorti de la boucle");
			benchmarkDAO.selectOneColumn();
		}else {
			System.out.println("i="+i);
			int lower = 1;
			int higher = 100;
			int random = (int)(Math.random() * (higher-lower)) + lower;
			selectOneColumnRecursif(random);
		}
	}
	
}
