package engine.balls;

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
    public Vec2 vel = new Vec2(0d, 0d);

    public Ball(String id, Vec2 position, double radius) {
        super(id, new CircleGenerator(radius, Utils.randomColor()).generateMesh(), position.asVector2f());
        this.radius = radius;
        this.pos = position;
    }

    public Ball(String id, Vec2 position, double radius, Color color) {
        super(id, new CircleGenerator(radius, color).generateMesh(), position.asVector2f());
        this.radius = radius;
        this.pos = position;
    }

    public void updateEntityPos() {
        this.setPosition(new Vector2f((float) this.pos.x, (float) this.pos.y));
    }


    public void move() {
        this.pos = this.pos.add(this.vel);
        squareBoundary();
        this.vel.y -= 0.1d;
        this.vel = this.vel.mul(0.9995d);
        this.updateEntityPos();
    }

    public void circleBoundary() {
        double distance = this.pos.length();
        double allowedDistance = 900d - this.radius;
        if (distance > allowedDistance) {
            double moveDist = distance - allowedDistance;
            Vec2 resolutionVec = this.pos.normalize().mul(moveDist);

            this.pos = this.pos.sub(resolutionVec);
            this.vel = this.vel.sub(resolutionVec);
        }
    }

    public void squareBoundary() {
        if (pos.x > 900d) {
            vel.x -= pos.x - 900d;
            pos.x = 900d;
        } else if (pos.x < -900d) {
            vel.x -= pos.x + 900d;
            pos.x = -900d;
        }

        if (pos.y > 500d) {
            vel.y -= pos.y - 500d;
            pos.y = 500d;
        } else if (pos.y < -500d) {
            vel.y -= pos.y + 500d;
            pos.y = -500d;
        }
    }

    public void collide(@NotNull Ball other) {
        double addedRadii = this.radius + other.radius;
        if (Math.abs(this.pos.x - other.pos.x) < addedRadii && Math.abs(this.pos.y - other.pos.y) < addedRadii) {
            double distance = this.pos.distance(other.pos);
            if (distance < addedRadii) {
                // Position handling
                double moveDist = addedRadii - distance;
                Vec2 resolutionVec = this.pos.sub(other.pos).normalize().mul(moveDist * 0.5d);

                this.pos = this.pos.add(resolutionVec);
                this.updateEntityPos();
                this.vel = this.vel.add(resolutionVec);

                other.pos = other.pos.sub(resolutionVec);
                other.updateEntityPos();
                other.vel = other.vel.sub(resolutionVec);
            }
        }
    }
}
