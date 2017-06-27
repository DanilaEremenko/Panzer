import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.security.Key;
import java.util.ArrayList;


public class PanzerGame extends Application {
    static int sceneHeight = 600;
    static int sceneWidt = 600;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Panzer panzer = new Panzer(Color.GREEN);
        Panzer panzer2 = new Panzer(Color.RED);
        panzer.opponent = panzer2;
        panzer2.opponent = panzer;
        for (int i = 0; i < 10; i++) {
            panzer.bullets.add(new PanzerBullet());
            panzer2.bullets.add(new PanzerBullet());
            root.getChildren().addAll(panzer.bullets.get(i),panzer2.bullets.get(i));

        }

        panzer2.setTranslateX(300);
        root.getChildren().addAll(panzer, panzer2);
        Scene scene = new Scene(root, 600, 600);


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int k=0;
                for (PanzerBullet bullet:panzer.bullets) {
                    bullet.move(panzer2);
                }
                for (PanzerBullet bullet:panzer2.bullets) {
                    bullet.move(panzer);
                }
                panzer.move();
                panzer2.move();
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.RIGHT)
                        panzer.setVector("R");
                    else if (event.getCode() == KeyCode.LEFT)
                        panzer.setVector("L");
                    else if (event.getCode() == KeyCode.UP)
                        panzer.setVector("U");
                    else if (event.getCode() == KeyCode.DOWN)
                        panzer.setVector("D");

                    if (event.getCode() == KeyCode.A)
                        panzer2.setVector("L");
                    else if (event.getCode() == KeyCode.D)
                        panzer2.setVector("R");
                    else if (event.getCode() == KeyCode.W)
                        panzer2.setVector("U");
                    else if (event.getCode() == KeyCode.S)
                        panzer2.setVector("D");

                    if (event.getCode()==KeyCode.ENTER)
                        panzer.fire(panzer.bullets);
                    if(event.getCode()==KeyCode.SPACE)
                        panzer2.fire(panzer2.bullets);






                });

            }

        };
        timer.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
