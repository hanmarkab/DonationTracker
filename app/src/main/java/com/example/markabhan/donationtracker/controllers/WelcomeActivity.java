package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.LocationType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

        TextView locationView = (TextView) findViewById(R.id.textView3);
        Log.d("displayed", "Im here");
        ArrayList<Location> locations = enterLocations();
        String displayed = "";
        for (Location l : locations)
            displayed += (Integer.toString(l.getId())) + ") " + l.getName() + "\n\n";
        Log.d("displayed", locations.size() + "");
        locationView.setText(displayed);

        button1 = (Button) findViewById(R.id.Location1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openLocation1Activity();
            }


        });
    }

        public void openLocation1Activity() {
            Intent intent = new Intent(this, Location1Activity.class);
            startActivity(intent);
        }

        public ArrayList<Location> enterLocations () {
            ArrayList<Location> locationList = new ArrayList<>();
            try {
                BufferedReader scanner = new BufferedReader(new InputStreamReader(getAssets().open("LocationData.csv")));
                scanner.readLine();
                String sc = scanner.readLine();
                while (sc != null) {
                    Log.d("displayed", sc);
                    String[] x = sc.split(",");
                    Location location = new Location();
                    location.setId(Integer.parseInt(x[0]));
                    location.setName(x[1]);
                    location.setLatitude(Double.parseDouble(x[2]));
                    location.setLatitude(Double.parseDouble(x[3]));
                    location.setAddress(x[4]);
                    location.setCity(x[5]);
                    location.setState(x[6]);
                    location.setZip(Integer.parseInt(x[7]));
                    location.setType(x[8]);
                    location.setPhonenum(x[9]);
                    locationList.add(location);
                    sc = scanner.readLine();
                }
                scanner.close();
            } catch (Exception f) {
                Log.d("displayed", "File not read");
            }
            Log.d("displayed", "finsihed");
            return locationList;

        }

    }
