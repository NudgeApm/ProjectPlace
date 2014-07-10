package com.nla.domain;

public abstract class Schema {
    public Schema() {
        super();
    }

    private String name;
    private String connectionName;

    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();

    public Schema(String name) {
        super();
        this.name = name;
    }

    public String toString() {
        return this.getName();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

}
