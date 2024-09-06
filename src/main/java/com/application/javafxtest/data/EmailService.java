package com.application.javafxtest.data;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;


public class EmailService extends Message {
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
            protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(FROM_EMAIL, PASSWORD); }
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

    @Override
    public Address[] getFrom() throws MessagingException {
        return new Address[0];
    }

    @Override
    public void setFrom() throws MessagingException {

    }

    @Override
    public void setFrom(Address address) throws MessagingException {

    }

    @Override
    public void addFrom(Address[] addresses) throws MessagingException {

    }

    @Override
    public Address[] getRecipients(RecipientType recipientType) throws MessagingException {
        return new Address[0];
    }

    @Override
    public void setRecipients(RecipientType recipientType, Address[] addresses) throws MessagingException {

    }

    @Override
    public void addRecipients(RecipientType recipientType, Address[] addresses) throws MessagingException {

    }

    @Override
    public String getSubject() throws MessagingException {
        return null;
    }

    @Override
    public void setSubject(String s) throws MessagingException {

    }

    @Override
    public Date getSentDate() throws MessagingException {
        return null;
    }

    @Override
    public void setSentDate(Date date) throws MessagingException {

    }

    @Override
    public Date getReceivedDate() throws MessagingException {
        return null;
    }

    @Override
    public Flags getFlags() throws MessagingException {
        return null;
    }

    @Override
    public void setFlags(Flags flags, boolean b) throws MessagingException {

    }

    @Override
    public Message reply(boolean b) throws MessagingException {
        return null;
    }

    @Override
    public void saveChanges() throws MessagingException {

    }

    @Override
    public int getSize() throws MessagingException {
        return 0;
    }

    @Override
    public int getLineCount() throws MessagingException {
        return 0;
    }

    @Override
    public String getContentType() throws MessagingException {
        return null;
    }

    @Override
    public boolean isMimeType(String s) throws MessagingException {
        return false;
    }

    @Override
    public String getDisposition() throws MessagingException {
        return null;
    }

    @Override
    public void setDisposition(String s) throws MessagingException {

    }

    @Override
    public String getDescription() throws MessagingException {
        return null;
    }

    @Override
    public void setDescription(String s) throws MessagingException {

    }

    @Override
    public String getFileName() throws MessagingException {
        return null;
    }

    @Override
    public void setFileName(String s) throws MessagingException {

    }

    @Override
    public InputStream getInputStream() throws IOException, MessagingException {
        return null;
    }

    @Override
    public DataHandler getDataHandler() throws MessagingException {
        return null;
    }

    @Override
    public Object getContent() throws IOException, MessagingException {
        return null;
    }

    @Override
    public void setDataHandler(DataHandler dataHandler) throws MessagingException {

    }

    @Override
    public void setContent(Object o, String s) throws MessagingException {

    }

    @Override
    public void setText(String s) throws MessagingException {

    }

    @Override
    public void setContent(Multipart multipart) throws MessagingException {

    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {

    }

    @Override
    public String[] getHeader(String s) throws MessagingException {
        return new String[0];
    }

    @Override
    public void setHeader(String s, String s1) throws MessagingException {

    }

    @Override
    public void addHeader(String s, String s1) throws MessagingException {

    }

    @Override
    public void removeHeader(String s) throws MessagingException {

    }

    @Override
    public Enumeration getAllHeaders() throws MessagingException {
        return null;
    }

    @Override
    public Enumeration getMatchingHeaders(String[] strings) throws MessagingException {
        return null;
    }

    @Override
    public Enumeration getNonMatchingHeaders(String[] strings) throws MessagingException {
        return null;
    }

//I still need to finish this
}
