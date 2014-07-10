package com.nla.domain;

import com.nla.constante.Standard7011;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author NLA
 */
public class Metric implements java.io.Serializable {
    int id;

    String name;
    String description;
    double grade;
    int weight;
    int checkOK;
    int checkKO;
    boolean critical;
    String technicalCriteria;
    double tcGrade;
    int tcWeight;
    int tcId;
    String healthFactor;
    int hfId;

    // duplicated attribute for comparison
    String name2;
    String description2;
    double grade2;
    int weight2;
    int checkOK2;
    int checkKO2;
    boolean critical2;
    String technicalCriteria2;
    double tcGrade2;
    int tcWeight2;
    int tcId2;


    String healthFactor2;
    int hfId2;

    //param
    double threshold1;
    double threshold2;
    double threshold3;
    double threshold4;
    double status;

    ArrayList<Action> actionList;
    // par defaut, on affiche la metrique
    int affiche = 1;


    public Metric(int pId, double status, double threshold1, double threshold2, double threshold3,
                  double threshold4) {
        super();
        this.id = pId;
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.threshold3 = threshold3;
        this.threshold4 = threshold4;
        this.status = status;
    }


    public Metric(int pId, String pName, double status, double threshold1, double threshold2, double threshold3,
                  double threshold4) {
        super();
        this.id = pId;
        this.name = pName;
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.threshold3 = threshold3;
        this.threshold4 = threshold4;
        this.status = status;
    }

    public Metric(String name) {
        super();
        this.name = name;
        actionList = new ArrayList<Action>();
    }

