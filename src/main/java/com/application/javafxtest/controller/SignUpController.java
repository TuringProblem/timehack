package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        if (!password.equals(confirmPass)) {
            showAlert("Sign Up Failed", "Passwords do not match.");
            return;
        }
        if (userManager.addUser(email, password)) {
            showAlert("Sign Up Successful", "Your account has been created.");
            //need to send the user to the next stages of the application
        } else {
            showAlert("Sign Up Failed", "An account with this email already exists.");
        }
    }
    @FXML
    private void goBackToLogin() {
       SceneManager scene = new SceneManager((Stage) backToLoginButton.getScene().getWindow());
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/hello-view.fxml"));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
