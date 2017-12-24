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



            }

        };
        timer.start();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

