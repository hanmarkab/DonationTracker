package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.DonationCategory;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.Donation;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;

public class EditDonationActivity extends AppCompatActivity {

    private EditText descriptionText;
    private EditText timeText;
    private EditText valueText;
    private EditText fullDescriptionText;
    private Spinner categorySpinner;
    private EditText commentsText;

    private User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_edit_donation);

        descriptionText = findViewById(R.id.description_input);
        timeText = findViewById(R.id.time_stamp_input);
        valueText = findViewById(R.id.estimated_value_input);
        fullDescriptionText =  findViewById(R.id.full_description_input);
        categorySpinner = findViewById(R.id.category_spinner);
        commentsText = findViewById(R.id.comment_input);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DonationCategory.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        for (User user : UserDatabase.getInstance().getUserList()) {
            if (user.isActive()) {
                activeUser = user;
            }
        }

        final Button mAddButton = findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Donation newDonation = new Donation(0, descriptionText.getText().toString(), timeText.getText().toString(),
                        valueText.getText().toString(), fullDescriptionText.getText().toString()
                        ,(DonationCategory) categorySpinner.getSelectedItem(), commentsText.getText().toString() ,activeUser.getUserLocation());
                activeUser.getUserLocation().getDonationList().add(newDonation);
                newDonation.setId(activeUser.getUserLocation().getDonationList().size());
                startActivity(new Intent(EditDonationActivity.this, DonateActivity.class));
            }
        });



    }
}
