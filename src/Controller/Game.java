package Controller;

import Graphic.GraphicBullet;
import Graphic.GraphicPanzer;
import Logic.LogicBullet;
import Logic.LogicPanzer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

//Класс, в котором запускается игра
public class Game extends Application {

    public static int sceneHeight = 800;
    public static int sceneWidt = 900;
    private Level myLevel;


    @Override
    public void start(Stage primaryStage) throws Exception {


        myLevel = new Level("levels/LevelTwo.txt");

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
                    for (GraphicBullet graphicBullet: panzer.getBullets())
                        graphicBullet.drawing();

                for (GraphicPanzer graphicPanzer: myLevel.getGraphicPanzers())
                    graphicPanzer.drawing();


            }

        };
        timer.start();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

