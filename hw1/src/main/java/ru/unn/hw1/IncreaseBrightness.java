package ru.unn.hw1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class IncreaseBrightness {
    public static int Truncate(int value) {
        if (value < 0) {
            value = 0;
        } else if (value > 255) {
            value = 255;
        }
        return value;
    }

    public static void AdjustBrightness(String inpPath, String outPath) throws IOException {
        File f = new File(inpPath);
        BufferedImage image = ImageIO.read(f);

        int[] rgb;

        // ЗДЕСЬ МЕНЯЕМ ЯРКОСТЬ.
        int brightnessValue = 25;

        // Outer loop for width of image
        for (int i = 0; i < image.getWidth(); i++) {

            for (int j = 0; j < image.getHeight(); j++) {

                rgb = image.getRaster().getPixel(i, j, new int[3]);

                int red = Truncate(rgb[0] + brightnessValue);
                int green = Truncate(rgb[1] + brightnessValue);
                int blue = Truncate(rgb[2] + brightnessValue);

                int[] arr = {red, green, blue};

                image.getRaster().setPixel(i, j, arr);
            }
        }

        ImageIO.write(image, "jpg", new File(outPath));
    }

    public static void main(String[] args) throws Exception {
        String inputPath = "hw1/src/main/resources/input/lab1.jpg";
        String outputPath = "hw1/src/main/resources/output/lab1_brightness.jpg";

        AdjustBrightness(inputPath, outputPath);
    }
}
