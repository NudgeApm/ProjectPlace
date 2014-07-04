package com.nla.service;

import com.nla.domain.Metric;

import java.util.ArrayList;

public interface KnowledgeBaseService {

    public ArrayList<Metric> getMetricFromEnlightenView(String schemaName, int enlightenID);
}
