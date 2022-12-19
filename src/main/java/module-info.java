module com.xuyufei.marketsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.xuyufei.marketsystem to javafx.fxml;
    exports com.xuyufei.marketsystem;
    exports com.xuyufei.marketsystem.entity;
    opens com.xuyufei.marketsystem.entity to javafx.fxml;
    exports com.xuyufei.marketsystem.controller;
    opens com.xuyufei.marketsystem.controller to javafx.fxml;
}