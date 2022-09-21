package engine;

import engine.balls.Ball;
import engine.input.MouseInput;
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

        scene.addEntity(new Ball("ball1", new Vec2(-500d, 0d), Math.random() * 30 + 30));
        scene.addEntity(new Ball("ball2", new Vec2(500d, 100d), Math.random() * 30 + 30));
        scene.addEntity(new Ball("ball3", new Vec2(200d, -400d), Math.random() * 30 + 30));
        scene.addEntity(new Ball("ball4", new Vec2(-50d, 800d), Math.random() * 30 + 30));
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        MouseInput mouseInput = window.getMouseInput();
        if (mouseInput.isLeftButtonPressed()) {
            scene.addEntity(new Ball("ball" + (scene.getNumEntities() + 1), mouseInput.getCurrentPos(), Math.random() * 30 + 30));
        }
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
        for (int i = 0; i < 4; i++) {
            physicsIteration(ballList);
        }
    }

    public void physicsIteration(ArrayList<Ball> balls) {
        for (Ball ball1 : balls) {
            for (Ball ball2 : balls) {
                if (ball1 != ball2) {
                    ball1.collide(ball2);
                }
            }
        }
    }
}