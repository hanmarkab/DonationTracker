package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.Location;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;
import com.example.markabhan.donationtracker.model.LocationDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mRegistrationFormView;
    private AutoCompleteTextView mNameView;
    private Spinner mAccountTypeSpinner;
    private Spinner mLocationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mNameView = (AutoCompleteTextView) findViewById(R.id.name);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);

        mAccountTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AccountType.values());
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountTypeSpinner.setAdapter(accountTypeAdapter);

        mLocationSpinner = findViewById(R.id.locationSpinner);

        final List<Location> fullLocationArray = enterLocations();
        String[] locationArray = new String[fullLocationArray.size()];

        for (int i = 0; i < locationArray.length; i++) {
            StringBuilder tempString = new StringBuilder(fullLocationArray.get(i).getName().toLowerCase());
            System.out.println(tempString);
            tempString.setCharAt(0, Character.toUpperCase(tempString.charAt(0)));
            System.out.println(tempString);
            locationArray[i] = tempString.toString();
        }
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


        final Button mRegisterButton = (Button) findViewById(R.id.register_button);
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
        mRegistrationFormView = findViewById(R.id.registration_form);
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