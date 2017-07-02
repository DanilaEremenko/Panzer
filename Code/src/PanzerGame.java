import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class PanzerGame extends Application {
    static int sceneHeight = 700;
    static int sceneWidt = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Panzer panzer = new Panzer(Color.GREEN);
        panzer.setTranslateX(100);
        panzer.setTranslateY(200);
        Panzer panzer2 = new Panzer(Color.RED);
        panzer2.setTranslateX(500);
        panzer2.setTranslateY(600);
        panzer.setOpponent(panzer2);
        panzer2.setOpponent(panzer);
        Panzer.elements.add(PanzerElement.generateVertical(100));
        Panzer.elements.add(PanzerElement.generateGorizontal(100));
        Panzer.elements.add(PanzerElement.generateVertical(100));
        Panzer.elements.add(PanzerElement.generateGorizontal(100));
        Panzer.elements.add(PanzerElement.generateVertical(100));
        Panzer.elements.add(PanzerElement.generateGorizontal(100));
        Panzer.elements.add(PanzerElement.generateVertical(100));
        Panzer.elements.add(PanzerElement.generateGorizontal(100));

        Panzer.elements.get(0).setTranslateX(250);//вертикальная
        Panzer.elements.get(0).setTranslateY(130);
        Panzer.elements.get(1).setTranslateX(150);//горизонтальная
        Panzer.elements.get(1).setTranslateY(230);
        Panzer.elements.get(2).setTranslateX(500);//вертикальная
        Panzer.elements.get(2).setTranslateY(130);
        Panzer.elements.get(3).setTranslateX(520);//горизонтальная
        Panzer.elements.get(3).setTranslateY(230);
        Panzer.elements.get(4).setTranslateX(500);//вертикальная
        Panzer.elements.get(4).setTranslateY(390);
        Panzer.elements.get(5).setTranslateX(520);//горизонтальная
        Panzer.elements.get(5).setTranslateY(370);
        Panzer.elements.get(6).setTranslateX(250);//вертикальная
        Panzer.elements.get(6).setTranslateY(390);
        Panzer.elements.get(7).setTranslateX(150);//горизонтальная
        Panzer.elements.get(7).setTranslateY(370);
        //Рамка
        PanzerElement.gorizontal.setTranslateY(sceneHeight - 20);
        PanzerElement.vertical.setTranslateX(sceneWidt - 20);
        Panzer.elements.add(PanzerElement.vertical);
        Panzer.elements.add(PanzerElement.vertical2);
        Panzer.elements.add(PanzerElement.gorizontal);
        Panzer.elements.add(PanzerElement.gorizontal2);

        for (int i = 0; i < Panzer.bulletDigit; i++)
            root.getChildren().addAll(panzer.bullets.get(i), panzer2.bullets.get(i));

        for (PanzerElement element : Panzer.elements)
            root.getChildren().addAll(element);


        panzer2.setTranslateX(300);
        root.getChildren().addAll(panzer, panzer2);
        Scene scene = new Scene(root, sceneWidt, sceneHeight);


        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (PanzerBullet bullet : panzer.bullets)
                    bullet.move(panzer2);

                for (PanzerBullet bullet : panzer2.bullets)
                    bullet.move(panzer);

                panzer.move();
                panzer2.move();


                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.RIGHT)
                        panzer.setVector(PanzerDirection.R);
                    else if (event.getCode() == KeyCode.LEFT)
                        panzer.setVector(PanzerDirection.L);
                    else if (event.getCode() == KeyCode.UP)
                        panzer.setVector(PanzerDirection.U);
                    else if (event.getCode() == KeyCode.DOWN)
                        panzer.setVector(PanzerDirection.D);

                    if (event.getCode() == KeyCode.A)
                        panzer2.setVector(PanzerDirection.L);
                    else if (event.getCode() == KeyCode.D)
                        panzer2.setVector(PanzerDirection.R);
                    else if (event.getCode() == KeyCode.W)
                        panzer2.setVector(PanzerDirection.U);
                    else if (event.getCode() == KeyCode.S)
                        panzer2.setVector(PanzerDirection.D);

                    if (event.getCode() == KeyCode.ENTER)
                        panzer.fire(panzer.bullets);
                    if (event.getCode() == KeyCode.SPACE)
                        panzer2.fire(panzer2.bullets);


                });

            }

        };
        timer.start();

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
