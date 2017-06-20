import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Meteor extends Pane {
    Rectangle rect;
    public int width;//Длина стены



    public Meteor(int width) {
        this.width = width;
        rect = new Rectangle(width, 20);
        getChildren().addAll(rect);//Добавляем прямоугольник на панель

    }
}
