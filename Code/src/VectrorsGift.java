import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Random;

public class VectrorsGift extends Pane {


    VectrorsGift(){
        getChildren().addAll(new Rectangle(20,20, Color.RED));

    }

    public void generateNew(){
        setTranslateX(new Random().nextInt(580 ));
        setTranslateY(new Random().nextInt(580 ));

    }



}
