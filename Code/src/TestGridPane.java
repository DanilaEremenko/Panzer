import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class TestGridPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));//С помощью этого метода устанавливаем отступы
        root.setHgap(5);//Горизонтальные отступы между строчками
        root.setVgap(5);//Вертикальные отступы между столбцами

        TextField name = new TextField();//Обеспечивает возможность получения текста введенного пользователем и его дальнейшую обработку
        name.setPromptText("Name");
        GridPane.setConstraints(name, 0, 0);//Устанавливаем позицию


        TextField lastName = new TextField();
        lastName.setPromptText("Lastname");
        GridPane.setConstraints(lastName, 0, 1);


        TextField comment = new TextField();
        comment.setPromptText("Enter your comment");//Текст как подсказка
        GridPane.setConstraints(comment, 0, 2);


        //Кнопка которая проверяет поля на заплненность
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        root.getChildren().addAll(submit);

        //Кнопка которая очищает все поля
        Button clear = new Button("clear");
        GridPane.setConstraints(clear, 1, 1);
        root.getChildren().addAll(clear);

        //Кнопка для выхода из программы
        Button exit = new Button("Exit");
        GridPane.setConstraints(exit, 1, 2);
        root.getChildren().addAll(exit);

        //Поле выдюащее результат
        Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        root.getChildren().addAll(label);


        //Действие для кнопки отправки
        submit.setOnAction(event ->
        {
            if ((!comment.getText().isEmpty()) && (!name.getText().isEmpty()) && (!lastName.getText().isEmpty())) {
                label.setText("Поля заполнены правильно");
            } else
                label.setText("Не все поля заполнены");

        });

        exit.setOnAction(event -> {
            primaryStage.close();

        });

        clear.setOnAction(event -> {
            comment.clear();
            lastName.clear();
            name.clear();
            label.setText(null);

        });


        root.getChildren().addAll(name, lastName, comment);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
