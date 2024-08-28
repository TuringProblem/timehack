module com.application.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.application.javafxtest to javafx.fxml;
    exports com.application.javafxtest;
}