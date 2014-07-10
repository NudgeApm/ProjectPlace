package com.nla.domain;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Application extends ObjectBase implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int id;
    private String centralBaseName;
    private String name;
    private String version;
    private ArrayList<Snapshot> listSnapshot = new ArrayList<Snapshot>();
    double maxLocValue;

    public double getMaxLocValue() {
        return maxLocValue;
    }

    public void setMaxLocValue(double maxLocValue) {
        this.maxLocValue = maxLocValue;
    }

    public ArrayList<Snapshot> getListSnapshot() {
        return listSnapshot;
    }

    public void setListSnapshot(ArrayList<Snapshot> listSnapshot) {
        this.listSnapshot = listSnapshot;
    }

    ArrayList<Module> moduleList = new ArrayList<Module>();

    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCentralBaseName() {
        return centralBaseName;
    }

    public void setCentralBaseName(String centralBaseName) {
        this.centralBaseName = centralBaseName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getNumberOfSnapshot() {
        return getListSnapshot().size();
    }

    public String getLastSnapshotFunctionalDate() {
        String returnVal = null;
        for (Snapshot s : listSnapshot) {
            if (returnVal == null) {
                returnVal = s.getFunctionalDate();
            } else {
                if (returnVal.compareTo(s.getFunctionalDate()) < 0) {
                    returnVal = s.getFunctionalDate();
                }
            }
        }
        return returnVal;
    }

    public ArrayList<Contribution> getContributionLastSnapshot() {
        String returnVal = null;
        Snapshot tempLastSnapshot = null;
        for (Snapshot s : listSnapshot) {
            if (returnVal == null) {
                returnVal = s.getFunctionalDate();
                tempLastSnapshot = s;
            } else {
                if (returnVal.compareTo(s.getFunctionalDate()) < 0) {
                    returnVal = s.getFunctionalDate();
                    tempLastSnapshot = s;
                }
            }
        }

        return tempLastSnapshot.getListHealthFactor();
    }

    public ArrayList<Technology> getTechnologiesLastSnapshot() {
        String returnVal = null;
        Snapshot tempLastSnapshot = null;
        for (Snapshot s : listSnapshot) {
            if (returnVal == null) {
                returnVal = s.getFunctionalDate();
                tempLastSnapshot = s;
            } else {
                if (returnVal.compareTo(s.getFunctionalDate()) < 0) {
                    returnVal = s.getFunctionalDate();
                    tempLastSnapshot = s;
                }
            }
        }

        return tempLastSnapshot.getListTechnologies();
    }


    public double getContributionById(int pId) {
        for (Contribution c : getContributionLastSnapshot()) {
            if (c.getId() == pId) {
                return c.getGrade();
            }
        }
        return 0;
    }

    public double getTqi() {
        return getContributionById(60017);
    }

    public String getLoC() {

        double amount = getContributionById(10151);
        NumberFormat numberFormatter;

        numberFormatter = NumberFormat.getNumberInstance();
        return (numberFormatter.format(amount).equals("0")) ? "-" : numberFormatter.format(amount);
        //return getContributionById(10151);
    }

    public String getLocPercentage() {
        double amount = getContributionById(10151);
        double locPer = (amount / getMaxLocValue()) * 100;
        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        return numberFormatter.format(locPer);
    }

    public String getNegativeLocPercentage() {
        double amount = getContributionById(10151);
        double locPer = (amount / getMaxLocValue()) * 100;

        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        return numberFormatter.format(100 - locPer);
    }

    public String getTechnologies() {
        String returnVal = "";
        for (Technology t : getTechnologiesLastSnapshot()) {
            returnVal += t.getName() + " ";
        }
        return returnVal;
    }
}
