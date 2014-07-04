package com.nla.dao;


import com.nla.domain.*;

import java.util.ArrayList;
import java.util.List;

public interface ForumDAO {

    /**
     * ***************************************************\
     * template for examples
     * \*****************************************************
     */
    public void insertForum(KbUsr kbUsr);

    public KbUsr selectForum(int forumId);

    /**
     * ***************************************************\
     * Central base as entry point
     * \*****************************************************
     */
    public boolean isConnectionOK();

    public List<SchemaCb> getListSchemaCb();

    public void updateVersion(List<Schema> pListe);

    public void updateSnapshot(List<Schema> pListe);

    public void updateHealthFactors(List<Schema> pListe);

    public void updateVolumes(List<Schema> pListe);

    public void updateTechnologies(List<Schema> pListe);

    public void updateActions(List<Schema> pListe);

    public void updateLocalSite(List<Schema> pListe);

    public void updateEnlightenViews(List<Schema> pListe);

    public List<Metric> getListMetric(final String pCentralBaseName, final String pSnapshotIDApplicationID);

    public List<Violation> getListViolation(final String pCentralBaseName, final String pSnapshotIDApplicationID, final int pMetricID);

    public List<ObjectPoc> getListObj(final String pCentralBaseName, final String pSnapshotIDApplicationID,
                                      final String fhForm, final String weightForm, final String thresholdtForm, final String criticaltForm,
                                      final String moduleForm, final String objNameForm, final String metricNameForm, final String metricIDForm);

    public List<Metric> getListMetricForEnlightenView(final String schemaName, final int enlightenID);


    /**
     * ***************************************************\
     * Knowledge base as entry point
     * \*****************************************************
     */
    public List<SchemaKb> getListSchemaKB();

    /**
     * ***************************************************\
     * Management base as entry point
     * \*****************************************************
     */
    public List<SchemaMNGT> getListSchemaMNGT();

    public void updateCentralBaseList(List<SchemaMNGT> pListe);

    public void updateKnowledgeBaseList(List<Schema> pListe);

    public void updateApplicationAndModule(List<SchemaMNGT> pListe);

    public ArrayList<Metric> getActionListFromCB(String pSchema);

    public void deleteFromAP(String pSchema, String pMetricID, String pObjID);

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
