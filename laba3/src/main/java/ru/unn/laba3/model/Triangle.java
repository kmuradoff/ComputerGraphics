package ru.unn.laba3.model;

public class Triangle implements Shape {
    private final Vec3 v0, v1, v2;
    private final Vec3 color;

    public Triangle(Vec3 v0, Vec3 v1, Vec3 v2, Vec3 color) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
    }

    @Override
    public boolean intersect(Ray ray, Intersection intersection) {
        Vec3 edge1 = v1.subtract(v0);
        Vec3 edge2 = v2.subtract(v0);
        Vec3 h = ray.getDirection().cross(edge2);
        double a = edge1.dot(h);

        if (a > -0.0001 && a < 0.0001) {
            return false; // Луч параллелен треугольнику
        }

        double f = 1.0 / a;
        Vec3 s = ray.getOrigin().subtract(v0);
        double u = f * (s.dot(h));

        if (u < 0.0 || u > 1.0) {
            return false;
        }

        Vec3 q = s.cross(edge1);
        double v = f * ray.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return false;
        }

        double t = f * edge2.dot(q);

        if (t > 0.0001 && t < intersection.distance) {
            intersection.distance = t;
            intersection.point = ray.getOrigin().add(ray.getDirection().multiply(t));
            intersection.normal = edge1.cross(edge2).normalize();
            intersection.color = color;
            return true;
        }

        return false;
    }

    @Override
    public Vec3 getColor() {
        return color;
    }
}
