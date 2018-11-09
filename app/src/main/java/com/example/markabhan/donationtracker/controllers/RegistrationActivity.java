package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;
import com.example.markabhan.donationtracker.model.LocationDatabase;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private AutoCompleteTextView mNameView;
    private Spinner mAccountTypeSpinner;
    private Spinner mLocationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mNameView = findViewById(R.id.name);

        mUsernameView = findViewById(R.id.username);

        mPasswordView = findViewById(R.id.password);

        mAccountTypeSpinner = findViewById(R.id.spinner);

        //noinspection unchecked,unchecked
        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AccountType.values());
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountTypeSpinner.setAdapter(accountTypeAdapter);

        mLocationSpinner = findViewById(R.id.locationSpinner);

        final List<Location> fullLocationArray = LocationDatabase.getInstance().getLocationList();
        /*String[] locationArray = new String[fullLocationArray.size()];

        for (int i = 0; i < locationArray.length; i++) {
            StringBuilder tempString = new StringBuilder(fullLocationArray.get(i).getName().toLowerCase());
            System.out.println(tempString);
            tempString.setCharAt(0, Character.toUpperCase(tempString.charAt(0)));
            System.out.println(tempString);
            locationArray[i] = tempString.toString();
        }*/

        String[] locationArray = manipulateLocationList(fullLocationArray);

        //noinspection unchecked,unchecked
        ArrayAdapter<String> locationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locationArray);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocationSpinner.setAdapter(locationAdapter);

        mLocationSpinner.setEnabled(false);

        mAccountTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mAccountTypeSpinner.getSelectedItem().toString().equals(AccountType.LOCATION_EMPLOYEE.toString())) {
                    mLocationSpinner.setEnabled(true);
                } else {
                    mLocationSpinner.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        final Button mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User(mNameView.getText().toString(), mUsernameView.getText().toString(),
                        mPasswordView.getText().toString(), (AccountType) mAccountTypeSpinner.getSelectedItem(), false);
                UserDatabase.getInstance().add(newUser);
                if (mLocationSpinner.isEnabled()) {
                    int locationIndex = mLocationSpinner.getSelectedItemPosition();
                    newUser.setUserLocation(fullLocationArray.get(locationIndex));
                }
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    public String[] manipulateLocationList(List<Location> fullLocationArray) {
        String[] locationArray = new String[fullLocationArray.size()];

        for (int i = 0; i < locationArray.length; i++) {
            StringBuilder tempString = new StringBuilder(fullLocationArray.get(i).getName().toLowerCase());
            System.out.println(tempString);
            tempString.setCharAt(0, Character.toUpperCase(tempString.charAt(0)));
            System.out.println(tempString);
            locationArray[i] = tempString.toString();
        }

        return locationArray;
    }
}