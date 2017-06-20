import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Rocket extends Pane {
    public Point2D velocity = new Point2D(0, 0);
    Rectangle rect;

    public Rocket() {
        rect = new Rectangle(20, 20, Color.RED);
        velocity = new javafx.geometry.Point2D(0, 0);
        setTranslateY(300);
        setTranslateX(220);
        getChildren().addAll(rect);

    }

    public void moveX(int value) {
        for (int i = 0; i < value; i++) {
            setTranslateX(getTranslateX() + 1);
        }

    }

    public void moveY(int value) {
        boolean moveDown = value > 0 ? true : false;//Если объект больше нуля движется вниз
        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall w : FlappyBird.walls) {
//Если граница об
                if (this.getBoundsInParent().intersects(w.getBoundsInParent()))//Граница персонажа
                {
                    if (moveDown){
                        //setTranslateY(getTranslateY() - 1);
                        }
                    else {
                        setTranslateY(getTranslateY() + 1);
                        return;
                    }
                }

            }
            if (getTranslateY() < 0) {
                setTranslateY(0);
            }
            if (getTranslateY() > 580) {
                setTranslateY(580);
            }

            setTranslateY(getTranslateY() + (moveDown ? 1 : -1));//текущая коордиата и прибавить или отнять 1
        }

    }

    public void right(){
        setTranslateX(getTranslateX()+15);
    }


    public void left(){setTranslateX(getTranslateX()-15);}

    public void up(){setTranslateY(getTranslateY()-15);}

    public void down(){setTranslateY(getTranslateY()+15);}

    public void go(){setTranslateY(getTranslateY()-2);}

}
