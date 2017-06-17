import com.sun.org.apache.regexp.internal.RECompiler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class TestCheckBox extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        VBox btns = new VBox(10);//Панель для кнопок
        HBox rects = new HBox();//Хранение прямоугольников

        //Тут создаем кнопки
        CheckBox[] cbs = new CheckBox[]{
                new CheckBox("Red"),
                new CheckBox("Green"),
                new CheckBox("Blue"),

        };

        //Тут создаем прямоугольники
        Rectangle[] rectangles = new Rectangle[]{
                new Rectangle(100, 100, Color.RED),
                new Rectangle(100, 100, Color.GREEN),
                new Rectangle(100, 100, Color.BLUE)
        };


        for (int i = 0; i < 3; i++) {
            CheckBox cb = cbs[i];
            Rectangle rect = rectangles[i];
            btns.getChildren().add(cb);
            //Если кнопка выбрана заносим прямоугольник в Hbox
            cb.selectedProperty().addListener(event ->
            {
                if(cb.isSelected())
                    rects.getChildren().add(rect);
                else
                    rects.getChildren().remove(rect);


            });
        }


        //Слева устанавливаем кнопки
        root.setLeft(btns);
        //Справа прямоугольники
        root.setCenter(rects);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
