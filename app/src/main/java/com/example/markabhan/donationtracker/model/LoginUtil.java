package com.example.markabhan.donationtracker.model;

public class LoginUtil {
    public static User checkLogin (String username, String password ) {
        for (User user : UserDatabase.getInstance().getUserList()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    user.setActive(true);
                    return user;
                }
            }
        }
        return null;
    }
}
