package com.application.javafxtest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class SceneManager {

    public Stage primaryStage;
    public SceneManager(Stage primaryStage) { this.primaryStage = primaryStage; }

    public void switchScene(String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
