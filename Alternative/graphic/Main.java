package graphic;

import geometry.Point;
import geometry.Vector;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import units.Bullet;
import units.Panzer;
import units.PanzerType;

import java.util.ArrayList;
import java.util.List;

class BulletView extends Circle {
    Bullet bullet;

    BulletView(Bullet bullet) {
        super(5, Color.RED);
        this.bullet = bullet;
        setTranslateX(bullet.getPoint().x);
        setTranslateY(bullet.getPoint().y);
    }

    public void move() {
        bullet.move();
        setTranslateX(bullet.getPoint().x);
        setTranslateY(bullet.getPoint().y);
    }
}

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        game.Player player = new game.GeneralPlayer(new Point(0, 0),
                new Panzer(PanzerType.FAST),
                new Vector(0, 0)
        );
        List<BulletView> bullets = new ArrayList<>();
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle rectangle = new Rectangle(30, 30);

        rectangle.setFill(Color.BLUE);
        rectangle.setRotate(1);
        pane.getChildren().add(rectangle);

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case W:
                    player.drive(0.5);
                    break;
                case S:
                    player.drive(-0.5);
                    break;
                case D:
                    player.rotate(Math.PI / 20);
                    System.out.println(player.getRotationDegrees());
                    break;
                case A:
                    player.rotate(-Math.PI / 20);
                    break;
                case SPACE:
                    Bullet bullet = player.fire();
                    if (bullet != null) {
                        BulletView bulletView = new BulletView(bullet);
                        bullets.add(bulletView);
                        pane.getChildren().add(bulletView);
                    }
                    break;
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (BulletView bulletView : bullets) {
                    bulletView.move();
                }
                player.move();
                rectangle.setRotate(player.getRotationDegrees());
                rectangle.setTranslateX(player.getPoint().x - rectangle.getWidth() / 2);
                rectangle.setTranslateY(player.getPoint().y - rectangle.getHeight() / 2);
            }
        };
        animationTimer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
