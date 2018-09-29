package com.example.markabhan.donationtracker.model;

public enum AccountType {
    USER("User"), LOCATION_EMPLOYEE("Location Employee"), ADMIN("Administrator"),
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
    }
}
