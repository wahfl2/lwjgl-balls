package engine.util;

import org.jetbrains.annotations.NotNull;

public class Vec2 {
    public double x;
    public double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(@NotNull Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 sub(@NotNull Vec2 other) {
        return new Vec2(this.x - other.x, this.y - other.y);
    }

    public Vec2 mul(double mul) {
        return new Vec2(this.x * mul, this.y * mul);
    }

    public final double distance(Vec2 other) {
        double a = this.x - other.x;
        double b = this.y - other.y;
        return Math.sqrt((a * a) + (b * b));
    }

    public final double length() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public final Vec2 normalize() {
        double len = this.length();
        return new Vec2(this.x / len, this.y / len);
    }

    public static final Vec2 ZERO = new Vec2(0d, 0d);
}
