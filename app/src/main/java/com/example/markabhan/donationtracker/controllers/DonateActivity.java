package com.example.markabhan.donationtracker.controllers;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.Donation;
import com.example.markabhan.donationtracker.model.LocationDatabase;
import com.example.markabhan.donationtracker.model.UserDatabase;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.User;

public class DonateActivity extends AppCompatActivity {

    private User activeUser;
    private Location userLocation;
    private SearchView search;
    private Spinner locationTypeSpinner;
    private Spinner searchTypeSpinner;
    private List list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Creating a new Donation", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), EditDonationActivity.class);
                startActivity(intent);
            }
        });

        //Create Location selection spinner
        Spinner locationTypeSpinner = (Spinner) findViewById(R.id.locationSelectSpinner);

        List<Location> fullLocationArray = LocationDatabase.getInstance().getLocationList();
        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fullLocationArray);
        locationTypeAdapter.add("All Locations");
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        //Create Search Type Spinner
        Spinner searchTypeSpinner = (Spinner) findViewById(R.id.typeSelectSpinner);

        ArrayList<String> fullTypesArray = new ArrayList<>();
        fullTypesArray.add("Category");
        fullTypesArray.add("Name");
        ArrayAdapter<String> searchTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fullTypesArray);
        searchTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchTypeSpinner.setAdapter(searchTypeAdapter);

        //code the search itself

        SearchView search = (SearchView) findViewById(R.id.search_bar);




        for (User user : UserDatabase.getInstance().getUserList()) {
            if (user.isActive()) {
                activeUser = user;
                userLocation = user.getUserLocation();
            }
        }


        final ArrayList<Donation> donationArrayList = userLocation.getDonationList();
        String[] donationName = new String[donationArrayList.size()];

        for (int i = 0; i < donationArrayList.size(); i++) {
            donationName[i] = donationArrayList.get(i).getName();
        }

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
