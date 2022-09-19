package engine.render;

import engine.util.Vec2;

import java.awt.*;
import static org.lwjgl.opengl.GL30.*;

public class Triangle implements Renderable {
    public Vec2 p1;
    public Vec2 p2;
    public Vec2 p3;
    public Color color = Color.BLACK;

    public Triangle(Vec2 p1, Vec2 p2, Vec2 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(Vec2 p1, Vec2 p2, Vec2 p3, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }

    public void intoBuffer() {
        glBegin(GL_TRIANGLES);
            glColor3i(color.getRed(), color.getGreen(), color.getBlue());
            glVertex2d(p1.x, p1.y);
            glVertex2d(p2.x, p2.y);
            glVertex2d(p3.x, p3.y);
        glEnd();
    }
}
