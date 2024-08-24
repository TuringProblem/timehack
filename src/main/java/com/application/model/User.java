package com.application.model;

public class User extends Entity {
    private String password;

    public User(String ownerUsername, String password) {
        super(ownerUsername);
        this.password = password;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    @Override
    public void validate() {
        //this is where the SHA is going to check

    }

}