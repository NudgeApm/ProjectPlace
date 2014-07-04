package com.nla.domain;

import java.util.ArrayList;

/**
 * Central Base
 *
 * @author NLA
 */
public class SchemaCb extends Schema implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SchemaCb() {
        super(null);
    }

    public SchemaCb(String name, ArrayList<Snapshot> snapshotList,
                    ArrayList<EnlightenView> enlightenViewList,
                    ArrayList<String> localSiteList, ArrayList<Action> actionList,
                    ArrayList<Metric> removedMetric, ArrayList<Metric> addedMetric,
                    ArrayList<Metric> paramModifyMetric,
                    ArrayList<Metric> paramModifyMetricSTD,
                    ArrayList<Metric> disabledMetric) {
        super(name);
        this.snapshotList = snapshotList;
        this.enlightenViewList = enlightenViewList;
        this.localSiteList = localSiteList;
        this.actionList = actionList;
        this.removedMetric = removedMetric;
        this.addedMetric = addedMetric;
        this.paramModifyMetric = paramModifyMetric;
        this.paramModifyMetricSTD = paramModifyMetricSTD;
        this.disabledMetric = disabledMetric;
    }

    /**
     * number of snapshot
     *
     * @param name
     */
    ArrayList<Snapshot> snapshotList = new ArrayList<Snapshot>();

    ArrayList<EnlightenView> enlightenViewList = new ArrayList<EnlightenView>();

    ArrayList<String> localSiteList = new ArrayList<String>();

    ArrayList<Action> actionList = new ArrayList<Action>();

    // metric removed Versus Reference schema central
    ArrayList<Metric> removedMetric = new ArrayList<Metric>();

    // metric added Versus Reference schema central
    ArrayList<Metric> addedMetric = new ArrayList<Metric>();

    // metric with param modify
    ArrayList<Metric> paramModifyMetric = new ArrayList<Metric>();

    // metric with param modify Versus standard
    ArrayList<Metric> paramModifyMetricSTD = new ArrayList<Metric>();

    // disabled metric
    ArrayList<Metric> disabledMetric = new ArrayList<Metric>();

    public ArrayList<String> getLocalSiteList() {
        return localSiteList;
    }

    public void setLocalSiteList(ArrayList<String> localSiteList) {
        this.localSiteList = localSiteList;
    }

    public ArrayList<Snapshot> getSnapshotList() {
        return snapshotList;
    }

    public void setSnapshotList(ArrayList<Snapshot> snapshotList) {
        this.snapshotList = snapshotList;
    }

    public SchemaCb(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Central";
    }

    public int getNumberOfSnapshot() {
        return this.snapshotList.size();
    }

    public int getNumberOfEnlightenView() {
        return this.enlightenViewList.size();
    }

    public int getNumberOfAction() {
        return this.actionList.size();
    }

    public int getNumberOfLocalSite() {
        return getLocalSiteList().size();
    }

    public ArrayList<Action> getActionList() {
        return actionList;
    }

    public void setActionList(ArrayList<Action> actionList) {
        this.actionList = actionList;
    }

    public ArrayList<EnlightenView> getEnlightenViewList() {
        return enlightenViewList;
    }

    public void setEnlightenViewList(ArrayList<EnlightenView> enlightenViewList) {
        this.enlightenViewList = enlightenViewList;
    }

    public ArrayList<Metric> getRemovedMetric() {
        return removedMetric;
    }

    public void setRemovedMetric(ArrayList<Metric> removedMetric) {
        this.removedMetric = removedMetric;
    }

    public ArrayList<Metric> getAddedMetric() {
        return addedMetric;
    }

    public void setAddedMetric(ArrayList<Metric> addedMetric) {
        this.addedMetric = addedMetric;
    }

    public ArrayList<Metric> getDisabledMetric() {
        return disabledMetric;
    }

    public void setDisabledMetric(ArrayList<Metric> disabledMetric) {
        this.disabledMetric = disabledMetric;
    }

    public ArrayList<Metric> getParamModifyMetric() {
        return paramModifyMetric;
    }

    public void setParamModifyMetric(ArrayList<Metric> paramModifyMetric) {
        this.paramModifyMetric = paramModifyMetric;
    }

    public ArrayList<Metric> getParamModifyMetricSTD() {
        return paramModifyMetricSTD;
    }

    public void setParamModifyMetricSTD(ArrayList<Metric> paramModifyMetricSTD) {
        this.paramModifyMetricSTD = paramModifyMetricSTD;
    }


}
