import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Panzer extends Pane {
    private int speed = 2;
    public ArrayList<PanzerBullet> bullets = new ArrayList<PanzerBullet>();
    private Rectangle body;
    public int numberofBullet = 0;
    public int health = 2;
    public String vector = "R";
    public Panzer opponent;//Соперник


    public Panzer(Color color) {
        Rectangle gun = new Rectangle(20, 14, Color.BLACK);
        body = new Rectangle(30, 30, color);
        gun.setTranslateX(body.getTranslateX() + 30);
        gun.setTranslateY(body.getTranslateY() + 8);
        getChildren().addAll(body, gun);


    }

    //Метод ставящий пулю на место стрялющего танка
    public void fire(ArrayList<PanzerBullet> bullets) {
        bullets.get(numberofBullet).vector = vector;
        if (vector == "R") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() + 50);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() + 8);
        } else if (vector == "L") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() - 60);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() - 21);
        } else if (vector == "U") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX()+8);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() -60);
        } else if (vector == "D") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() -22);
            bullets.get(numberofBullet).setTranslateY(getTranslateY()+ 45);
        }

        numberofBullet++;
        if (numberofBullet == bullets.size())
            numberofBullet = 0;

    }


    //Постоянно работающий метод движения танка
    public void move() {
        if (health == 0)
            speed = 0;

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

        if (getTranslateX() > PanzerGame.sceneWidt + 20)
            setTranslateX(1);
        if (getTranslateX() < -20)
            setTranslateX(PanzerGame.sceneWidt - 20);
        if (getTranslateY() < -20)
            setTranslateY(PanzerGame.sceneHeight - 20);
        if (getTranslateY() > PanzerGame.sceneHeight + 20)
            setTranslateY(1);


    }


    //Метод поворачивающий танк
    public void setVector(String newVector) {
        if ((vector == "R" && newVector == "L") || (vector == "L" && newVector == "R") || (vector == "U" && newVector == "D") || (vector == "D" && newVector == "U")) {
            getTransforms().add(new Rotate(180, 0, 0));
        } else if ((vector == "R" && newVector == "D") || (vector == "D" && newVector == "L") || (vector == "L" && newVector == "U") || (vector == "U" && newVector == "R")) {
            getTransforms().add(new Rotate(90, 0, 0));
        } else if ((vector == "R" && newVector == "U") || (vector == "U" && newVector == "L") || (vector == "L" && newVector == "D") || (vector == "D" && newVector == "R")) {
            getTransforms().add(new Rotate(-90, 0, 0));
        }
        vector = newVector;
    }


}


