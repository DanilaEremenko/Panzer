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
    public static ArrayList<Meteor> meteors = new ArrayList<>();//Список помех

    Rocket rocket=new Rocket();
    public static int score = 0;
    public static int bestScore =0;
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

        for (int i = 0;i<100; i++) {
            int enter = (int) (Math.random() * 100 + 50);//рандомная число (0-100) +50
            int height = new Random().nextInt(600 - enter);
            Meteor meteor = new Meteor(height);
            meteor.setTranslateY(-(i * 350 + 600));//Каждая стена через 350 пикселей+600 чтобы за пределы экрана
            meteor.setTranslateX(0);
            meteors.add(meteor);

            Meteor meteor2=new Meteor(600 - enter - height);//Вторая стена=-проем-первая стена
            meteor2.setTranslateY(-(i * 350 + 600));
            meteor2.setTranslateX(height + enter);
            meteors.add(meteor2);
            gameRoot.getChildren().addAll(meteor,meteor2);




        }


        Meteor a=new Meteor(20);
        gameRoot.getChildren().add(rocket);
        gameRoot.getChildren().addAll(a);
        appRoot.getChildren().addAll(gameRoot,scoreLabel,bestLabel);
        return appRoot;
    }

    //Метод который вызывается каждый кадр
    public void update() {

        if (rocket.velocity.getX() < 5) {
            rocket.velocity = rocket.velocity.add(0,0);
        }
        rocket.go();

        rocket.moveX((int) rocket.velocity.getX());
        rocket.moveY((int) rocket.velocity.getY());
        scoreLabel.setText("Score " + score);
        bestLabel.setText("Best "+bestScore );

        //??????????
        rocket.translateYProperty().addListener((ovs, old,newValue) -> {
            int offset=newValue.intValue();
            if(offset<200)gameRoot.setLayoutY(-(offset-200));
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                rocket.left();
            }
            if(event.getCode()==KeyCode.RIGHT){
                rocket.right();
            }
            if(event.getCode()==KeyCode.UP){
                rocket.up();
            }
            if(event.getCode()==KeyCode.DOWN){
                rocket.down();
            }

        });

        scene.setOnMouseClicked(event -> rocket.setTranslateY(rocket.getTranslateY()+5));

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
