module com.GTime.GTime {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.GTime.GTime to javafx.fxml;
    exports com.GTime.GTime;
}
