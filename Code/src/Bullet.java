import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Bullet extends Pane {
    Rectangle rect = new Rectangle(5, 5);



    Bullet() {
        rect.setFill(Color.BLACK);
        setTranslateY(305);
        setTranslateX(220);
        getChildren().addAll(rect);

    }


    public void right() {
        setTranslateX(getTranslateX() + 15);
        if (getTranslateX() > 580)
            setTranslateX(580);
    }


    public void left() {
        setTranslateX(getTranslateX() - 15);
        if (getTranslateX() < 0)
            setTranslateX(0);
    }

    public void up() {
        setTranslateY(getTranslateY() - 15);
    }

    public void down() {
        setTranslateY(getTranslateY() + 15);
    }

    public void go() {
        setTranslateY(getTranslateY() - 2);
    }

    public void Pow(){
        setTranslateY(getTranslateY()-50);
    }

}


