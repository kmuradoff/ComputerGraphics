module ru.unn.lab1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens ru.unn.lab1 to javafx.fxml;
    exports ru.unn.lab1;
    exports ru.unn.lab1.controller;
    opens ru.unn.lab1.controller to javafx.fxml;
}