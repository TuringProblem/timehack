package com.application.javafxtest;

import com.application.javafxtest.data.User;
import com.application.javafxtest.model.UserPreferences;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author @Override
 * @since 08/30/2024 @19:42
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class SceneManager {
     public Stage primaryStage;
     private UserPreferences userPreferences;

     public SceneManager () {
         this.primaryStage = new Stage();
         this.userPreferences = UserPreferences.getInstance();
     }
    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userPreferences = UserPreferences.getInstance();
    }

    private void loadScene(String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            userPreferences.applyTheme(scene);

            if (primaryStage.isShowing()) {
                primaryStage.close();
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void signInScreen() { switchScene("hello-view.fxml"); }
    public void switchToNewUserScene() { switchScene("new-user.fxml"); }
    public void switchToExistingUserScene() { switchScene("existing-user.fxml"); }

    public void switchToSignUpScene() { loadScene("/com/application/javafxtest/signup.fxml");}
    public void switchToForgotPasswordScene() { loadScene("/com/application/javafxtest/forgot-password.fxml");}


    public void switchScene(String fxmlFilePath) { loadScene(fxmlFilePath); }
    public void show() { this.primaryStage.show(); }
    public void close() { this.primaryStage.close(); }



}

