package com.application.javafxtest.controller;

import com.application.javafxtest.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NewUserSetupController {
    @FXML private Button googleButton;
    @FXML private Button appleButton;
    @FXML private Button outlookButton;
    @FXML private Button createOwnButton;
    private SceneManager sceneManager;
    @FXML
    private void handleGoogleCalendar() { goToNextStage(); }
    @FXML
    private void handleAppleCalendar() { goToNextStage(); }
    @FXML
    private void handleOutlookCalendar() { goToNextStage(); }
    @FXML
    private void handleCreateYourOwnCalendar() { goToNextStage(); }


    private void goToNextStage() {
        Platform.runLater(() -> {
           googleButton.setOnAction(actionEvent -> {

           });
        });
    }

}
