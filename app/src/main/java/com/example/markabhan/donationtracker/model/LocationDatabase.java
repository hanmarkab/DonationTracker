package com.example.markabhan.donationtracker.model;
import java.util.ArrayList;

public class LocationDatabase {
    private ArrayList<Location> locationList = new ArrayList<>();

    private static final LocationDatabase LD = new LocationDatabase();

    public static LocationDatabase getInstance() {
        return LD;
    }

    public void add(Location location) {
        locationList.add(location);
    }

    public Location get(int index) {
        return locationList.get(index);
    }

    public int size() {
        return locationList.size();
    }

    public ArrayList<Location> getLocationList() {
        return locationList;
    }
}
