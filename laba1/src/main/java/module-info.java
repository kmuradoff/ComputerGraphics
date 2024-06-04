module ru.unn.laba1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens ru.unn.laba1 to javafx.fxml;
    opens ru.unn.laba1.controller to javafx.fxml;
    exports ru.unn.laba1;
    exports ru.unn.laba1.controller;
}