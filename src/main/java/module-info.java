module com.example.numemoryapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.numemoryapp to javafx.fxml;
    exports com.example.numemoryapp;
}