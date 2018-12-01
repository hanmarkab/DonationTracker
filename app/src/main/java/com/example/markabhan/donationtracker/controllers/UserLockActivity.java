package com.example.markabhan.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;

public class UserLockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lock);

        int index = this.getIntent().getIntExtra("index", -1);
        final User currentUser = UserDatabase.getInstance().get(index);

        Button lockButton = findViewById(R.id.lock_button);
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser.setLocked(true);
                startActivity(new Intent(UserLockActivity.this, UserListActivity.class));
            }
        });

        Button unlockButton = findViewById(R.id.unlock_button);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser.setLocked(false);
                startActivity(new Intent(UserLockActivity.this, UserListActivity.class));
            }
        });
    }
}
