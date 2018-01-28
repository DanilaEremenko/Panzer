package game;

import geometry.Point;
import geometry.Vector;
import units.Bullet;
import units.Panzer;

public class GeneralPlayer implements Player {
    private Point point;
    private Vector vector;
    private Panzer panzer;
    final int MAX_SPEED;

    public GeneralPlayer(Point point, Panzer panzer, Vector vector) {
        this.point = point;
        this.panzer = panzer;
        this.vector = vector;
        MAX_SPEED = panzer.maxSpeed;
    }

    @Override
    public void drive(double acceleration) {
        double newSpeed = vector.getLength() + acceleration;
        if (Math.abs(newSpeed) <= MAX_SPEED) vector.setLength(newSpeed);
    }

    @Override
    public Bullet fire() {
        if (panzer.getCollar() != 0) {
            panzer.fire();
            return new Bullet(point.copy(), vector.copy(), this, panzer.getDamage());
        }
        return null;
    }

    @Override
    public void move() {
        point.x += vector.xPart();
        point.y += vector.yPart();
    }

    @Override
    public void rotate(double angle) {
        vector.rotate(angle);
    }

    @Override
    public double getRotationDegrees() {
        //Переводим из радиан в градусы, потому что javafx работает с градусами
        return vector.getAngle() * (180 / Math.PI);
    }

    @Override
    public boolean isAlive() {
        return panzer.isAlive();
    }

    @Override
    public void takeDamage(int damage) {
        panzer.takeDamage(damage);
    }

    @Override
    public Vector getVector() {
        return vector;
    }

    @Override
    public Point getPoint() {
        return point;
    }
}
