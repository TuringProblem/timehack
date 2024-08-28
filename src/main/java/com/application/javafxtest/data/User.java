package com.application.javafxtest.data;

public class User {
    private String email;
    private int[] hashWithSalt;
    private boolean isNew;

    public User(String email, int[] hashWithSalt, boolean isNew) {
        this.email = email;
        this.hashWithSalt = hashWithSalt;
        this.isNew = isNew;
    }

    public String getEmail() { return email; }
    public int[] getHashWithSalt() { return hashWithSalt; }
    public boolean isNew() { return isNew; }

    public void setNew(boolean isNew) { this.isNew = isNew; }
}