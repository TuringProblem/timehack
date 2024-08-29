package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Override
 * @since 08/28/2024 @02:47
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class SignUpController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signUpButton;
    @FXML private Button backToLoginButton;
    private UserManager userManager;
    public SceneManager scene = new SceneManager();
    public LoginUtility logs = new LoginUtility();
    @FXML
    private void initialize() {
        userManager = new UserManager();
        scene = new SceneManager((Stage) signUpButton.getScene().getWindow());

        signUpButton.setOnAction(actionEvent -> handleSignUp());
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/applicatoin/javafxtest/hello-view.fxml"));
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
            goBackToLogin();
        }
    }
    @FXML
    private void goBackToLogin() {
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/hello-view.fxml"));
    }
}
