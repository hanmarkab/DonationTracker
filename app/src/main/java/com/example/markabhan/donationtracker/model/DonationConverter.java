package com.example.markabhan.donationtracker.model;

import java.util.ArrayList;

public class DonationConverter {
    public static String[] convert(ArrayList<Donation> donationArrayList) {
        String[] donationName = new String[donationArrayList.size()];

        for (int i = 0; i < donationArrayList.size(); i++) {
            donationName[i] = donationArrayList.get(i).getName();
        }

        return donationName;
    }
}
