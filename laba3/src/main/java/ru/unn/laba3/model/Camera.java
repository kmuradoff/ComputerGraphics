package ru.unn.laba3.model;

public class Camera {
    private final Vec3 origin;
    private final Vec3 lowerLeftCorner;
    private final Vec3 horizontal;
    private final Vec3 vertical;

    public Camera() {
        origin = new Vec3(0, 1, 5);
        lowerLeftCorner = new Vec3(-2, -1, -1);
        horizontal = new Vec3(4, 0, 0);
        vertical = new Vec3(0, 2, 0);
    }

    public Ray getRay(double u, double v) {
        return new Ray(origin, lowerLeftCorner.add(horizontal.multiply(u)).add(vertical.multiply(v)).subtract(origin));
    }
}
