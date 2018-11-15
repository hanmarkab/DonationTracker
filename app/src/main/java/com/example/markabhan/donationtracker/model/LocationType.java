package com.example.markabhan.donationtracker.model;

@SuppressWarnings({"ALL", "unused"})
public enum LocationType {
    DROP_OFF("Drop Off"), STORE("Store"), @SuppressWarnings("unused") WAREHOUSE("Warehouse");

    private String type;


    public String toString(){
        return type;
    }

    public String getLocationType() {
        return this.type;
    }

    LocationType(String type) {
        this.type = type;
    }
}
