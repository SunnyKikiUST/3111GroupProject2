module com.example._3111groupproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example._3111groupproject to javafx.fxml;
    exports com.example._3111groupproject;
}