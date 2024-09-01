package com.application.javafxtest.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;

/**
 * @author @Override
 * @since 09/01/2024 @13:41
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class UserPreferences {
    /**
     * @see <a href="https://stackoverflow.com/questions/70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java">Singleton</a>
     */
    private static UserPreferences instance;//made UserPreferences a Singleton
    private BooleanProperty darkMode = new SimpleBooleanProperty(false);

    private UserPreferences() {}
    public static UserPreferences getInstance() {
        if (instance == null) {
            instance = new UserPreferences();
        }
        return instance;
    }

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
            scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
        }
    }

}