package ru.unn.laba2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import ru.unn.laba2.model.Tomogram;
import ru.unn.laba2.model.TomogramReader;
import ru.unn.laba2.utils.ImageUtil;

import java.io.File;
import java.io.IOException;

public class TomogramController {
    @FXML
    private ImageView imageView;
    @FXML
    private Slider layerSlider;
    @FXML
    private Slider minDensitySlider;
    @FXML
    private Slider maxDensitySlider;
    private FileChooser fileChooser = new FileChooser();
    private TomogramReader reader;
    private Tomogram tomogram;

    @FXML
    private void initialize() {
        layerSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateImage());
        minDensitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateImage());
        maxDensitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateImage());
    }

    @FXML
    public void openTomogram() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
        fileChooser.setTitle("Open Tomogram");
        File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
            reader = new TomogramReader();
            try {
                reader.read(file.getAbsolutePath());
                tomogram = new Tomogram(reader);
                layerSlider.setMax(reader.getDepth() - 1);
                System.out.println("Tomogram opened: " + file.getAbsolutePath());
                updateImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void saveImage() {
        ImageUtil imageUtil = new ImageUtil();
        imageUtil.saveImage(imageView);
    }

    private void updateImage() {
        if (tomogram != null) {
            int layer = (int) layerSlider.getValue();
            double minDensity = minDensitySlider.getValue();
            double maxDensity = maxDensitySlider.getValue();
            System.out.println("Updating image: layer=" + layer + ", minDensity=" + minDensity + ", maxDensity=" + maxDensity);
            imageView.setImage(tomogram.getSlice(layer, minDensity, maxDensity));
        }
    }
}
