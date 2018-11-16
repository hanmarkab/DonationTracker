package com.example.markabhan.donationtracker;

import com.example.markabhan.donationtracker.model.ListConverter;
import com.example.markabhan.donationtracker.model.Location;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class ListConverterTest {
    private List<Location> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void emptyLocations() {
        list.add(null);
        list.add(null);
        list.add(null);

        String[] expected = new String[list.size()];

        assertEquals(expected, ListConverter.manipulateLocationList(list));
    }

    @Test
    public void lowerCase() {
        Location location1 = new Location();
        Location location2 = new Location();
        Location location3 = new Location();
        location1.setName("lower one");
        location2.setName("lower two");
        location3.setName("lower three");

        list.add(location1);
        list.add(location2);
        list.add(location3);

        String[] expected = new String[list.size()];
        expected[0] = "Lower one";
        expected[1] = "Lower two";
        expected[2] = "Lower three";

        assertEquals(expected, ListConverter.manipulateLocationList(list));
    }

    @Test
    public void upperCase() {
        Location location1 = new Location();
        Location location2 = new Location();
        Location location3 = new Location();
        location1.setName("UPPER ONE");
        location2.setName("UPPER TWO");
        location3.setName("UPPER THREE");

        list.add(location1);
        list.add(location2);
        list.add(location3);

        String[] expected = new String[list.size()];
        expected[0] = "Upper one";
        expected[1] = "Upper two";
        expected[2] = "Upper three";

        assertEquals(expected, ListConverter.manipulateLocationList(list));
    }

    @Test
    public void emptyString() {
        Location location1 = new Location();
        Location location2 = new Location();
        Location location3 = new Location();
        location1.setName("");
        location2.setName("");
        location3.setName("");

        list.add(location1);
        list.add(location2);
        list.add(location3);

        String[] expected = new String[list.size()];

        assertEquals(expected, ListConverter.manipulateLocationList(list));
    }

    @Test
    public void mix() {
        Location location1 = new Location();
        Location location2 = new Location();
        Location location3 = new Location();
        Location location4 = new Location();
        location1.setName("mix one");
        location2.setName("MIX TWO");
        location3.setName("MiX THreE");
        location4.setName("");

        list.add(null);
        list.add(location1);
        list.add(location2);
        list.add(location3);
        list.add(location4);

        String[] expected = new String[list.size()];
        expected[1] = "Mix one";
        expected[2] = "Mix two";
        expected[3] = "Mix three";

        assertEquals(expected, ListConverter.manipulateLocationList(list));
    }

}