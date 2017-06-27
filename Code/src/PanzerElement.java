import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class PanzerElement extends Pane {
    static PanzerElement gorizontal=PanzerElement.generateGorizontal(PanzerGame.sceneWidt);
    static PanzerElement gorizontal2=PanzerElement.generateGorizontal(PanzerGame.sceneWidt);
    static PanzerElement vertical=PanzerElement.generateVertical(PanzerGame.sceneHeight);
    static PanzerElement vertical2=PanzerElement.generateVertical(PanzerGame.sceneHeight);

    public static PanzerElement GenerateG(int width, int height) {
        Rectangle rect = new Rectangle(width, 30, Color.BLACK);
        Rectangle rect2 = new Rectangle(30, height,Color.BLACK);
        PanzerElement element = new PanzerElement();
        element.getChildren().addAll(rect, rect2);
        return element;
    }

    public static PanzerElement generateVertical(int height) {
        Rectangle rect = new Rectangle(20, height,Color.BLACK);
        PanzerElement element = new PanzerElement();
        element.getChildren().addAll(rect);
        return element;
    }

    public static PanzerElement generateGorizontal(int width) {
        Rectangle rect = new Rectangle(width, 20,Color.BLACK);
        PanzerElement element = new PanzerElement();
        element.getChildren().addAll(rect);
        return element;
    }
}
