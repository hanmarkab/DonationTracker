package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import android.widget.ListView;

public class WelcomeActivity extends AppCompatActivity {
    public ArrayList<Location> locationsList;



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

        ArrayList<Location> locations = enterLocations();
        String[] locationName = new String[locations.size()];
        for (int i = 0; i < locations.size(); i ++) {
            locationName[i] = locations.get(i).getName();
        }

        final ArrayList<Location> locationIntent = locations;

        ListView listView = findViewById(R.id.LocationsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, locationName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WelcomeActivity.this, Location1Activity.class);

                intent.putExtra("index", position);
                intent.putExtra("list", locationIntent);

                startActivity(intent);

            }
        });




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
