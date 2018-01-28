package units;

public enum PanzerType {
    DEFAULT(10, 2, 2, 10), FAST(10, 3, 2, 10), STRONG(20, 2, 3, 15);
    final int health;
    final int maxSpeed;
    final int damage;
    final int collar;

    PanzerType(int health, int maxSpeed, int damage, int collar) {
        this.health = health;
        this.maxSpeed = maxSpeed;
        this.damage = damage;
        this.collar = collar;
    }
}
