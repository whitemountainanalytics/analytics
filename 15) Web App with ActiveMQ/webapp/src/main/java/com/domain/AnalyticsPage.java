package com.domain;

import java.io.Serializable;

public class AnalyticsPage implements Serializable {

    public AnalyticsPage(String sessionID, String cookeID, String viewDate, String pageID, String pageName) {
        this.sessionID = sessionID;
        this.cookeID = cookeID;
        this.viewDate = viewDate;
        this.pageID = pageID;
        this.pageName = pageName;
    }

    public AnalyticsPage(){
    }

    private String sessionID;
    private String cookeID;
    private String viewDate;
    private String pageID;
    private String pageName;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getCookeID() {
        return cookeID;
    }

    public void setCookeID(String cookeID) {
        this.cookeID = cookeID;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }

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
