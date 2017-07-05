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
            for (Panzer panzer : Levels.panzers)
                root.getChildren().addAll(panzer.getBullets().get(i));

        for (PanzerElement element : Panzer.elements)
            root.getChildren().addAll(element);

        for (Panzer panzer : Levels.panzers)
            root.getChildren().addAll(panzer);


        Scene scene = new Scene(root, sceneWidt, sceneHeight);
        Scene menu = new Scene(PanzerMenu.menuBox, sceneWidt, sceneHeight);
        primaryStage.setScene(menu);
        primaryStage.show();
        PanzerMenu.newGame.setOnMouseClicked(event -> primaryStage.setScene(scene));
        PanzerMenu.exit.setOnMouseClicked(event -> primaryStage.close());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Panzer panzer : Levels.panzers)
                    for (PanzerBullet bullet : panzer.getBullets())
                        bullet.move();

                for (Panzer panzer : Levels.panzers)
                    panzer.move();


                scene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {

                        case RIGHT:
                            Levels.panzers.get(0).setVector(PanzerDirection.R);
                            break;
                        case LEFT:
                            Levels.panzers.get(0).setVector(PanzerDirection.L);
                            break;
                        case UP:
                            Levels.panzers.get(0).setVector(PanzerDirection.U);
                            break;
                        case DOWN:
                            Levels.panzers.get(0).setVector(PanzerDirection.D);
                            break;
                        case ENTER:
                            Levels.panzers.get(0).fire();
                            break;

                        case A:
                            Levels.panzers.get(1).setVector(PanzerDirection.L);
                            break;
                        case D:
                            Levels.panzers.get(1).setVector(PanzerDirection.R);
                            break;
                        case W:
                            Levels.panzers.get(1).setVector(PanzerDirection.U);
                            break;
                        case S:
                            Levels.panzers.get(1).setVector(PanzerDirection.D);
                            break;
                        case SPACE:
                            Levels.panzers.get(1).fire();
                            break;

                        case ESCAPE:
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
