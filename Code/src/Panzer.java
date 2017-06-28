import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import java.util.ArrayList;

//Для для создания и управления танками
public class Panzer extends Pane {
    private int speed = 4;//скорость передвжиения танков
    private int bulletDigit=20;//Колличество пуль у каждого танка
    private int numberofBullet = 0;//Пуля на очереди
    private int health = 2;//Здоровье танка
    private String vector = "R";//Направление движения танка
    private Panzer opponent;//Соперник
    public static ArrayList<PanzerElement> elements = new ArrayList<PanzerElement>();//Список препятствий
    public ArrayList<PanzerBullet> bullets = new ArrayList<PanzerBullet>();//Список патронов


    public Panzer(Color color) {
        Rectangle gun = new Rectangle(20, 14, Color.BLACK);
        Rectangle body = new Rectangle(30, 30, color);
        gun.setTranslateX(body.getTranslateX() + 30);
        gun.setTranslateY(body.getTranslateY() + 8);
        getChildren().addAll(body, gun);
        for (int i = 0; i < bulletDigit; i++) {
            bullets.add(new PanzerBullet());

        }


    }

    //Метод устанавливающий пулю на место стрялющего танка
    public void fire(ArrayList<PanzerBullet> bullets) {
        bullets.get(numberofBullet).setVector(vector);
        if (vector == "R") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() + 50);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() + 8);
        } else if (vector == "L") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() - 60);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() - 21);
        } else if (vector == "U") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() + 8);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() - 60);
        } else if (vector == "D") {
            bullets.get(numberofBullet).setTranslateX(getTranslateX() - 22);
            bullets.get(numberofBullet).setTranslateY(getTranslateY() + 45);
        }

        numberofBullet++;
        if (numberofBullet == bullets.size())
            numberofBullet = 0;

    }


    //Постоянно работающий метод движения танка
    public void move() {
        if (health == 0)
            speed = 0;
        double lastPositionX = getTranslateX();
        double lastPositionY = getTranslateY();

        if (vector == "R")
            setTranslateX(getTranslateX() + speed);
        else if (vector == "L")
            setTranslateX(getTranslateX() - speed);
        else if (vector == "D")
            setTranslateY(getTranslateY() + speed);
        else if (vector == "U")
            setTranslateY(getTranslateY() - speed);

        if (getBoundsInParent().intersects(opponent.getBoundsInParent())) {
            setTranslateX(lastPositionX);
            setTranslateY(lastPositionY);
        }
        for (PanzerElement element : elements)
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

    public void setOpponent(Panzer opponent) {
        this.opponent = opponent;
    }


    public void setHealth(int damage) {
        health -= damage;

    }




}


