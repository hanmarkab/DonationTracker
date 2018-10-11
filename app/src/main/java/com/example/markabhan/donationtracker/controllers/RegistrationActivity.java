package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mRegistrationFormView;
    private AutoCompleteTextView mNameView;
    private Spinner mAccountTypeSpinner;

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

        final Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User(mNameView.getText().toString(), mUsernameView.getText().toString(),
                        mPasswordView.getText().toString());
                UserDatabase.getInstance().add(newUser);
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
        mRegistrationFormView = findViewById(R.id.registration_form);
    }


}