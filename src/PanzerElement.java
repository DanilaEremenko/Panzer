import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Класс для создания препеятствий
public class PanzerElement extends Pane {

    private static PanzerElement UP_GORIZONTAL =PanzerElement.generateGorizontal(PanzerGame.sceneWidt,0,0);
    private static PanzerElement DOWN_GORIZONTAL =PanzerElement.generateGorizontal(PanzerGame.sceneWidt,0,PanzerGame.sceneHeight-20);
    private static PanzerElement LEFT_VERTICAL =PanzerElement.generateVertical(PanzerGame.sceneHeight,0,0);
    private static PanzerElement RIGHT_VERTICAL =PanzerElement.generateVertical(PanzerGame.sceneHeight,PanzerGame.sceneWidt-20,0);

    private PanzerElement(){}

    public static PanzerElement generateRectangle(int side) {
        Rectangle rect = new Rectangle(side, side, Color.BLACK);
        PanzerElement element = new PanzerElement();
        element.getChildren().addAll(rect);
        return element;
    }

    public static PanzerElement generateVertical(int height,int X,int Y) {
        Rectangle rect = new Rectangle(20, height,Color.BLACK);
        PanzerElement element = new PanzerElement();
        element.getChildren().addAll(rect);
        element.setTranslateX(X);
        element.setTranslateY(Y);
        return element;
    }

    public static PanzerElement generateGorizontal(int width,int X,int Y) {
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
