package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import com.application.javafxtest.data.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class OnboardingController {
    @FXML private ComboBox<String> calendarTypeComboBox;
    @FXML private Button createOwnButton;
    @FXML private Button browseLifestylesButton;

    private SceneManager sm;
    private UserManager um;

    @FXML
    public void initialize() {
       sm = new SceneManager();
       um = new UserManager();
       calendarTypeComboBox.getItems().addAll("Google", "Apple", "Outlook");

    }

    private void createOwnLifestyle() {
        //need to add the scenemanager scene for lifestyle browser page... that's going to be after.
    }

}
