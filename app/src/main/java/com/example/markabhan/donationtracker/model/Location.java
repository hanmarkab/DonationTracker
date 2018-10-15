package com.example.markabhan.donationtracker.model;

import java.io.Serializable;

public class Location implements Serializable {
    String name;
    double longitude;
    double latitude;
    String address;
    String city;
    String state;
    int zip;
    String type;
    String phonenum;
    int id;

    public Location() {
        this(null,0.0,0.0,null,null,null,0,null,0,null);
    }

    public Location(String name, double longitude, double latitude, String address, String city, String state, int zip, String phonenum, int id, String type) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.id = id;
        this.type = type;
        id = LocationDatabase.getInstance().size();
    }

    public String getName() {
        return name;
    }

    public String getLongitude() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    /*end of getters beginning of setters*/

    public void setName(String name) {
        this.name = name;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "\nName: " + name + "\n\nLongitude: " + Double.toString(longitude) + "\n\nLatitude: " + Double.toString(latitude)
                + "\n\nAddress: " + address + "\n\nState: " + state + "\n\nZip Code: " + Integer.toString(zip) + "\n\nPhone Number: "
                + phonenum + "\n\nLocation Type: " + type.toString();
    }
}
