package com.happy.entity;

public class User {

    private int userId;
    private String username;
    private String email;
    private String password;
    private String date;
    private String profile;



    public User(int userId, String username, String email, String password, String date) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public User(int userId, String username, String email, String password, String date, String profile) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
        this.profile = profile;
    }

    public User() {

    }

    public User(String username, String email, String password, String date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public User(int userId, String username, String email,String profile) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profile = profile;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // getters and setters


    public String getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
