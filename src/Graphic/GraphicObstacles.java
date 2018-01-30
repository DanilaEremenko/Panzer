package Graphic;

import Controller.Game;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Класс для создания препеятствий
public class GraphicObstacles extends Pane {

    private GraphicObstacles() {
    }

    public static GraphicObstacles generateRectangle(double side) {
        Rectangle rect = new Rectangle(side, side, Color.BLACK);
        GraphicObstacles element = new GraphicObstacles();
        element.getChildren().addAll(rect);
        return element;
    }

    public static GraphicObstacles generateVertical(double height, double X, double Y) {
        Rectangle rect = new Rectangle(20, height, Color.BLACK);
        GraphicObstacles element = new GraphicObstacles();
        element.getChildren().addAll(rect);
        element.setTranslateX(X);
        element.setTranslateY(Y);
        return element;
    }

    public static GraphicObstacles generateGorizontal(double width, double X, double Y) {
        Rectangle rect = new Rectangle(width, 20, Color.BLACK);
        GraphicObstacles element = new GraphicObstacles();
        element.getChildren().addAll(rect);
        element.setTranslateX(X);
        element.setTranslateY(Y);
        return element;
    }


//____________________________________________________________________________________________________________________________________

//для создания рамки

    public static GraphicObstacles getLeftVertical(double sceneWidth,double sceneHeight) {
        return generateVertical(sceneHeight, 0, 0);
    }

    public static GraphicObstacles getRightVertical(double sceneWidth, double sceneHeight) {
        return generateVertical(sceneHeight, sceneWidth - 20, 0);
    }

    public static GraphicObstacles getUpGorizontal(double sceneWidth,double sceneHeight) {
        return generateGorizontal(sceneWidth, 0, 100);
    }

    public static GraphicObstacles getDownGorizontal(double sceneWidth,double sceneHeight) {
        return generateGorizontal(sceneWidth, 0, sceneHeight - 20);
    }
}
