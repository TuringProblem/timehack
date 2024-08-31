package com.application.javafxtest.controller;

import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.model.Lifestyles;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class LivestyleBrowserController {
    @FXML private ListView<Lifestyles> lifestyleListView;
    private DatabaseManager dbManager;

    @FXML
    public void initialize() {
        dbManager = new DatabaseManager();
        loadPublicLifestyles();
    }

    private void loadPublicLifestyles() {
        List<Lifestyles> publicLifestyles = dbManager.getPublicLifestyles();
        lifestyleListView.getItems().addAll(publicLifestyles);
    }
    @FXML
    private void handleLifestyleSelection() {
        Lifestyles selectedLifestyle = (Lifestyles) lifestyleListView.getSelectionModel().getSelectedItems();
        if (selectedLifestyle != null) {


        }
    }
}
