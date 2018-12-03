package com.example.markabhan.donationtracker.controllers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.markabhan.donationtracker.model.LocationDatabase;
import com.example.markabhan.donationtracker.model.Location;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    private FusedLocationProviderClient mFusedLocationClient;
    private ArrayList<Location> locations;
    double lat;
    double lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        locations = new ArrayList<>();

        Thread loadData = new Thread(new Runnable() {
            @Override
            public void run() {
                locations = enterLocations();
            }
        });

        loadData.start();

        try{
            loadData.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new LocationDatabase(locations);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }

        googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    /*private ArrayList<Location> enterLocations () {
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

    }*/

    private ArrayList<Location> enterLocations() {
        HttpURLConnection con;
        ArrayList<Location> locationList = new ArrayList<>();
        String url = "http://35.231.154.102/?table=locations";

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

                String line;

                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    String[] s = line.split(",", -1);
                    Location loc = new Location(s[0], Double.valueOf(s[6]), Double.valueOf(s[7]),s[5], s[8], s[4], Integer.valueOf(s[2]), s[3], Integer.valueOf(s[9]), s[1], null, null);
                    locationList.add(loc);
                }
            }

            con.disconnect();

        } catch (IOException e) {
            System.out.println("error "+e);
        }
        return locationList;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(MainActivity.class.getSimpleName(), "Connected to Google Play Services!");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            final Task<android.location.Location> lastLocation  = mFusedLocationClient.getLastLocation();

            lastLocation.addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    if (location != null) {
                        lat = location.getLatitude();
                        lon = location.getLongitude();
                        ArrayList<Location> locations = LocationDatabase.getInstance().getLocationList();
                        Location deviceLoc = new Location("Device Location", lon, lat, null, null, "Georgia", 30, null, 4, null, null, null);
                        locations.add(deviceLoc);
                    }
                }
            });


        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(MainActivity.class.getSimpleName(), "Can't connect to Google Play Services!");
    }

}
