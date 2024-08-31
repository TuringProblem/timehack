package com.application.javafxtest;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class DailyPlannerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager scene = new SceneManager();
        scene.switchScene("/com/application/javafxtest/hello-view.fxml");
        scene.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
