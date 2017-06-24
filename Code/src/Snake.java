import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.awt.geom.RoundRectangle2D;

public class Snake extends Pane {
    public javafx.geometry.Point2D velocity = new javafx.geometry.Point2D(0, 0);
    int width = 20;
    String vector;

    public Snake() {
        Rectangle rect = new Rectangle(width, 20, Color.RED);
        getChildren().addAll(rect);
    }

    public Snake(Snake snake) {
        Rectangle rect = new Rectangle(width, 20, Color.BLACK);
        getChildren().addAll(rect);
        setTranslateX(snake.getTranslateX() - 20);
        setTranslateY(snake.getTranslateY());

    }

    //Точка в которую движется голова
    public void setTarget(double x, double y) {
        velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(5);
        //double angle = calcAngle(velocity.getX(), velocity.getY());
        //getTransforms().clear();
        //getTransforms().add(new Rotate(angle, 0, 0));

    }


    public String getVector() {
        return vector;

    }


    //Движение для головы
    public void move() {
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());

    }


    //Движение для хвотов
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
                    vector = "R";
                else if (snake.getTranslateY() == getTranslateY() - 20)
                    vector = "L";
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

    }


    public double calcAngle(double vecX, double vecY) {
        double angle = new javafx.geometry.Point2D(vecX, vecY).angle(1, 0);
        return vecY > 0 ? angle : -angle;
    }


}
