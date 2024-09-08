package com.application.javafxtest.controller.login;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.controller.BaseController;
import com.application.javafxtest.controller.utility.LoginUtility;
import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.data.EmailService;
import com.application.javafxtest.data.User;
import com.application.javafxtest.data.UserManager;
import com.application.javafxtest.model.FeatherModule;
import com.application.javafxtest.model.UserPreferences;
import com.application.javafxtest.model.interfaces.SceneManagerFactory;
import jakarta.inject.Inject;
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
    private EmailService emailService;
    private SceneManagerFactory sceneManagerFactory;

    @Inject
    public ForgotPasswordController(UserPreferences userPreferences, SceneManager scene, LoginUtility logs,
                                    UserManager userManager, EmailService emailService, SceneManagerFactory sceneManagerFactory) {
        super(userPreferences);
        this.scene = scene;
        this.logs = logs;
        this.userManager = userManager;
        this.emailService = emailService;
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
        submitButton.setOnAction(actionEvent -> handleSubmit());
        backToLogin.setOnAction(actionEvent -> scene.switchScene(actionEvent, scene::signInScreen));
    }

    @FXML
    private void handleSubmit() {
        String email = emailField.getText();
        if (userManager.userExists(email)) {
            String resetToken = userManager.generateResetToken(email);
            if (resetToken != null) {
                emailService.sendPasswordResetEmail(email, resetToken);
                logs.showInformationAlert("Password Reset", "A password reset link has been sent to your email.");
                scene.signInScreen();
            } else {
                logs.showErrorAlert("Error", "Failed to generate reset token. Please try again.");
            }
        } else {
            logs.showErrorAlert("Error", "No account found with this email address.");
        }
    }
}
