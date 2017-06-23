import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by danil on 23.06.2017.
 */
public class SnakeGame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Snake snake=new Snake();
        snake.setTranslateX(200);
        snake.setTranslateY(200);
        SnakeGift snakeGift=new SnakeGift();
        root.getChildren().addAll(snake, snakeGift);
        Scene scene = new Scene(root, 600, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT)
                snake.setTarget(snake.getTranslateX()-10, snake.getTranslateY());
            if(event.getCode()==KeyCode.RIGHT)
                snake.setTarget(snake.getTranslateX()+10,snake.getTranslateY());
            if(event.getCode()==KeyCode.UP)
                snake.setTarget(snake.getTranslateX(),snake.getTranslateY()-10);
            if(event.getCode()==KeyCode.DOWN)
                snake.setTarget(snake.getTranslateX(),snake.getTranslateY()+10);
        });


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                snake.move();
                if (snake.getBoundsInParent().intersects(snakeGift.getBoundsInParent())) {
                    snakeGift.generateNew();
                    snake.sizePlus();
                }

            }
        };
        timer.start();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
