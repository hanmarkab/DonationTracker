package com.example.markabhan.donationtracker.model;

public class User {
    String name;
    String username;
    String password;
    int id;
    AccountType type;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        id = UserDatabase.getInstance().size();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
