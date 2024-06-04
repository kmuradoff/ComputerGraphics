module ru.unn.laba3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens ru.unn.laba3 to javafx.fxml;
    exports ru.unn.laba3;
    exports ru.unn.laba3.controller;

    opens ru.unn.laba3.controller to javafx.fxml;
}