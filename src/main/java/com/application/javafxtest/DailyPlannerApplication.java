package com.application.javafxtest;

import com.application.javafxtest.controller.utility.LoginUtility;
import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.data.EmailService;
import com.application.javafxtest.data.User;
import com.application.javafxtest.data.UserManager;
import com.application.javafxtest.model.FeatherModule;
import com.application.javafxtest.model.UserPreferences;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;

public class DailyPlannerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SQLiteDataSource db = new SQLiteDataSource();
        db.setUrl("jdbc:sqlite:users.db");
        FeatherModule featherModule = new FeatherModule(null, new LoginUtility(), new EmailService(),
                new UserPreferences(), new DatabaseManager(db), db);
        SceneManager sceneManager = new SceneManager(stage, featherModule);
        sceneManager.signInScreen();
    }
    public static void main(String[] args) {
        launch();
    }
}
