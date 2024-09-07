package com.application.javafxtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DailyPlannerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.signInScreen();
    }
    public static void main(String[] args) {
        launch();
    }
}
