import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class SnakeGame extends Application {
    public int score = 0;
    static int sceneHeight=600;
    static int sceneWidt=600;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Snake head = new Snake();
        head.setTranslateX(200);
        head.setTranslateY(200);
        SnakeGift snakeGift = new SnakeGift();
        ArrayList<Snake> snakeTails = new ArrayList<Snake>();
        snakeTails.add(new Snake(head));
        score = snakeTails.size();
        Label label = new Label("Score = " + score);
        root.getChildren().addAll(head, snakeTails.get(snakeTails.size() - 1), snakeGift, label);
        Scene scene = new Scene(root,sceneWidt, sceneHeight);
        //ниже устанавливаем события для управления змеей
        head.setTarget(head.getTranslateX() + 20, head.getTranslateY());
        head.vector = "R";
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT && !head.getVector().equals("R")) {
                head.setTarget(head.getTranslateX() - 20, head.getTranslateY());
                head.vector = "L";
            }
            if (event.getCode() == KeyCode.RIGHT && !head.getVector().equals("L")) {
                head.setTarget(head.getTranslateX() + 20, head.getTranslateY());
                head.vector = "R";
            }
            if (event.getCode() == KeyCode.UP && !head.getVector().equals("D")) {
                head.setTarget(head.getTranslateX(), head.getTranslateY() - 20);
                head.vector = "U";
            }
            if (event.getCode() == KeyCode.DOWN && !head.getVector().equals("U")) {
                head.setTarget(head.getTranslateX(), head.getTranslateY() + 20);
                head.vector = "D";
            }
            if (event.getCode() == KeyCode.SPACE) {
                head.setTranslateX(250);
                head.setTranslateY(250);
            }

                //ДЛЯ ТЕСТИРОВАНИЯ
            if (event.getCode() == KeyCode.Z) {
                snakeTails.add(new Snake(snakeTails.get(snakeTails.size() - 1)));
                root.getChildren().addAll(snakeTails.get(snakeTails.size() - 1));
            }

        });
        scene.setOnMouseClicked(event -> {
            head.velocity = new Point2D(0, 0);
            head.vector = "STOP";
        });


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                head.move();
                snakeTails.get(0).move(head);
                for (int i = 1; i < snakeTails.size(); i++)
                    snakeTails.get(i).move(snakeTails.get(i - 1));
                for (int i = 0; i < snakeTails.size(); i++) {
                    if (head.getTranslateX() == snakeTails.get(i).getTranslateX() &&
                            head.getTranslateY() == snakeTails.get(i).getTranslateY())
                        head.vector = "STOP";
//              НЕ ЛУЧШИЙ ВАРИАНТ Т.К. ПЕРЕСЕКАЮТСЯ ПРИ БЫСТРОМ ПОВОРОТЕ НА 180 ГРАДУСОВ
//                    if (head.getBoundsInParent().intersects(snakeTails.get(i).getBoundsInParent()))

                }

                if (head.getBoundsInParent().intersects(snakeGift.getBoundsInParent())) {
                    snakeGift.generateNew();
                    snakeTails.add(new Snake(snakeTails.get(snakeTails.size() - 1)));
                    score = snakeTails.size();
                    label.setText("Score = " + score);
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
