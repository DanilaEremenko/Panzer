package Graphic;

import Controller.Game;
import Controller.Level;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import Logic.*;

//Графическое отображение танка
public class GraphicPanzer extends Pane {
    private HealthLabel healthLabel;
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
        healthLabel = new HealthLabel(this, 10, 10);

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
    //Д
    //В
    //И
    //Ж
    //Е
    //Н
    //И
    //E


    //Постоянно работающий метод движения танка
    public void move() {

        if (logicPanzer.isNeedDraw()) {
            healthLabel.update();
            logicPanzer.setNeedDraw(false);
        }
        transformPanzer();
        setTranslateX(logicPanzer.getTranslateX());
        setTranslateY(logicPanzer.getTranslateY());
        checkArea();


    }

    //Проверка того, что танк может ехать
    private void checkArea() {
        for (PanzerElement element : level.getElements())
            if (getBoundsInParent().intersects(element.getBoundsInParent()))
                logicPanzer.comeBackIfCanNotMove();

        for (GraphicPanzer graphicPanzer : level.getGraphicPanzers())
            if (getBoundsInParent().intersects(graphicPanzer.getBoundsInParent()) && graphicPanzer != this)
                logicPanzer.comeBackIfCanNotMove();
    }

    public void setTranslateLabel(double x, double y){
        healthLabel.setTranslateX(x);
        healthLabel.setTranslateY(y);
    }

    //Метод поворачивающий танк
    private void transformPanzer() {

        getTransforms().add(new Rotate(logicPanzer.getAngleOfTurn(),
                logicPanzer.getWidthBody() / 2,
                logicPanzer.getHightBody() / 2));
        logicPanzer.setAngleOfTurn(0);

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

    public HealthLabel getHealthLabel() {
        return healthLabel;
    }

    //____________________________________________________________________________________________________________________________________

    //Класс для создания препеятствий
    public static class PanzerElement extends Pane {

        private static PanzerElement UP_GORIZONTAL = PanzerElement.generateGorizontal(Game.sceneWidt,0,100);
        private static PanzerElement DOWN_GORIZONTAL = PanzerElement.generateGorizontal(Game.sceneWidt,0, Game.sceneHeight-20);
        private static PanzerElement LEFT_VERTICAL = PanzerElement.generateVertical(Game.sceneHeight,0,0);
        private static PanzerElement RIGHT_VERTICAL = PanzerElement.generateVertical(Game.sceneHeight, Game.sceneWidt-20,0);


        private PanzerElement(){}

        public static PanzerElement generateRectangle(int side) {
            Rectangle rect = new Rectangle(side, side, Color.BLACK);
            PanzerElement element = new PanzerElement();
            element.getChildren().addAll(rect);
            return element;
        }

        public static PanzerElement generateVertical(int height, int X, int Y) {
            Rectangle rect = new Rectangle(20, height,Color.BLACK);
            PanzerElement element = new PanzerElement();
            element.getChildren().addAll(rect);
            element.setTranslateX(X);
            element.setTranslateY(Y);
            return element;
        }

        public static PanzerElement generateGorizontal(int width, int X, int Y) {
            Rectangle rect = new Rectangle(width, 20,Color.BLACK);
            PanzerElement element = new PanzerElement();
            element.getChildren().addAll(rect);
            element.setTranslateX(X);
            element.setTranslateY(Y);
            return element;
        }


        public static PanzerElement getLeftVertical() {
            return LEFT_VERTICAL;
        }

        public static PanzerElement getRightVertical() {
            return RIGHT_VERTICAL;
        }

        public static PanzerElement getUpGorizontal() {
            return UP_GORIZONTAL;
        }

        public static PanzerElement getDownGorizontal() {
            return DOWN_GORIZONTAL;
        }
    }
}


