package com.nla.domain;

import java.util.ArrayList;

public class ViewCentralBaseList implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fileName;
    ArrayList<Schema> listeSchemaCb;
    ArrayList<Schema> listeSchemaKb;


    public ArrayList<Schema> getListeSchemaCb() {
        return listeSchemaCb;
    }

    public void setListeSchemaCb(ArrayList<Schema> listeSchema) {
        this.listeSchemaCb = listeSchema;
    }

    public ArrayList<Schema> getListeSchemaKb() {
        return listeSchemaKb;
    }

    public void setListeSchemaKb(ArrayList<Schema> listeSchema) {
        this.listeSchemaKb = listeSchema;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String pFileName) {
        this.fileName = pFileName;
    }

    /**
     * Method used for queries into CSS database
     *
     * @return
     */
    public String getListeSchemaNameKb() {
        String returnVal = "";
        boolean isFirstOccurence = true;
        for (Schema s : listeSchemaKb) {
            returnVal += s.getName();
            if (isFirstOccurence) {
                returnVal += s.getName();
                isFirstOccurence = false;
            } else {
                returnVal += "," + s.getName();
            }
        }
        return returnVal;
    }
}
