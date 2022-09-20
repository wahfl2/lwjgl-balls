package engine.render;

import org.joml.Vector2f;

public class Entity {
    private final String id;
    private final Mesh mesh;

    private Vector2f position;

    public Entity(String id, Mesh mesh) {
        this.id = id;
        this.mesh = mesh;
        this.position = new Vector2f();
    }

    public Entity(String id, Mesh mesh, Vector2f position) {
        this.id = id;
        this.mesh = mesh;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
}
