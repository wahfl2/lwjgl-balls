package engine.render;

import engine.util.Vec2;
import org.lwjgl.opengl.GL;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL30.*;

public class Circle implements Renderable {
    private Vec2 pos;
    public Color color;
    private int numVertices;
    private double radius;

    private final ArrayList<Vec2> computedNormalizedOffsets = new ArrayList<>();
    private final ArrayList<Vec2> computedOffsets = new ArrayList<>();
    private final ArrayList<Vec2> computedVertexPositions = new ArrayList<>();

    public Circle(Vec2 pos, double radius, Color color) {
        GL.createCapabilities();
        this.pos = pos;
        this.radius = radius;
        this.color = color;
        this.numVertices = 32;

        calculateVertexPos();
    }

    public Circle(Vec2 pos, double radius, Color color, int numVertices) {
        GL.createCapabilities();
        this.pos = pos;
        this.radius = radius;
        this.color = color;

        assert numVertices > 2;
        this.numVertices = numVertices;

        calculateVertexPos();
    }

    public Vec2 getPos() {
        return pos;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public double getRadius() {
        return radius;
    }

    public void setPos(Vec2 pos) {
        this.pos = pos;
        recalcVertexPos();
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
        calculateVertexPos();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        recalcOffsets();
    }

    private void calculateVertexPos() {
        computedVertexPositions.clear();
        double numD = numVertices;
        for (int i = 0; i < numVertices; i++) {
            double angle = (numD / i) * Math.PI;

            Vec2 vNormOffset = new Vec2((Math.cos(angle)), (Math.sin(angle)));
            computedNormalizedOffsets.add(vNormOffset);

            Vec2 vOffset = vNormOffset.mul(radius);
            computedOffsets.add(vOffset);

            computedVertexPositions.add(vOffset.add(pos));
        }
    }

    private void recalcVertexPos() {
        computedVertexPositions.clear();
        for (Vec2 v : computedOffsets) {
            computedVertexPositions.add(v.add(pos));
        }
    }

    private void recalcOffsets() {
        computedOffsets.clear();
        computedVertexPositions.clear();
        for (Vec2 v : computedNormalizedOffsets) {
            Vec2 vOffset = v.mul(radius);
            computedOffsets.add(vOffset);
            computedVertexPositions.add(vOffset.add(pos));
        }
    }

    @Override
    public void intoBuffer() {
        glBegin(GL_POLYGON);
            glColor3i(color.getRed(), color.getGreen(), color.getBlue());
            for (Vec2 v : computedVertexPositions) {
                glVertex2d(v.x, v.y);
            }
        glEnd();
    }
}
