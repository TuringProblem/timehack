package com.application.javafxtest.data;

public class User {
    private String email;
    private String hashWithSalt;
    private boolean isNew;

    public User(String email, String hashWithSalt, boolean isNew) {
        this.email = email;
        this.hashWithSalt = hashWithSalt;
        this.isNew = isNew;
    }

    public boolean hasEmail() { return this.email != null;}
    public String getEmail() { return email; }
    public String getHashWithSalt() { return hashWithSalt; }
    public boolean isNew() { return isNew; }

    public void setNew(boolean isNew) { this.isNew = isNew; }
}