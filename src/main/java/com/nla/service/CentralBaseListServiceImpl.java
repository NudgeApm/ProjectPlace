package com.nla.service;

import com.nla.dao.BenchmarkDAO;
import com.nla.dao.DataBaseLoader;
import com.nla.dao.ForumDAO;
import com.nla.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class CentralBaseListServiceImpl implements CentralBaseListService {

    private ArrayList<Schema> listeSchemaCb;
    private ArrayList<Schema> listeSchemaKb;
    private ArrayList<Metric> metricList;

    @Autowired
    private BenchmarkDAO benchmarkDAO;

    @Autowired
    private ForumDAO forumDAO;

    @Autowired
    private DataBaseLoader dataBaseLoader;

    @Override
    public void add(ViewCentralBaseList log) {
    }


    public void generateNullPointerException() {
        String s = null;
        int i = s.length();
    }

    public void loadSchema() {
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        listeSchemaCb = new ArrayList<Schema>();
        listeSchemaKb = new ArrayList<Schema>();
        Calendar c = Calendar.getInstance();
        //c.getTimeInMillis();
        System.out.println("NLA debut");
        for (String s : listDatabaseName) {
            loadSchema(s);
        }
        Calendar c_fin = Calendar.getInstance();
        c_fin.getTimeInMillis();
        System.out.println("NLA fin duree:" + (c_fin.getTimeInMillis() - c.getTimeInMillis()));
    }

    private void loadSchema(final String DAOAccess) {

        if (forumDAO.isConnectionOK()) {
            List<Schema> listeSchemaCb2 = (ArrayList) forumDAO.getListSchemaCb();
            List<Schema> listeSchemaKb2 = (ArrayList) forumDAO.getListSchemaKB();
            forumDAO.updateVersion(listeSchemaCb2);
            forumDAO.updateLocalSite(listeSchemaCb2);
            forumDAO.updateSnapshot(listeSchemaCb2);
            forumDAO.updateEnlightenViews(listeSchemaCb2);
            forumDAO.updateActions(listeSchemaCb2);
            forumDAO.updateHealthFactors(listeSchemaCb2);
            forumDAO.updateVolumes(listeSchemaCb2);
            forumDAO.updateTechnologies(listeSchemaCb2);

            for (Schema s : listeSchemaCb2) {
                s.setConnectionName(DAOAccess);
            }


            listeSchemaCb.addAll(listeSchemaCb2);
            listeSchemaKb.addAll(listeSchemaKb2);
        }

    }

    public ArrayList<Schema> getListSchemaCb() {
        return listeSchemaCb;
    }

    public ArrayList<Schema> getListSchemaKb() {
        return listeSchemaKb;
    }

    @Override
    public ArrayList<Metric> getActionListFromCB(String pSchema) {

        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            ArrayList<Metric> temp = getActionListFromCB(s, pSchema);
            if (temp != null) {
                metricList = temp;
                return metricList;
            }
        }
        return metricList;
    }

    private ArrayList<Metric> getActionListFromCB(final String databaseType, final String pSchema) {
        try {
            if (forumDAO.isConnectionOK()) {
                return (ArrayList<Metric>) forumDAO.getActionListFromCB(pSchema);
            }
        } catch (UncategorizedSQLException use) {
            return null;
        }
        return null;


    }

    @Override
    public void deleteFromAP(String pSchema, String pMetricID, String pObjID) {
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            if (deleteFromAp(s, pSchema, pMetricID, pObjID)) {
                return;
            }

        }
    }

    private boolean deleteFromAp(final String databaseType, String pSchema, String pMetricID, String pObjID) {
        try {
            if (forumDAO.isConnectionOK()) {
                forumDAO.deleteFromAP(pSchema, pMetricID, pObjID);
            }
        } catch (UncategorizedSQLException use) {
            return false;
        }
        return true;
    }

    @Override
    public void compareSchema(Schema pSchema, Schema pSchemaRef) {
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            if (compareSchema(s, pSchema, pSchemaRef)) {
                return;
            }
        }
    }

    public boolean compareSchema(final String databaseType, Schema pSchema, Schema pSchemaRef) {
        try {
            if (forumDAO.isConnectionOK()) {
                forumDAO.updateSchemaParamModifiedWithSTD(pSchema);
            }
        } catch (UncategorizedSQLException use) {
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
        for (Schema sche : listeSchemaCb) {
            SchemaCb cb = (SchemaCb) sche;
            for (Snapshot snap : cb.getSnapshotList()) {
                Application a = snap.getAppli();
                Application b = existApplication(returnVal, a.getName());
                if (b != null) {
                    b.getListSnapshot().add(snap);
                } else {
                    Application app = new Application();
                    app.setName(a.getName());
                    app.getListSnapshot().add(snap);
                    app.setVersion(cb.getVersion());
                    app.setCentralBaseName(cb.getName());
                    app.setMaxLocValue(a.getMaxLocValue());
                    returnVal.add(app);
                }
            }
        }
        return returnVal;
    }

    @Override
    public void setMaxLocForAllApp() {
        double returnVal = 1;
        for (Schema sche : listeSchemaCb) {
            SchemaCb cb = (SchemaCb) sche;
            for (Snapshot snap : cb.getSnapshotList()) {
                double loc = snap.getcontribution(10151);
                if (loc != 0 && loc > returnVal) {
                    returnVal = loc;
                }
            }
        }

        for (Schema sche : listeSchemaCb) {
            SchemaCb cb = (SchemaCb) sche;
            for (Snapshot snap : cb.getSnapshotList()) {
                Application a = snap.getAppli();
                a.setMaxLocValue(returnVal);
            }
        }

    }

    @Override
    public ArrayList<StringBean> getListVersion() {
        ArrayList<StringBean> returnVal = new ArrayList<StringBean>();
        for (Application a : getListApplication()) {
            StringBean sbb = null;
            for (StringBean sb : returnVal) {
                if (sb.getS().equals(a.getVersion())) {
                    sbb = sb;
                }
            }
            if (sbb != null) {
                sbb.increment();
            } else {
                sbb = new StringBean();
                sbb.setS(a.getVersion());
                sbb.increment();
                returnVal.add(sbb);
            }
        }
        return returnVal;
    }

    @Override
    public ArrayList<Technology> getListTechnologies() {
        ArrayList<Technology> returnVal = new ArrayList<Technology>();
        for (Application a : getListApplication()) {
            for (Technology t : a.getTechnologiesLastSnapshot()) {
                Technology technoTemp = null;
                for (Technology tt : returnVal) {
                    if (tt.getId() == t.getId()) {
                        technoTemp = tt;
                    }
                }
                if (technoTemp != null) {
                    technoTemp.incrementAppCount();
                } else {
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


    private Application existApplication(ArrayList<Application> listapp, String AppName) {
        for (Application a : listapp) {
            if (a.getName().equals(AppName)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void loadApplications() {
        benchmarkDAO.loadApplication(null);
    }

    @Override
    public void selectCartesianProduct() {
        benchmarkDAO.selectCartesianProduct();
    }

    @Override
    public void insertData() {
        benchmarkDAO.insertdata(null);
    }

    @Override
    public void selectStar() {
        benchmarkDAO.selectStar();
    }

    @Override
    public void selectStar100(int i) {
        benchmarkDAO.selectStar100(100);
    }

    @Override
    public ArrayList<Account> getListAccount(int i) {
        return benchmarkDAO.getListAccountDAO(i);
    }

    @Override
    public void selectOneColumn() {
        benchmarkDAO.selectOneColumn();
    }

    @Override
    public void selectOneColumnLoop() {
        for (int i = 0; i < 100; i++) {
            benchmarkDAO.selectOneColumn();
        }
    }

    @Override
    public void selectOneColumnRecursifEntryPoint() {
        int lower = 1;
        int higher = 100;

        int random = (int) (Math.random() * (higher - lower)) + lower;
        selectOneColumnRecursif(random);
    }


    private void selectOneColumnRecursif(int i) {
        if (i == 50) {
            benchmarkDAO.selectOneColumn();
        } else {
            System.out.println("i=" + i);
            int lower = 1;
            int higher = 100;
            int random = (int) (Math.random() * (higher - lower)) + lower;
            selectOneColumnRecursif(random);
        }
    }

}
