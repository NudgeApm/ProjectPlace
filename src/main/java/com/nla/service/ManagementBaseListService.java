package com.nla.service;

import java.util.ArrayList;

import com.nla.domain.Schema;


public interface ManagementBaseListService {
	public void loadSchema();
	public ArrayList<Schema> getListSchemaMNGT();
	public int getNBSchema();
}
