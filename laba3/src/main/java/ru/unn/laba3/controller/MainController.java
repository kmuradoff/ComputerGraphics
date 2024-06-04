package ru.unn.laba3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MainController {
    @FXML
    private ImageView imageView;
    @FXML
    private Button renderButton;

    private RayTracingController rayTracingController;

    @FXML
    public void initialize() {
        rayTracingController = new RayTracingController();
        rayTracingController.setImageView(imageView);
        rayTracingController.initialize();
    }

    @FXML
    private void onRenderButtonClicked() {
        rayTracingController.render();
    }
}
