package engine;

import engine.render.Circle;
import engine.render.Mesh;
import engine.render.Render;
import engine.render.Scene;
import engine.util.Vec2;

import java.awt.*;
import java.util.logging.Logger;

public class Main implements IAppLogic {
    public static final Logger LOGGER = Logger.getLogger("Engine");

    public static void main(String[] args) {
        Main main = new Main();
        Engine gameEng = new Engine("balls", new Window.WindowOptions(), main);
        gameEng.start();
    }

    @Override
    public void cleanup() {
        // Nothing to be done yet
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        float[] positions = new float[]{
                0.0f, 0.5f,
                -0.5f, -0.5f,
                0.5f, -0.5f,
        };
        Mesh mesh = new Mesh(positions, 3);
        scene.addMesh("triangle", mesh);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        // Nothing to be done yet
    }
}