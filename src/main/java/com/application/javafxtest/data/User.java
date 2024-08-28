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
    public boolean getIsNew() { return isNew; }

    public void setEmail(String email) { this.email = email; }
    public void setHashWithSalt(int[] hashWithSalt) { this.hashWithSalt = hashWithSalt; }
    public void setNew(boolean isNew) { this.isNew = isNew; }
}