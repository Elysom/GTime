module com.GTime.GTime {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;

    opens GTime to javafx.fxml;
    exports GTime;
}
