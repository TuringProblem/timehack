package com.application.javafxtest.controller;

import com.application.javafxtest.model.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public abstract class BaseController {
    @FXML protected ToggleButton themeToggle;
    protected UserPreferences userPreferences;

    public void initialize() {
        userPreferences = new UserPreferences();
        initializeThemeToggle();
    }
    private void initializeThemeToggle() {
        if (themeToggle != null) {
            themeToggle.setSelected(userPreferences.isDarkMode());
            updateThemeToggleText();
            themeToggle.setOnAction(event -> toggleTheme());
        }
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
