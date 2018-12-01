package com.example.markabhan.donationtracker.model;

import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<User> userList = new ArrayList<>();

    private static final UserDatabase UD = new UserDatabase();

    public static UserDatabase getInstance() {
        return UD;
    }

    public void add(User user) {
        userList.add(user);
    }

    public User get(int index) {
        return userList.get(index);
    }

    public int size() {
        return userList.size();
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void clear() {
        userList = new ArrayList<>();
    }

    public String[] toArray() {
        String[] array = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            array[i] = userList.get(i).getName();
        }
        return array;
    }
}
