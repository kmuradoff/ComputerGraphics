package ru.unn.laba3.model;

public class Vec3 {
    public final double x;
    public final double y;
    public final double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vec3 subtract(Vec3 v) {
        return new Vec3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public Vec3 multiply(double scalar) {
        return new Vec3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vec3 divide(double scalar) {
        return new Vec3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public double dot(Vec3 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(
                this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x
        );
    }

    public Vec3 normalize() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vec3(this.x / length, this.y / length, this.z / length);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public String toString() {
        return "Vec3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
