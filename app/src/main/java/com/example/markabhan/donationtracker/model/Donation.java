package com.example.markabhan.donationtracker.model;

import java.io.Serializable;

public class Donation implements Serializable{
    private int id;
    private String name;
    private String time;
    private String value;
    private String fullDescription;
    private DonationCategory category;
    private String comment;
    private Location location;

    public Donation(int id, String name, String time, String value, String fullDescription, DonationCategory category, String comment, Location location) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.value = value;
        this.fullDescription = fullDescription;
        this.category = category;
        this.comment = comment;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public DonationCategory getCategory() {
        return category;
    }

    public void setCategory(DonationCategory category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString() {
        return "\nShort Description: " + name + "\n\nTime Stamp: " + time + "\n\nLocation: " + location.getName() + "\n\nEstimated Value: " + value + "\n\nFull Description: " + fullDescription + "\n\nCategory: " + category + "\n\nComments: " + comment;
    }
}
