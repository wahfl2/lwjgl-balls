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
        x += other.x; y += other.y;
        return this;
    }

    public Vec2 sub(@NotNull Vec2 other) {
        x -= other.x; y -= other.y;
        return this;
    }

    public Vec2 mul(@NotNull double mul) {
        x *= mul; y *= mul;
        return this;
    }
}
