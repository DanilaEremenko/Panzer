import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class PanzerGame extends Application {
    static int sceneHeight = 700;
    static int sceneWidt = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        PanzerElement.gorizontal.setTranslateY(sceneHeight - 20);
        PanzerElement.vertical.setTranslateX(sceneWidt - 20);
        Panzer panzer = new Panzer(Color.GREEN);
        panzer.setTranslateX(200);
        panzer.setTranslateY(200);
        Panzer panzer2 = new Panzer(Color.RED);
        panzer2.setTranslateY(200);
        panzer.opponent = panzer2;
        panzer2.opponent = panzer;
        Panzer.elements.add(PanzerElement.GenerateG(100, 100));
        Panzer.elements.get(0).setTranslateX(300);
        Panzer.elements.get(0).setTranslateY(300);
        Panzer.elements.add(PanzerElement.vertical);
        Panzer.elements.add(PanzerElement.vertical2);
        Panzer.elements.add(PanzerElement.gorizontal);
        Panzer.elements.add(PanzerElement.gorizontal2);
        for (int i = 0; i < 10; i++) {
            panzer.bullets.add(new PanzerBullet());
            panzer2.bullets.add(new PanzerBullet());
            root.getChildren().addAll(panzer.bullets.get(i), panzer2.bullets.get(i));
        }
        for (PanzerElement element : Panzer.elements)
            root.getChildren().addAll(element);


        panzer2.setTranslateX(300);
        root.getChildren().addAll(panzer, panzer2);
        Scene scene = new Scene(root, sceneWidt, sceneHeight);


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int k = 0;
                for (PanzerBullet bullet : panzer.bullets) {
                    bullet.move(panzer2);
                }
                for (PanzerBullet bullet : panzer2.bullets) {
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

                    if (event.getCode() == KeyCode.ENTER)
                        panzer.fire(panzer.bullets);
                    if (event.getCode() == KeyCode.SPACE)
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
