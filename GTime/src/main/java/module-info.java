module com.GTime.GTime {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires commons.validator;
	requires transitive javafx.graphics;

    opens GTime to javafx.fxml;
    exports GTime;
}
