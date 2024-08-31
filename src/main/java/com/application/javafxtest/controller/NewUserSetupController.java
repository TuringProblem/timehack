package com.application.javafxtest.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NewUserSetupController {
    @FXML
    private void handleGoogleCalendar() { goToNextStage(); }
    @FXML
    private void handleAppleCalendar() { goToNextStage(); }
    @FXML
    private void handleOutlookCalendar() { goToNextStage(); }
    @FXML
    private void handleCreateYourOwnCalendar() { goToNextStage(); }


    private void goToNextStage() {}

}
