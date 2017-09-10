import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

//Для для создания и управления танками
public class Panzer extends Pane {
    private int hB = 30;
    private int wB = 30;
    private int wG = 20;
    private int hG = 14;
    public static double scaley = 1;//Должно хранится в классе Levels
    private double speed = 4 * scaley;//скорость передвжиения танков
    private static int bulletDigit = 30;//Колличество пуль у каждого танка
    private int numberofBullet = 0;//Пуля на очереди
    private int health = 2;//Здоровье танка
    private ArrayList<Panzer> opponents = new ArrayList<>();//Соперник
    private PanzerDirection vector = PanzerDirection.R;
    private ArrayList<PanzerBullet> bullets = new ArrayList<PanzerBullet>();//Список патронов
    private Levels myLevel;


    public Panzer(Color color, int X, int Y,Levels level) {
        Rectangle gun = new Rectangle(wG, hG, Color.BLACK);
        Rectangle body = new Rectangle(wB, hB, color);
        gun.setTranslateX(body.getTranslateX() + wB);
        gun.setTranslateY(body.getTranslateY() + (wB - hG) / 2);
        getChildren().addAll(body, gun);
        setTranslateX(X);
        setTranslateY(Y);
        setScaleY(scaley);
        setScaleY(scaley);
        myLevel=level;
        for (int i = 0; i < bulletDigit; i++) {
            bullets.add(new PanzerBullet(this));

        }


    }

    //Метод устанавливающий пулю на место стрялющего танка
    public void fire() {
        bullets.get(numberofBullet).setVector(vector);
        switch (vector) {
            case R:
                bullets.get(numberofBullet).setTranslateX(getTranslateX());
                bullets.get(numberofBullet).setTranslateY(getTranslateY() + (hB + hG - 2 * PanzerBullet.getSize()) / 2);
                break;
            case L:
                bullets.get(numberofBullet).setTranslateX(getTranslateX() - PanzerBullet.getSize());
                bullets.get(numberofBullet).setTranslateY(getTranslateY() - (hB + hG) / 2);
                break;
            case D:
                bullets.get(numberofBullet).setTranslateX(getTranslateX() - (hB + hG) / 2
                );
                bullets.get(numberofBullet).setTranslateY(getTranslateY() + (wG + wB) / 2);
                break;
            case U:
                bullets.get(numberofBullet).setTranslateX(getTranslateX() + (hB - hG) / 2);
                bullets.get(numberofBullet).setTranslateY(getTranslateY() - (hG + hB + PanzerBullet.getSize()) / 2);
                break;

        }

        numberofBullet++;
        if (numberofBullet == bulletDigit)
            numberofBullet = 0;

    }


    //Постоянно работающий метод движения танка
    public void move() {
        if (health == 0)
            speed = 0;
        double lastPositionX = getTranslateX();
        double lastPositionY = getTranslateY();

        switch (vector) {
            case R:
                setTranslateX(getTranslateX() + speed);
                break;
            case L:
                setTranslateX(getTranslateX() - speed);
                break;
            case D:
                setTranslateY(getTranslateY() + speed);
                break;
            case U:
                setTranslateY(getTranslateY() - speed);
                break;
            case STOP:
                break;


        }

        for (Panzer opponent : opponents) {

            if (getBoundsInParent().intersects(opponent.getBoundsInParent())) {
                setTranslateX(lastPositionX);
                setTranslateY(lastPositionY);
            }
        }
        for (PanzerElement element : myLevel.getElements())
            if (getBoundsInParent().intersects(element.getBoundsInParent())) {
                setTranslateX(lastPositionX);
                setTranslateY(lastPositionY);
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
    public void setVector(PanzerDirection newVector) {
        getTransforms().add(new Rotate(vector.getAngle() - newVector.getAngle(), 0, 0));
        vector = newVector;
    }

    public void addOpponent(Panzer opponent) {
        if (opponent != this)
            opponents.add(opponent);
    }

    public void addOpponents(ArrayList<Panzer> opponents) {
        for (Panzer opponent : opponents)
            if (opponent != this)
                this.opponents.add(opponent);


    }

    public ArrayList<Panzer> getOpponents() {
        return opponents;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<PanzerBullet> getBullets() {
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

