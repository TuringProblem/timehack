package com.application.javafxtest.controller.login;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.controller.BaseController;
import com.application.javafxtest.controller.utility.LoginUtility;
import com.application.javafxtest.data.UserManager;
import com.application.javafxtest.model.FeatherModule;
import com.application.javafxtest.model.UserPreferences;
import com.application.javafxtest.model.interfaces.SceneManagerFactory;
import jakarta.inject.Inject;
import javafx.application.Platform;
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

public class SignUpController extends BaseController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signUpButton;
    @FXML private Button backToLoginButton;
    public SceneManager scene;
    public LoginUtility logs = new LoginUtility();
    private UserManager userManager;
    private SceneManagerFactory sceneManagerFactory;

    @Inject
    public SignUpController(UserPreferences userPreferences, LoginUtility logs, UserManager userManager, SceneManagerFactory sceneManagerFactory) {
        super(userPreferences);
        this.logs = logs;
        this. userManager = userManager;
        this.sceneManagerFactory = sceneManagerFactory;
    }

    @FXML
    @Override
    public void initialize() {
        super.initialize();
        Platform.runLater(this::setupScene);
    }

    private void setupScene() {
        Stage stage = (Stage) emailField.getScene().getWindow();
        scene = this.sceneManagerFactory.create(stage);
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene(actionEvent, scene::signInScreen));
        signUpButton.setOnAction(actionEvent -> handleSignUp());
    }

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
            scene.close();
            Platform.runLater(() -> {
                scene.signInScreen();
            });
        } else {
            logs.showErrorAlert("Sign Up Failed", "An account with this email already exists.");
            goBackToLogin();
        }
    }
    @FXML
    private void goBackToLogin() {
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/login/hello-view.fxml"));
    }
}
