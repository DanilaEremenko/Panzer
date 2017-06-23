import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.awt.geom.Point2D;

public class VectorsBullet extends Pane {
    public javafx.geometry.Point2D velocity = new javafx.geometry.Point2D(0, 0);

    int width = 20;

    public VectorsBullet() {
        getChildren().addAll(new Rectangle(width, 2, Color.RED));

    }

    public void setTarget(double x, double y) {
        velocity = new javafx.geometry.Point2D(x, y).subtract(getTranslateX(), getTranslateY()).normalize().multiply(5);
        double angle = calcAngle(velocity.getX(), velocity.getY());
        getTransforms().clear();
        getTransforms().add(new Rotate(angle, 0, 0));

    }

    public void move() {
        VectrorsGift vectrorsGift = new VectrorsGift();
        setTranslateX(getTranslateX() + velocity.getX());
        setTranslateY(getTranslateY() + velocity.getY());

    }

    public double calcAngle(double vecX, double vecY) {
        double angle = new javafx.geometry.Point2D(vecX, vecY).angle(1, 0);
        return vecY > 0 ? angle : -angle;
    }

    public void sizePlus() {
        width += 5;
        getChildren().addAll(new Rectangle(width, 2, Color.RED));
    }


}

