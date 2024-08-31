package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.SHA;
import com.application.javafxtest.data.User;
import com.application.javafxtest.data.UserManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
/**
 * @author Override
 * @since 8/27/2024 @ 19:01
 * @see <a href="https://github.com/TuringProblem/timehack.git">GitHub Reference</a>
 */

public class LoginController {
    @FXML private Label messageLabel;
    @FXML private Hyperlink signUpLink;
    @FXML private Hyperlink forgotPassword;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button signIn;

    private UserManager userManager;
    private SceneManager sceneManager;

    @FXML
    public void initialize() {
       Platform.runLater(()  -> {
           userManager = new UserManager();
           Stage currentStage = (Stage) signIn.getScene().getWindow();
           sceneManager = new SceneManager(currentStage);
           signUpLink.getScene().getWindow();
           signUpLink.setOnAction(event -> sceneManager.switchScene("/com/application/javafxtest/signup.fxml"));
           forgotPassword.setOnAction(event -> sceneManager.switchScene("/com/application/javafxtest/forgot-password.fxml"));
           signIn.setOnAction(event -> handleSignIn());
       });
    }

    private void handleSignIn() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (!userManager.userExists(email)) {
            showAlert("Create an Account", "This email has not been found in our database");
            sceneManager.switchScene("/com/application/javafxtest/signup.fxml");
            return;
        }

        if (userManager.authenticateUser(email, password)) {
            User user = userManager.getUser(email);

            if (user != null && SHA.verifyPassword(password, user.getHashWithSalt())) {
                if (user.isNew()) {
                    sceneManager.switchToNewUserScene();
                } else {
                    sceneManager.switchToExistingUserScene();
                }
            }
        } else {
            showAlert("Authentication Failed", "Invalid email or password.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

