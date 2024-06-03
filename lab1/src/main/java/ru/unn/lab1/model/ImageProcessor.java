package ru.unn.lab1.model;

import javafx.scene.image.*;
import javafx.scene.paint.Color;
import ru.unn.lab1.utils.ImageUtil;

public class ImageProcessor {

    ImageUtil imageUtil = new ImageUtil();

    public void applySepia(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();

                double tr = 0.393 * red + 0.769 * green + 0.189 * blue;
                double tg = 0.349 * red + 0.686 * green + 0.168 * blue;
                double tb = 0.272 * red + 0.534 * green + 0.131 * blue;

                red = Math.min(tr, 1.0);
                green = Math.min(tg, 1.0);
                blue = Math.min(tb, 1.0);

                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    public void applyGrayScale(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double gray = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
                color = Color.color(gray, gray, gray);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    public void applyDilation(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyErosion(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyTopHat(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyBlackHat(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyGrad(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyMedianFilter(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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
                reds = imageUtil.sort(reds);
                greens = imageUtil.sort(greens);
                blues = imageUtil.sort(blues);
                red = reds[count / 2];
                green = greens[count / 2];
                blue = blues[count / 2];
                color = Color.color(red, green, blue);
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    public void applyWavesFilter(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyMotionBlur(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
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

    public void applyInversion(ImageView imageView) {
        Image image = imageView.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                color = Color.color(1 - color.getRed(), 1 - color.getGreen(), 1 - color.getBlue());
                pixelWriter.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }
}
