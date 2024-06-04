module ru.unn.laba2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens ru.unn.laba2 to javafx.fxml;
    exports ru.unn.laba2;
    exports ru.unn.laba2.controller;
    opens ru.unn.laba2.controller to javafx.fxml;
}