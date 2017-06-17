import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;


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
