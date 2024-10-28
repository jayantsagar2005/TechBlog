package com.happy.entity;

public class Category {
    private int cId;
    private String cName;
    private String cDescription;
    private String cFileName;

    public Category(int cId, String cName, String cDescription, String cFileName) {
        this.cId = cId;
        this.cName = cName;
        this.cDescription = cDescription;
        this.cFileName = cFileName;
    }

    public Category() {
    }

    public Category(String cName, String cDescription, String cFileName) {
        this.cName = cName;
        this.cDescription = cDescription;
        this.cFileName = cFileName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getcFileName() {
        return cFileName;
    }

    public void setcFileName(String cFileName) {
        this.cFileName = cFileName;
    }
}
