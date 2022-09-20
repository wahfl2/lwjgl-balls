package engine;

import engine.balls.Ball;
import engine.render.*;
import engine.util.CircleGenerator;
import engine.util.Utils;
import engine.util.Vec2;
import org.joml.Vector2f;

import java.util.ArrayList;
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

        scene.addEntity(new Ball("ball1", new Vec2(-500d, 0d), 100d));

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
        ArrayList<Ball> ballList = new ArrayList<>();
        for (Entity entity : scene.getEntityMap().values()) {
            if (entity instanceof Ball ball) {
                ballList.add(ball);
                ball.move();
            }
        }
        for (Ball ball1 : ballList) {
            for (Ball ball2 : ballList) {
                if (ball1 != ball2) {
                    ball1.collide(ball2);
                }
            }
        }
    }
}