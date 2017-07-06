import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Класс для создания препеятствий
public class PanzerElement extends Pane {

    static PanzerElement gorizontal=PanzerElement.generateGorizontal(PanzerGame.sceneWidt,0,0);
    static PanzerElement gorizontal2=PanzerElement.generateGorizontal(PanzerGame.sceneWidt,0,PanzerGame.sceneHeight-20);
    static PanzerElement vertical=PanzerElement.generateVertical(PanzerGame.sceneHeight,0,0);
    static PanzerElement vertical2=PanzerElement.generateVertical(PanzerGame.sceneHeight,PanzerGame.sceneWidt-20,0);

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

}
