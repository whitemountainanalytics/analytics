package com.domain;

import java.io.Serializable;

public class PageRef implements Serializable {

    private String pageID;
    private String pageName;

    // public void PageRef(){}

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
