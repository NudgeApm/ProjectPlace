package com.nla.service;

import com.nla.dao.DataBaseLoader;
import com.nla.dao.ForumDAO;
import com.nla.domain.Metric;
import com.nla.domain.ObjectPoc;
import com.nla.domain.Schema;
import com.nla.domain.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class MetricListServiceImpl implements MetricListService {

    private ArrayList<Metric> metricList;
    private ArrayList<Violation> violationList;

    @Autowired
    private DataBaseLoader dataBaseLoader;

    @Autowired
    private ForumDAO forumDAO;

    @Override
    public ArrayList<Metric> getListMetric(final String pCentralBaseName, final String pSnapshotIDApplicationID) {

        ArrayList<Metric> tempList;
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            tempList = getListMetricPrivate(s, pCentralBaseName, pSnapshotIDApplicationID);
            if (tempList != null) {
                metricList = tempList;
                return tempList;
            }
        }
        return null;
    }

    private ArrayList<Metric> getListMetricPrivate(final String DAOaccess, final String pCentralBaseName, final String pSnapshotIDApplicationID) {

        try {
            if (forumDAO.isConnectionOK()) {
                return (ArrayList) forumDAO.getListMetric(pCentralBaseName, pSnapshotIDApplicationID);
            }
        } catch (UncategorizedSQLException use) {
            return null;
        }
        return null;

    }

    @Override
    public ArrayList<Violation> getListViolation(final String pCentralBaseName, final String pSnapshotIDApplicationID, final int pMetricID) {
        ArrayList<Violation> tempList;
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            tempList = getListViolation(s, pCentralBaseName, pSnapshotIDApplicationID, pMetricID);
            if (tempList != null) {
                violationList = tempList;
                return tempList;
            }
        }
        return null;
    }

    private ArrayList<Violation> getListViolation(final String DAOAccess, final String pCentralBaseName, final String pSnapshotIDApplicationID, final int pMetricID) {
        try {
            if (forumDAO.isConnectionOK()) {
                return (ArrayList) forumDAO.getListViolation(pCentralBaseName, pSnapshotIDApplicationID, pMetricID);
            }
        } catch (UncategorizedSQLException use) {
            return null;
        }
        return null;
    }

    @Override
    public int getMaxMetricWeight() {
        int returnVal = 0;
        for (Metric m : metricList) {
            if (m.getWeight() > returnVal) {
                returnVal = m.getWeight();
            }
        }
        return returnVal;
    }

    @Override
    public int getMaxTCWeight() {
        int returnVal = 0;
        for (Metric m : metricList) {
            if (m.getTcWeight() > returnVal) {
                returnVal = m.getTcWeight();
            }
        }
        return returnVal;
    }

    @Override
    public void setHealthFactor(int healftFactorID) {
        for (Metric m : metricList) {
            if (m.getHfId() != healftFactorID) {
                m.setAffiche(0);
            }
        }
    }

    @Override
    public void addToActionPlan(String pSchemaName,
                                String snapshotIDslashappliID, Integer pMetricID, String objID,
                                String priority, String comment) {
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            if (addToActionPlan(s, pSchemaName, snapshotIDslashappliID, pMetricID, objID, priority, comment)) {
                return;
            }
        }
    }

    private boolean addToActionPlan(final String databaseType, String pSchemaName,
                                    String snapshotIDslashappliID, Integer pMetricID, String objID,
                                    String priority, String comment) {
        try {
            if (forumDAO.isConnectionOK()) {
                return forumDAO.addObjToActionPlan(pSchemaName, snapshotIDslashappliID, pMetricID, objID, priority, comment);

            }
        } catch (UncategorizedSQLException use) {
            return false;
        }
        return false;
    }

    @Override
    public ArrayList<ObjectPoc> getListObj(final String pCentralBaseName, final String pSnapshotIDApplicationID, final String hfForm,
                                           final String weightForm, final String thresholdtForm, final String criticaltForm, final String moduleForm,
                                           final String objNameForm,
                                           final String metricNameForm,
                                           final String metricIDForm) {
        ArrayList<String> listDatabaseName = dataBaseLoader.getBaseNames();
        for (String s : listDatabaseName) {
            ArrayList<ObjectPoc> tempList = new ArrayList<ObjectPoc>();
            tempList = getListObj(s, pCentralBaseName, pSnapshotIDApplicationID, hfForm, weightForm, thresholdtForm, criticaltForm, moduleForm, objNameForm, metricNameForm, metricIDForm);
            if (tempList != null) {
                return tempList;
            }
        }
        return null;

    }

    private ArrayList<ObjectPoc> getListObj(final String DAOAccess, final String pCentralBaseName, final String pSnapshotIDApplicationID, final String hfForm, final String weightForm, final String thresholdtForm, final String criticaltForm, final String moduleForm,
                                            final String objNameForm,
                                            final String metricNameForm,
                                            final String metricIDForm) {
        try {
            if (forumDAO.isConnectionOK()) {
                return (ArrayList) forumDAO.getListObj(pCentralBaseName, pSnapshotIDApplicationID, hfForm, weightForm, thresholdtForm, criticaltForm, moduleForm, objNameForm, metricNameForm, metricIDForm);

            }
        } catch (UncategorizedSQLException use) {
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<Metric> getListMetricDiff(Schema schemaName,
                                               Schema schemaComp, String snapshotIDslashappliID1,
                                               String snapshotIDslashappliID2) {
        ArrayList<Metric> metricList2;
        metricList = (ArrayList) forumDAO.getListMetric(schemaName.getName(), snapshotIDslashappliID1);
        metricList2 = (ArrayList) forumDAO.getListMetric(schemaComp.getName(), snapshotIDslashappliID2);

        return concatMetricList(metricList, metricList2);
    }

    private ArrayList<Metric> concatMetricList(ArrayList<Metric> list1, ArrayList<Metric> list2) {
        HashMap<Integer, Metric> returnVal = new HashMap<Integer, Metric>();
        for (Metric m1 : list1) {
            returnVal.put(m1.getId(), m1);
        }
        for (Metric m2 : list2) {
            Metric metric = returnVal.get(m2.getId());
            if (metric == null) {
                metric = new Metric(m2.getId());
                metric.copyPropertyinto2(m2);
                returnVal.put(m2.getId(), metric);
            } else {
                metric.copyPropertyinto2(m2);
            }
        }

        ArrayList<Metric> returnList = new ArrayList<Metric>();
        for (Metric m : returnVal.values()) {
            returnList.add(m);
        }
        return returnList;
    }

}
