package com.nla.service;

import java.util.ArrayList;

import com.nla.domain.Metric;
import com.nla.domain.ObjectCAST;
import com.nla.domain.Schema;
import com.nla.domain.Violation;

public interface MetricListService {

	public ArrayList<Metric> getListMetric(final String pCentralBaseName,final String pSnapshotIDApplicationID);
	public ArrayList<Violation> getListViolation(final String pCentralBaseName,final String pSnapshotIDApplicationID, final int pMetricID);
	public int getMaxMetricWeight();	
	public int getMaxTCWeight();
	public void setHealthFactor(int healftFactorID);
	public void addToActionPlan(String pSchemaName,
			String snapshotIDslashappliID, Integer valueOf, String objID,
			String priority, String comment);
	public ArrayList<ObjectCAST> getListObjCAST(
			final String pCentralBaseName,
			final String pSnapshotIDApplicationID, 
			final String hfForm,
			final String weightForm,
			final String thresholdtForm, 
			final String criticaltForm,
			final String moduleForm,
			final String objNameForm,
			final String metricNameForm,
			final String metricIDForm);
	public ArrayList<Metric> getListMetricDiff(Schema schemaName,
			Schema schemaComp, String snapshotIDslashappliID1, String snapshotIDslashappliID2);
}
