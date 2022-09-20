package engine;

import engine.render.*;
import engine.util.CircleGenerator;
import engine.util.Utils;
import org.joml.Vector2f;

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
        Mesh mesh = new CircleGenerator(100d, Utils.randomColor()).generateMesh();

        scene.addEntity(new Entity(
                "circle",
                mesh,
                new Vector2f(0f, 0f)
        ));

//        scene.addEntity(new Entity(
//                "circle 2",
//                mesh,
//                new Vector2f(-200f, -50f)
//        ));
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