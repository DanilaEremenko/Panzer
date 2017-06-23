import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class SnakeGift extends Pane {


    SnakeGift(){
        getChildren().addAll(new Rectangle(20,20, Color.RED));
    }

    public void generateNew(){
        setTranslateX(new Random().nextInt(580 ));
        setTranslateY(new Random().nextInt(580 ));

    }


}
