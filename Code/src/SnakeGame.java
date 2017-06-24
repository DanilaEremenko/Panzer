import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by danil on 23.06.2017.
 */
public class SnakeGame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Snake head = new Snake();
        head.setTranslateX(200);
        head.setTranslateY(200);
        SnakeGift snakeGift = new SnakeGift();
        ArrayList<Snake> snakeTails = new ArrayList<Snake>();
        snakeTails.add(new Snake(head));
        root.getChildren().addAll(head, snakeTails.get(snakeTails.size() - 1), snakeGift);
        Scene scene = new Scene(root, 600, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                head.setTarget(head.getTranslateX() - 20, head.getTranslateY());
                head.vector = "L";
            }
            if (event.getCode() == KeyCode.RIGHT) {
                head.setTarget(head.getTranslateX() + 20, head.getTranslateY());
                head.vector = "R";
            }
            if (event.getCode() == KeyCode.UP) {
                head.setTarget(head.getTranslateX(), head.getTranslateY() - 20);
                head.vector = "U";
            }
            if (event.getCode() == KeyCode.DOWN) {
                head.setTarget(head.getTranslateX(), head.getTranslateY() + 20);
                head.vector = "D";
            }

//            for (int i = 1; i < snakeTails.size(); i++)
//                snakeTails.get(i).setTarget(snakeTails.get(i - 1));

        });


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                head.move();
                snakeTails.get(0).move(head);
                for (int i=1;i<snakeTails.size();i++)
                    snakeTails.get(i).move(snakeTails.get(i-1));

                if (head.getBoundsInParent().intersects(snakeGift.getBoundsInParent())) {
                    snakeGift.generateNew();
                    snakeTails.add(new Snake(snakeTails.get(snakeTails.size() - 1)));
//                    snakeTails.get(snakeTails.size() - 1).move();
                    root.getChildren().addAll(snakeTails.get(snakeTails.size() - 1));
                }

            }
        };
        timer.start();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
