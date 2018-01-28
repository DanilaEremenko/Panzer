package geometry;

public class Vector {
    /**
     * Точка в полярной системе координат
     * Длина задает расстояние от начала координат
     * Угол задает угол относительно полярной оси
     * Угол задается в радианах
     */
    private double length;
    private double angle;

    public Vector(double length, double angle) {
        this.length = length;
        setAngle(angle);
    }

    //Горизонтальная составляющая вектора
    public double xPart() {
        return Math.cos(angle) * length;
    }

    //Вертикальная составляющая вектора
    public double yPart() {
        return Math.sin(angle) * length;
    }

    public void rotate(double angle) {
        this.angle = (this.angle + angle) % (2 * Math.PI);
    }

    public Vector copy() {
        return new Vector(length, angle);
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setAngle(double angle) {
        //Берем остаток от деления на 2Pi радиан,
        //ибо это максимальный угол
        this.angle = angle % (2 * Math.PI);
    }

    public double getLength() {
        return length;
    }

    public double getAngle() {
        return angle;
    }
}
