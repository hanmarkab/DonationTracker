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

        /*TextView locationView = findViewById(R.id.textView3);
        Log.d("displayed", "Im here");
        ArrayList<Location> locations = enterLocations();
        String displayed = "";

        for (Location l : locations)
            displayed += (Integer.toString(l.getId())) + ") " + l.getName() + "\n\n";
        Log.d("displayed", locations.size() + "");
        locationView.setText(displayed);*/

        ArrayList<Location> locations = enterLocations();
        String[] locationName = new String[locations.size()];
        for (int i = 0; i < locations.size(); i ++) {
            locationName[i] = locations.get(i).toString();
        }

        ListView listView = findViewById(R.id.LocationsListView);
        ArrayAdapter<String> adpter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, locationName);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WelcomeActivity.this, Location1Activity.class);
                startActivity(intent);

            }
        });


        /*button1 = findViewById(R.id.Location1);

        Button button2 = findViewById(R.id.Location2);
        Button button3 = findViewById(R.id.Location3);

        Button button4 = findViewById(R.id.Location4);
        Button button5 = findViewById(R.id.Location5);
        Button button6 = findViewById(R.id.Location6);




        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        */
    }

        /*
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    openLocation1Activity();
                    break;
                case R.id.button2:
                    openLocation2Activity();
                    break;
                case R.id.button3:
                    openLocation3Activity();
            }
        }


        public void openLocation1Activity() {
            Intent intent = new Intent(this, Location1Activity.class);
            startActivity(intent);
        }

        public void openLocation2Activity() {
            Intent intent = new Intent(this, Location2Activity.class);
            startActivity(intent);
        }
        public void openLocation3Activity() {
            Intent intent = new Intent(this, Location3Activity.class);
            startActivity(intent);
        }

        */


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
