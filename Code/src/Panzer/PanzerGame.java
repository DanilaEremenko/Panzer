import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class PanzerGame extends Application {
    static int sceneHeight = 700;
    static int sceneWidt = 900;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Levels.level(Levels.LevelNumbers.ONE);

        for (int i = 0; i < Panzer.bulletDigit; i++)
            root.getChildren().addAll(Levels.panzer.getBullets().get(i), Levels.panzer2.getBullets().get(i));

        for (PanzerElement element : Panzer.elements)
            root.getChildren().addAll(element);

        root.getChildren().addAll(Levels.panzer, Levels.panzer2);
        Scene scene = new Scene(root, sceneWidt, sceneHeight);
        Scene menu=new Scene(PanzerMenu.menuBox,sceneWidt,sceneHeight);
        primaryStage.setScene(menu);
        primaryStage.show();
        PanzerMenu.newGame.setOnMouseClicked(event -> primaryStage.setScene(scene));
        PanzerMenu.exit.setOnMouseClicked(event -> primaryStage.close());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (PanzerBullet bullet : Levels.panzer.getBullets())
                    bullet.move(Levels.panzer.getOpponents());

                for (PanzerBullet bullet : Levels.panzer2.getBullets())
                    bullet.move(Levels.panzer2.getOpponents());

                Levels.panzer.move();
                Levels.panzer2.move();


                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.RIGHT)
                       Levels.panzer.setVector(PanzerDirection.R);
                    else if (event.getCode() == KeyCode.LEFT)
                        Levels.panzer.setVector(PanzerDirection.L);
                    else if (event.getCode() == KeyCode.UP)
                        Levels.panzer.setVector(PanzerDirection.U);
                    else if (event.getCode() == KeyCode.DOWN)
                        Levels.panzer.setVector(PanzerDirection.D);

                    if (event.getCode() == KeyCode.A)
                        Levels.panzer2.setVector(PanzerDirection.L);
                    else if (event.getCode() == KeyCode.D)
                        Levels.panzer2.setVector(PanzerDirection.R);
                    else if (event.getCode() == KeyCode.W)
                        Levels.panzer2.setVector(PanzerDirection.U);
                    else if (event.getCode() == KeyCode.S)
                        Levels.panzer2.setVector(PanzerDirection.D);

                    if (event.getCode() == KeyCode.ENTER)
                        Levels.panzer.fire(Levels.panzer.getBullets());
                    if (event.getCode() == KeyCode.SPACE)
                        Levels.panzer2.fire(Levels.panzer2.getBullets());

                    if (event.getCode() == KeyCode.ESCAPE) {
                        primaryStage.setScene(menu);

                    }

                });

            }

        };
        timer.start();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

//Для танка управляемого мышкой в метод handle
//                panzer2.moveMouse();
//                scene.setOnMouseMoved(event -> {
//                    panzer2.setTarget(event.getSceneX(), event.getSceneY());
//                });
//                scene.setOnMouseClicked(event -> {
//                    panzer2.velocity = new Point2D(0, 0);
//                });
