module com.example.controllsdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.controllsdemo to javafx.fxml;
    exports com.example.controllsdemo;
}