import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

//Для для создания и управления танками
public class GraphicPanzer extends Pane {
    private double speed = 4 * scaley;//скорость передвжиения танков
    private PanzerDirection vector = PanzerDirection.R;
    private ArrayList<GraphicBullet> bullets = new ArrayList<GraphicBullet>();//Список патронов
    private LogicPanzer logicPanzer;


    public GraphicPanzer(LogicPanzer logicPanzer, Color color, int X, int Y) {
        this.logicPanzer = logicPanzer;
        makeBody(color, logicPanzer);
        setTranslateX(X);
        setTranslateY(Y);
        setScaleY(scaley);
        setScaleY(scaley);
        for (int i = 0; i < bulletDigit; i++) {
            bullets.add(new GraphicBullet(this));

        }


    }

    private void makeBody(Color color, LogicPanzer logicPanzer) {
        Rectangle gun = new Rectangle(logicPanzer.getWidthGun(), logicPanzer.getHightGun(), Color.BLACK);
        Rectangle body = new Rectangle(logicPanzer.getWidthGun(), logicPanzer.getHightBody(), color);
        gun.setTranslateX(body.getTranslateX() + logicPanzer.getWidthBody());
        gun.setTranslateY(body.getTranslateY() + (logicPanzer.getWidthBody() - logicPanzer.getHightGun()) / 2);
        getChildren().addAll(body, gun);


    }

    //Метод устанавливающий пулю на место стрялющего танка
    public void fire() {
    }


    //Постоянно работающий метод движения танка
    public void move() {

    }

    //Метод поворачивающий танк
    public void setVector(PanzerDirection newVector) {
        getTransforms().add(new Rotate(vector.getAngle() - newVector.getAngle(), wB / 2, hB / 2));
        vector = newVector;
    }

    public void addOpponent(GraphicPanzer opponent) {
    }

    public void addOpponents(ArrayList<GraphicPanzer> opponents) {

    }

    public ArrayList<GraphicPanzer> getOpponents() {
        return opponents;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<GraphicBullet> getBullets() {
        return bullets;
    }

    public Levels getMyLevel() {
        return myLevel;
    }

    public static int getBulletDigit() {
        return bulletDigit;
    }
}


//Для танка управляемого мышкой
//    public void setTarget(double x, double y) {
//        velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(5);
//                double angle = calcAngle(velocity.getX(), velocity.getY());
//                getTransforms().clear();
//                getTransforms().add(new Rotate(angle, 0, 0));
//
//                }
//
//public void moveMouse() {
//        setTranslateX(getTranslateX() + velocity.getX());
//        setTranslateY(getTranslateY() + velocity.getY());
//
//        }
//
//public double calcAngle(double vecX, double vecY) {
//        double angle = new javafx.geometry.Point2D(vecX, vecY).angle(1, 0);
//        return vecY > 0 ? angle : -angle;
//        }

