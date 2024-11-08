module com.GTime.GTime {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires commons.validator;

    opens GTime to javafx.fxml;
    exports GTime;
}
