package ru.unn.laba3.model;

public class Intersection {
    public Vec3 point;
    public Vec3 normal;
    public Vec3 color;
    public double distance;

    public Intersection() {
        this.point = null;
        this.normal = null;
        this.color = null;
        this.distance = Double.MAX_VALUE;
    }
}
