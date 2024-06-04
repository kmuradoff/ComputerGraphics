module ru.unn.laba4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens ru.unn.laba4 to javafx.fxml;
    exports ru.unn.laba4;
}