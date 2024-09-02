package com.application.javafxtest.controller.login;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.controller.BaseController;
import com.application.javafxtest.data.SHA;
import com.application.javafxtest.data.User;
import com.application.javafxtest.data.UserManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * @author Override
 * @since 8/27/2024 @ 19:01
 * @see <a href="https://github.com/TuringProblem/timehack.git">GitHub Reference</a>
 */
public class LoginController extends BaseController {
    @FXML private Label messageLabel;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button signIn;
    @FXML private Hyperlink signUpLink;
    @FXML private Hyperlink forgotPassword;

    private UserManager userManager;
    private SceneManager sceneManager;

    @FXML
    @Override
    public void initialize() {
        super.initialize();
        userManager = new UserManager();
        Platform.runLater(this::setupSceneManager);
    }
    private void setupSceneManager() {
        Stage stage = (Stage) emailField.getScene().getWindow();
        sceneManager = new SceneManager(stage);
        signIn.setOnAction(actionEvent -> sceneManager.switchScene(actionEvent, this::handleSignIn));
        signUpLink.setOnAction(actionEvent -> sceneManager.switchScene(actionEvent, sceneManager::switchToSignUpScene));
        forgotPassword.setOnAction(actionEvent -> sceneManager.switchScene(actionEvent, sceneManager::switchToForgotPasswordScene));
    }

    private void handleSignIn() {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (!userManager.userExists(email)) {
            showAlert("Create an Account", "This email has not been found in our database");
            sceneManager.switchScene("/com/application/javafxtest/login/signup.fxml");
            return;
        }

        if (userManager.authenticateUser(email, password)) {
            User user = userManager.getUser(email);

            if (user != null && SHA.verifyPassword(password, user.getHashWithSalt())) {
                if (user.isNew()) {
                    sceneManager.switchToNewUserScene();
                } else {
                    sceneManager.switchToHomePage();
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

