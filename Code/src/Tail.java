import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tail extends Pane {

    public Tail(){
        getChildren().addAll(new Rectangle(20,20, Color.BLACK));
    }

}
