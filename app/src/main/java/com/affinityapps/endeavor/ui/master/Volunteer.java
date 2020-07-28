package com.affinityapps.endeavor.ui.master;

public class Volunteer {

    private String documentTitle, organization, project, date;
    private double hours, miles, purchases;


    public Volunteer() {};

    public Volunteer(String documentTitle, String organization, String project, String date, double hours, double miles, double purchases) {
        this.documentTitle = documentTitle;
        this.organization = organization;
        this.project = project;
        this.date = date;
        this.hours = hours;
        this.miles = miles;
        this.purchases = purchases;
    }


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

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getMiles() {
        return miles;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public double getPurchases() {
        return purchases;
    }

    public void setPurchases(double purchases) {
        this.purchases = purchases;
    }
}
