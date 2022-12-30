module com.example.projectjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.projectjavafx to javafx.fxml;
    exports com.example.projectjavafx;
}