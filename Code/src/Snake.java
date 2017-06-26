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
    public String vector = "R";//Указывает в какую сторону движется объект, принимать значения U,D,R,L
    public static double speed = 10;//скорость перемещния змейки

    //Конструтор для головы
    public Snake() {
        Rectangle rect = new Rectangle(20, 20, Color.RED);
        getChildren().addAll(rect);
    }

    //Конструктор для элементов не являющихся головой
    public Snake(Snake snake) {
        Rectangle rect = new Rectangle(20, 20, Color.BLUE);
        rect.setArcHeight(50);
        rect.setArcWidth(50);


        getChildren().addAll(rect);
        setTranslateX(snake.getTranslateX() - 20);
        setTranslateY(snake.getTranslateY());
    }

//КАК ВАРИАНТ,ТОЛЬКО ТОГДА ПРИДЕТСЯ
    //Точка в которую движется голова
    public void setTarget(double x, double y) {
        //    velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(this.x);

    }

    //У каждого объекта класса Snake есть направление в любой момент времени
    //Возможно не понадобится
    public String getVector() {
        return vector;

    }


    //Движение для головы
    public void move() {
        lastPositoionX = getTranslateX();
        lastPositoionY = getTranslateY();
        //setTranslateX(getTranslateX() + velocity.getX());
        //setTranslateY(getTranslateY() + velocity.getY());
        if (getVector() == "D")
            setTranslateY(getTranslateY() + speed);
        if (getVector() == "U")
            setTranslateY(getTranslateY() - speed);
        if (getVector() == "R")
            setTranslateX(getTranslateX() + speed);
        if (getVector() == "L")
            setTranslateX(getTranslateX() - speed);
        if (getVector() == "STOP") {
            setTranslateX(getTranslateX());
            setTranslateY(getTranslateY());
        }

        //Немного подвинуть границу
        if (getTranslateX() > SnakeGame.sceneWidt+20)
            setTranslateX(1);
        if (getTranslateX() < -20)
            setTranslateX(SnakeGame.sceneWidt-20);
        if (getTranslateY() < -20)
            setTranslateY(SnakeGame.sceneHeight-20);
        if (getTranslateY() > SnakeGame.sceneHeight+20)
            setTranslateY(1);

    }

    //Движение для объектов не являющихся головой,в конструкторе указываем объект за которым необходимо следовать
    public void move(Snake snake) {
        lastPositoionX = getTranslateX();
        lastPositoionY = getTranslateY();
        setTranslateX(snake.lastPositoionX);
        setTranslateY(snake.lastPositoionY);

    }


}
