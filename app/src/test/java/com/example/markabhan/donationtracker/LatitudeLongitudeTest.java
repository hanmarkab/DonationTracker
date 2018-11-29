package com.example.markabhan.donationtracker;

import com.example.markabhan.donationtracker.model.LatitudeLongitude;
import com.example.markabhan.donationtracker.model.Location;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class LatitudeLongitudeTest {

    private ArrayList<Location> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void addLatLng() {
        LatLng a = new LatLng(-84.37742, 33.75416);
        LatLng b = new LatLng(-84.43971, 33.73182);
        LatLng c = new LatLng(-84.41853, 33.70866);
        LatLng d = new LatLng(-84.25537, 33.80129);
        LatLng e = new LatLng(-84.2521, 33.71747);
        LatLng f = new LatLng(-84.3688, 33.96921);

        ArrayList<LatLng> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        expected.add(d);
        expected.add(e);
        expected.add(f);

        Location loc1 = new Location("AFD Station 4", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);
        Location loc2 = new Location("Boys and Girls Club", -84.43971, 33.73182, null, null, null, 40, null, 4, null, null, null);
        Location loc3 = new Location("Pathway Upper Room Christian Ministries", -84.41853, 33.70866, null, null, null, 40, null, 4, null, null, null);
        Location loc4 = new Location("Pavilion of Hope Inc", -84.25537, 33.80129, null, null, null, 40, null, 4, null, null, null);
        Location loc5 = new Location("D&D Convenience Store", -84.2521, 33.71747, null, null, null, 40, null, 4, null, null, null);
        Location loc6 = new Location("Keep North Fulton Beautiful", -84.3688, 33.96921, null, null, null, 40, null, 4, null, null, null);

        list.add(loc1);
        list.add(loc2);
        list.add(loc3);
        list.add(loc4);
        list.add(loc5);
        list.add(loc6);

        assertEquals(expected, LatitudeLongitude.createLatLngList(list));
    }

    @Test
    public void addSameValues() {
        LatLng a = new LatLng(-84.37742, 33.75416);
        LatLng b = new LatLng(-84.37742, 33.75416);
        LatLng c = new LatLng(-84.37742, 33.75416);
        LatLng d = new LatLng(-84.37742, 33.75416);
        LatLng e = new LatLng(-84.37742, 33.75416);
        LatLng f = new LatLng(-84.37742, 33.75416);

        ArrayList<LatLng> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        expected.add(d);
        expected.add(e);
        expected.add(f);

        Location loc1 = new Location("name", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);
        Location loc2 = new Location("name", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);
        Location loc3 = new Location("name", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);
        Location loc4 = new Location("name", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);
        Location loc5 = new Location("name", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);
        Location loc6 = new Location("name", -84.37742, 33.75416, null, null, null, 40, null, 4, null, null, null);

        list.add(loc1);
        list.add(loc2);
        list.add(loc3);
        list.add(loc4);
        list.add(loc5);
        list.add(loc6);

        assertEquals(expected, LatitudeLongitude.createLatLngList(list));
    }


}
