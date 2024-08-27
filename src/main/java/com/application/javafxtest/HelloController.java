package com.application.javafxtest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML public Hyperlink signUpLink;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            SceneManager sceneManager = new SceneManager((Stage) signUpLink.getScene().getWindow());
            signUpLink.setOnAction(actionEvent -> sceneManager.switchScene("/com/application/javafxtest/signup.fxml"));
        });
    }
}
