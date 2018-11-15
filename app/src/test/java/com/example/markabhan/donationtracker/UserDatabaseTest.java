package com.example.markabhan.donationtracker;

import com.example.markabhan.donationtracker.model.AccountType;
import com.example.markabhan.donationtracker.model.User;
import com.example.markabhan.donationtracker.model.UserDatabase;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserDatabaseTest {
    @Test
    public void addition_isCorrect() {
        User user1 = new User("Heramb", "htamhankar", "pass", AccountType.LOCATION_EMPLOYEE, true);
        User user2 = new User("Ahan", "ahanu", "sums", AccountType.ADMIN, true);

        UserDatabase ud = UserDatabase.getInstance();

        ud.add(user1);
        ud.add(user2);
        ArrayList<User> expectedList = new ArrayList<>();

        expectedList.add(user1);
        expectedList.add(user2);

        ArrayList<User> testList = ud.getUserList();

        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), testList.get(i));
        }
    }
}