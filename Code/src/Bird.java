import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.geom.Point2D;

public class Bird extends Pane {
    public javafx.geometry.Point2D velocity = new javafx.geometry.Point2D(0, 0);
    Rectangle rect;

    public Bird() {
        rect = new Rectangle(20, 20, Color.RED);
        velocity = new javafx.geometry.Point2D(0, 0);
        setTranslateY(300);
        setTranslateX(100);
        getChildren().addAll(rect);
    }

    public void moveY(int value) {
        boolean moveDown = value > 0 ? true : false;//Если объект больше нуля движется вниз
        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall w : FlappyBird.walls) {

                if (this.getBoundsInParent().intersects(w.getBoundsInParent()))//Граница персонажа
                {
                    if (moveDown)
                        setTranslateY(getTranslateY() - 1);
                    else {
                        setTranslateY(getTranslateY() + 1);
                        return;
                    }
                }

            }
            if (getTranslateY() < 0) {
                setTranslateY(0);
            }
            if (getTranslateY() > 580) {
                setTranslateY(580);
            }

            setTranslateY(getTranslateY() + (moveDown ? 1 : -1));//текущая коордиата и прибавить или отнять 1
        }

    }

    //Принмает значение на которое нужно передвинуть птицу
    public void moveX(int value) {
        for (int i = 0; i < value; i++) {
            setTranslateX(getTranslateX()+1);
            for (Wall w : FlappyBird.walls) {

                if (getBoundsInParent().intersects(w.getBoundsInParent()))
                    if (getTranslateX() + 20 == w.getTranslateX()) {
                        setTranslateX(getTranslateX() - 1);
                        return;
                    }

                if (getTranslateX() + 20==w.getTranslateX())
                    FlappyBird.score++;

            }

        }


    }
    public void jump(){
        velocity=new javafx.geometry.Point2D(3,-15);


    }

}
