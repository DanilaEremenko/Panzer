package Graphic;

import Controller.Game;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Класс для создания препеятствий
public class GraphicObstacles extends Pane {

    private static GraphicObstacles UP_GORIZONTAL = GraphicObstacles.generateGorizontal(Game.sceneWidt, 0, 100);
    private static GraphicObstacles DOWN_GORIZONTAL = GraphicObstacles.generateGorizontal(Game.sceneWidt, 0, Game.sceneHeight - 20);
    private static GraphicObstacles LEFT_VERTICAL = GraphicObstacles.generateVertical(Game.sceneHeight, 0, 0);
    private static GraphicObstacles RIGHT_VERTICAL = GraphicObstacles.generateVertical(Game.sceneHeight, Game.sceneWidt - 20, 0);


    private GraphicObstacles() {
    }

    public static GraphicObstacles generateRectangle(int side) {
        Rectangle rect = new Rectangle(side, side, Color.BLACK);
        GraphicObstacles element = new GraphicObstacles();
        element.getChildren().addAll(rect);
        return element;
    }

    public static GraphicObstacles generateVertical(int height, int X, int Y) {
        Rectangle rect = new Rectangle(20, height, Color.BLACK);
        GraphicObstacles element = new GraphicObstacles();
        element.getChildren().addAll(rect);
        element.setTranslateX(X);
        element.setTranslateY(Y);
        return element;
    }

    public static GraphicObstacles generateGorizontal(int width, int X, int Y) {
        Rectangle rect = new Rectangle(width, 20, Color.BLACK);
        GraphicObstacles element = new GraphicObstacles();
        element.getChildren().addAll(rect);
        element.setTranslateX(X);
        element.setTranslateY(Y);
        return element;
    }


    public static GraphicObstacles getLeftVertical() {
        return LEFT_VERTICAL;
    }

    public static GraphicObstacles getRightVertical() {
        return RIGHT_VERTICAL;
    }

    public static GraphicObstacles getUpGorizontal() {
        return UP_GORIZONTAL;
    }

    public static GraphicObstacles getDownGorizontal() {
        return DOWN_GORIZONTAL;
    }
}
