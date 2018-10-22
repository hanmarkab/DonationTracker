package com.example.markabhan.donationtracker.model;

public enum DonationCategory {
    /*USER("User"), LOCATION_EMPLOYEE("Location Employee"), ADMIN("Administrator"),
    CORPORATE_ANALYST("Corporate Analyst");

    private String type;


    public String toString(){
        return type;
    }

    public String getAccountType() {
        return this.type;
    }

    AccountType(String type) {
        this.type = type;

        Clothing, Hat, Kitchen, Electronics, Household, Other
    }*/
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
