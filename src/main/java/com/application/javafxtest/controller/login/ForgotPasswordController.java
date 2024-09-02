package com.application.javafxtest.controller.login;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.controller.BaseController;
import com.application.javafxtest.controller.utility.LoginUtility;
import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.data.UserManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController extends BaseController {
    @FXML private TextField emailField;
    @FXML private Button submitButton;
    @FXML private Button backToLogin;

    private SceneManager scene;
    private LoginUtility logs = new LoginUtility();
    private UserManager userManager;
    @FXML
    @Override
    public void initialize() {
       super.initialize();
       userManager = new UserManager();
       Platform.runLater(this::setupScene);
    }
    private void setupScene() {
        Stage stage = (Stage) emailField.getScene().getWindow();
        scene = new SceneManager(stage);
        submitButton.setOnAction(actionEvent -> handleVerify());
        backToLogin.setOnAction(actionEvent -> scene.switchScene(actionEvent, scene::signInScreen));
    }

    private void handleVerify() {
        String email = emailField.getText();
        if (!logs.isValidEmail(email)) {
            logs.showErrorAlert("Invalid Email", "Please enter a valid email address.");
        } else if (userManager.userExists(email)){
            logs.showInformationAlert("Email sent", "Please check your inbox for a password reset");
            submitButton.setOnAction(actionEvent -> scene.switchScene(actionEvent, scene::signInScreen));
        } else {
            logs.showErrorAlert("Invalid Email", "That email does not exist, try creating an account");

        }

    }

}
