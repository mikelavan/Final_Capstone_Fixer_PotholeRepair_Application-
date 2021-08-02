package com.techelevator.model;

import java.sql.Date;
import java.time.LocalDate;

public class PotholeInformation {
    private int potholeId;
    private int severity;
    private LocalDate dateCreated;
    private double longitude;
    private double latitude;
    // private String pictureUrl;
    // private String address;

    public int getPotholeId(int id) {
        return potholeId;
    }

    public void setPotholeId(int potholeId) {
        this.potholeId = potholeId;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public LocalDate getDateCreated(Date date_created) {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
