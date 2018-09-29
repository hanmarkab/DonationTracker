package com.example.markabhan.donationtracker.model;

public enum AccountType {
    USER("User"), LOCATION_EMPLOYEE("Location Employee"), ADMIN("Administrator"),
    CORPORATE_ANALYST("Coporate Analyst");

    private String stringRepresentation;

    @Override
    public String toString(){
        return this.stringRepresentation;
    }

    AccountType(String stringRep) {
        this.stringRepresentation = stringRepresentation;
    }
}
