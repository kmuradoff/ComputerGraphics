package ru.unn.laba3.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import ru.unn.laba3.model.*;

public class RayTracingController {
    @FXML
    private ImageView imageView;
    private Scene scene;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void initialize() {
        Camera camera = new Camera();
        scene = new Scene(camera);
        scene.addShape(new Sphere(new Vec3(0, 0, -5), 1, new Vec3(1, 0, 0))); // Красная сфера
        scene.addShape(new Triangle(
                new Vec3(-1, -1, -3),
                new Vec3(1, -1, -3),
                new Vec3(0, 1, -3),
                new Vec3(0, 1, 0))); // Зеленый треугольник
    }

    public void render() {
        int imageWidth = 800;
        int imageHeight = 450;
        WritableImage writableImage = new WritableImage(imageWidth, imageHeight);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        Camera camera = scene.getCamera();

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                double u = (double) x / (imageWidth - 1);
                double v = (double) y / (imageHeight - 1);
                Ray ray = camera.getRay(u, v);
                Vec3 color = scene.traceRay(ray);
                int r = (int) (color.x * 255.99);
                int g = (int) (color.y * 255.99);
                int b = (int) (color.z * 255.99);

                pixelWriter.setColor(x, y, Color.rgb(r, g, b));
            }
        }

        imageView.setImage(writableImage);
    }
}
