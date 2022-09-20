package engine.balls;

import engine.Main;
import engine.render.Entity;
import engine.util.CircleGenerator;
import engine.util.Utils;
import engine.util.Vec2;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;

import java.awt.*;

public class Ball extends Entity {

    public double radius;
    public Vec2 pos;
    public Vec2 vel = Vec2.ZERO;

    public Ball(String id, Vec2 position, double radius) {
        super(id, new CircleGenerator(radius, Utils.randomColor()).generateMesh());
        this.pos = position;
    }

    public Ball(String id, Vec2 position, double radius, Color color) {
        super(id, new CircleGenerator(radius, color).generateMesh());
        this.pos = position;
    }

    public void updateEntityPos() {
        this.setPosition(new Vector2f((float) this.pos.x, (float) this.pos.y));
    }


    public void move() {
        this.pos.add(this.vel);
        double distance = this.pos.length();
        double rad = 900d - this.radius;
        if (distance > rad) {
            double moveDist = distance - rad;
            Vec2 resolutionVec = this.pos.clone().normalize().mul(moveDist);

            this.pos.sub(resolutionVec);
            this.vel.sub(resolutionVec);
        }
        this.vel.y -= 0.1d;
        this.vel.mul(0.9995d);
        this.updateEntityPos();
    }

    public void collide(@NotNull Ball other) {
        double addedRadii = this.radius + other.radius;
        if (Math.abs(this.pos.x - other.pos.x) < addedRadii && Math.abs(this.pos.y - other.pos.y) < addedRadii) {
            double distance = this.pos.distance(other.pos);
            if (distance < addedRadii) {
                // Position handling
                double moveDist = addedRadii - distance;
                Vec2 resolutionVec = this.pos.clone().sub(other.pos).normalize().mul(moveDist / 2);

                this.pos.add(resolutionVec);
                this.updateEntityPos();
                other.pos.sub(resolutionVec);
                other.updateEntityPos();
            }
        }
    }
}
