package com.application.javafxtest.data;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;


public class EmailService {
    private static final String FROM_EMAIL = "overridezenyte@gmail.com";
    private static final String PASSWORD = "Ligma6969";

    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        //need to add Session for javax.mail api
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Password Reset Request");

            String resetLink = "https://localhost:8080/reset-password?token=" + resetToken;
            String htmlContent = "<h1>Password Reset Requeset</h1>"
                    + "<p> Click the link below to reset your password:</p>"
                    + "<a href='" + resetLink + "'>Reset Password</a>";
            message.setContent(htmlContent, "text/html");
            Transport.send(message);
            System.out.printf("Password reset email sent successfully to %s", toEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}