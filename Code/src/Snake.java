import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.awt.geom.RoundRectangle2D;

public class Snake extends Pane {
    public javafx.geometry.Point2D velocity = new javafx.geometry.Point2D(0, 0);
    int width = 20;

    public Snake() {
        Rectangle rect=new Rectangle(width,20,Color.RED);
        getChildren().addAll(rect);
    }

    public void setTarget(double x, double y) {
        velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(5);
        double angle = calcAngle(velocity.getX(), velocity.getY());
        getTransforms().clear();
        getTransforms().add(new Rotate(angle, 0, 0));

    }

    public void move() {
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());

    }

    public double calcAngle(double vecX, double vecY) {
        double angle = new javafx.geometry.Point2D(vecX, vecY).angle(1, 0);
        return vecY > 0 ? angle : -angle;
    }

    public void sizePlus() {
        width += 20;
        Rectangle rect=new Rectangle(width,20,Color.RED);
        getChildren().addAll(rect);

    }


}
