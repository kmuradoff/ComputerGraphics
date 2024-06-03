package ru.unn.lab1.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.control.ProgressBar;
import javafx.concurrent.Task;
import ru.unn.lab1.model.ImageProcessor;
import ru.unn.lab1.utils.ImageUtil;


public class MenuController {
    ImageUtil imageUtil = new ImageUtil();
    FileChooser fileChooser = new FileChooser();
    ImageProcessor imageProcessor = new ImageProcessor();

    @FXML
    public ImageView imageView;
    @FXML
    private ProgressBar progressBar;

    @FXML
    public void inversion() {
        applyFilterWithProgress(() -> imageProcessor.applyInversion(imageView));
    }

    @FXML
    public void motionBlur() {
        applyFilterWithProgress(() -> imageProcessor.applyMotionBlur(imageView));
    }

    @FXML
    public void wavesFilter() {
        applyFilterWithProgress(() -> imageProcessor.applyWavesFilter(imageView));
    }

    @FXML
    public void openImage() {
        imageUtil.openImage(fileChooser, imageView);
    }

    @FXML
    public void saveImage() {
        imageUtil.saveImage(imageView);
    }

    @FXML
    public void sepia() {
        applyFilterWithProgress(() -> imageProcessor.applySepia(imageView));
    }

    @FXML
    public void grayScale() {
        applyFilterWithProgress(() -> imageProcessor.applyGrayScale(imageView));
    }

    @FXML
    public void dilation() {
        applyFilterWithProgress(() -> imageProcessor.applyDilation(imageView));
    }

    @FXML
    public void erosion() {
        applyFilterWithProgress(() -> imageProcessor.applyErosion(imageView));
    }

    @FXML
    public void opening() {
        applyFilterWithProgress(() -> {
            imageProcessor.applyErosion(imageView);
            imageProcessor.applyDilation(imageView);
        });
    }

    @FXML
    public void closing() {
        applyFilterWithProgress(() -> {
            imageProcessor.applyDilation(imageView);
            imageProcessor.applyErosion(imageView);
        });
    }

    @FXML
    public void topHat() {
        applyFilterWithProgress(() -> imageProcessor.applyTopHat(imageView));
    }

    @FXML
    public void blackHat() {
        applyFilterWithProgress(() -> imageProcessor.applyBlackHat(imageView));
    }

    @FXML
    public void grad() {
        applyFilterWithProgress(() -> imageProcessor.applyGrad(imageView));
    }

    @FXML
    public void medianFilter() {
        applyFilterWithProgress(() -> imageProcessor.applyMedianFilter(imageView));
    }
    
    @FXML
    public void applyFilterWithProgress(Runnable filterTask) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                filterTask.run();
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());

        task.setOnRunning(e -> progressBar.setVisible(true));
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> {
            progressBar.setVisible(false);
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }
}
