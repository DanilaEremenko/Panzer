import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SpaceRocket extends Pane {
    private Point2D velocity = new Point2D(0, 0);
    private double speed=10;
    Rectangle rect;

    public SpaceRocket() {
        rect = new Rectangle(200, 20, Color.RED);
        velocity = new javafx.geometry.Point2D(0, 0);
        setTranslateY(570);
        setTranslateX(0);
        getChildren().addAll(rect);

    }


    public void right() {
        setTranslateX(getTranslateX() + 200);
        if (getTranslateX() > 400)
            setTranslateX(400);
    }


    public void left() {
        setTranslateX(getTranslateX() - 200);
        if (getTranslateX() < 0)
            setTranslateX(0);
    }


    public void go() {
        setTranslateY(getTranslateY() - speed);

        for (SpaceMeteor spaceMeteor : SpaceAdventure.spaceMeteors) {
            if (getTranslateY() == spaceMeteor.getTranslateY() + 20) {
                if (getTranslateX() == spaceMeteor.getTranslateX()) {
                    setTranslateY(getTranslateY() + 20);
                    if (SpaceAdventure.score > SpaceAdventure.bestScore)
                        SpaceAdventure.bestScore = SpaceAdventure.score;
                    SpaceAdventure.score = 0;
                    return;
                } else SpaceAdventure.score++;
            }
        }
    }

}


