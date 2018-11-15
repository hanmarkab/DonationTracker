package com.example.markabhan.donationtracker.model;
import java.util.List;

public class ListConverter {
    public static String[] manipulateLocationList(List<Location> fullLocationArray) {
        String[] locationArray = new String[fullLocationArray.size()];

        for (int i = 0; i < locationArray.length; i++) {
            if (fullLocationArray.get(i) != null) {
                if (!fullLocationArray.get(i).getName().equals("")) {
                    StringBuilder tempString = new StringBuilder(fullLocationArray.get(i).getName().toLowerCase());
                    tempString.setCharAt(0, Character.toUpperCase(tempString.charAt(0)));
                    locationArray[i] = tempString.toString();
                }
            }
        }

        return locationArray;
    }

}
