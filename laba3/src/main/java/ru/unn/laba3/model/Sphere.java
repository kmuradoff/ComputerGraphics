package ru.unn.laba3.model;

public class Sphere implements Shape {
    private final Vec3 center;
    private final double radius;
    private final Vec3 color;

    public Sphere(Vec3 center, double radius, Vec3 color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Vec3 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean intersect(Ray ray, Intersection intersection) {
        Vec3 oc = ray.getOrigin().subtract(center);
        double a = ray.getDirection().dot(ray.getDirection());
        double b = 2.0 * oc.dot(ray.getDirection());
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double t = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            if (t > 0.001 && t < intersection.distance) {
                intersection.distance = t;
                intersection.point = ray.getOrigin().add(ray.getDirection().multiply(t));
                intersection.normal = intersection.point.subtract(center).normalize();
                intersection.color = color;
                return true;
            }

            t = (-b + Math.sqrt(discriminant)) / (2.0 * a);
            if (t > 0.001 && t < intersection.distance) {
                intersection.distance = t;
                intersection.point = ray.getOrigin().add(ray.getDirection().multiply(t));
                intersection.normal = intersection.point.subtract(center).normalize();
                intersection.color = color;
                return true;
            }
        }
        return false;
    }

    @Override
    public Vec3 getColor() {
        return color;
    }
}
