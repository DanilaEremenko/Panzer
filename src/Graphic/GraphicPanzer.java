package Graphic;

import Controller.Level;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import Logic.*;

//Графическое отображение танка
public class GraphicPanzer extends Pane implements GraphicElement {
    private LogicPanzer logicPanzer;//Танк, который отрисовывается
    private GraphicBullet[] bullets;//Массив графических пуль
    private Level level;
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


    public GraphicPanzer(LogicPanzer logicPanzer, Color color, Level level) {

        this.level = level;
        this.logicPanzer = logicPanzer;
        makeBody(color);
        setTranslateX(logicPanzer.getTranslateX());
        setTranslateY(logicPanzer.getTranslateY());

        bullets = new GraphicBullet[logicPanzer.getBullets().length];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = new GraphicBullet(logicPanzer.getBullets()[i]);


    }

    private void makeBody(Color color) {
        Rectangle gun = new Rectangle(logicPanzer.getWidthGun(), logicPanzer.getHightGun(), Color.BLACK);
        Rectangle body = new Rectangle(logicPanzer.getWidthBody(), logicPanzer.getHightBody(), color);
        gun.setTranslateX(body.getTranslateX() + logicPanzer.getWidthBody());
        gun.setTranslateY(body.getTranslateY() + (logicPanzer.getWidthBody() - logicPanzer.getHightGun()) / 2);
        getChildren().addAll(body, gun);


    }

//____________________________________________________________________________________________________________________________________
    // И
    // Г
    // Р
    // О
    // В
    // Ы
    // Е


    //Постоянно работающий метод движения танка
    @Override
    public void drawing() {
        if (logicPanzer.isNeedDraw()) {
            logicPanzer.setNeedDraw(false);
        }
        setTranslateX(logicPanzer.getTranslateX());
        setTranslateY(logicPanzer.getTranslateY());
        checkArea();
    }

    //Проверка того, что танк может ехать
    private void checkArea() {
        for (GraphicObstacles obstacle : level.getObstacles())
            if (getBoundsInParent().intersects(obstacle.getBoundsInParent()))
                logicPanzer.comeBackIfCanNotMove();

        for (GraphicPanzer graphicPanzer : level.getGraphicPanzers())
            if (getBoundsInParent().intersects(graphicPanzer.getBoundsInParent()) && graphicPanzer != this)
                logicPanzer.comeBackIfCanNotMove();
    }


    //Метод поворачивающий танк
    public void transformPanzer(double angle) {
        getTransforms().add(new Rotate(angle*57.3,//TODO!!!!
                logicPanzer.getWidthBody() / 2,
                logicPanzer.getHightBody() / 2));

    }
//____________________________________________________________________________________________________________________________________
    //Г
    //Е
    //Т
    //Е
    //Р
    //Ы

    public LogicPanzer getLogicPanzer() {
        return logicPanzer;
    }

    public GraphicBullet[] getBullets() {
        return bullets;
    }


//____________________________________________________________________________________________________________________________________

}


