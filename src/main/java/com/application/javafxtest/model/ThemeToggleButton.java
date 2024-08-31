package com.application.javafxtest.model;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

public class ThemeToggleButton extends ToggleButton {
    private static final String LIGHT_ICON = "☀️";
    private static final String DARK_ICON = "🌙";
    public ThemeToggleButton(UserPreferences userPreferences) {
        setFont(Font.font(16));
        updateButton(userPreferences.isDarkMode());

        setOnAction(actionEvent -> {
            boolean newDarkMode = isSelected();
            userPreferences.setDarkMode(newDarkMode);
            userPreferences.applyTheme(getScene());
        });

        userPreferences.darkModeProperty().addListener((obs, oldVal, newVal) -> updateButton(newVal));
    }

    private void updateButton(boolean isDarkMode) {
        setSelected(isDarkMode);
        setText(isDarkMode ? DARK_ICON : LIGHT_ICON);
    }
}
