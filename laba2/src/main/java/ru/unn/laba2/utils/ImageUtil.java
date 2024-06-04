package ru.unn.laba2.utils;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public void openImage(FileChooser fileChooser, ImageView imageView) {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        imageView.setPreserveRatio(true);
        fileChooser.setTitle("Open Image");
        try {
            File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveImage(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(imageView.getScene().getWindow());

        if (file != null) {
            try {
                WritableImage writableImage = imageView.snapshot(new SnapshotParameters(), null);
                BufferedImage bufferedImage = convertToBufferedImage(writableImage);
                ImageIO.write(bufferedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage convertToBufferedImage(WritableImage img) {
        BufferedImage bImage = new BufferedImage((int) img.getWidth(), (int) img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int argb = img.getPixelReader().getArgb(x, y);
                bImage.setRGB(x, y, argb);
            }
        }
        return bImage;
    }

}
