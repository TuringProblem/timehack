package com.application.javafxtest;

import com.application.javafxtest.model.interfaces.SceneSwitcher;
import com.application.javafxtest.model.UserPreferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;


/**
 * @author @Override
 * @since 08/30/2024 @19:42
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class SceneManager implements SceneSwitcher {
     public Stage primaryStage;
     private UserPreferences userPreferences;

     public SceneManager () {
         this.primaryStage = new Stage();
         this.userPreferences = UserPreferences.getInstance();
     }
    public SceneManager(Window primaryStage) {
        this.primaryStage = (Stage) primaryStage;
        this.userPreferences = UserPreferences.getInstance();
    }

    public void switchScene(String fxmlFilePath) { loadScene(fxmlFilePath); }
    private void loadScene(String fxmlFilePath) {
        System.out.printf("Attempting to load scene: %s\n", fxmlFilePath);
        URL resourceURL = getClass().getResource(fxmlFilePath);
        if (resourceURL == null) {
            System.err.println("Resource not found: " + fxmlFilePath + "\n");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            System.out.printf("root successfully loaded\nAttemping to load %s...\n", root);
            Scene scene = new Scene(root);
            System.out.printf("scene successfully loaded\nAttemping to apply the theme %s...\n", scene);
            userPreferences.applyTheme(scene);
            System.out.println("setting the scene now\n");
            primaryStage.setScene(scene);
            System.out.println("now trying to show\n");
            primaryStage.show();
        } catch (IOException e) {
            System.out.printf("Error loading scene; %s\n", fxmlFilePath);
            e.printStackTrace();
        }
    }

    public void signInScreen() { switchScene("/com/application/javafxtest/login/hello-view.fxml"); }
    public void switchToNewUserScene() { switchScene("/com/application/javafxtest/login/new-user.fxml"); }
    public void switchToHomePage() { switchScene("/com/application/javafxtest/homescreen/existing-user.fxml"); }

    public void switchToSignUpScene() { switchScene("/com/application/javafxtest/login/signup.fxml"); }
    public void switchToForgotPasswordScene() { switchScene("/com/application/javafxtest/login/forgot-password.fxml"); }



    public void show() { this.primaryStage.show(); }
    public void close() { this.primaryStage.close(); }


    @Override
    public void switchScene(ActionEvent event, Runnable action) {
         action.run();
    }
}

