package com.application.javafxtest.controller;

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

    @FXML
    @Override
    public void initialize() {
        super.initialize();
    }


}
