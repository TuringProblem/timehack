package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.User;
import com.application.javafxtest.model.UserPreferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class NewUserSetupController {
    @FXML private Button googleButton;
    @FXML private Button appleButton;
    @FXML private Button outlookButton;
    @FXML private Button createOwnButton;
    @FXML private ToggleButton darkModeToggle;
    private SceneManager sceneManager;
    private UserPreferences userPrefs;
    @FXML
    public void initialize() {
        sceneManager = new SceneManager();
        userPrefs = UserPreferences.getInstance();
    }

    @FXML
    private void handleGoogleCalendar() { goToNextStage(); }
    @FXML
    private void handleAppleCalendar() { goToNextStage(); }
    @FXML
    private void handleOutlookCalendar() { goToNextStage(); }
    @FXML
    private void handleCreateYourOwnCalendar() { goToNextStage(); }
    @FXML
    private void toggleDarkMode() {
        userPrefs.setDarkMode(darkModeToggle.isSelected());
        applyTheme();
    }
    private void applyTheme() {
        Scene scene = darkModeToggle.getScene();
        if (scene != null) {
            userPrefs.applyTheme(scene);
        }
    }


    private void goToNextStage() {
        Platform.runLater(() -> {
           googleButton.setOnAction(actionEvent -> {

           });
        });
    }

}
