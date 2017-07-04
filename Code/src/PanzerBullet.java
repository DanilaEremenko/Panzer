import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

//Класс для создания пуль и управления их траекторией
public class PanzerBullet extends Pane {
    private int speed = 20; //Скорость полета пули
    private PanzerDirection vector = PanzerDirection.STOP;//Направление движения пули
    private static int size = 14;

    public PanzerBullet() {
        Rectangle bullet = new Rectangle(size, size, Color.BLACK);
        bullet.setArcHeight(50);
        bullet.setArcWidth(50);
        setTranslateX(-15);
        setTranslateY(-15);
        getChildren().addAll(bullet);
//        setLayoutX(50);//Смещение
//        setLayoutY(50);//Еще пригодится
    }

    public void move(ArrayList<Panzer> opponents) {
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
                opponent.setHealth(opponent.getHealth() - 1);
                setTranslateX(-15);
                setTranslateY(-15);
                vector = PanzerDirection.STOP;
            }
        }

        for (PanzerElement element : Panzer.elements)
            if (getBoundsInParent().intersects(element.getBoundsInParent())) {
                setTranslateX(-15);
                setTranslateY(-15);
                vector = PanzerDirection.STOP;
            }
        //Такое
        if (getTranslateX() > PanzerGame.sceneWidt + 20)
            setTranslateX(1);
        if (getTranslateX() < -20)
            setTranslateX(PanzerGame.sceneWidt - 20);
        if (getTranslateY() < -20)
            setTranslateY(PanzerGame.sceneHeight - 20);
        if (getTranslateY() > PanzerGame.sceneHeight + 20)
            setTranslateY(1);


    }


    public static int getSize() {
        return size;
    }

    public void setVector(PanzerDirection vector) {
        this.vector = vector;
    }

    public PanzerDirection getVector() {
        return vector;
    }


}


