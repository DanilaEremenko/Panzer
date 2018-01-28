package game;

import geometry.Point;
import geometry.Vector;
import units.Bullet;

public interface Player {
    void drive(double acceleration);

    void rotate(double angle);

    void takeDamage(int damage);

    void move();

    Bullet fire();

    boolean isAlive();

    double getRotationDegrees();

    Vector getVector();

    Point getPoint();
}
