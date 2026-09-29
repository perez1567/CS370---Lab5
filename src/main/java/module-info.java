module com.example.lab5cs370 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab5cs370 to javafx.fxml;
    exports com.example.lab5cs370;
}