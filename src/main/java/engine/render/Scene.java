package engine.render;

import java.util.ArrayList;

public class Scene {
    private final ArrayList<Renderable> objects = new ArrayList<>();

    public Scene() {
    }

    public ArrayList<Renderable> getObjects() {
        return objects;
    }

    public void cleanup() {
        // Nothing to be done here yet
    }
}
