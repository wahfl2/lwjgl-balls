package engine.render;

import engine.util.Vec2;

import java.awt.*;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL30.*;

public class TriMesh implements Renderable {
    private Color color;
    private final ArrayList<Vec2> vertices = new ArrayList<>();
    private final ArrayList<int[]> indices = new ArrayList<>();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Vec2> getVertices() {
        return vertices;
    }

    public int numVertices() {
        return vertices.size();
    }

    public void addVertex(Vec2 pos) {
        vertices.add(pos);
    }

    public ArrayList<int[]> getIndices() {
        return indices;
    }

    public void addIndices(int one, int two, int three) {
        indices.add(new int[] {one, two, three});
    }

    public void addTriangle(Vec2 v1, Vec2 v2, Vec2 v3) {
        this.addVertex(v1);
        this.addVertex(v2);
        this.addVertex(v3);

        int num = this.numVertices();
        this.addIndices(num - 1, num - 2, num - 3);
    }

    @Override
    public void intoBuffer() {
        glBegin(GL_TRIANGLES);
            glColor3i(color.getRed(), color.getGreen(), color.getBlue());
            for (int[] i : indices) {
                for (int j : i) {
                    Vec2 vertex = vertices.get(j);
                    glVertex2d(vertex.x, vertex.y);
                }
            }
        glEnd();
    }
}
