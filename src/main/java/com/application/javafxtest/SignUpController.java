package com.application.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SignUpController {
    @FXML private Button backToLoginButton;

    @FXML
    private void initialize() {
        backToLoginButton.setOnAction(actionEvent ->  goBackToLogin());
    }
    @FXML
    private void goBackToLogin() {}

}
