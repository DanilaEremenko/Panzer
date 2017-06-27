import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Panzer extends Pane {
    static double speed = 2;
    private Rectangle bullet;
    private Rectangle body;
    String vector = "R";
    javafx.geometry.Point2D pointFire = new javafx.geometry.Point2D(0, 0);
    public Panzer opponent;

    public Panzer(Color color) {
        bullet = new Rectangle(12, 14, Color.BLACK);
        body = new Rectangle(30, 30, color);
        bullet.setTranslateX(body.getTranslateX() + 30);
        bullet.setTranslateY(body.getTranslateY() + 7);
        getChildren().addAll(body, bullet);
    }

    public void shot() {
        //pointFire.subtract()
        //bvector="START";
        double lastX = bullet.getTranslateX();
        double lastY = bullet.getTranslateY();

        if (vector == "R") {
            while (bullet.getTranslateX() < 600 || getBoundsInParent().intersects(opponent.getBoundsInParent()))
                setTranslateX(getTranslateX() + 1);

            bullet.setTranslateX(lastX);
            bullet.setTranslateY(lastY);
            return;
        }


        if (vector == "L")
            bullet.setTranslateX(bullet.getTranslateX() - 1);

        if (vector == "D")
            bullet.setTranslateY(bullet.getTranslateY() + 1);

        if (vector == "U")
            bullet.setTranslateY(bullet.getTranslateY() - 1);

//        bullet.setTranslateX(lastX);
//        bullet.setTranslateY(lastY);

    }

    public void move() {

        if (vector == "R") {
            setTranslateX(getTranslateX() + speed);
            if (getBoundsInParent().intersects(opponent.getBoundsInParent()))
                setTranslateX(getTranslateX() - speed);

        } else if (vector == "L") {
            setTranslateX(getTranslateX() - speed);
            if (getBoundsInParent().intersects(opponent.getBoundsInParent()))
                setTranslateX(getTranslateX() + speed);
        } else if (vector == "D") {
            setTranslateY(getTranslateY() + speed);
            if (getBoundsInParent().intersects(opponent.getBoundsInParent()))
                setTranslateY(getTranslateY() - speed);
        } else if (vector == "U") {
            setTranslateY(getTranslateY() - speed);
            if (getBoundsInParent().intersects(opponent.getBoundsInParent()))
                setTranslateY(getTranslateY() + speed);
        }

    }

    public void right() {
        vector = "R";
        body.setTranslateX(body.getTranslateX() + 20);
        bullet.setTranslateX(body.getTranslateX() + 30);
        bullet.setTranslateY(body.getTranslateY() + 7);
        if (body.getBoundsInParent().intersects(opponent.getBoundsInParent()))
            body.setTranslateX(body.getTranslateX() - 20);


    }

    public void left() {
        vector = "L";
        body.setTranslateX(body.getTranslateX() - 20);
        bullet.setTranslateX(body.getTranslateX() - 12);
        bullet.setTranslateY(body.getTranslateY() + 7);
    }

    public void down() {
        vector = "D";
        body.setTranslateY(body.getTranslateY() + 20);
        bullet.setTranslateX(body.getTranslateX() + 9);
        bullet.setTranslateY(body.getTranslateY() + 30);
    }

    public void up() {
        vector = "U";
        body.setTranslateY(body.getTranslateY() - 20);
        bullet.setTranslateX(body.getTranslateX() + 9);
        bullet.setTranslateY(body.getTranslateY() - 14);
    }

}
