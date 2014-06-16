package com.nla.service;

import java.util.ArrayList;

import com.nla.domain.Metric;

public interface KnowledgeBaseService {

	public ArrayList<Metric> getMetricFromEnlightenView(String schemaName, int enlightenID);
}
