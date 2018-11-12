package com.example.markabhan.donationtracker;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.LoginUtil;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    User user1;

    @Before
    public void setUp() {
        user1 = new User("Markab", "mjh", "SecurePassword", AccountType.USER, true);
    }

    @Test
    public void validLogin() {
        UserDatabase.getInstance().add(user1);
        assertEquals(user1, LoginUtil.checkLogin("mjh", "SecurePassword"));
    }

    @Test
    public void InvalidLogin() {
        UserDatabase.getInstance().add(new User("Markab", "mjh", "SecurePassword", AccountType.USER, true));
        assertEquals(null, LoginUtil.checkLogin("mjh", "NotSecurePassword"));
    }
}