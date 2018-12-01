package com.example.markabhan.donationtracker.model;

public class User {
    private String name;
    private String username;
    private String password;
    private int id;
    private AccountType type;
    private Location userLocation;
    private boolean active;
    private boolean locked;

    public User(String name, String username, String password, AccountType type, boolean active) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
        id = UserDatabase.getInstance().size();
        this.active = active;
        locked = false;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
