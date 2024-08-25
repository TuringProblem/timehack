package com.application.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginPage extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Login");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);

    Label usernameLabel = new Label("Username:");
    grid.add(usernameLabel, 0, 0);

    Label passwordLabel = new Label("Password:");
    grid.add(passwordLabel, 0, 1);

    PasswordField passwordField = new passwordField();
    grid.add(passwordField, 1, 1);

    Button loginButton = new Button("Login");
    grid.add(loginButton, 1, 2);

    loginButton.setOnAction(event -> {
      String username = usernameField.getText();
      String password = passwordField.getText();
      System.out.printf("Username: %s\nPassword: %s", username, password);
    }); 
    Scene scene = new Scene(grid, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
