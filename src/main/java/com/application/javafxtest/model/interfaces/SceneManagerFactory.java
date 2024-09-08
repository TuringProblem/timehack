package com.application.javafxtest.model.interfaces;

import com.application.javafxtest.SceneManager;
import javafx.stage.Stage;

@FunctionalInterface
public interface SceneManagerFactory {

    SceneManager create(Stage stage);
}
