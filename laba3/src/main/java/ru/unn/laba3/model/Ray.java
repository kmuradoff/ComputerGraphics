package ru.unn.laba3.model;

public class Ray {
    private final Vec3 origin;
    private final Vec3 direction;

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    public Vec3 getOrigin() {
        return origin;
    }

    public Vec3 getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Ray{" + "origin=" + origin + ", direction=" + direction + '}';
    }
}
