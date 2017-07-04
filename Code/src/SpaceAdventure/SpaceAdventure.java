import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class SpaceAdventure extends Application {
    public static Pane appRoot = new Pane();//Панель самого приложения
    public static Pane gameRoot = new Pane();//Поле стенок
    public static ArrayList<SpaceMeteor> spaceMeteors = new ArrayList<>();//Список помех

    SpaceRocket spaceRocket = new SpaceRocket();
    public static int score = 0;
    public static int bestScore = 0;
    public Label scoreLabel = new Label();
    public Label bestLabel = new Label();


    //Метод отвечающий за создание сцены
    public Parent createContent() {
        scoreLabel.setTranslateY(570);
        scoreLabel.setTranslateX(20);
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);
        bestLabel.setTranslateY(570);
        bestLabel.setTranslateX(120);
        bestLabel.setScaleX(2);
        bestLabel.setScaleY(2);


        gameRoot.setPrefSize(600, 600);

        for (int i = 0; i < 1000; i++) {
            int enter = 200;


            SpaceMeteor spaceMeteor = new SpaceMeteor(200);
            spaceMeteor.setTranslateY(-(i * 350 + 600));//Каждая стена через 350 пикселей+600 чтобы за пределы экрана
            spaceMeteor.setTranslateX(new Random().nextInt(3) * 200);
            spaceMeteors.add(spaceMeteor);

            SpaceMeteor spaceMeteor2 = new SpaceMeteor(200);//Вторая стена=-проем-первая стена
            spaceMeteor2.setTranslateY(-(i * 350 + 600));
            spaceMeteor2.setTranslateX(new Random().nextInt(3) * 200);
            while (spaceMeteor.getTranslateX() == spaceMeteor2.getTranslateX())
                spaceMeteor2.setTranslateX(new Random().nextInt(3) * 200);
            spaceMeteors.add(spaceMeteor2);
            gameRoot.getChildren().addAll(spaceMeteor, spaceMeteor2);


        }


        gameRoot.getChildren().add(spaceRocket);
        appRoot.getChildren().addAll(gameRoot, scoreLabel, bestLabel);
        return appRoot;
    }

    //Метод который вызывается каждый кадр
    public void update() {

        spaceRocket.go();


        scoreLabel.setText("Score " + score);
        bestLabel.setText("Best " + bestScore);

        //??????????
        spaceRocket.translateYProperty().addListener((ovs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset < 400) gameRoot.setLayoutY(-(offset - 400));
        });


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                spaceRocket.left();

            }
            if (event.getCode() == KeyCode.RIGHT) {
                spaceRocket.right();
            }
            if (event.getCode() == KeyCode.ESCAPE)
                primaryStage.close();


        });

        scene.setOnMouseClicked(event -> spaceRocket.setTranslateY(spaceRocket.getTranslateY() + 5));

        primaryStage.setScene(scene);
        primaryStage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
