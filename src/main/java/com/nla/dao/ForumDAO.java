package com.nla.dao;


import java.util.ArrayList;
import java.util.List;

import com.nla.domain.KbUsr;
import com.nla.domain.Metric;
import com.nla.domain.ObjectCAST;
import com.nla.domain.Schema;
import com.nla.domain.SchemaCb;
import com.nla.domain.SchemaKb;
import com.nla.domain.SchemaMNGT;
import com.nla.domain.Violation;

public interface ForumDAO {

	/******************************************************\
	 * template for examples
	\******************************************************/
	public void insertForum(KbUsr kbUsr);
	public KbUsr selectForum(int forumId);

	/******************************************************\
	 * Central base as entry point
	\******************************************************/
	public boolean isConnectionOK();
	
	public List<SchemaCb> getListSchemaCb();
	public void updateVersionCAST(List<Schema> pListe);
	public void updateSnapshotCAST(List<Schema> pListe);
	public void updateHealthFactors(List<Schema> pListe);
	public void updateVolumes(List<Schema> pListe);
	public void updateTechnologies(List<Schema> pListe);
	
	public void updateActions(List<Schema> pListe);
	public void updateLocalSite(List<Schema> pListe);
	public void updateEnlightenViews(List<Schema> pListe);
	public List<Metric> getListMetric(final String pCentralBaseName,final String pSnapshotIDApplicationID);
	public List<Violation> getListViolation(final String pCentralBaseName,final String pSnapshotIDApplicationID, final int pMetricID);
	public List<ObjectCAST> getListObjCAST(final String pCentralBaseName,final String pSnapshotIDApplicationID,
			final String fhForm,final String weightForm,final String thresholdtForm,final String criticaltForm,
			final String moduleForm, final String objNameForm,final String metricNameForm, final String metricIDForm);
	
	public List<Metric> getListMetricForEnlightenView(final String schemaName, final int enlightenID);
	
	
	/******************************************************\
	 * Knowledge base as entry point
	\******************************************************/	
	public List<SchemaKb> getListSchemaKB();

	/******************************************************\
	 * Management base as entry point
	\******************************************************/
	public List<SchemaMNGT> getListSchemaMNGT();
	public void updateCentralBaseList(List<SchemaMNGT> pListe);
	public void updateKnowledgeBaseList(List<Schema> pListe);
	public void updateApplicationAndModule(List<SchemaMNGT> pListe);
	public ArrayList<Metric> getActionListFromCB(String pSchema);
	public void deleteFromAP(String pSchema,String pMetricID, String pObjID);
	public boolean addObjToActionPlan(String pSchemaName,
			String snapshotIDslashappliID, Integer pMetricID, String objID,
			String priority, String comment);
	public void updateSchemaWithRemovedMetric(Schema pSchemaName,
			Schema pSchemaNameRef);
	public void updateSchemaWithAddedMetric(Schema pSchemaName,
			Schema pSchemaNameRef);
	public void updateSchemaDisabledMetric(Schema pSchema, Schema pSchemaRef);
	public void updateSchemaParamModified(Schema pSchema, Schema pSchemaRef);
	public void updateSchemaParamModifiedWithSTD(Schema pSchema);
	
	
}
