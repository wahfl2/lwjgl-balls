package engine.render;

import engine.util.Vec2;

import java.awt.*;

public class RenderTriangle {
    private Vec2 p1;
    private Vec2 p2;
    private Vec2 p3;
    private Color color = Color.BLACK;

    public RenderTriangle(Vec2 p1, Vec2 p2, Vec2 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public RenderTriangle(Vec2 p1, Vec2 p2, Vec2 p3, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }

    public Vec2 getP1() {
        return p1;
    }

    public Vec2 getP2() {
        return p2;
    }

    public Vec2 getP3() {
        return p3;
    }

    public Color getColor() {
        return color;
    }

    public void setP1(Vec2 p1) {
        this.p1 = p1;
    }

    public void setP2(Vec2 p2) {
        this.p2 = p2;
    }

    public void setP3(Vec2 p3) {
        this.p3 = p3;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
