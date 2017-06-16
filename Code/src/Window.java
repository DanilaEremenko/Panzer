import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.Color;

import static java.awt.Color.CYAN;
import static java.awt.Color.RED;
import static java.awt.Color.blue;
import static java.awt.SystemColor.control;
import static java.awt.SystemColor.text;

public class Window extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage-Контйнер самого выского уровня т.е. окно
        //Button-кнопка
        //Scene-то что внутри окна
        primaryStage.setTitle("Мое окно");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);


        Pane root = new Pane();//Макет

        Button btn = new Button();
        btn.setText("Кнопка");//Текст
        btn.setTranslateX(125);//Распололожение относительно горизонтальной оси
        btn.setTranslateY(200);//Расположение относительно вертикальной оси
        btn.setPrefSize(150, 30);//Размер кнопки

        Button btn2 = new Button();
        btn2.setText("Кнопка");
        btn2.setTranslateX(300);
        btn2.setTranslateY(200);
        btn2.setPrefSize(150, 30);


        Rectangle rect = new Rectangle(50, 50);
        rect.setX(250);
        rect.setY(100);

        //Действие при нажатии на кнопку
        btn.setOnAction(event ->
        {

            rect.setFill(javafx.scene.paint.Color.BLUE);
        });
        btn2.setOnAction(event -> {
            rect.setFill(javafx.scene.paint.Color.RED);

        });


        Scene scene = new Scene(root);//
        root.getChildren().addAll(btn,btn2,rect);//Добавляем объекты на сцену
        primaryStage.setScene(scene);//Устанавливаем сцену внутри окна
        primaryStage.show();//Вывод окна на экран
    }


    public static void main(String[] args) {
        launch(args);//Здесь запускаем приложение
    }
}
//    //КУСОК КОДА МАЛИКА
//    public Button(String string) {
//        bg = new Rectangle(100, 40);
//        bg.setFill(Color.AZURE);
//        bg.setArcWidth(5);
//        bg.setArcHeight(5);
//        fillTransition = new FillTransition(Duration.seconds(1), bg);
//        fillTransition.setFromValue(Color.AZURE);
//        fillTransition.setToValue(Color.DEEPSKYBLUE);
//        fillTransition.setAutoReverse(true);
//        fillTransition.setCycleCount(Animation.INDEFINITE);
//        setAlignment(Pos.CENTER);
//
//        this.text = new Text(string);
//
//        text.setId("button"); //Установим красоту через CSS
//        getChildren().addAll(bg,text);
//        setCursor(Cursor.HAND);
//        setPrefSize(100,40);
//
//        setOnMouseEntered(event -> {
//            fillTransition.play();
//        });
