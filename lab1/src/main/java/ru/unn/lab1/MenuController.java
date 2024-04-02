package ru.unn.lab1;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MenuController {
    FileChooser fileChooser = new FileChooser();

    @FXML
    public ImageView imageView;
    @FXML
    public void inversion() {
        //get current image from imageView and inverse pixels
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                color = Color.color(1 - color.getRed(), 1 - color.getGreen(), 1 - color.getBlue());
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void motionBlur() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 0;
                double green = 0;
                double blue = 0;
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        red += c.getRed();
                        green += c.getGreen();
                        blue += c.getBlue();
                        count++;
                    }
                }
                red /= count;
                green /= count;
                blue /= count;
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void wavesFilter() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                int newX = (int) (x + 20 * Math.sin(2 * Math.PI * y / 60));
                if (newX < width && newX >= 0) {
                    color = pixelReader.getColor(newX, y);
                }
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void openImage() {
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        imageView.setPreserveRatio(true);
        fileChooser.setTitle("Open Image");
        try {
            File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
            InputStream stream = new FileInputStream(file);
            Image image = new Image(stream);
            //Setting image to the image view
            imageView.setImage(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void dilation() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 0;
                double green = 0;
                double blue = 0;
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        red = Math.max(red, c.getRed());
                        green = Math.max(green, c.getGreen());
                        blue = Math.max(blue, c.getBlue());
                        count++;
                    }
                }
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void erosion() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 1;
                double green = 1;
                double blue = 1;
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        red = Math.min(red, c.getRed());
                        green = Math.min(green, c.getGreen());
                        blue = Math.min(blue, c.getBlue());
                        count++;
                    }
                }
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void opening() {
        erosion();
        dilation();
    }

    @FXML
    public void closing() {
        dilation();
        erosion();
    }

    @FXML
    public void topHat() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 1;
                double green = 1;
                double blue = 1;
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        red = Math.min(red, c.getRed());
                        green = Math.min(green, c.getGreen());
                        blue = Math.min(blue, c.getBlue());
                        count++;
                    }
                }
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void blackHat() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 0;
                double green = 0;
                double blue = 0;
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        red = Math.max(red, c.getRed());
                        green = Math.max(green, c.getGreen());
                        blue = Math.max(blue, c.getBlue());
                        count++;
                    }
                }
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    public void grad() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 0;
                double green = 0;
                double blue = 0;
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        red = Math.abs(red - c.getRed());
                        green = Math.abs(green - c.getGreen());
                        blue = Math.abs(blue - c.getBlue());
                        count++;
                    }
                }
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }
    @FXML
    public void medianFilter() {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = 0;
                double green = 0;
                double blue = 0;
                int count = 0;
                double[] reds = new double[10];
                double[] greens = new double[10];
                double[] blues = new double[10];
                for (int i = 0; i < 10; i++) {
                    if (x + i < width) {
                        Color c = pixelReader.getColor(x + i, y);
                        reds[i] = c.getRed();
                        greens[i] = c.getGreen();
                        blues[i] = c.getBlue();
                        count++;
                    }
                }
                reds = sort(reds);
                greens = sort(greens);
                blues = sort(blues);
                red = reds[count / 2];
                green = greens[count / 2];
                blue = blues[count / 2];
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    private double[] sort(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}