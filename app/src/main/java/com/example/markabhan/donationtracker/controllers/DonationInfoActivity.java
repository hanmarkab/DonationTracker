package com.example.markabhan.donationtracker.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.markabhan.donationtracker.model.Donation;

import java.util.List;

@SuppressWarnings("ALL")
public class DonationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int index = this.getIntent().getIntExtra("index", -1);
        //noinspection unchecked
        List<Donation> donations = (List<Donation>) this.getIntent().getExtras().getSerializable("list");
        setTitle(donations.get(index).getName());

        TextView donationTextView = findViewById(R.id.donationTextView);

        donationTextView.setText(donations.get(index).toString());
    }

}
