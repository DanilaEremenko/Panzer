import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;


public class PanzerGame extends Application {

    static int sceneHeight = 700;
    static int sceneWidt = 900;
    private Levels myLevel;


    @Override
    public void start(Stage primaryStage) throws Exception {


        myLevel = new Levels("levels/LevelOne.txt");


        primaryStage.setScene(myLevel.getMenu());
        primaryStage.show();
        PanzerMenu.newGame.setOnMouseClicked(event -> primaryStage.setScene(myLevel.getScene()));
        PanzerMenu.exit.setOnMouseClicked(event -> primaryStage.close());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (GraphicPanzer panzer : myLevel.getGraphicPanzers())
                    for (GraphicBullet bullet : panzer.getBullets())
                        bullet.move();

                for (GraphicPanzer panzer : myLevel.getGraphicPanzers())
                    panzer.move();


                myLevel.getScene().setOnKeyPressed(event -> {
                    switch (event.getCode()) {

                        case RIGHT:
                            myLevel.getGraphicPanzers().get(0).setVector(PanzerDirection.R);
                            break;
                        case LEFT:
                            myLevel.getGraphicPanzers().get(0).setVector(PanzerDirection.L);
                            break;
                        case UP:
                            myLevel.getGraphicPanzers().get(0).setVector(PanzerDirection.U);
                            break;
                        case DOWN:
                            myLevel.getGraphicPanzers().get(0).setVector(PanzerDirection.D);
                            break;
                        case ENTER:
                            myLevel.getGraphicPanzers().get(0).fire();
                            break;

                        case A:
                            myLevel.getGraphicPanzers().get(1).setVector(PanzerDirection.L);
                            break;
                        case D:
                            myLevel.getGraphicPanzers().get(1).setVector(PanzerDirection.R);
                            break;
                        case W:
                            myLevel.getGraphicPanzers().get(1).setVector(PanzerDirection.U);
                            break;
                        case S:
                            myLevel.getGraphicPanzers().get(1).setVector(PanzerDirection.D);
                            break;
                        case SPACE:
                            myLevel.getGraphicPanzers().get(1).fire();
                            break;

                        case ESCAPE:
                            primaryStage.setScene(myLevel.getMenu());

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
