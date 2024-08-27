package com.application.javafxtest;

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
        signUpLink.setOnAction(event -> {
            try {
                openSignUpScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void openSignUpScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/application/javafxtext/views/signup.fxml"));
        AnchorPane signUpPane = loader.load();

        Stage stage = (Stage) signUpLink.getScene().getWindow();
        Scene scene = new Scene(signUpPane);
        stage.setScene(scene);
    }
}