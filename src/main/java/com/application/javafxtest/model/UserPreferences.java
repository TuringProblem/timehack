package com.application.javafxtest.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;

public class UserPreferences {
    private BooleanProperty darkMode = new SimpleBooleanProperty(false);

    public boolean isDarkMode() {
        return darkMode.get();
    }

    public BooleanProperty darkModeProperty() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode.set(darkMode);
        System.out.println("Dark mode set to: " + darkMode);
    }

    public void applyTheme(Scene scene) {
        if (scene != null) {
            scene.getStylesheets().clear();
            String cssFile = isDarkMode() ? "/com/application/javafxtest/styles/darkMode.css" : "/com/application/javafxtest/styles/lightMode.css";
            System.out.println("Attempting to load CSS file: " + cssFile);
            java.net.URL resourceUrl = getClass().getResource(cssFile);
            if (resourceUrl != null) {
                String cssUrl = resourceUrl.toExternalForm();
                System.out.println("CSS URL: " + cssUrl);
                scene.getStylesheets().add(cssUrl);
                System.out.println("CSS applied to scene");
            } else {
                System.err.println("Warning: CSS file not found: " + cssFile);
                System.err.println("Current classpath: " + System.getProperty("java.class.path"));
            }
        } else {
            System.err.println("Warning: Scene is null");
        }
    }
}
