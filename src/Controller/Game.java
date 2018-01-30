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

    private Level myLevel;


    @Override
    public void start(Stage primaryStage) throws Exception {

        myLevel = new Level("levels/LevelTwo.txt");

        primaryStage.setScene(myLevel.getScene());
        primaryStage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animation();

            }

        };
        timer.start();

    }

    private void animation() {
        for (LogicPanzer logicPanzer : myLevel.getLogicPanzers()) {
            logicPanzer.move();
            for (LogicBullet logicBullet : logicPanzer.getBullets())
                logicBullet.move();
        }

        for (GraphicPanzer panzer : myLevel.getGraphicPanzers()) {
            panzer.drawing();
            for (GraphicBullet graphicBullet : panzer.getBullets())
                graphicBullet.drawing();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}

