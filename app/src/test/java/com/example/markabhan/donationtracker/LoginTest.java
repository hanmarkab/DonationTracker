package com.example.markabhan.donationtracker;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.LoginUtil;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private User user1;

    @Before
    public void setUp() {
        UserDatabase.getInstance().clear();
        user1 = new User("Markab", "mjh", "SecurePassword", AccountType.USER, false);
    }

    @Test
    public void validLoginTest() {
        UserDatabase.getInstance().add(user1);
        assertEquals(user1, LoginUtil.checkLogin("mjh", "SecurePassword"));
        assertEquals(true, user1.isActive());
    }

    @Test
    public void InvalidLoginTest() {
        UserDatabase.getInstance().add(user1);
        assertEquals(null, LoginUtil.checkLogin("mjh", "NotSecurePassword"));
        assertEquals(false, user1.isActive());
    }

    @Test
    public void EmptyDatabaseTest() {
        assertEquals(null, LoginUtil.checkLogin("mjh", "password"));
    }

    @Test
    public void NullTest() {
        UserDatabase.getInstance().add(user1);
        assertEquals(null, LoginUtil.checkLogin("mjh", null));
        assertEquals(null, LoginUtil.checkLogin(null, "SecurePassword"));
        assertEquals(null, LoginUtil.checkLogin(null, null));
    }
}