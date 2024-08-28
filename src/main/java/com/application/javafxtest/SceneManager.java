package com.application.javafxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    public Stage primaryStage;
    public  SceneManager() {}
    public SceneManager(Stage primaryStage) { this.primaryStage = primaryStage; }
    public void switchToNewUserScene() { switchScene("new-user.fxml"); }
    public void switchToExistingUserScene() { switchScene("existing-user.fxml"); }

    public void switchScene(String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
