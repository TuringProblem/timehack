package com.application.javafxtest.controller;

import com.application.javafxtest.data.User;
import com.application.javafxtest.model.UserPreferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

import java.util.function.BiFunction;

public abstract class BaseController {
    @FXML protected ToggleButton themeToggle;
    protected UserPreferences userPreferences;

    public void initialize() {
        userPreferences = UserPreferences.getInstance();
        initializeThemeToggle();
    }
    private void initializeThemeToggle() {
        if (themeToggle != null) {
            themeToggle.setSelected(userPreferences.isDarkMode());
            updateThemeToggleText();
            themeToggle.setOnAction(event -> toggleTheme());
        }
    }

    public void setupEventHandlers() {
        BiFunction<ActionEvent, Runnable, Void> eventHandler = (actionEvent, action) -> {
          action.run();
          return null;
        };
    }

    private void toggleTheme() {
        userPreferences.setDarkMode(themeToggle.isSelected());
        updateThemeToggleText();
        userPreferences.applyTheme(themeToggle.getScene());
    }

    private void updateThemeToggleText() {
        themeToggle.setText(userPreferences.isDarkMode() ? "🌙" : "☀️");
    }
}
