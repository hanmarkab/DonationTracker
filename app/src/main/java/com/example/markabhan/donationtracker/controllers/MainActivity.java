package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.markabhan.donationtracker.model.LocationDatabase;
import com.example.markabhan.donationtracker.model.UserDatabase;
import com.example.markabhan.donationtracker.model.Location;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabase userData = new UserDatabase();

        Button loginScreenButton = findViewById(R.id.go_to_login_button);
        loginScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        Button registerScreenButton = findViewById(R.id.go_to_registration_button);
        registerScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        new LocationDatabase(enterLocations());
    }

    private ArrayList<Location> enterLocations () {
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
                location.setLongitude(Double.parseDouble(x[3]));
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
        Log.d("displayed", "finished");
        return locationList;

    }
}
