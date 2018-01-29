package Graphic;

import Logic.LogicBullet;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Класс для создания пуль и управления их траекторией
public class GraphicBullet extends Pane implements GraphicElement {
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

    @Override
    public void drawing() {
        setTranslateX(logicBullet.getTranslateX());
        setTranslateY(logicBullet.getTranslateY());
        for (GraphicPanzer opponent : logicBullet.getLogicPanzer().getMyLevel().getGraphicPanzers()) {
            if (getBoundsInParent().intersects(opponent.getBoundsInParent()) && opponent.getLogicPanzer() != logicBullet.getLogicPanzer()) {
                opponent.getLogicPanzer().takeDamage(logicBullet);
                logicBullet.setOnDefaultPosition();
                logicBullet.setCurrentSpeed(0);
            }
        }

        for (GraphicObstacles element : logicBullet.getLogicPanzer().getMyLevel().getObstacles())
            if (getBoundsInParent().intersects(element.getBoundsInParent())) {
                logicBullet.setOnDefaultPosition();
                logicBullet.setCurrentSpeed(0);
            }

    }

//____________________________________________________________________________________________________________________________________

}


