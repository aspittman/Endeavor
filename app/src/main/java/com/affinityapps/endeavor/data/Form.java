package com.affinityapps.endeavor.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "volunteer_table")
public class Form {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String documentTitle;

    private String organization;

    private String project;

    private float date;

    private double hours;

    private double miles;

    private double purchases;


    public Form(int id, String documentTitle, String organization, String project, float date, double hours, double miles, double purchases) {
        this.id = id;
        this.documentTitle = documentTitle;
        this.organization = organization;
        this.project = project;
        this.date = date;
        this.hours = hours;
        this.miles = miles;
        this.purchases = purchases;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public String getOrganization() {
        return organization;
    }

    public String getProject() {
        return project;
    }

    public float getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }

    public double getMiles() {
        return miles;
    }

    public double getPurchases() {
        return purchases;
    }
}


