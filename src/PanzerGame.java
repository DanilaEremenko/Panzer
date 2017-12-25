import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

//Класс, в котором запускается игра
public class PanzerGame extends Application {

    static int sceneHeight = 800;
    static int sceneWidt = 900;
    private Level myLevel;


    @Override
    public void start(Stage primaryStage) throws Exception {


        myLevel = new Level("levels/LevelOne.txt");

        primaryStage.setScene(myLevel.getScene());
        primaryStage.show();

        //Постоянно вызывающийся метод
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (LogicPanzer logicPanzer : myLevel.getLogicPanzers()) {
                    logicPanzer.move();
                    for (LogicBullet logicBullet : logicPanzer.getBullets())
                        logicBullet.move();
                }


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

