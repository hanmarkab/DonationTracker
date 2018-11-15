package com.example.markabhan.donationtracker.model;
import java.util.ArrayList;

public class LocationDatabase {

    private static ArrayList<Location> locationList = new ArrayList<>();

    public LocationDatabase(ArrayList<Location> locationList) {
        LocationDatabase.locationList = locationList;
    }

    private static final LocationDatabase LD = new LocationDatabase(locationList);

    public static LocationDatabase getInstance() {
        return LD;
    }

    @SuppressWarnings("unused")
    public void add(Location location) {
        locationList.add(location);
    }

    @SuppressWarnings("unused")
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
