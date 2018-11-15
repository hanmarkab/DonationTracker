package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import com.example.markabhan.donationtracker.model.Donation;
import com.example.markabhan.donationtracker.model.DonationConverter;
import com.example.markabhan.donationtracker.model.UserDatabase;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.User;

public class DonateActivity extends AppCompatActivity {

    //private Location userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Location userLocation = null;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Creating a new Donation", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), EditDonationActivity.class);
                startActivity(intent);
            }
        });

        for (User user : UserDatabase.getInstance().getUserList()) {
            if (user.isActive()) {
                userLocation = user.getUserLocation();
            }
        }


        final ArrayList<Donation> donationArrayList = Objects.requireNonNull(userLocation).getDonationList();
        String[] donationName = DonationConverter.convert(donationArrayList);

        ListView listView = findViewById(R.id.DonationsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donationName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DonateActivity.this, DonationInfoActivity.class);

                intent.putExtra("index", position);
                intent.putExtra("list", donationArrayList);

                startActivity(intent);
            }
        });

    }



}
