package com.application.javafxtest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SignUpController {

    @FXML private Button backToLoginButton;
    @FXML
    private void initialize() {
        backToLoginButton.setOnAction(actionEvent ->  goBackToLogin());
    }
    @FXML
    private void goBackToLogin() {
       SceneManager scene = new SceneManager((Stage) backToLoginButton.getScene().getWindow());
        backToLoginButton.setOnAction(actionEvent -> scene.switchScene("/com/application/javafxtest/hello-view.fxml"));
    }

}
