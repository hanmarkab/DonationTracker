package com.example.markabhan.donationtracker.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.markabhan.donationtracker.model.LatitudeLongitude;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.LocationDatabase;
import com.example.markabhan.donationtracker.model.UserDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

@SuppressWarnings("ALL")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static ArrayList<LatLng> listofLatLng;
    private GoogleMap mMap;
    private DirectionsResult result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final ArrayList<Location> fullLocationArray = LocationDatabase.getInstance().getLocationList();
        ArrayList<LatLng> locationArray = LatitudeLongitude.createLatLngList(fullLocationArray);

        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setQueryRateLimit(3)
                .setApiKey("AIzaSyDijBk4a1xfMZfo4rSWjDl4y9teeknfVWM")
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);

        DateTime now = new DateTime();
        int userId = this.getIntent().getIntExtra("User", -1);
        Location userLocation = UserDatabase.getInstance().get(userId).getUserLocation();
        double userLat = userLocation.getLatitude();
        double userLon = userLocation.getLongitude();

        try {
            result = DirectionsApi.newRequest(geoApiContext)
                    .mode(TravelMode.DRIVING).origin(new com.google.maps.model.LatLng(userLat, userLon))
                    .destination(new com.google.maps.model.LatLng(-84.37742, 33.75416)).departureTime(now)
                    .await();


        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

         //Trying to get location lat, long data
        ArrayList<Location> locations = LocationDatabase.getInstance().getLocationList();


        for (int i = 0; i < locations.size(); i++) {
            Location location = locations.get(i);
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            System.out.println("latitude: " + lat);
            System.out.println("longitude" + lon);
            LatLng loc = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(loc).title(location.getName() + " " + location.getPhonenum()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }


    }


}
