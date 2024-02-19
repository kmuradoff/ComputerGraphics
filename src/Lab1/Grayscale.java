package Lab1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Grayscale {
    public static void main(String[] args) {
        BufferedImage img = null;
        File f = null;

        try {
            f = new File("C:/Users/kmuradoff/Pictures/Komp Grafika/lab1.jpg");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < pixels.length; i++) {
            int p = pixels[i];

            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;

            int avg = (r + g + b) / 3;

            p = (a << 24) | (avg << 16) | (avg << 8) | avg;

            pixels[i] = p;
        }
        img.setRGB(0, 0, width, height, pixels, 0, width);
        try {
            f = new File(
                    "C:/Users/kmuradoff/Pictures/Komp Grafika/lab1_grayscaled.jpg");
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
