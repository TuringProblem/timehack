package com.application.javafxtest.data;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class EmailService {
    private static final String FROM_EMAIL = "overridezenyte@gmail.com";
    private static final String PASSWORD = "needtoset";

    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


    }

//I still need to finish this
}
