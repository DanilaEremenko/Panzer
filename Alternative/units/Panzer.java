package units;

import com.sun.istack.internal.NotNull;

public class Panzer {
    private boolean isAlive = true;
    private int health;
    private int collar;
    private final int damage;
    public final int maxSpeed;

    public Panzer(int health, int maxSpeed, int damage, int collar) {
        this.health = health;
        this.damage = damage;
        this.collar = collar;
        this.maxSpeed = maxSpeed;
    }

    public Panzer(@NotNull PanzerType type) {
        this(type.health, type.maxSpeed, type.damage, type.collar);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) isAlive = false;
    }

    public void fire() {
        if (collar != 0) collar--;
    }

    public int getHealth() {
        return health;
    }

    public int getCollar() {
        return collar;
    }

    public int getDamage() {
        return damage;
    }
}
