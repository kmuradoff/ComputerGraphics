package ru.unn.laba3.model;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<Shape> shapes;
    private final Camera camera;

    public Scene(Camera camera) {
        this.shapes = new ArrayList<>();
        this.camera = camera;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public Camera getCamera() {
        return camera;
    }

    public Vec3 traceRay(Ray ray) {
        Intersection closestIntersection = new Intersection();

        for (Shape shape : shapes) {
            boolean hit = shape.intersect(ray, closestIntersection);
            if (hit) {
                return shade(closestIntersection, ray);
            }
        }

        return backgroundColor(ray);
    }

    private Vec3 backgroundColor(Ray ray) {
        Vec3 unitDirection = ray.getDirection().normalize();
        double t = 0.5 * (unitDirection.y + 1.0);
        return new Vec3(1.0, 1.0, 1.0).multiply(1.0 - t).add(new Vec3(0.5, 0.7, 1.0).multiply(t));
    }

    private Vec3 shade(Intersection intersection, Ray ray) {
        Vec3 lightDirection = new Vec3(1, 1, -1).normalize();
        double lightIntensity = Math.max(0.0, intersection.normal.dot(lightDirection));
        return intersection.color.multiply(lightIntensity);
    }
}
