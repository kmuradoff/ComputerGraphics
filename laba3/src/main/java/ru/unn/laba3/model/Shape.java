package ru.unn.laba3.model;

public interface Shape {
    boolean intersect(Ray ray, Intersection intersection);
    Vec3 getColor();
}
