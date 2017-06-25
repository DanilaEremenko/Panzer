import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.awt.geom.RoundRectangle2D;
import java.util.Random;

//каждое деление змеи-элемент класса Snake

public class Snake extends Pane {
    public javafx.geometry.Point2D velocity = new javafx.geometry.Point2D(0, 0);
    private double lastPositoionX;
    private double lastPositoionY;
    String vector;//Указывает в какую сторону движется объект, принимать значения U,D,R,L

    //Конструтор для головы
    public Snake() {
        Rectangle rect = new Rectangle(20, 20, Color.RED);
        getChildren().addAll(rect);
    }

    //Конструктор для элементов не являющихся головой
    public Snake(Snake snake) {
        Color massColor[] = new Color[]{Color.GREEN, Color.BLUE, Color.GRAY, Color.BLACK};
        Rectangle rect = new Rectangle(20, 20, massColor[new Random().nextInt(4)]);
        rect.setArcHeight(50);
        rect.setArcWidth(50);


        getChildren().addAll(rect);
        setTranslateX(snake.getTranslateX() - 20);
        setTranslateY(snake.getTranslateY());
    }


    //Точка в которую движется голова
    public void setTarget(double x, double y) {
        velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(7);
    }

    //У каждого объекта класса Snake есть направление в любой момент времени
    public String getVector() {
        return vector;

    }


    //Движение для головы
    public void move() {
        lastPositoionX = getTranslateX();
        lastPositoionY = getTranslateY();
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());
        if (getTranslateX() > 620)
            setTranslateX(1);
        if (getTranslateX() < -20)
            setTranslateX(580);
        if (getTranslateY() < -20)
            setTranslateY(580);
        if (getTranslateY() > 620)
            setTranslateY(1);

    }

    //ПЕРЕДЕЛАТЬ ДВИЖЕНИЕ ЧЕРЕЗ velocity
    //Движение для объектов не являющихся головой,в конструкторе указываем объект за которым необходимо следовать
    public void move(Snake snake) {
        lastPositoionX = getTranslateX();
        lastPositoionY = getTranslateY();
        setTranslateX(snake.lastPositoionX);
        setTranslateY(snake.lastPositoionY);
    }


}
