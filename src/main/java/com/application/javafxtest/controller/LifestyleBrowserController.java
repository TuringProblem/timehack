package com.application.javafxtest.controller;

import com.application.javafxtest.data.DatabaseManager;
import com.application.javafxtest.model.Lifestyle;
import com.application.javafxtest.model.UserPreferences;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class LifestyleBrowserController extends BaseController {
    @FXML private ListView<Lifestyle> lifestyleListView;
    private DatabaseManager dbManager;
    @Inject
    public LifestyleBrowserController(UserPreferences userPreferences, DatabaseManager dbManager) {
        super(userPreferences);
        this.dbManager = dbManager;
    }
    @FXML
    public void initialize() {
        loadPublicLifestyles();
    }

    private void loadPublicLifestyles() {
        List<Lifestyle> publicLifestyles = dbManager.getPublicLifestyles();
        lifestyleListView.getItems().addAll(publicLifestyles);
    }
    @FXML
    private void handleLifestyleSelection() {
        //Lifestyles selectedLifestyle = (Lifestyles) lifestyleListView.getSelectionModel().getSelectedItems();
        //if (selectedLifestyle != null) {
        //}
    }
}
