package com.application.javafxtest.controller;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUtility {

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
