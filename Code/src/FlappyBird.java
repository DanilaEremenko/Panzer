import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends Application {

    public static Pane appRoot = new Pane();//Панель самого приложения
    public static Pane gameRoot = new Pane();//Поле стенок
    public static ArrayList<Wall> walls = new ArrayList<>();//Список стен, для проверки столкновений

    Bird bird = new Bird();
    public static int score = 0;
    public static int bestScore = 0;
    public Label scoreLabel = new Label();
    public Label bestLabel = new Label();


    //Метод отвечающий за создание сцены
    public Parent createContent() {
        scoreLabel.setTranslateX(20);
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);
        bestLabel.setTranslateX(120);
        bestLabel.setScaleX(2);
        bestLabel.setScaleY(2);


        gameRoot.setPrefSize(600, 600);

        for (int i = 0; i < 100; i++) {
            int enter = (int) (Math.random() * 100 + 50);//рандомная число (0-100) +50
            int height = new Random().nextInt(600 - enter);
            Wall wall = new Wall(height);
            wall.setTranslateX(i * 350 + 600);//Каждая стена через 350 пикселей+600 чтобы за пределы экрана
            wall.setTranslateY(0);
            walls.add(wall);

            Wall wall2 = new Wall(600 - enter - height);//Вторая стена=-проем-первая стена
            wall2.setTranslateX(i * 350 + 600);
            wall2.setTranslateY(height + enter);
            walls.add(wall2);
            gameRoot.getChildren().addAll(wall, wall2);


        }


        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot, scoreLabel, bestLabel);
        return appRoot;
    }

    //Метод который вызывается каждый кадр
    public void update() {

        if (bird.velocity.getY() < 5) {
            bird.velocity = bird.velocity.add(0, 1);
        }

        bird.moveX((int) bird.velocity.getX());
        bird.moveY((int) bird.velocity.getY());
        scoreLabel.setText("Score " + score);
        bestLabel.setText("Best " + bestScore);

        //??????????
        bird.translateXProperty().addListener((ovs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 200) gameRoot.setLayoutX(-(offset - 200));
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnMouseClicked(event -> bird.jump());

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
