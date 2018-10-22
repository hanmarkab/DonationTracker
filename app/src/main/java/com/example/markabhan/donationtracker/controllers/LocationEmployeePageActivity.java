package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;

public class LocationEmployeePageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_employee_page);

        FloatingActionButton fab = findViewById(R.id.fabLocation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationEmployeePageActivity.this, MainActivity.class));
            }
        });

        Button locationListButton = findViewById(R.id.locationButton);
        locationListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationEmployeePageActivity.this, WelcomeActivity.class));
            }
        });

        Button addItemButton = findViewById(R.id.donationButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationEmployeePageActivity.this, DonateActivity.class));
            }
        });
    }

}
