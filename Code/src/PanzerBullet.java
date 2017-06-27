import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PanzerBullet extends Pane {
    public int speed = 5;
    public String vector = "R";

    public PanzerBullet() {
        Rectangle bullet = new Rectangle(10, 10, Color.BLACK);
        setTranslateX(-15);
        setTranslateY(-15);
        getChildren().addAll(bullet);

    }

    public void move(Panzer vrag) {
        if (vector == "R")
            setTranslateX(getTranslateX() + speed);
        else if (vector == "L")
            setTranslateX(getTranslateX() - speed);
        else if (vector == "D")
            setTranslateY(getTranslateY() + speed);
        else if (vector == "U")
            setTranslateY(getTranslateY() - speed);
        if (getBoundsInParent().intersects(vrag.getBoundsInParent())) {
            vrag.health--;
            setTranslateY(-15);
            setTranslateX(-15);
        }

    }


}


