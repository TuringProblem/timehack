package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.data.UserManager;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class OnboardingController {
    @FXML private ComboBox<String> calendarTypeComboBox;
    @FXML private Button createOwnButton;
    @FXML private Button browseLifestylesButton;

    private SceneManager sceneManager;
    private UserManager userManager;
    @Inject
    public OnboardingController(UserManager userManager, SceneManager sceneManager) {
        this.userManager= userManager;
        this.sceneManager= sceneManager;
    }
    @FXML
    public void initialize() {
       calendarTypeComboBox.getItems().addAll("Google", "Apple", "Outlook");

    }

    private void createOwnLifestyle() {
        //need to add the scenemanager scene for lifestyle browser page... that's going to be after.
    }

}
