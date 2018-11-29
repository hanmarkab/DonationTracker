package com.example.markabhan.donationtracker;

import com.example.markabhan.donationtracker.model.DonationCategory;
import com.example.markabhan.donationtracker.model.DonationConverter;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.Donation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class DonationConverterTest {
    private ArrayList<Donation> donationList;

    @Before
    public void setUp() {
        donationList = new ArrayList<>();
    }

    @Test
    public void emptyDonations() {
        donationList.add(null);
        donationList.add(null);
        donationList.add(null);
        donationList.add(null);

        String[] expected = new String[donationList.size()];

        assertEquals(expected, DonationConverter.convert(donationList));
    }

    @Test
    public void checkVariousDonations() {
        Donation donation1 = new Donation(0, "Stuffed Bear", "", "", "",
                DonationCategory.OTHER, "", new Location());
        Donation donation2 = new Donation(0, "Wrangler Jeans", "", "", "",
                DonationCategory.CLOTHING, "", new Location());
        Donation donation3 = new Donation(0, "Dishes", "", "", "",
                DonationCategory.KITCHEN, "", new Location());
        Donation donation4 = new Donation(0, "Laptop", "", "", "",
                DonationCategory.ELECTRONICS, "", new Location());

        donationList.add(donation1);
        donationList.add(donation2);
        donationList.add(donation3);
        donationList.add(donation4);

        String[] expected = new String[donationList.size()];
        expected[0] = "Stuffed Bear";
        expected[1] = "Wrangler Jeans";
        expected[2] = "Dishes";
        expected[3] = "Laptop";

        assertEquals(expected, DonationConverter.convert(donationList));
    }

    @Test
    public void checkSameDonationItems() {
        Donation donation1 = new Donation(0, "Hanger", "", "", "",
                DonationCategory.CLOTHING, "", new Location());
        Donation donation2 = new Donation(0, "Hanger", "", "", "",
                DonationCategory.CLOTHING, "", new Location());
        Donation donation3 = new Donation(0, "Hanger", "", "", "",
                DonationCategory.CLOTHING, "", new Location());
        Donation donation4 = new Donation(0, "Hanger", "", "", "",
                DonationCategory.CLOTHING, "", new Location());

        donationList.add(donation1);
        donationList.add(donation2);
        donationList.add(donation3);
        donationList.add(donation4);

        String[] expected = new String[donationList.size()];
        expected[0] = "Hanger";
        expected[1] = "Hanger";
        expected[2] = "Hanger";
        expected[3] = "Hanger";

        assertEquals(expected, DonationConverter.convert(donationList));
    }


}