    public Metric(int id) {
        super();
        this.id = id;
        actionList = new ArrayList<Action>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCheckOK() {
        return checkOK;
    }

    public void setCheckOK(int checkOK) {
        this.checkOK = checkOK;
    }

    public int getCheckKO() {
        return checkKO;
    }

    public void setCheckKO(int checkKO) {
        this.checkKO = checkKO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnicalCriteria() {
        return technicalCriteria;
    }

    public void setTechnicalCriteria(String technicalCriteria) {
        this.technicalCriteria = technicalCriteria;
    }

    public double getTcGrade() {
        return tcGrade;
    }

    public void setTcGrade(double tcGrade) {
        this.tcGrade = tcGrade;
    }

    public int getTcWeight() {
        return tcWeight;
    }

    public void setTcWeight(int tcWeight) {
        this.tcWeight = tcWeight;
    }

    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public String getHealthFactor() {
        return healthFactor;
    }

    public void setHealthFactor(String healthFactor) {
        this.healthFactor = healthFactor;
    }

    public int getHfId() {
        return hfId;
    }

    public void setHfId(int hfId) {
        this.hfId = hfId;
    }

    /**
     * getter for Total number of check
     */
    public int getTotal() {
        return getCheckKO() + getCheckOK();
    }

    public String getNameHTML() {
        String temp = StringUtils.replace(getName(), "(", "");
        temp = StringUtils.replace(temp, ")", "");
        temp = StringUtils.replace(temp, "'", "");
        return temp;
    }

    public String getNameHTML2() {
        String temp = StringUtils.replace(getName2(), "(", "");
        temp = StringUtils.replace(temp, ")", "");
        temp = StringUtils.replace(temp, "'", "");
        return temp;
    }

    public String getCriticalHTML() {
        String returnVal = "";
        if (isCritical()) {
            returnVal = "<i class=\'icon-fire\'></i>";
        }
        return returnVal;

    }

    public int getAffiche() {
        return affiche;
    }

    public void setAffiche(int affiche) {
        this.affiche = affiche;
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public void setActionList(ArrayList<Action> actionList) {
        this.actionList = actionList;
    }

    public void addActionToActionList(Action a) {
        actionList.add(a);
    }

    public int getNbAction() {
        return actionList.size();
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public double getGrade2() {
        return grade2;
    }

    public void setGrade2(double grade2) {
        this.grade2 = grade2;
    }

    public int getWeight2() {
        return weight2;
    }

    public void setWeight2(int weight2) {
        this.weight2 = weight2;
    }

    public int getCheckOK2() {
        return checkOK2;
    }

    public void setCheckOK2(int checkOK2) {
        this.checkOK2 = checkOK2;
    }

    public int getCheckKO2() {
        return checkKO2;
    }

    public void setCheckKO2(int checkKO2) {
        this.checkKO2 = checkKO2;
    }

    public boolean isCritical2() {
        return critical2;
    }

    public void setCritical2(boolean critical2) {
        this.critical2 = critical2;
    }

    public String getTechnicalCriteria2() {
        return technicalCriteria2;
    }

    public void setTechnicalCriteria2(String technicalCriteria2) {
        this.technicalCriteria2 = technicalCriteria2;
    }

    public double getTcGrade2() {
        return tcGrade2;
    }

    public void setTcGrade2(double tcGrade2) {
        this.tcGrade2 = tcGrade2;
    }

    public int getTcWeight2() {
        return tcWeight2;
    }

    public void setTcWeight2(int tcWeight2) {
        this.tcWeight2 = tcWeight2;
    }

    public int getTcId2() {
        return tcId2;
    }

    public void setTcId2(int tcId2) {
        this.tcId2 = tcId2;
    }

    public String getHealthFactor2() {
        return healthFactor2;
    }

    public void setHealthFactor2(String healthFactor2) {
        this.healthFactor2 = healthFactor2;
    }

    public int getHfId2() {
        return hfId2;
    }

    public void setHfId2(int hfId2) {
        this.hfId2 = hfId2;
    }

    public void copyPropertyinto2(Metric m2) {
        setCheckKO2(m2.getCheckKO());
        setCheckOK2(m2.getCheckOK());
        setCritical2(m2.isCritical());
        setDescription2(m2.getDescription());
        setGrade2(m2.getGrade());
        setHealthFactor2(m2.getHealthFactor());
        setHfId2(m2.getHfId());
        setName2(m2.getName());
        setTcGrade2(m2.getGrade());
        setTcId2(m2.getTcId());
        setTcWeight2(m2.getTcWeight());
        setTechnicalCriteria2(m2.getTechnicalCriteria());
        setWeight2(m2.getWeight());
    }

    public void copyProperty(Metric m2) {
        try {
            BeanUtils.copyProperties(this, m2);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public double getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(double threshold1) {
        this.threshold1 = threshold1;
    }

    public double getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(double threshold2) {
        this.threshold2 = threshold2;
    }

    public double getThreshold3() {
        return threshold3;
    }

    public void setThreshold3(double threshold3) {
        this.threshold3 = threshold3;
    }

    public double getThreshold4() {
        return threshold4;
    }

    public void setThreshold4(double threshold4) {
        this.threshold4 = threshold4;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public boolean isthresholdModified() {
        Standard7011 std7011 = Standard7011.getInstance();
        Metric stdM = std7011.getListMetric().get(getId());
        if (stdM != null) {
            if (!compareThrehold(stdM.getThreshold1(), getThreshold1())) {
                return true;
            }
            if (!compareThrehold(stdM.getThreshold2(), getThreshold2())) {
                return true;
            }
            if (!compareThrehold(stdM.getThreshold3(), getThreshold3())) {
                return true;
            }
            if (!compareThrehold(stdM.getThreshold4(), getThreshold4())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdded() {
        Standard7011 std7011 = Standard7011.getInstance();
        Metric stdM = std7011.getListMetric().get(getId());
        return stdM == null;
    }


    private boolean compareThrehold(double t1, double t2) {

        if (String.valueOf(t1).length() > 5 &&
                String.valueOf(t2).length() > 5 &&
                String.valueOf(t1).substring(0, 5).equals(String.valueOf(t2).substring(0, 5))) {
            return true;
        } else if (String.valueOf(t1).length() == String.valueOf(t2).length() && t1 == t2) {
            return true;
        } else {
            return false;
        }

    }

    public String getThreholdHTML() {
        return getThreshold1() + "/" + getThreshold2() + "/" + getThreshold3() + "/" + getThreshold4();
    }

    public String getThreholdStandardHTML() {
        Standard7011 std7011 = Standard7011.getInstance();
        Metric stdM = std7011.getListMetric().get(getId());
        return stdM.getThreshold1() + "/" + stdM.getThreshold2() + "/" + stdM.getThreshold3() + "/" + stdM.getThreshold4();
    }


}
