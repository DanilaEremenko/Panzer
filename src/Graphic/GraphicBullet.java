package Graphic;

import Logic.LogicBullet;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Logic.*;

//Класс для создания пуль и управления их траекторией
public class GraphicBullet extends Pane {
    private LogicBullet logicBullet;//Пуля, которая отрисовывается
//____________________________________________________________________________________________________________________________________
    //К
    //О
    //Н
    //С
    //Т
    //Р
    //У
    //К
    //Т
    //О
    //Р


    public GraphicBullet(LogicBullet logicBullet) {
        this.logicBullet = logicBullet;
        makeBody();
        setTranslateX(logicBullet.getTranslateX());
        setTranslateY(logicBullet.getTranslateY());
    }


    private void makeBody() {
        Rectangle rectangle = new Rectangle(logicBullet.getSize(), logicBullet.getSize(), Color.BLACK);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        getChildren().addAll(rectangle);

    }
//____________________________________________________________________________________________________________________________________
    //Д
    //Л
    //Я

    //Д
    //В
    //И
    //Ж
    //Е
    //Н
    //И
    //Я

    //Отрисовывает пулю
    //Вызывается в AnimationTimer-e
    public void move() {
        setTranslateX(logicBullet.getTranslateX());
        setTranslateY(logicBullet.getTranslateY());
        for (GraphicPanzer opponent : logicBullet.getLogicPanzer().getMyLevel().getGraphicPanzers()) {
            if (getBoundsInParent().intersects(opponent.getBoundsInParent()) && opponent.getLogicPanzer() != logicBullet.getLogicPanzer()) {
                opponent.getLogicPanzer().takeDamage(logicBullet);
                setTranslateX(-15);
                setTranslateY(-15);
                logicBullet.setVector(PanzerDirection.STOP);
            }
        }

        for (GraphicPanzer.PanzerElement element : logicBullet.getLogicPanzer().getMyLevel().getElements())
            if (getBoundsInParent().intersects(element.getBoundsInParent())) {
                setTranslateX(-15);
                setTranslateY(-15);
                logicBullet.setVector(PanzerDirection.STOP);
            }

    }

//____________________________________________________________________________________________________________________________________

}


