module com.GTime.GTime {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires commons.validator;
	requires transitive javafx.graphics;

    opens com.GTime.GTime to javafx.fxml;
    exports com.GTime.GTime;
}
