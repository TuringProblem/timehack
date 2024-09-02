package com.application.javafxtest.model;

import javafx.event.ActionEvent;

@FunctionalInterface
public interface SceneSwitcher {
    void switchScene(ActionEvent event, Runnable action);
}
