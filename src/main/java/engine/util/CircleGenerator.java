package engine.util;

import engine.render.Mesh;
import engine.util.Utils;
import engine.util.Vec2;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL30.*;

public class CircleGenerator {
    public Color color;
    private int numVertices;
    private double radius;
    private final ArrayList<Vec2> computedVertexPositions = new ArrayList<>();

    public CircleGenerator(double radius, Color color) {
        this.radius = radius;
        this.color = color;
        this.numVertices = 32;

        calculateVertexPos();
    }

    public CircleGenerator(double radius, Color color, int numVertices) {
        this.radius = radius;
        this.color = color;

        assert numVertices > 2;
        this.numVertices = numVertices;

        calculateVertexPos();
    }

    public int getNumVertices() {
        return numVertices;
    }

    public double getRadius() {
        return radius;
    }

    private void calculateVertexPos() {
        computedVertexPositions.clear();
        double numD = numVertices;
        for (int i = 1; i <= numVertices; i++) {
            double angle = (i / numD) * Math.PI * 2;
            computedVertexPositions.add(new Vec2((Math.cos(angle)), (Math.sin(angle))).mul(radius));
        }
    }

    public Mesh generateMesh() {
        int[] indices = new int[(numVertices - 2) * 3];

        for (int i = 2; i < numVertices; i++) {
            int index = (i - 2) * 3;

            indices[index] = 0;
            indices[index + 1] = i - 1;
            indices[index + 2] = i;
        }

        float[] colors = Utils.repeatFloatArray(
                new float[] { color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f },
                numVertices
        );

        float[] positions = expandPositions(computedVertexPositions);

        return new Mesh(positions, colors, indices);
    }

    private float[] expandPositions(ArrayList<Vec2> pos) {
        float[] ret = new float[pos.size() * 2];

        int index = 0;
        for (Vec2 p : pos) {
            ret[index] = (float) p.x;
            ret[index + 1] = (float) p.y;
            index += 2;
        }

        return ret;
    }
}
