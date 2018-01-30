package Server;

/**
 * Сообщение с этим классом передается на сервер
 */
public class Action {
    //будем передавать 1, если ускоряемся, -1, если замедляемся и 0, если ускорения нет
    private int acceleration;
    //1 вправо, -1 влево, 0 нет поворота
    private int rotate;
    //true - выстрел, false - нет выстрела
    private boolean fire;

    public Action(int acceleration, int rotate, boolean fire) {
        this.acceleration = acceleration;
        this.rotate = rotate;
        this.fire = fire;
    }
    public int getAcceleration() {
        return acceleration;
    }

    public int getRotate() {
        return rotate;
    }

    public boolean isFire() {
        return fire;
    }
}
