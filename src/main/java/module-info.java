module com.application.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.application.javafxtest to javafx.fxml;
    exports com.application.javafxtest;
    exports com.application.javafxtest.controller;
    opens com.application.javafxtest.controller to javafx.fxml;
    exports com.application.javafxtest.controller.login;
    opens com.application.javafxtest.controller.login to javafx.fxml;
    exports com.application.javafxtest.controller.utility;
    opens com.application.javafxtest.controller.utility to javafx.fxml;
}