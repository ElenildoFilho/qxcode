module com.qxcode {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    exports com.qxcode;
    opens com.qxcode.Controller to javafx.fxml;

}