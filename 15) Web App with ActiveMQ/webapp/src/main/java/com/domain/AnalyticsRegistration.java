package com.domain;

import java.io.Serializable;

public class AnalyticsRegistration implements Serializable {

    private String sessionID;
    private String cookeID;
    private String registrationDate;
    private String firstName;
    private String zip;
    private String className;

    public AnalyticsRegistration(String sessionID, String cookeID, String registrationDate, String firstName, String zip, String className) {
        this.sessionID = sessionID;
        this.cookeID = cookeID;
        this.registrationDate = registrationDate;
        this.firstName = firstName;
        this.zip = zip;
        this.className = className;
    }

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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
