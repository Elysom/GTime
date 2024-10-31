module com.GTime.GTime {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens com.GTime.GTime to javafx.fxml;
    exports com.GTime.GTime;
}
