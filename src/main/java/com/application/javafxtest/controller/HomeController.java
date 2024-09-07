package com.application.javafxtest.controller;

import com.application.javafxtest.model.UserPreferences;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class HomeController extends BaseController{
    @FXML private DatePicker datePicker;
    @FXML private ListView<String> categoriesList;
    @FXML private Label weekLabel;
    @FXML private GridPane scheduleGrid;

    @Inject
     public HomeController(UserPreferences userPreferences) {
        super(userPreferences);
    }

    @FXML
    @Override
    public void initialize() {
        super.initialize();
    }


}
