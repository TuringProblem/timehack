package com.application.javafxtest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Override
 * @since 8/27/2024 @ 19:01
 * @see <a href="https://github.com/TuringProblem/timehack.git">GitHub Reference</a>
 */

public class HelloController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Hyperlink signUpLink;
    @FXML private Hyperlink forgotPassword;
    @FXML private Button signIn;

    private UserManager userManager;
    private SceneManager scene;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            userManager = new UserManager();//still adding
            scene = new SceneManager((Stage) signUpLink.getScene().getWindow());
            signUpLink.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/signup.fxml"));
            forgotPassword.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/forgot-password.fxml"));
        });
    }

    private void handleSignIn() {
        String email = emailField.getText();
        String passworld = passwordField.getText();


    }

}
