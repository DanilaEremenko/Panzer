import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestVector extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        VectrorsGift vectrorsGift = new VectrorsGift();
        VectorsBullet bullet = new VectorsBullet();
        root.getChildren().addAll(bullet, vectrorsGift);
        Scene scene = new Scene(root, 600, 600);
        scene.setOnMouseMoved(event -> {
            bullet.setTarget(event.getSceneX(), event.getSceneY());
        });
        scene.setOnMouseClicked(event -> {
            bullet.velocity = new Point2D(0, 0);
        });


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.move();
                if (bullet.getBoundsInParent().intersects(vectrorsGift.getBoundsInParent())) {
                    vectrorsGift.generateNew();
                    bullet.sizePlus();
                }

            }
        };
        timer.start();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
