package ru.unn.laba4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;

public class SteganographyController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField textField;

    private WritableImage writableImage;

    @FXML
    public void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.bmp"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                writableImage = convertToWritableImage(bufferedImage);
                imageView.setImage(writableImage);
            } catch (IOException e) {
                showError("Error loading image: " + e.getMessage());
            }
        }
    }

    @FXML
    public void encodeText() {
        if (writableImage == null) {
            showError("No image loaded.");
            return;
        }

        String text = textField.getText();
        if (text.isEmpty()) {
            showError("No text to encode.");
            return;
        }

        writableImage = addNoise(writableImage);
        writableImage = encodeTextInImage(writableImage, text);
        imageView.setImage(writableImage);
    }

    @FXML
    public void decodeText() {
        if (writableImage == null) {
            showError("No image loaded.");
            return;
        }

        String decodedText = decodeTextFromImage(writableImage);
        textField.setText(decodedText);
    }

    private WritableImage convertToWritableImage(BufferedImage bufferedImage) {
        WritableImage writableImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int argb = bufferedImage.getRGB(x, y);
                writableImage.getPixelWriter().setArgb(x, y, argb);
            }
        }
        return writableImage;
    }

    private WritableImage addNoise(WritableImage image) {
        Random random = new Random();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixelReader().getColor(x, y);
                int noise = random.nextBoolean() ? 1 : 0;
                int red = (int) (color.getRed() * 255);
                int green = (int) (color.getGreen() * 255);
                int blue = (int) (color.getBlue() * 255);

                red = (red & 0xFE) | noise;

                color = Color.rgb(red, green, blue);
                image.getPixelWriter().setColor(x, y, color);
            }
        }
        return image;
    }

    private WritableImage encodeTextInImage(WritableImage image, String text) {
        byte[] textBytes = text.getBytes();
        BitSet bits = BitSet.valueOf(textBytes);
        int bitIndex = 0;

        for (int y = 0; y < image.getHeight() && bitIndex < bits.length(); y++) {
            for (int x = 0; x < image.getWidth() && bitIndex < bits.length(); x++) {
                Color color = image.getPixelReader().getColor(x, y);

                int red = (int) (color.getRed() * 255);
                int green = (int) (color.getGreen() * 255);
                int blue = (int) (color.getBlue() * 255);

                for (int i = 0; i < 8 && bitIndex < bits.length(); i++) {
                    int bit = bits.get(bitIndex) ? 1 : 0;
                    red = (red & ~(1 << i)) | (bit << i);
                    bitIndex++;
                }

                color = Color.rgb(red, green, blue);
                image.getPixelWriter().setColor(x, y, color);
            }
        }
        return image;
    }

    private String decodeTextFromImage(WritableImage image) {
        BitSet bits = new BitSet();
        int bitIndex = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixelReader().getColor(x, y);

                int red = (int) (color.getRed() * 255);

                for (int i = 0; i < 8; i++) {
                    int bit = (red >> i) & 1;
                    bits.set(bitIndex, bit == 1);
                    bitIndex++;
                }
            }
        }

        byte[] textBytes = bits.toByteArray();
        return new String(textBytes);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
