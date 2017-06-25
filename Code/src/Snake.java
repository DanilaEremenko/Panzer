import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.awt.geom.RoundRectangle2D;
import java.util.Random;

//каждое деление змеи-элемент класса Snake

public class Snake extends Pane {
    public javafx.geometry.Point2D velocity = new javafx.geometry.Point2D(0, 0);
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
        //Для удобства при обнаружении ошибок можно установить на каждый объект цифру вместо цвета

        getChildren().addAll(rect);
        setTranslateX(snake.getTranslateX() - 20);
        setTranslateY(snake.getTranslateY());
    }


    //Точка в которую движется голова
    public void setTarget(double x, double y) {
        velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(2);
        //double angle = calcAngle(velocity.getX(), velocity.getY());
        //getTransforms().clear();
        //getTransforms().add(new Rotate(angle, 0, 0));

    }

    //У каждого объекта класса Snake есть направление в любой момент времени
    public String getVector() {
        return vector;

    }


    //Движение для головы
    public void move() {
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());
        if (getTranslateX() > 620)
            setTranslateX(1);
        if (getTranslateX() <-20)
            setTranslateX(580);
        if (getTranslateY() < -20)
            setTranslateY(580);
        if (getTranslateY() > 620)
            setTranslateY(1);

    }

    //ПЕРЕДЕЛАТЬ ДВИЖЕНИЕ ЧЕРЕЗ velocity
    //Движение для объектов не являющихся головой,в конструкторе указываем объект за которым необходимо следовать
    public void move(Snake snake) {
        if (snake.getTranslateX() != getTranslateX() && snake.getTranslateY() != getTranslateY()) {
            if (snake.getVector().equals("R") || snake.getVector().equals("L")) {
                setTranslateY(snake.getTranslateY());
                if (snake.getTranslateY() == getTranslateY() + 20)
                    vector = "D";
                else if (snake.getTranslateY() == getTranslateY() - 20)
                    vector = "U";
            } else if (snake.getVector().equals("U") || snake.getVector().equals("D")) {
                setTranslateX(snake.getTranslateX());
                if (snake.getTranslateX() == getTranslateX() + 20)
                    vector = "U";
                else if (snake.getTranslateY() == getTranslateY() - 20)
                    vector = "D";
            }
        } else if (snake.getTranslateX() == getTranslateX() && snake.getTranslateY() < getTranslateY() + 20) {
            setTranslateY(snake.getTranslateY() + 20);
            vector = "U";
        } else if (snake.getTranslateX() == getTranslateX() && snake.getTranslateY() > getTranslateY() - 20) {
            setTranslateY(snake.getTranslateY() - 20);
            vector = "D";
        } else if (snake.getTranslateY() == getTranslateY() && snake.getTranslateX() > getTranslateX() + 20) {
            setTranslateX(snake.getTranslateX() - 20);
            vector = "R";
        } else if (snake.getTranslateY() == getTranslateY() && snake.getTranslateX() < getTranslateX() - 20) {
            setTranslateX(snake.getTranslateX() + 20);
            vector = "L";
        }

        if (getTranslateY() == snake.getTranslateY() && getTranslateX() == snake.getTranslateX())
            System.out.println("эээээээээ");
    }


}
