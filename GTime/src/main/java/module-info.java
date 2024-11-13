module com.GTime.GTime {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires commons.validator;
	requires javafx.graphics;
	

    opens com.GTime.GTime to javafx.fxml;
    exports com.GTime.GTime;
}
