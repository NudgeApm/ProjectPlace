package com.nla.service;

import com.nla.domain.Schema;

import java.util.ArrayList;


public interface ManagementBaseListService {
    public void loadSchema();

    public ArrayList<Schema> getListSchemaMNGT();

    public int getNBSchema();
}
