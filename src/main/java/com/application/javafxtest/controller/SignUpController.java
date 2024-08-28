package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signUpButton;
    @FXML private Button backToLoginButton;
    private UserManager userManager;
    LoginUtility logs = new LoginUtility();
    @FXML
    private void initialize() {
        userManager = new UserManager();
        signUpButton.setOnAction(actionEvent -> handleSignUp());
        backToLoginButton.setOnAction(actionEvent ->  goBackToLogin());
    }
    @FXML
    private void handleSignUp() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPass = confirmPasswordField.getText();

        if (!logs.isValidEmail(email)) {
            logs.showAlert("Invalid Email", "Please enter a valid email addres.");
            return;
        }
        if (!password.equals(confirmPass)) {
            logs.showAlert("Sign Up Failed", "Passwords do not match.");
            return;
        }
        if (userManager.addUser(email, password)) {
            logs.showAlert("Sign Up Successful", "Your account has been created.");
            //need to send the user to the next stages of the application
        } else {
            logs.showAlert("Sign Up Failed", "An account with this email already exists.");
        }
    }

    @FXML
    private void goBackToLogin() {
       SceneManager scene = new SceneManager((Stage) backToLoginButton.getScene().getWindow());
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/hello-view.fxml"));
    }



}
