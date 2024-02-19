package Lab1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Binarize {
    public static BufferedImage binarize(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setRGB(i, j, gamma(image.getRGB(i, j)) > 127 ? Color.white.getRGB() : Color.black.getRGB());
        return image;
    }

    static int gamma(int rgb) {
        return (red(rgb) + green(rgb) + blue(rgb)) / 3;
    }

    static int red(int rgb) {
        return (rgb >> 16) & 0x000000FF;
    }

    static int green(int rgb) {
        return (rgb >> 8) & 0x000000FF;
    }

    static int blue(int rgb) {
        return (rgb) & 0x000000FF;
    }

    public static void main(String[] args) {
        BufferedImage img = null;
        File f = null;

        try {
            f = new File("C:/Users/kmuradoff/Pictures/Komp Grafika/lab1.jpg");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        img = binarize(img);


        try {
            f = new File(
                    "C:/Users/kmuradoff/Pictures/Komp Grafika/lab1_binarized.jpg");
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
