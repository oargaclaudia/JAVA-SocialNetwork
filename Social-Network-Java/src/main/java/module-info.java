module com.tema6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com to javafx.fxml;
    exports com;
    exports com.domain;
    exports com.service;
    exports com.controller to javafx.fxml;
    opens com.controller to javafx.fxml;
}