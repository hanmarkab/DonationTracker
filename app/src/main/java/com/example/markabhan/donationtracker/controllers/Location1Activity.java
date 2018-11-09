package com.example.markabhan.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.markabhan.donationtracker.model.Location;

public class Location1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location1);

        int index = this.getIntent().getIntExtra("index", -1);
        //noinspection unchecked
        ArrayList<Location> locations = (ArrayList<Location>) this.getIntent().getExtras().getSerializable("list");
        setTitle(locations.get(index).getName());

        TextView locationTextView = findViewById(R.id.locationTextView);

        locationTextView.setText(locations.get(index).toString());
    }
}
