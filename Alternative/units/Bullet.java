package units;

import game.Player;
import geometry.Point;
import geometry.Vector;

public class Bullet {
    private Point point;
    private Vector vector;
    private Player player;
    private int damage;

    public Bullet(Point point, Vector vector, Player player, int damage) {
        this.point = point;
        this.vector = vector;
        this.player = player;
        this.damage = damage;
        vector.setLength(vector.getLength() + 2);
    }

    public void move() {
        point.x += vector.xPart();
        point.y += vector.yPart();
    }

    public Point getPoint() {
        return point;
    }

    public Vector getVector() {
        return vector;
    }

    public Player getOwner() {
        return player;
    }

    public int getDamage() {
        return damage;
    }
}
