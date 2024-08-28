package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignUpController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signUpButton;
    @FXML private Button backToLoginButton;
    private UserManager userManager;
    SceneManager scene = new SceneManager();
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
            logs.showErrorAlert("Invalid Email", "Please enter a valid email address.");
            return;
        }
        if (!password.equals(confirmPass)) {
            logs.showErrorAlert("Sign Up Failed", "Passwords do not match.");
            return;
        }
        if (userManager.addUser(email, password)) {
            logs.showInformationAlert("Sign Up Successful", "Your account has been created.");
            scene.switchScene("/com/application/javafxtest/new-users.fxml");
        } else {
            logs.showErrorAlert("Sign Up Failed", "An account with this email already exists.");
            //scene.switchScene();
        }
    }

    @FXML
    private void goBackToLogin() {
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/hello-view.fxml"));
    }



}
