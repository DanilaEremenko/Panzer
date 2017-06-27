import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.security.Key;

/**
 * Created by danil on 27.06.2017.
 */
public class PanzerGame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Panzer panzer = new Panzer(Color.GREEN);
        Panzer panzer2= new Panzer(Color.RED);
        panzer.opponent=panzer2;
        panzer2.opponent=panzer;
        panzer2.setTranslateX(300);
        Pane root = new Pane();
        root.getChildren().addAll(panzer,panzer2);
        Scene scene = new Scene(root, 600, 600);


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                panzer.move();
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.RIGHT)
                        panzer.vector="R";
                    else if (event.getCode() == KeyCode.LEFT)
                        panzer.vector="L";
                    else if (event.getCode() == KeyCode.UP)
                        panzer.vector="U";
                    else if (event.getCode() == KeyCode.DOWN)
                        panzer.vector="D";

                    if (event.getCode() == KeyCode.SPACE)
                        panzer.shot();
                });

            }

        };
        timer.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
