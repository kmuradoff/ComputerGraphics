package ru.unn.laba2.model;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Tomogram {
    private final TomogramReader reader;

    public Tomogram(TomogramReader reader) {
        this.reader = reader;
    }

    public WritableImage getSlice(int z, double minDensity, double maxDensity) {
        int width = reader.getWidth();
        int height = reader.getHeight();
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                short voxel = reader.getVoxel(x, y, z);
                double intensity = (voxel - minDensity) / (maxDensity - minDensity);
                intensity = Math.max(0, Math.min(1, intensity));
                Color color = Color.gray(intensity);
                writer.setColor(x, y, color);
            }
        }

        System.out.println("Slice " + z + " generated with minDensity=" + minDensity + ", maxDensity=" + maxDensity);
        return image;
    }
}
