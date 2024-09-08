module com.application.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires jdk.httpserver;
    requires dev.mccue.jdk.httpserver;
    requires dev.mccue.urlparameters;
    requires dev.mccue.jdbc;
    requires dev.mccue.feather;
    requires com.samskivert.jmustache;
    requires jakarta.inject;
    requires jakarta.mail;

    opens com.application.javafxtest to javafx.fxml, dev.mccue.feather;
    exports com.application.javafxtest;
    exports com.application.javafxtest.model;
    exports com.application.javafxtest.controller;
    opens com.application.javafxtest.controller to javafx.fxml;
    exports com.application.javafxtest.controller.login;
    opens com.application.javafxtest.controller.login to javafx.fxml;
    exports com.application.javafxtest.controller.utility;
    opens com.application.javafxtest.controller.utility to javafx.fxml;
}