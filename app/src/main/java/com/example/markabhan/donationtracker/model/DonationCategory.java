package com.example.markabhan.donationtracker.model;

public enum DonationCategory {
    CLOTHING("Clothing"), HAT("Hat"), KITCHEN("Kitchen"), ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"), OTHER("Onther");

    private String type;

    public String toString() {
        return type;
    }

    public String getDonationCategory() {
        return this.type;
    }

    DonationCategory(String type) {
        this.type = type;
    }
}
