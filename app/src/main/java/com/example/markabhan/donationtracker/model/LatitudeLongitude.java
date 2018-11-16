package com.example.markabhan.donationtracker.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class LatitudeLongitude {

    public static ArrayList<LatLng> createLatLngList(ArrayList<Location> locations) {
        ArrayList<LatLng> listOfLatLng = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            LatLng loc = new LatLng(lon, lat);
            listOfLatLng.add(loc);
        }
        return listOfLatLng;
    }
}
