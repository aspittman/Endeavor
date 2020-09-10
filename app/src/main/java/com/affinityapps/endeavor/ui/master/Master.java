package com.affinityapps.endeavor.ui.master;

public class Master {

    private String id, documentTitle, organization, project, date, hours, miles, purchases;


    public Master() {
    }

    public Master(String id, String documentTitle, String organization, String project, String date, String hours, String miles, String purchases) {
        this.id = id;
        this.documentTitle = documentTitle;
        this.organization = organization;
        this.project = project;
        this.date = date;
        this.hours = hours;
        this.miles = miles;
        this.purchases = purchases;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public String getPurchases() {
        return purchases;
    }

    public void setPurchases(String purchases) {
        this.purchases = purchases;
    }
}
