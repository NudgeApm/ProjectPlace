package com.nla.dao;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.StringTokenizer;


@Component
public class DatabaseLoaderImpl implements DataBaseLoader {

    private String dataBaseName;

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public ArrayList<String> getBaseNames() {
        ArrayList<String> returnList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(dataBaseName, ",");
        while (st.hasMoreElements()) {
            String s = (String) st.nextElement();
            returnList.add(s);
        }
        return returnList;
    }

}
